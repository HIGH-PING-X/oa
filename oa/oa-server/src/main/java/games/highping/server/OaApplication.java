package games.highping.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("games.highping.server.mapper")
@EnableScheduling
public class OaApplication {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(OaApplication.class, args);
    }
}
