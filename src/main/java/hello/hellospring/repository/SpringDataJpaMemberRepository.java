package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    /*
    SpringDataJPA가 JpaRepository를 상속 받은 SpringDataJpaMemberRepository에 대한 구현체를 스프링 빈으로 등록해주면
    스프링이 자동으로 이 인터페이스에 대한 구현 클래스를 생성하고 빈으로 등록해준다.
    <Member, Long>은 제네릭 타입으로 지정한 엔티티 클래스와 엔티티의 ID 타입이다.
    JpaRepository는 Spring Data JPA가 제공하는 기본적인 CRUD 메서드를 포함하고 있다.
     */
    Optional<Member> findByName(String name);
}
