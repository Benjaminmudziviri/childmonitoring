package com.radical.childmonitoring.child.repository;

import com.radical.childmonitoring.child.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child,Long> {
}
