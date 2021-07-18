package com.test.test.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private Study study;

    public Long getId() {
        return id;
    }

    public Member setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Member setName(String name) {
        this.name = name;
        return this;
    }

    public Study getStudy() {
        return study;
    }

    public Member setStudy(Study study) {
        this.study = study;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(getId(), member.getId()) &&
                Objects.equals(getName(), member.getName()) &&
                Objects.equals(getStudy(), member.getStudy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStudy());
    }
}
