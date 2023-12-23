package ua.com.alevel.persistence.types;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.DEVELOPERS_READ)),
    AWAITING_TEACHER(Set.of(Permission.DEVELOPERS_READ, Permission.DEVELOPERS_AWAITING_EDUCATE)),
    TEACHER(Set.of(Permission.DEVELOPERS_READ, Permission.DEVELOPERS_EDUCATE)),
    ADMIN(Set.of(Permission.DEVELOPERS_READ, Permission.DEVELOPERS_AWAITING_EDUCATE, Permission.DEVELOPERS_WRITE, Permission.DEVELOPERS_EDUCATE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}