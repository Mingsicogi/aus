package my.mins.aus.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class Welcome {

    @GetMapping("/user")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("message", "Hello User, " + principal.getName());

        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "Hello Admin, " + principal.getName());

        return "admin";
    }
}
