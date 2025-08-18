package com.radical.childmonitoring.child.controller;


import com.radical.childmonitoring.child.dto.ChildRequest;
import com.radical.childmonitoring.child.service.ChildService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/child")
@RequiredArgsConstructor
public class ChildController {

    private final ChildService childService;

    @PostMapping("/create")
    public ResponseEntity<String> createChild(@RequestBody ChildRequest request) {

        childService.createChild(request);

        return ResponseEntity.ok("Child created successfully!");
    }
}
