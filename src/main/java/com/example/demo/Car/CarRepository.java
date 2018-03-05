package com.example.demo.Car;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long>{
	@Override
	public List<Car> findAll();
	

}
