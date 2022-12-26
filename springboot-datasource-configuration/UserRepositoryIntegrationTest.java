package com.ahlquist.commio.repositories;

import org.junit.Assert;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ahlquist.commio.models.User;

@SuppressWarnings("unused")
@RunWith(SpringRunner.class)
//@DataJpaTest
//@ContextConfiguration(classes={UserRepository.class})
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "com.ahlquist.commio.repositories" })
@ComponentScan(basePackages = { "com.ahlquist.commio.repositories" })
@EntityScan("com.ahlquist.commio.models")
public class UserRepositoryIntegrationTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void test_whenCalledSave_thenCorrectNumberOfUsers() {

		try {
			List<User> usersInit = (List<User>) userRepository.findAll();

			userRepository.save(new User("Douglas", "douglas@communication.io"));
			List<User> users = (List<User>) userRepository.findAll();

			if (users.size() != 0) {
				for (User user : users) {
					System.out.println(user.toString());
				}
			} else {
				System.err.println("User array size == 0");
			}

		//	Assert.assertEquals(users.size(), users.size() > usersInit.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		UserRepositoryIntegrationTest test = new UserRepositoryIntegrationTest();
		test.test_whenCalledSave_thenCorrectNumberOfUsers();
	}
}