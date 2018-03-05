package com.example.demo;

<<<<<<< HEAD
import javax.persistence.*;

@Entity

=======

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
>>>>>>> f46c6a3ee1d8fec4127f446fb3cb479e74ca7d66
public class Car {
	@Id
	@GeneratedValue
	long id;
	String name;
<<<<<<< HEAD

=======
	long serialnr;
>>>>>>> f46c6a3ee1d8fec4127f446fb3cb479e74ca7d66
}
