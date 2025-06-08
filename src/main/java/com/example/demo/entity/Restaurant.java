package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
	
	private int restaurantId;
	private String restaurantName;
	private String catchPhrase;
	private int reviewCnt; 
	
}
	
