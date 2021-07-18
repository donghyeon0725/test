package com.test.test;

import com.test.test.domain.Member;
import com.test.test.domain.Study;
import com.test.test.domain.StudyState;
import com.test.test.repository.StudyRepository;
import com.test.test.service.MemberService;
import com.test.test.service.StudyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * BDD style 로 작성한다면 아래와 같이 코드를 리팩토링 할 수 있음
 * */
@ExtendWith(MockitoExtension.class)
public class Test18 {

    /**
     * 기존의 코드
     * */
    @Test
    void test(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {

        // given
        StudyService studyService = new StudyService(memberService, studyRepository);

        Member member = new Member();
        member.setId(1L);
        member.setName("테스터");

        Study study = new Study();
        study.setName("오늘의 공부");
        study.setId(2L);

        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(any())).thenReturn(study);

        // when
        studyService.createNewStudy(1L, study);


        // then
        verify(memberService, times(1)).findById(1L);
        verify(memberService, times(1)).validateCheck(member);
        verifyNoMoreInteractions(memberService);
    }


    /**
     * BDD 방식의 테스트 코드
     * */
    @Test
    void test_styling_bdd(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {

        // given
        StudyService studyService = new StudyService(memberService, studyRepository);

        Member member = new Member();
        member.setId(1L);
        member.setName("테스터");

        Study study = new Study();
        study.setName("오늘의 공부");
        study.setId(2L);

        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(any())).willReturn(study);

        // when
        studyService.createNewStudy(1L, study);


        // then
        then(memberService).should(times(1)).findById(1L);
        then(memberService).should(times(1)).validateCheck(member);
        then(memberService).shouldHaveNoMoreInteractions();
    }

    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    @Test
    void openStudy(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(1L, "더 자바, 테스트");

        // TODO studyRepository Mock 객체의 save 메소드를호출 시 study를　리턴하도록 만들기.
        given(studyRepository.save(study)).willReturn(study);

        // When
        studyService.openStudy(study);

        // Then
        // TODO study의 status가 OPENED로 변경됐는지 확인
        // TODO study의 openedDataTime이 null이 아닌지 확인
        // TODO memberService의 notify(study)가 호출 됐는지 확인.
        assertEquals(study.getState(), StudyState.OPENED);
        assertNotNull(study.getOpenedDateTime());
        then(memberService).should().notify(study);
    }
}
