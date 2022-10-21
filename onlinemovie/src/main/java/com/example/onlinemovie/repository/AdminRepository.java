package com.example.onlinemovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlinemovie.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{

	Admin findByusername(String username);

}
