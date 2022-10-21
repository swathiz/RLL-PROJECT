package com.example.onlinemovie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlinemovie.exception.ResourceNotFoundException;
import com.example.onlinemovie.model.Admin;
import com.example.onlinemovie.model.Product;
import com.example.onlinemovie.repository.AdminRepository;
import com.example.onlinemovie.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired AdminRepository adminRepository;

	@GetMapping("/products/Admin")
	public List<Product> getAdminProducts() {
		return productRepository.findAll();
	}

	@GetMapping("/products/cust")
	public List<Product> getAllProducts() {
		List<Product> prodList=productRepository.findIfAvail();
		if(prodList.isEmpty()) {
			List<Admin> adminList = adminRepository.findAll();
			if(adminList.isEmpty()) {
				adminRepository.save(new Admin("admin","password"));
			}
			addProdIfEmpty(new Product(1,"Fantstic mr.fox","George clooney,meryl streep as felicity","2hr:30min","Comedey",350,0,0,"yes","./assets/images/fantastic.jpeg"));
			addProdIfEmpty(new Product(1,"Jungle Cruise","Dwanye  Johnson, Emily Blunt, Edger Ramirez","2hr:45min","Comedy",350,0,0,"yes","./assets/images/jungle.jpeg"));
			addProdIfEmpty(new Product(2,"RRR","N.T.Ramarao as komaram bheem, ram charan as alluri sitaramaraju","3hr:15min","Historical", 350,0,0,"yes","./assets/images/rrr.jpeg"));
			addProdIfEmpty(new Product(1,"Samrat Prithviraj","Akshay kuar, manushi chhillar, manav vij,sonu sood","2hr:45min","Historical",350,0,0,"yes","./assets/images/samrat.jpeg"));
			addProdIfEmpty(new Product(1,"Runway 34","Ajay Devgn,Amitabh bachchan, Rakul preet","2hr:35min","Thriller",350,0,0,"yes","./assets/images/thriller.jpeg"));
			addProdIfEmpty(new Product(1,"MARAKKAR","Mohanlal as mammali, Arjun sarja","2hr:45min","Drama",350,0,0,"yes","./assets/images/marakkar.jpeg"));
			//addProdIfEmpty(new Product(1,"TAYOTA","tayota is good adventures car and its going good","Below 50L","RAM MOHAN",1234567, 350,0,0,"yes","./assets/images/tayota.jpg"));
			
			prodList=productRepository.findIfAvail();
		}
		return prodList;
	}
	
	public void addProdIfEmpty(Product product) {
		int min = 10000;
		int max = 99999;
		int b = (int) (Math.random() * (max - min + 1) + min);
		product.setId(b);
		float temp = (product.getActualPrice()) * (product.getDiscount() / 100);
		float price = product.getActualPrice() - temp;
		product.setPrice(price);
		productRepository.save(product);
	}

	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		int min = 10000;
		int max = 99999;
		int b = (int) (Math.random() * (max - min + 1) + min);
		product.setId(b);
		float temp = (product.getActualPrice()) * (product.getDiscount() / 100);
		float price = product.getActualPrice() - temp;
		product.setPrice(price);
		return productRepository.save(product);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails){
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with " + id));
		product.setName(productDetails.getName());
		product.setDesc(productDetails.getDesc());
		product.setDurations(productDetails.getDurations());
		product.setCategory(productDetails.getCategory());
		product.setImagepath(productDetails.getImagepath());
		product.setActualPrice(productDetails.getActualPrice());
		product.setDiscount(productDetails.getDiscount());
		product.setAvail(productDetails.getAvail());
		float temp = (product.getActualPrice()) * (product.getDiscount() / 100);
		float price = product.getActualPrice() - temp;
		product.setPrice(price);
		
		Product updatedProd = productRepository.save(product);
		return ResponseEntity.ok(updatedProd);
		
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with " + id));
		productRepository.delete(product);
		Map<String, Boolean> map = new HashMap<>();
		map.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(map);
	}

	@GetMapping("products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product Not Found with " + id));
		return ResponseEntity.ok(product);
	}

	@GetMapping("products/search/{keyword}")
	public List<Product> getSearchProducts(@PathVariable String keyword) {
		return productRepository.homeSearch(keyword);
	}

	@GetMapping("products/Drama")
	public List<Product> getDrama() {
		return productRepository.getDrama();
	}

	@GetMapping("products/Comedy")
	public List<Product> getComedy() {
		return productRepository.getComedy();
	}

	@GetMapping("products/Historical")
	public List<Product> getHistorical() {
		return productRepository.getHistorical();
	}

	@GetMapping("products/Thriller")
	public List<Product> getThriller(){
		return productRepository.getThriller();
	}
}