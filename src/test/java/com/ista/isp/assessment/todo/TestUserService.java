package com.ista.isp.assessment.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ista.isp.assessment.todo.dao.impl.UserDaoImpl;
import com.ista.isp.assessment.todo.model.User;
import com.ista.isp.assessment.todo.service.UserServiceImpl;

@SpringBootTest
public class TestUserService {
	
	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserDaoImpl userDao;

	
	@Test
	void testSaveUser() {
		    User user = new User(1, "Marcelo", "Address", "testsave@email.com", false);
		    userService.saveUser(user);
			verify(userDao, times(1)).saveUser(user);
	}
	
	@Test
	void testUpdateUser() {
			User user = new User(1, "Marcelo", "Address", "testsave@email.com", false);
		    /**
			    * Update the user
			*/
			when(userDao.updateUser(user)).thenReturn(new User(1,"Marcelo","Address","testUpdate@email.com", true));
			user.setEmail("testUpdate@email.com");
			user = userService.updateUser(user);
			assertEquals(true, user.isChecked());
	}
	
	@Test
	public void getUserByIdTest()
	{
		when(userDao.getUserById(1)).thenReturn(new User(1,"Marcelo","Address","testUpdate@email.com", true));
		User user = userService.getUserById(1);
		assertEquals("Marcelo", user.getName());
		assertEquals("Address", user.getAddress());
		assertEquals("testUpdate@email.com", user.getEmail());
		assertEquals(true, user.isChecked());

	}

}
