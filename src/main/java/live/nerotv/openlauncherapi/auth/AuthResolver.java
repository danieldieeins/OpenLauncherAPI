package live.nerotv.openlauncherapi.auth;

public interface AuthResolver {

    default void preAuth() {}

    default void postAuth(String name, String suid) {}
}