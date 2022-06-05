package com.ahlquist.commio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ahlquist.commio.models.ERole;
import com.ahlquist.commio.models.Role;
import com.ahlquist.commio.repository.RoleRepository;

@SpringBootApplication
public class MognoTemplateApplication implements CommandLineRunner {
	
	@Autowired
	RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(MognoTemplateApplication.class, args);
	}

	/* Add some rows into roles collection before assigning any role to Employee. */
	@Override
	public void run(String... args) throws Exception {
		try {
			Role role = new Role();
			role.setName(ERole.ROLE_EMPLOYEE);
			roleRepository.save(role);
			Role role2 = new Role();
			role2.setName(ERole.ROLE_ADMIN);
			roleRepository.save(role2);
		} catch (Exception e) {

		}
	}

}
