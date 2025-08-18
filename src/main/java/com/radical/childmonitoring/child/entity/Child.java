package com.radical.childmonitoring.child.entity;

import com.radical.childmonitoring.parent.entity.Parent;
import com.radical.childmonitoring.security.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 📘 Entity: Child
 *
 * Represents a child in the monitoring system.
 * - Linked to a User account (for login/identity)
 * - Belongs to one Parent (many children can have the same parent)
 *
 * 🔗 Relationships:
 * - @ManyToOne to Parent
 * - @OneToOne to User
 */
@Entity
@Table(name = "child")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String surname;

    /**
     * 🔗 Many children can belong to one parent.
     */
    @ManyToOne
    @JoinColumn(name = "parent_id") // foreign key column in child table
    private Parent parent;

    /**
     * 🔗 One-to-one relationship to the user account associated with this child.
     */
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
