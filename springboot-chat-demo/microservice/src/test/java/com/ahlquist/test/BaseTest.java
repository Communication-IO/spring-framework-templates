package com.ahlquist.test;

//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahlquist.app.Application;

@ContextConfiguration(classes = { Application.class })
@ServletComponentScan(basePackages = {"com.ahlquist.commio.models","com.ahlquist.commio.repositories"})
@PropertySource("classpath:application.properties")

@RunWith(SpringRunner.class)
@DataJpaTest
public class BaseTest {

	public BaseTest() {
		super();
	}
}
