package com.attractor.exam_9.dto;

import lombok.*;
import org.apache.catalina.User;

@Getter @Setter @ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class UserDTO {
    private String id;
    private String login;
    private String image;
    private String email;
    private int comments;

    public static UserDTO from(User user) {
        return builder().id(getId())
                .email(getEmail())
                .image(getImage())
                .login(getLogin())
                .comments(getComments())
                .build();

    }

}

