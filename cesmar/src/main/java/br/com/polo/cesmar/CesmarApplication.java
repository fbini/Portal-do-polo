package br.com.polo.cesmar;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CesmarApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CesmarApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

    }
}
