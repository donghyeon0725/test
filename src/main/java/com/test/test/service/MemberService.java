package com.test.test.service;

import com.test.test.domain.Member;
import com.test.test.domain.Study;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(Long id);

    void validateCheck(Member member);

    void notify(Study study);
}
