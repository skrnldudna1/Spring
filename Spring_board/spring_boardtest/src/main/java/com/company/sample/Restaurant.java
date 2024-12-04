package com.company.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class Restaurant {
	
	//Chef chef = new Chef();
	@Autowired
	private Chef chef;

}
