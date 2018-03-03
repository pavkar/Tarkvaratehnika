package com.example.demo;

import javax.persistence.*;

@Entity

public class Car {
	@Id
	@GeneratedValue
	long id;
	String name;

}
