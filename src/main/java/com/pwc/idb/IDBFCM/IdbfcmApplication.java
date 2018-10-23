package com.pwc.idb.IDBFCM;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IdbfcmApplication  {

	UserRepository userrepo;
	public IdbfcmApplication(UserRepository userrpo) {
		this.userrepo = userrpo;
	}
	public static void main(String[] args) {
		SpringApplication.run(IdbfcmApplication.class, args);
	}

}
