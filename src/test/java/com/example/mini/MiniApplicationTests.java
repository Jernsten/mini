package com.example.mini;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

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
	public void checkDBConnection(){
		Repository sut = rep;
		
		HashMap<String,User> userList = sut.getUserList();
		
		assertEquals(userList.get("Kemal").getNickName(), "Kemal" );
	}
}
