package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "utility")

public class Utility {
	
	 @Id
	 @Column(name = "id")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @Column(name = "name", nullable = false)
	 private String name;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted;

	@Column(name = "price", nullable = false)
	private Double price = 0.0;

    public Utility(String name) {
		this.id=id;
		this.name=name;
		this.isDeleted=false;
    }
}
