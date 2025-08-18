package com.radical.childmonitoring.panic.controller;

import com.radical.childmonitoring.child.entity.Child;
import com.radical.childmonitoring.panic.dto.PanicRequest;
import com.radical.childmonitoring.panic.entity.Panic;
import com.radical.childmonitoring.panic.service.PanicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/panic")
@RequiredArgsConstructor
public class PanicController {

    private final PanicService panicService;

    // Create new panic alert
    @PostMapping()
    public ResponseEntity<String> createPanic(@RequestBody PanicRequest panicRequest) {
        panicService.createPanic(panicRequest.childId(), panicRequest.message());
        return ResponseEntity.ok("Success");
    }


    // Get panic by ID
    @GetMapping("/{id}")
    public ResponseEntity<Panic> getPanicById(@PathVariable Long id) {
        return panicService.getPanicById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all panics
    @GetMapping
    public ResponseEntity<List<Panic>> getAllPanics() {
        return ResponseEntity.ok(panicService.getAllPanics());
    }

    //Get panics by child ID
    @GetMapping("/child/{childId}")
    public ResponseEntity<List<Panic>> getPanicsByChildId(@PathVariable Long childId) {
        return ResponseEntity.ok(panicService.getPanicsByChildId(childId));
    }
}
