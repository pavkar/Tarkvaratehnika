package com.example.demo;

import javax.persistence.*;

@Entity

public class Car {

	long id;
	String name;
	
	public static void main(String[] args) {
		Car car = new Car();
	}

}
