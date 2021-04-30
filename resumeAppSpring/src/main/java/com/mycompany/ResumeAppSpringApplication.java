package com.mycompany;

import com.mycompany.dao.inter.UserRepository;
import com.mycompany.entity.UserTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com.mycompany")
public class ResumeAppSpringApplication {


	public static void main(String[] args) {
		SpringApplication.run(ResumeAppSpringApplication.class, args);
	}
/*
	@Autowired
	private UserDaoInter userDaoInter;

	@Bean
	public CommandLineRunner run(){
		CommandLineRunner clr = new CommandLineRunner(){

			@Override
			public void run(String... args) throws Exception {
				List<UserTable> userList = userDaoInter.searchUsers(null,null,null);
				System.out.println(userList);
			}
		};
		return clr;
	}*/

	@Autowired
	private UserRepository userRepository;

	@Bean
	public CommandLineRunner run(){
		CommandLineRunner clr = new CommandLineRunner(){

			@Override
			public void run(String... args) throws Exception {
				//List<UserTable> userTableList = userRepository.findAll();
				/*System.out.println(userTableList);

				List<UserTable> userTableList2 = userRepository.findAll(Sort.by(Sort.Order.desc("id")));
				System.out.println(userTableList2);*/

/*
				UserTable userTable = userRepository.findById(13).get();
				System.out.println(userTable);*/

				//UserTable userTable = userRepository.findByName("test2");
				UserTable userTable = userRepository.findBySurname("testov2");
				System.out.println(userTable.getNationality().getName());
			}
		};
		return clr;
	}
}
