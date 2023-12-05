//package com.example.userdetails;
//
//import com.example.entity.Authority;
//import com.example.entity.Member;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class CustomUserDetails implements UserDetails {
//    private final String username;
//    private final String password;
//    private final List<String> authorities;
//
//    public CustomUserDetails(Member member) {
//        this.username = member.getEmail();
//        this.password = member.getPassword();
//        this.authorities = member.getAuthorities().stream().map(Authority::getName).collect(Collectors.toList());
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
package com.example.userdetails;

import com.example.entity.Authority;
import com.example.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private final String username;
    private final String password;
    private final List<String> authorities;

    public CustomUserDetails(Member member) {
        this.username = member.getEmail();
        this.password = member.getPassword();
        this.authorities = member.getAuthorities().stream().map(Authority::getName).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // SimpleGrantedAuthority로 변환한 권한 목록 반환
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 여부를 반환 (기본값: true)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금 여부를 반환 (기본값: true)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 자격 증명(비밀번호) 만료 여부를 반환 (기본값: true)
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정 활성화 여부를 반환 (기본값: true)
        return true;
    }
}
