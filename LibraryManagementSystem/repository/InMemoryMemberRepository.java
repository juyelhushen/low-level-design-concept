package LibraryManagementSystem.repository;

import LibraryManagementSystem.entity.Member;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryMemberRepository implements Repository<Member> {
    private static Map<String, Member> memberMap = new ConcurrentHashMap<>();

    @Override
    public void save(Member member) {
        memberMap.put(member.getPersonId(), member);
    }

    @Override
    public Optional<Member> findById(String memberId) {
        return Optional.ofNullable(memberMap.get(memberId));
    }

    @Override
    public Optional<Member> findByIsbn(String isbn) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return List.of();
    }
}
