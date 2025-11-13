package users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone;

@SpringBootApplication
public class UsersServiceApplication {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        System.out.println("JVM default timezone = " + TimeZone.getDefault());
        SpringApplication.run(UsersServiceApplication.class, args);
    }
}
