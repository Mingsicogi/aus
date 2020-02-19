package my.mins.aus.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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


    public void test() {

        // Get SecurityContext
        SecurityContext securityContext = SecurityContextHolder.getContext();

        // Get Authentication
        Authentication authentication = securityContext.getAuthentication();

        // Get UserDetails
        UserDetails principal = (UserDetails)authentication.getPrincipal();

        // Detail info
        principal.getUsername(); // ID
        principal.getPassword(); // password
        principal.getAuthorities(); // my authorities
    }
}
