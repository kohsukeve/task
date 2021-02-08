package com.example.test.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.domain.model.Member;
import com.example.test.domain.repository.MemberRepository;

@Service
public class MemberService {
	@Autowired
	private MemberRepository repository;

	public List<Member> findAll() {
		return repository.findAll();
	}

	public Optional<Member> findById(Long id) {
		return repository.findById(id);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public void save(Member user) {
		repository.save(user);
	}

	public List<Member> findByName(String name) {
		return repository.findByNameContains(name);
	}

}