package com.example.mini;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MiniApplicationTests {

	@Autowired
	Repository rep;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void createUserObject(){
		User sut = new User("Kemme", "password", "http:/");
		
		assertEquals(sut.getNickName(), "Kemme");
		assertEquals(sut.getPassword(), "password");
		assertEquals(sut.isOnline(), true);
	}
	
	@Test
	public void createOnlineUsersObject(){
		OnlineUsers sut = new OnlineUsers();
		
		sut.addUser(new User("Kemme", "password", "http:/"));
		sut.addUser(new User("apa","hej", "haha"));
		
		assertEquals(sut.getOnlineUsers().size(),2);
	}
	
	@Test
	public void loadUsersFromDataBase(){
		Repository sut = rep;
		
		sut.loadUsers();
		
		assertEquals(sut.getUserList().size(), 25);
	}
}
