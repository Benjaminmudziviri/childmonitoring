package com.radical.childmonitoring.child.service;

import com.radical.childmonitoring.child.dto.ChildRequest;
import org.springframework.stereotype.Service;

@Service
public interface ChildService {
    public void createChild(ChildRequest request);
}
