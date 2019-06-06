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

    @NonNull
    @Getter
    private Long id;

    @NonNull
    @Getter
    private Collection<? extends GrantedAuthority> authorities;

    @NonNull
    @Getter
    private String username;

    @NonNull
    @Getter
    private String password;

    @NonNull
    @Getter
    private boolean accountNonExpired;

    @NonNull
    @Getter
    private boolean accountNonLocked;

    @NonNull
    @Getter
    private boolean credentialsNonExpired;

    @NonNull
    @Getter
    private boolean enabled;

    public static UserPrincipal of(@NonNull User _user) {

        return new UserPrincipal(_user.getId(),
                                 _user.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()),
                                 _user.getEmail(),
                                 _user.getPassword(),
                                 true,
                                 true,
                                 true,
                                 true);
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
