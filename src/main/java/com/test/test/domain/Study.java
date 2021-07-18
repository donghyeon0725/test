package com.test.test.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Study {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(mappedBy = "study", fetch = FetchType.LAZY)
    private Member owner;

    private StudyState state;

    private LocalDateTime openedDateTime;

    public StudyState getState() {
        return state;
    }

    public Study setState(StudyState state) {
        this.state = state;
        return this;
    }

    public Study() {
    }

    public Study(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Study setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Study setName(String name) {
        this.name = name;
        return this;
    }

    public Member getOwner() {
        return owner;
    }

    public Study setOwner(Member owner) {
        this.owner = owner;
        return this;
    }

    public void open() {
        this.state = StudyState.OPENED;
        this.openedDateTime = LocalDateTime.now();
    }

    public LocalDateTime getOpenedDateTime() {
        return openedDateTime;
    }

    public Study setOpenedDateTime(LocalDateTime openedDateTime) {
        this.openedDateTime = openedDateTime;
        return this;
    }
}
