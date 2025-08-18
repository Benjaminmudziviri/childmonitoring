package com.radical.childmonitoring.parent.entity;

import com.radical.childmonitoring.child.entity.Child;
import com.radical.childmonitoring.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The Parent entity represents a parent user in the system.
 * It is linked to:
 *  - A User account (via one-to-one)
 *  - A list of their children (via one-to-many)
 */
@Entity
@Table(name = "parent")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Better for databases like MySQL/Postgres
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String surname;

    /**
     * One Parent can have many children.
     * This sets up a foreign key in the Child table called 'parent_id'.
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> children;

    /**
     * This Parent is linked to a User account (login credentials).
     * This creates a 'user_id' foreign key column in the parent table.
     */
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
