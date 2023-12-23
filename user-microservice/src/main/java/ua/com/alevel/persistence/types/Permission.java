package ua.com.alevel.persistence.types;

public enum Permission {
    DEVELOPERS_READ("ROLE_developers:read"),
    DEVELOPERS_WRITE("ROLE_developers:write"),
    DEVELOPERS_EDUCATE("ROLE_developers:educate"),
    DEVELOPERS_AWAITING_EDUCATE("ROLE_developers:awaiting_educate");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}