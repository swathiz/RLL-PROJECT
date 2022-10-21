package com.example.onlinemovie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.onlinemovie.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query("Select p FROM Product p WHERE p.avail='yes' ORDER BY 'category'")
	List<Product> findIfAvail();
	
	@Query("SELECT p FROM Product p WHERE (p.avail LIKE 'yes') AND (p.name LIKE %?1%"
			+" OR p.des LIKE %?1%"
			+" OR p.price LIKE %?1%"
			+" OR p.category LIKE %?1%)")
	public List<Product> homeSearch(String keyword);
	
	@Query("SELECT p FROM Product p WHERE p.category LIKE 'Drama' AND p.avail LIKE 'yes'")
	public List<Product> getDrama();
	
	@Query("SELECT p FROM Product p WHERE p.category LIKE 'Comedy' AND p.avail LIKE 'yes'")
	public List<Product> getComedy();
	
	//@Query("SELECT p FROM Product p WHERE p.category LIKE 'Historical' AND p.avail LIKE 'yes'")
	//public List<Product> getHistorical();
	
//	@Query("SELECT p FROM Product p WHERE p.category LIKE 'Thriller' AND p.avail LIKE 'yes'")
//	public List<Product> getThriller();
	@Query("SELECT p FROM Product p WHERE p.category LIKE 'Historical' AND p.avail LIKE 'yes'")
	List<Product> getHistorical();
	@Query("SELECT p FROM Product p WHERE p.category LIKE 'Thriller' AND p.avail LIKE 'yes'")
	public List<Product> getThriller();
}
