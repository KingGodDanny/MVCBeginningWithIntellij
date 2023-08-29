package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach  //메소드가 실행하기이전에 먼저 수행된다. service에서 사용하는 레퍼지토리를 테스트서비스에서도 같이 사용하기위해 만들어준것.
    public void beforeEach() {
        //dependency injection -> 같은 인스턴스를 사용하기위해 MemberService, Test에서 각각 new 로 생성해주지않고
        //서비스에서 레퍼지토리를 받아서 같이 사용한다.
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void dupUserThrow() {
        //given
        Member m1 = new Member();
        m1.setName("spring");

        Member m2 = new Member();
        m2.setName("spring");

        //when
        memberService.join(m1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(m2));

        assertThat(e.getMessage()).isEqualTo("Already exist user");

        //then


/*      테스트를 하는데 더 try catch 보다 더 적합한 코드가 있다.
        try {
            memberService.join(m2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("Already exist user");
        }*/

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}