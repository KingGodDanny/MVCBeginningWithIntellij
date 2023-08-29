package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    private DataSource dataSource;
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }
    //public SpringConfig(DataSource dataSource) {
    //     this.dataSource = dataSource;
    // }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    //이렇게 특별한 경우의 클래스는 직접 이렇게 등록해주는것이 좋으나 현재는 예시로 간단한 프로젝트기 때문에 TimeTraceAop에
    //Component를 걸어준다.
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new JpaMemberRepository(em);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JdbcMemberRepository(dataSource);
//        return new MemoryMemberRepository();
//    }
}
