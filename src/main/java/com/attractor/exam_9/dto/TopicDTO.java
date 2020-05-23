package com.attractor.exam_9.dto;


import com.attractor.exam_9.model.Topic;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TopicDTO {
    public static TopicDTO from(Topic topic){
        return TopicDTO.builder()
                .id(topic.getId())
                .name(topic.getName())
                .description(topic.getDescription())
                .date(topic.getDate())
                .User(UserDTO.from(topic.getUsers()))
                .qtyAnswer(topic.getQtyAnswer())
                .build();
    }

    private Integer id;
    private String name;
    private String description;
    private LocalDateTime date;
    private UserDTO User;
    private int qtyAnswer;
}
