package com.senacor;

import com.senacor.model.NaturalPerson;
import com.senacor.repository.NaturalPersonRepository;
import com.senacor.repository.SpeechRatingRepository;
import com.senacor.repository.SpeechRepository;
import com.senacor.service.SpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DevConRatingServiceApplication implements CommandLineRunner {

	@Autowired
	SpeechRepository speechRepository;

	@Autowired
	SpeechRatingRepository speechRatingRepository;

	@Autowired
	NaturalPersonRepository naturalPersonRepository;

	public static void main(String[] args) {
		SpringApplication.run(DevConRatingServiceApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		/*speechRatingRepository.deleteAll();
		speechRepository.deleteAll();*/
	}
}
