package com.radical.childmonitoring.example;

import com.radical.childmonitoring.example.entity.Hello;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository extends JpaRepository<Hello,Long> {
}
