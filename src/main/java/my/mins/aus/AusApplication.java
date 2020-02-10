package my.mins.aus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@SpringBootApplication
@Controller
public class AusApplication {

    public static void main(String[] args) {
        SpringApplication.run(AusApplication.class, args);
    }

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("message", "Hello Spring Security");

        return "index";
    }
}
