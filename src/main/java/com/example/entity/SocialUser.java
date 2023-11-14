package com.example.entity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class SocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String membername;

    private String password;
    private String email;
    private String address;

    private String provider;
    private String providerId;

    @Builder
    public SocialUser(String membername, String password, String email, String address, String provider, String providerId) {
        this.membername = membername;
        this.password = password;
        this.email = email;
        this.address = address;
        this.provider = provider;
        this.providerId = providerId;
    }
}
