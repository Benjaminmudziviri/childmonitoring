package com.radical.childmonitoring.example.controller;

import com.radical.childmonitoring.example.HelloRepository;
import com.radical.childmonitoring.example.entity.Hello;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/hello")
@RequiredArgsConstructor
public class HelloController {


    private final HelloRepository helloRepository;

    @PostMapping("/example")
    public void add (@RequestBody String message){

        Hello hello = new Hello();
        hello.setHello(message);

        helloRepository.save(hello);
    }
}
