package com.example.hellospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hellospring.domain.Member;
import java.util.Optional;

// interface는 다중 상속이 가능
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
// 조회 기능은 메소드 보고 쿼리를 알아서 짜준다
    @Override
    Optional<Member> findByName(String name);

}
