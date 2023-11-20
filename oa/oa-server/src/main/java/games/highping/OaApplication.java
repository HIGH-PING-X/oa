package games.highping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("games.highping.server.mapper")
public class OaApplication {
        public static void main(String[] args) {
            org.springframework.boot.SpringApplication.run(OaApplication.class, args);
        }
}
