package com.radical.childmonitoring.panic.service;

import com.radical.childmonitoring.child.entity.Child;
import com.radical.childmonitoring.child.repository.ChildRepository;
import com.radical.childmonitoring.panic.entity.Panic;
import com.radical.childmonitoring.panic.repository.PanicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PanicServiceImpl implements PanicService {

    private final PanicRepository panicRepository;
    private final ChildRepository childRepository;

    @Override
    public Panic createPanic(Long id, String message) {
        Child child = childRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Child Not Found"));
        Panic panic = new Panic();
        panic.setPanicStart(LocalDateTime.now());
        panic.setActive(true);
        panic.setChild(child);
        Panic saved = panicRepository.save(panic);



        return saved;
    }

    @Override
    public Optional<Panic> getPanicById(Long id) {
        return panicRepository.findById(id);
    }

    @Override
    public List<Panic> getAllPanics() {
        return panicRepository.findAll();
    }

    @Override
    public List<Panic> getPanicsByChildId(Long childId) {
        return panicRepository.findByChildId(childId);
    }
    @Override

    public Panic getPanicByChildId(Long childId){
        return panicRepository.findByChildIdAndActiveTrue(childId).orElseThrow(()->new RuntimeException("No panic found"));
    }
}
