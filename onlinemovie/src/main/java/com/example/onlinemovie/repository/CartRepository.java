package com.example.onlinemovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlinemovie.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
