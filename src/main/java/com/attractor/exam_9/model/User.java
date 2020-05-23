package com.attractor.exam_9.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name="User") public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email
    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    private String email;

    @NotBlank
    @Size(min = 8, max = 128)
    @Column(length = 128)
    private String password;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    private String fullname;

    @Column
    @Builder.Default
    private boolean enabled = true;

    @NotBlank
    @Size(min = 1, max = 128)
    @Column(length = 128)
    @Builder.Default
    private String role = "USER";
}
