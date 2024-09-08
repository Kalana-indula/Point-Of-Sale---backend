package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ItemDto {

    private String serialNumber;

    private String itemName;

    private Double price;

    private Long itemCategoryId;
}
