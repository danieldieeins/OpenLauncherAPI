package live.nerotv.openlauncherapi.auth;

import java.util.UUID;

public interface AuthResolver {

    default void preAuth() {

    }

    default void postAuth(String name, String suid) {}
}