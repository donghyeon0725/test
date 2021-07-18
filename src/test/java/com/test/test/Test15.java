package com.test.test;

import com.test.test.domain.Study;
import com.test.test.repository.StudyRepository;
import com.test.test.service.MemberService;
import com.test.test.service.StudyService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Mockito 를 이용해 Mock 객체를 생성하는 방법
 * */
@Disabled
@ExtendWith(MockitoExtension.class)
class Test15 {

    @Mock
    private MemberService memberService;

    @Mock
    private StudyRepository studyRepository;

    /**
     * 메소드 단에서 직접 mock 객체를 생성하는 방법
     * */
    @Test
    void test_with_method() {
        MemberService memberService = Mockito.mock(MemberService.class);
        StudyRepository studyRepository = Mockito.mock(StudyRepository.class);

        StudyService studyService = new StudyService(memberService, studyRepository);

        assertNotNull(studyService);
    }

    /**
     * 확장팩을 이용해서 mock 객체를 주입하는 방법
     * 필드에 주입
     * */
    @Test
    void test_with_extension() {
        StudyService studyService = new StudyService(memberService, studyRepository);

        assertNotNull(studyService);
    }

    /**
     * 확장팩을 이용해서 mock 객체를 주입하는 방법
     * 매개변수로 주입
     * */
    @Test
    void test_with_mock_param(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
        StudyService studyService = new StudyService(memberService, studyRepository);

        assertNotNull(studyService);
    }
}
