package com.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Cat on 28.09.2019.
 */
@Controller
public class StartController {
    private HttpSession session;
    @Autowired
    public void setSession(HttpSession session) {
        this.session = session;
    }
    @RequestMapping("/")
    public ModelAndView start(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView start = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            start.setViewName("errorHandle");
            request.setAttribute("error", "Not auth user");
            return start;
        }
        start.setViewName("userPage");
        request.setAttribute("userName", authentication.getName());
        session.setAttribute("user", authentication.getName());
        return start;
    }
}
