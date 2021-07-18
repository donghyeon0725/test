package com.test.test;

import com.test.test.domain.Member;
import com.test.test.domain.Study;
import com.test.test.repository.StudyRepository;
import com.test.test.service.MemberService;
import com.test.test.service.StudyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Mock 으로 생성한 객체의 상태 확인 & 검증하기
 * */
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import javax.swing.text.html.Option;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class Test17 {

    /**
     * 메소드가 원하는 회수로 적절하게 호출되고 있는지 검증하는 메소드
     * */
    @Test
    void test1(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {

        Member member = new Member();
        member.setId(1L);
        member.setName("테스터");

        Study study = new Study();
        study.setName("오늘의 공부");
        study.setId(2L);

        // stubbing
        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(any())).thenAnswer((Answer) invocation -> invocation.getArguments()[0]);

        StudyService studyService = new StudyService(memberService, studyRepository);
        Study newStudy = studyService.createNewStudy(1L, study);

        verify(memberService, times(1)).validateCheck(member);
        verify(memberService, times(1)).findById(1L);
        verify(studyRepository, times(1)).save(newStudy);

    }

    /**
     * 메소드 호출 순서가 다음과 같은지 확인하는 방법
     * */
    @Test
    void test2(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {

        Member member = new Member();
        member.setId(1L);
        member.setName("테스터");

        Study study = new Study();
        study.setName("오늘의 공부");
        study.setId(2L);

        // stubbing
        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(any())).thenAnswer((Answer) invocation -> invocation.getArguments()[0]);

        // 메소드 호출
        StudyService studyService = new StudyService(memberService, studyRepository);
        studyService.createNewStudy(1L, study);

        // 메소드 호출 순서가 다음과 같이 되었는지 확인할 수 있음
        InOrder inOrder = inOrder(memberService, studyRepository);
        inOrder.verify(memberService).findById(1L);
        inOrder.verify(memberService).validateCheck(member);
        inOrder.verify(studyRepository).save(study);
    }

    /**
     * 더 이상의 메소드 호출이 없는지 확인하는 방법
     * */
    @Test
    void test3(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {

        Member member = new Member();
        member.setId(1L);
        member.setName("테스터");

        Study study = new Study();
        study.setName("오늘의 공부");
        study.setId(2L);

        // stubbing
        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(any())).thenAnswer((Answer) invocation -> invocation.getArguments()[0]);

        // 메소드 호출
        StudyService studyService = new StudyService(memberService, studyRepository);
        studyService.createNewStudy(1L, study);

        // verify(memberService, times(1)).validateCheck(member);

        verify(memberService, times(1)).findById(1L);
        verify(memberService, times(1)).validateCheck(member);
        verifyNoMoreInteractions(memberService);


//        다음과 같이 InOrder 와 함께 사용하는 것도 가능하다.
//        InOrder inOrder = inOrder(memberService);
//        inOrder.verify(memberService).findById(1L);
//        inOrder.verify(memberService).validateCheck(member);
//        verifyNoMoreInteractions(memberService);

    }
}
