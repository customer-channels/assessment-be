package com.ista.isp.assessment.todo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ista.isp.assessment.todo.dto.UserDto;
import com.ista.isp.assessment.todo.io.entity.UserEntity;
import com.ista.isp.assessment.todo.repo.UserRepository;
import com.ista.isp.assessment.todo.service.UserService;
import com.ista.isp.assessment.todo.ui.controller.UserController;
import com.ista.isp.assessment.todo.ui.model.request.UserDetailsRequestModel;
import com.ista.isp.assessment.todo.ui.model.response.UserRest;

@SpringBootTest
class TodoApplicationTests {

		
		 	@Resource
		    private UserRepository userRepository;
		    
		    @Test
		    public void createUser() {
		    	UserDto user = new UserDto();
		    	user.setEmail("a");
		    	user.setFirstName("b");
		    	user.setLastName("a");
		    	UserEntity userEntity = new UserEntity();
				BeanUtils.copyProperties(user,userEntity);
				userRepository.save(userEntity);

		    }

}
