package prashant.thakur.redis_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisSpringBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(RedisSpringBootApplication.class, args);
  }
}
