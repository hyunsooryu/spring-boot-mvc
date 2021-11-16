package me.hyunsoo.springbootmvc.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/main")
    public String main(Model model){
        model.addAttribute("name", "hyunsoo");
        return "main";
    }
}
