/**
 * The main application class for the Group 25 project.
 * This class is responsible for bootstrapping the Spring Boot application.
 * 
 * <p>This class contains the main method which serves as the entry point for the application.</p>
 * 
 * <p>Usage:</p>
 * <pre>
 * {@code
 * java -jar Group25Application.jar
 * }
 * </pre>
 * 
 * <p>Dependencies:</p>
 * <ul>
 *   <li>Spring Boot</li>
 * </ul>
 * 
 * <p>Package: uk.ac.ucl.comp0010</p>
 * 
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 */
package uk.ac.ucl.comp0010;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Group25Application {

	public static void main(String[] args) {
		SpringApplication.run(Group25Application.class, args);
	}

}
