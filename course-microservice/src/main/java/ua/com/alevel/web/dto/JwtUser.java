package ua.com.alevel.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.com.alevel.util.GrantedAuthorityDeserializer;

import java.util.Collection;

public class JwtUser implements UserDetails {

    private final String username;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(
            String id,
            String firstName,
            String lastName,
            String email,
            String password, Collection<? extends GrantedAuthority> authorities,
            boolean enabled
    ) {
        this.username = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    public static JwtUser fromJson(String json) {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(GrantedAuthority.class, new GrantedAuthorityDeserializer())
                    .create();
            return gson.fromJson(json, JwtUser.class);
        } catch (JsonIOException e) {
            throw new RuntimeException(e);
        }
    }


    @JsonIgnore
    public String getId() {
        return username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }}
