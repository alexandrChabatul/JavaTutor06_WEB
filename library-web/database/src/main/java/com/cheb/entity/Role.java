package com.cheb.entity;

import java.util.Arrays;
import java.util.Optional;

public enum Role {
	
	ADMIN,
	USER;
	
	public static Optional<Role> find(String name){
		return Arrays.stream(values())
				.filter(n -> n.name().equals(name))
				.findFirst();
	}

}
