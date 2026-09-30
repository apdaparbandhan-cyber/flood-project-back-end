package com.floodreleafe.project;

import com.floodreleafe.project.entity.AdminUser;
import com.floodreleafe.project.repository.AdminUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	@Bean
	CommandLineRunner initDatabase(AdminUserRepository adminUserRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (adminUserRepository.count() == 0) {
				AdminUser superAdmin = AdminUser.builder()
						.username("admin")
						.password(passwordEncoder.encode("Admin@2026#Secure"))
						.fullName("National Operations Admin")
						.role("ROLE_SUPER_ADMIN")
						.email("admin@floodngo.org")
						.build();
				adminUserRepository.save(superAdmin);
				System.out.println(">>> Super Admin tayar: Username: admin | Password: Admin@2026#Secure");
			}
		};
	}
}
