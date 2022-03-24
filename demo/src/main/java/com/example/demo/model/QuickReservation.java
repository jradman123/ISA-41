package com.example.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "quickReservation")
//@Inheritance(strategy = InheritanceType.JOINED)
public class QuickReservation {
	
	 @Id
	 @Column(name = "id")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "guestLimit", nullable = false)
    private Integer guestLimit;

    @Column(name = "actionStart", nullable = false)
    private LocalDateTime actionStart;

    @Column(name = "actionEnd", nullable = false)
    private LocalDateTime actionEnd;

    @Column(name = "price", nullable = false)
    private Double price = 0.0;

    @Column(name = "isReserved", nullable = false)
    private Boolean isReserved = false;

   
}
