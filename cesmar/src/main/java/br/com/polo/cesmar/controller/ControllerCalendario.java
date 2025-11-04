package br.com.polo.cesmar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerCalendario {

    @GetMapping("/calendario")
     public ModelAndView calendario() {
        ModelAndView mv = new ModelAndView("calendario");
        return mv;
    }
}
