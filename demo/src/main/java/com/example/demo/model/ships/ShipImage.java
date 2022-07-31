package com.example.demo.model.ships;


import com.example.demo.model.Image;
import com.example.demo.model.cottages.Cottage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "shipImages")
public class ShipImage {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    private Ship ship;


    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;
}

