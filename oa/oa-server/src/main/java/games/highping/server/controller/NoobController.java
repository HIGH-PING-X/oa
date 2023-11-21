package games.highping.server.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "NoobController", description = "noob")
public class NoobController {

    @GetMapping("/noob")
    public String noob() {
        return "noob";
    }

}
