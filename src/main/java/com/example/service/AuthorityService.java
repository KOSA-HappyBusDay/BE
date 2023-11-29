package com.example.service;

import com.example.entity.Authority;
import com.example.repository.AuthorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Transactional
    public Authority createOrGetAuthorityByName(String name) {
        Authority authority = authorityRepository.findByName(name);
        if (authority == null) {
            authority = Authority.builder().name(name).build();
            authorityRepository.save(authority);
        }
        return authority;
    }
}
