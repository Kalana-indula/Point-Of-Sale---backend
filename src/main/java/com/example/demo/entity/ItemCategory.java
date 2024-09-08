package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Setter
@Getter
@ToString
public class ItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    //Specifying the inverse side of the relationship
    //Fetch type was set to 'LAZY' so that
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "itemCategory",cascade = CascadeType.ALL)
    private List<Item> items;

}
