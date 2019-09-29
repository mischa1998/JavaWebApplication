package com.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Cat on 27.09.2019.
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public ModelAndView hello(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("in HelloController");
        request.setAttribute("name", "Mischa");
        ModelAndView helloModel = new ModelAndView();
        helloModel.setViewName("helloView");
        return helloModel;
    }
}
