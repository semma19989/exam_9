package com.attractor.exam_9.dto;



import com.attractor.exam_9.model.Form;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FormDTO {

    private int id;
    private TopicDTO topic;
    private UserDTO author;
    private String text;
    private LocalDateTime date;


    public static FormDTO from(Form forms){
        return FormDTO.builder()
                .id(forms.getId())
                .topic(TopicDTO.from(forms.getTopic()))
                .users(UserDTO.from(forms.getUsers()))
                .text(forms.getText())
                .date(forms.getDate())
                .build();
    }
}
