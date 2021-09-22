package idv.liucheyu.tyb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"idv.liucheyu.tyb.config"})
public class TightYourBeltApplication {

	public static void main(String[] args) {
		SpringApplication.run(TightYourBeltApplication.class, args);
	}

}
