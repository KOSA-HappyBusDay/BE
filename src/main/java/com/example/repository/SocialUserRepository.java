package com.example.repository;

import com.example.entity.SocialUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialUserRepository extends JpaRepository<SocialUser, Long>{
    public SocialUser findByMembername(String membername);

    public SocialUser findByEmail(String email);
}
