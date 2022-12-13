package com.jdc.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamApp {

	public static void main(String[] args) {
		
		User user1 = new User("Mg Mg", "mgmg@twitter.com", "MgMg@123", 20);
		User user2 = new User("Thida", "thida@gmail.com", "Thida@456", 16);
		User user3 = new User("Kyaw Thiha", "kyawthiha@yahoomail.com","Kyaw@1010", 14);
		User user4 = new User("Ni Ni", "nini@gmail.com", "Nini4pass", 19);
		
		List<User> list = new ArrayList<>();
		
		Collections.addAll(list, user1, user2, user3, user4);
		
		IntSummaryStatistics result = list.stream().map(user -> user.getAge())
							 		.collect(Collectors.summarizingInt(t -> (Integer)t));
		 
		Optional<Integer> accu = list.stream().map(user -> user.getAge()).reduce((a, b) -> b - a);
		
		System.out.println(accu.get());
		
	}

}
