package idv.liucheyu.tyb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Pattern;

//@SpringBootTest
class TightYourBeltApplicationTests {

	@Test
	void contextLoads() {
		Pattern pricePattern = Pattern.compile("#{1}\\d*\\.?\\d*");
		System.out.println(pricePattern.matcher("#100.123").matches());
	}

}
