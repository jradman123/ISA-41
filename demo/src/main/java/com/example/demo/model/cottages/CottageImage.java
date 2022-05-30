package com.example.demo.model.cottages;

import com.example.demo.model.Image;
import com.example.demo.model.Utility;
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
@Table(name = "cottageImages")
public class CottageImage {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cottage_id")
    private Cottage cottage;


    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;



    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;

}
