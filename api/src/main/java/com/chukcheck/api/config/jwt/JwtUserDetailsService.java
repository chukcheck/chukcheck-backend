package com.chukcheck.api.config.jwt;

import com.chukcheck.core.dto.jwt.JwtRole;
import com.chukcheck.core.dto.jwt.JwtToken;
import com.chukcheck.api.service.AdminService;
import com.chukcheck.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.chukcheck.core.dto.jwt.JwtRole.ROLE_ADMIN;
import static com.chukcheck.core.dto.jwt.JwtRole.ROLE_USER;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final MemberService memberService;
    private final AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtToken token = getToken(username);

        if (ROLE_USER.equals(token.getScope())) memberService.read(token.getSubject());
        if (ROLE_ADMIN.equals(token.getScope())) adminService.read(token.getSubject());

        return JwtUserDetails.builder()
                .subject(token.getSubject())
                .scope(token.getScope())
                .build();
    }

    private JwtToken getToken(String username) {
        String[] names = username.split("/t");

        return JwtToken.builder()
                .subject(Long.valueOf(names[0]))
                .scope(JwtRole.valueOf(names[1]))
                .build();
    }
}
