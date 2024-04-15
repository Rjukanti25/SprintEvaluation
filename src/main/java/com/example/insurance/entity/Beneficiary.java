package com.example.insurance.entity;
 
import java.time.LocalDate;
 
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.OneToOne;

import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;

import lombok.ToString;
 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString

public class Beneficiary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false,unique = true)
	private int beneficiaryId;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	
	@JoinColumn(name = "user_id")
	Users user;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "policy_id")
	Policy policies;
 
	@Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
	private String name;

	@Size(min = 1, max = 255, message = "Relationship must be between 1 and 255 characters")
	private String relationship;

}
