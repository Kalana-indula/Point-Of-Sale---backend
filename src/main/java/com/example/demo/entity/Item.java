package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String serialNumber;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private Double price;

    //Create a collumn for foriegn key
    @ManyToOne
    @JoinColumn(name = "item_category_id")
    private ItemCategory itemCategory;

    @JsonIgnore
    @OneToOne(mappedBy = "item",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private Stock stock;

    @JsonIgnore
    @ManyToMany(mappedBy = "items")
    private List<PosOrder> orders=new ArrayList<>();
}
