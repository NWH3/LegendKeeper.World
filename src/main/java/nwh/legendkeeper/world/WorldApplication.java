package nwh.legendkeeper.world;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableAutoConfiguration
@EnableMongoRepositories
@SpringBootApplication
@ComponentScan({"nwh.legendkeeper.world"})
public class WorldApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(WorldApplication.class, args);
		context.getBean(WorldService.class).initMongoDB();
	}
}
