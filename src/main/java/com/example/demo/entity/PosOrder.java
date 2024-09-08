package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class PosOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderTime;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private Double tax;

    @ManyToMany
    @JoinTable(
            name="order_details",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name="item_id")
    )
    private List<Item> items=new ArrayList<>();
}
