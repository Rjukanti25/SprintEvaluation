package com.example.insurance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.insurance.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{

	Optional<Users> findByEmail(String email);

	Optional<Users> findByUsername(String userName);

	

}