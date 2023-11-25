package com.example.demo.user;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class UserService {
    @GetMapping()
    public List<User> getUsers() {
        return List.of(
                new User(
                        1L,
                        "Cosmin",
                        "pufyzeul@gmail.com",
                        LocalDate.of(2002, Month.MAY, 24)
                )
        );
    }
}
