package mx.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = {"mx.org.spring", "mx.org.citricos.dao"})
@SpringBootApplication
public class GsCompleteFase
{

	public static void main(String[] args) {
		SpringApplication.run(GsCompleteFase.class, args);
	}

}
