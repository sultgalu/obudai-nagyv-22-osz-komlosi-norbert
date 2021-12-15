package warehouse.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = { "warehouse.*" }) // should be persistence.data
@EnableJpaRepositories(basePackages = { "warehouse.persistence.repository" })
@SpringBootApplication(scanBasePackages = {
  "warehouse.*" })
public class SpringBootApp {
  @Autowired
  private App app;

  public static void main(String args[]) {
    SpringApplication.run(SpringBootApp.class, args).close();
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      this.app.play();
    };
  }
}
