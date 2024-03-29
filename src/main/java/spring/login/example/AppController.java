package spring.login.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

   @Autowired
   private UserRepository repo;

   @GetMapping("")
   public String viewHomePage() {
      return "index";
   }

   @GetMapping(value = "/register")
   public String showSignUpForm(Model model) {
      model.addAttribute("user", new User());
      return "sign_up";
   }

   @PostMapping(value = "/process_register")
   public String processRegistration(User user) {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      String encodedPassword = encoder.encode(user.getPassword());
      user.setPassword(encodedPassword);
      repo.save(user);
      return "register_success";
   }

   @GetMapping("/list_users")
   public String viewUsersList(Model model) {
      List<User> listUsers = repo.findAll();
      model.addAttribute("users", listUsers);
      return "users";
   }

}
