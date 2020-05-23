package com.attractor.exam_9.model;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;


@Data
@Table(name = "topics")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String description;

    @NotNull
    @Column(length = 128)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @PositiveOrZero
    @Column(length = 128)
    private int qtyAnswer;
}
