package com.radical.childmonitoring.panic.service;

import com.radical.childmonitoring.panic.entity.Panic;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PanicService {
    Panic createPanic(Long id, String Message);
    Optional<Panic> getPanicById(Long id);
    List<Panic> getAllPanics();
    List<Panic> getPanicsByChildId(Long childId);
    Panic getPanicByChildId(Long childId);
}

