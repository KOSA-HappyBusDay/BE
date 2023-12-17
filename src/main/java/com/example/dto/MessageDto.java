package com.example.dto;

import lombok.*;

import javax.persistence.Column;
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
    private Long senderId; // 메시지 발송자 ID

    // getter and setter methods...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //... 나머지 getter and setter methods
}
