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

		@Column(name = "isDeleted", nullable = false)
	    private boolean isDeleted;


	    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	    @JoinColumn(name = "cottage_id")
	    private Cottage cottage;

	    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	    @JoinColumn(name = "ship_id")
	    private Ship ship;
	 
	 
}
