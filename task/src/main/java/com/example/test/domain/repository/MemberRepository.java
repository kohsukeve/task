package com.example.test.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.test.domain.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	public List<Member> findByNameContains(String name);

}
