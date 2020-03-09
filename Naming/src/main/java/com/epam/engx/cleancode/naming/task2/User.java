package com.epam.engx.cleancode.naming.task2;

import java.util.Arrays;
import java.util.Date;

public class User {

	private Date dateBirth;

	private String name;

	private boolean isAdmin;

	private User[] users;

	private int rating;

	public User(String name) {
		super();
		this.name = name;
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "User{" +
				"dateBirth=" + dateBirth +
				", name='" + name + '\'' +
				", isAdmin=" + isAdmin +
				", users=" + java.util.Arrays.toString(users) +
				", rating=" + rating +
				'}';
	}
}
