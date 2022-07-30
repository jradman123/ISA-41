package com.example.demo.model;

import javax.persistence.*;

import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.ships.Ship;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "rules")
@RequiredArgsConstructor
@AllArgsConstructor
public class Rules {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		

	    @Column(name = "ruleDescription", nullable = false)
	    private String ruleDescription;

		@Column(name = "is_deleted_by_cottage", nullable = false)
	    private boolean isDeletedbyCottages;

	@Column(name = "is_deleted_by_ship", nullable = false)
	private boolean isDeletedByShip;



	 @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	    @JoinColumn(name = "cottage_id",nullable = true)
	    private Cottage cottage;

	    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	    @JoinColumn(name = "ship_id",nullable = true)
	    private Ship ship;
	 
	 
}
