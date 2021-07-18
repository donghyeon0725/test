package com.test.test;

import com.test.test.domain.Member;
import com.test.test.domain.Study;
import com.test.test.repository.StudyRepository;
import com.test.test.service.MemberService;
import com.test.test.service.StudyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.Optional;

/**
 * Mockito 를 이용해 Mock 행동을 정의하는 방법
 * */
@Disabled
@ExtendWith(MockitoExtension.class)
public class Test16 {

    /**
     * 기본적인 stubbing 방법
     * */
    @Test
    void test1(@Mock MemberService memberService) {

        Member member = new Member();
        member.setId(1L);
        member.setName("테스터");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        memberService.findById(1L);


        Member findMember = memberService.findById(1L).get();

        assertEquals(findMember.getId(), 1L);
        assertEquals(findMember.getName(), "테스터");
    }

    /**
     * stubbing 으로 동작을 정의한 뒤에, 실제 service 를 테스트 하는 부분
     * validation check 같이, void 타입이나, 특정 상황에 Exception 을 throw 해줘야 하는 부분 또한 정의 하였음
     *
     * 메소드 호출이 정상 동작 되었는지 확인하려면 다음과 같이 할 수 있음
     * */
    @Test
    void test2(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {

        Member member = new Member();
        member.setId(1L);
        member.setName("테스터");

        // 잘못된 객체 정보 세팅
        Member illigalMember1 = new Member();
        illigalMember1.setId(2L);
        illigalMember1.setName("부적절한이름");


        Study study = new Study();
        study.setName("오늘의 공부");
        study.setId(2L);


        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(memberService.findById(2L)).thenReturn(Optional.of(illigalMember1));
        // 받은 것과 동일한 객체를 반환
        when(studyRepository.save(any())).thenAnswer((Answer) invocation -> invocation.getArguments()[0]);

        // validation 메소드 가상 객체 구현
        doNothing().when(memberService).validateCheck(member);
        doThrow(new IllegalArgumentException()).when(memberService).validateCheck(illigalMember1);


        StudyService studyService = new StudyService(memberService, studyRepository);

        assertAll(
            () -> {
                Study createdStudy = studyService.createNewStudy(1L, study);
                assertEquals(createdStudy.getOwner(), member);
            },
            () -> {
                // 이 테스트는 실패하는 테스트 입니다.
                Study createdStudy = studyService.createNewStudy(2L, study);
                assertEquals(createdStudy.getOwner(), member);
            }
        );
    }


    /**
     * 같은 객체를 여러번 호출했을 때마다 내용을 달리하고 싶을 때
     * */
    @Test
    void test3(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
        Member member = new Member();
        member.setId(1L);
        member.setName("테스터");

        Study study = new Study();
        study.setName("오늘의 공부");
        study.setId(2L);


        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new IllegalArgumentException());
        // 받은 것과 동일한 객체를 반환
        when(studyRepository.save(any())).thenAnswer((Answer) invocation -> invocation.getArguments()[0]);


        StudyService studyService = new StudyService(memberService, studyRepository);

        // 처음 한번은 정상
        assertEquals(studyService.createNewStudy(1L, study).getOwner(), member);
        // 두번째 코드는 에러
        assertEquals(studyService.createNewStudy(1L, study).getOwner(), member);
    }



}
