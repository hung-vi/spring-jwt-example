package com.vnext.security.jwtex.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserPrincipal implements UserDetails {

    @Getter
    private Long id;

    @Getter
    private Collection<? extends GrantedAuthority> authorities;

    @Getter
    private String username;

    @Getter
    private String password;

    public static UserPrincipal of(@NonNull User _user) {

        return new UserPrincipal(_user.getId(),
                                 _user.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()),
                                 _user.getEmail(),
                                 _user.getPassword());
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal principal = (UserPrincipal) o;
        return Objects.equals(id, principal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
