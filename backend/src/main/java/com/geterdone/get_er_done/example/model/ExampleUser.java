// ===== SAMPLE FILE: ExampleUser.java =====
// This is ONLY an example entity to show how JPA works.
// You can delete or rename this for your real project.

package com.geterdone.get_er_done.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "example_users")
public class ExampleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String exampleName;

    @Column(nullable = false, unique = true)
    private String exampleEmail;

    //Constructers:

    public ExampleUser() {
        // No-arg constructor required by JPA
    }

    public ExampleUser(String exampleName, String exampleEmail) {
        this.exampleName = exampleName;
        this.exampleEmail = exampleEmail;
    }

    //Getters and Setters:

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExampleName() {
        return exampleName;
    }

    public void setExampleName(String exampleName) {
        this.exampleName = exampleName;
    }

    public String getExampleEmail() {
        return exampleEmail;
    }

    public void setExampleEmail(String exampleEmail) {
        this.exampleEmail = exampleEmail;
    }
}