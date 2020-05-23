package com.attractor.exam_9.service;


import com.attractor.exam_9.dto.UserDTO;
import com.attractor.exam_9.exception.UserAlreadyRegisteredException;
import com.attractor.exam_9.exception.UserNotFoundException;
import com.attractor.exam_9.model.User;
import com.attractor.exam_9.model.UserRegistor;
import com.attractor.exam_9.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserDTO register(UserRegistor form) {
        if (repository.existsByEmail(form.getEmail())) {
            throw new UserAlreadyRegisteredException();
        }

        var user = User.builder()
                .email(form.getEmail())
                .fullname(form.getName())
                .password(encoder.encode(form.getPassword()))
                .build();

        repository.save(user);

        return UserDTO.from(user);
    }

    public UserDTO getByEmail(String email) {
        var user = repository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        return UserDTO.from(user);
    }
}
