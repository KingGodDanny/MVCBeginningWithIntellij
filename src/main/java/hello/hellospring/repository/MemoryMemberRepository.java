package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(m -> m.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    //테스트코드를 한번에 실행했을경우 메소드의 실행순서는 랜덤이기때문에 이미 먼저 실행된 메소드에서 저장이 일어났거나 하는경우
    //다음 실행 메소드에서 오류가 날수있다. 그래서 각 메소드의 실행 후 클리어해주는 메소드를 작성한다.
    public void clearStore() {
        store.clear();
    }
}
