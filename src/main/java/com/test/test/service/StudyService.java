package com.test.test.service;

import com.test.test.domain.Member;
import com.test.test.domain.Study;
import com.test.test.repository.StudyRepository;

import java.util.Optional;

public class StudyService {
    private final MemberService memberService;
    private final StudyRepository studyRepository;

    public StudyService(MemberService memberService, StudyRepository studyRepository) {
        assert memberService != null;
        assert studyRepository != null;

        this.memberService = memberService;
        this.studyRepository = studyRepository;
    }

    public Study createNewStudy(Long memberId, Study study) {


        Optional<Member> memberOptional = memberService.findById(memberId);

        Member member = memberOptional.orElseThrow(() -> new IllegalArgumentException("Member does not exist"));

        memberService.validateCheck(member);

        study.setOwner(member);
        return studyRepository.save(study);
    }


    public Study openStudy(Study study) {
        study.open();
        Study openedStudy = studyRepository.save(study);
        memberService.notify(openedStudy);
        return openedStudy;
    }


}
