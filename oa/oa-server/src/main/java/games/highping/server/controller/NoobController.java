package games.highping.server.controller;

import games.highping.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class NoobController {

    @GetMapping("/noob")
    public String noob() {
        return "noob";
    }

    @GetMapping("/employee/basic/noob")
    public String employeeBasicNoob() {
        return "employeeBasicNoob";
    }

    @GetMapping("/employee/advanced/noob")
    public String employeeAdvancedNoob() {
        return "employeeAdvancedNoob";
    }
}
