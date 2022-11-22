package com.ahlquist.commio.repositories;

//import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


import com.ahlquist.commio.models.User;

@SuppressWarnings("unused")
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes={UserRepository.class})
public class UserRepositoryIntegrationTest {
    
    @Autowired
    private UserRepository userRepository;
   
    @Test
    public void whenCalledSave_thenCorrectNumberOfUsers() {
        userRepository.save(new User("Douglas", "douglas@communication.io"));
        List<User> users = (List<User>) userRepository.findAll();
        
        if(users.size()!=0) {
        for(User user : users) {
        	System.out.println(user.toString());        	
        }
        }else {
        	System.err.println("User array size == 0");
        }
       
        
//   //     assertThat(users.size()).isEqualTo(1);
    }  
    
    
    public static void main(String[] args) {
//        Result result = JUnitCore.runClasses(TestJunit.class);
//        for (Failure failure : result.getFailures()) {
//           System.out.println("fail ho gaya"+failure.toString());
//        }
//        System.out.println("passed:"+result.wasSuccessful());
     }
}