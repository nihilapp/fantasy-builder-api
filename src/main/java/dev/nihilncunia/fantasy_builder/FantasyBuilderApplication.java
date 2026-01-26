package dev.nihilncunia.fantasy_builder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FantasyBuilderApplication {

  public static void main(String[] args) {
    SpringApplication.run(FantasyBuilderApplication.class, args);
  }

}
