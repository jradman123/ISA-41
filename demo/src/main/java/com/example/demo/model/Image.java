package com.example.demo.model;

import javax.persistence.*;

import com.example.demo.model.cottages.Cottage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "image")
@RequiredArgsConstructor
public class Image {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "url", unique = false, nullable = false)
	String url;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "cottage_id", nullable = true)
	private Cottage cottage;

	@Column(name = "isDeleted", nullable = false)
	private boolean isDeleted;

}
