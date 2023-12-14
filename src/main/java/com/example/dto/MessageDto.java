package com.example.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private Long id;
    private Long chatRoomId;
    private String content;
    private Boolean isRead;
    private Date timestamp;

    // getter and setter methods...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //... 나머지 getter and setter methods
}
