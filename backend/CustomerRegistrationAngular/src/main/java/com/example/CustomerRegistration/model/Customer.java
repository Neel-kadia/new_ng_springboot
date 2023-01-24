package com.example.CustomerRegistration.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 50, nullable =  false)
	private String lastName;

	@Column(name = "date_of_birth", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(name = "mobile",length = 15, unique = true, nullable = false)
	private String mobile;

	@Column(name = "address1", length = 255, nullable = false)
	private String address1;

	@Column(name = "address2", length = 255)
	private String address2;

	@Column(name = "age", nullable = false, columnDefinition = "TINYINT")
	private int age;

	@Column(name = "gender", nullable = false, length = 10)
	private String gender;

	@Column(name = "email",length = 50, unique = true, nullable = false)
	private String email;

}