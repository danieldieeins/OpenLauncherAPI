package live.nerotv.openlauncherapi.auth;

import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import live.nerotv.openlauncherapi.util.AESUtil;
import live.nerotv.shademebaby.file.Config;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.UUID;

public class SimpleMicrosoftAuth {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            SimpleMicrosoftAuth auth = new SimpleMicrosoftAuth();
            auth.setAuthResolver(new AuthResolver() {
                @Override
                public void preAuth() {
                    AuthResolver.super.preAuth();
                    System.out.println("pre-Auth");
                }

                @Override
                public void postAuth(String name, String suid) {
                    AuthResolver.super.postAuth(name, suid);
                    System.out.println("post-Auth");
                    System.out.println(name+" ("+suid+")");
                }
            });
            auth.startAsyncWebview();
        });
    }

    private AuthInfos authInfos;
    private File saveFile;
    private byte[] key;
    private AuthResolver resolver;

    public SimpleMicrosoftAuth() {
        saveFile = null;
        key = null;
        resolver = new AuthResolver() {
            @Override
            public void preAuth() {
                AuthResolver.super.preAuth();
            }

            @Override
            public void postAuth(String name, String suid) {
                AuthResolver.super.postAuth(name, suid);
            }
        };
    }

    public AuthResolver getAuthResolver() {
        return resolver;
    }

    public void setAuthResolver(AuthResolver resolver) {
        this.resolver = resolver;
    }

    public void setKey(byte[] newKey) {
        key = newKey;
    }

    public void setSaveFilePath(String newPath) {
        saveFile = new File(newPath);
        new File(saveFile.getParent()).mkdirs();
    }

    public void setSaveFile(File newSaveFile) {
        saveFile = newSaveFile;
    }

    public boolean isLoggedIn() {
        if (authInfos != null) {
            return true;
        }
        if (saveFile != null) {
            if (key != null) {
                Config saver = new Config(saveFile);
                if (saver.get("opapi.ms.a") != null || saver.get("opapi.ms.r") != null || saver.get("opapi.ms.u") != null || saver.get("opapi.ms.n") != null) {
                    MicrosoftAuthenticator auth = new MicrosoftAuthenticator();
                    resolver.preAuth();
                    String r = (String)saver.get("opapi.ms.r");
                    try {
                        byte[] b = r.getBytes();
                        b = AESUtil.decrypt(key,b);
                        MicrosoftAuthResult response = auth.loginWithRefreshToken(new String(b));
                        authInfos = new AuthInfos(response.getProfile().getName(), response.getAccessToken(), response.getProfile().getId());
                        byte[] access = AESUtil.encrypt(key, response.getAccessToken().getBytes());
                        byte[] refresh = AESUtil.encrypt(key, response.getRefreshToken().getBytes());
                        byte[] uniqueID = AESUtil.encrypt(key, response.getProfile().getId().getBytes());
                        byte[] name = AESUtil.encrypt(key, response.getProfile().getName().getBytes());
                        resolver.postAuth(response.getProfile().getName(),response.getProfile().getId());
                        saver.set("opapi.ms.a", new String(access));
                        saver.set("opapi.ms.r", new String(refresh));
                        saver.set("opapi.ms.u", new String(uniqueID));
                        saver.set("opapi.ms.n", new String(name));
                        return true;
                    } catch (Exception ignore) {
                    }
                }
            }
        }
        return false;
    }

    public void startAsyncWebview() {
        MicrosoftAuthenticator auth = new MicrosoftAuthenticator();
        resolver.preAuth();
        auth.loginWithAsyncWebview().whenCompleteAsync((response, error) -> {
            if (error != null) {
                JFrame errorFrame = new JFrame();
                errorFrame.setTitle("Error");
                Label text = new Label(error.getMessage());
                errorFrame.add(text);
                errorFrame.pack();
                errorFrame.setVisible(true);
                return;
            }
            authInfos = new AuthInfos(response.getProfile().getName(), response.getAccessToken(), response.getProfile().getId());
            if (saveFile != null) {
                if (key != null) {
                    try {
                        byte[] access = AESUtil.encrypt(key, response.getAccessToken().getBytes());
                        byte[] refresh = AESUtil.encrypt(key, response.getRefreshToken().getBytes());
                        byte[] uniqueID = AESUtil.encrypt(key, response.getProfile().getId().getBytes());
                        byte[] name = AESUtil.encrypt(key, response.getProfile().getName().getBytes());
                        resolver.postAuth(response.getProfile().getName(),response.getProfile().getId());
                        Config saver = new Config(saveFile);
                        saver.set("opapi.ms.a", new String(access));
                        saver.set("opapi.ms.r", new String(refresh));
                        saver.set("opapi.ms.u", new String(uniqueID));
                        saver.set("opapi.ms.n", new String(name));
                    } catch (Exception e) {
                        System.out.println("[ERROR] couldn't save login credentials: " + e.getMessage());
                        resolver.postAuth(null,null);
                    }
                }
            }
        });
    }

    public AuthInfos getAuthInfos() {
        return authInfos;
    }

    public File getSaveFile() {
        return saveFile;
    }

    public void end() {
        authInfos = null;
        saveFile = null;
        key = null;
        resolver = null;
        System.gc();
    }
}