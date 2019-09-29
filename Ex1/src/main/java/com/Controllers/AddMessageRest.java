package com.Controllers;

import com.DataSource.H2Connect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cat on 29.09.2019.
 */
@RestController
public class AddMessageRest {
    private ApplicationContext context;
    private HttpSession session;
    @Autowired
    public void setSession(HttpSession session) {
        this.session = session;
    }
    @Autowired
    public void setContextHolder(ApplicationContext context) {
        this.context = context;
    }
    @RequestMapping("/addMessage")
    public ModelAndView addMessage(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView start = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            start.setViewName("errorHandle");
            request.setAttribute("error", "Not auth user");
            return start;
        }
        String message = request.getParameter("message");
        System.out.println(message);
        H2Connect connect = new H2Connect();
        Connection conn = null;
        PreparedStatement prSt = null;
        try {
            conn = connect.getH2Connection();
            prSt = conn.prepareStatement("insert into messages values(?, ?)");
            prSt.setString(1, (String)session.getAttribute("user"));
            prSt.setString(2, message);
            prSt.execute();
        } catch(Exception e) {
            request.setAttribute("error", e.getMessage());
            start.setViewName("errorHandle");
            return start;
        } finally {
            try {
                if(prSt != null) {
                    prSt.close();
                }
            } catch(Exception e){} finally {
                try {
                    if(conn != null) {
                        conn.close();
                    }
                } catch(Exception e) {}
            }
        }
        start.setViewName("userPage");
        request.setAttribute("userName", (String)session.getAttribute("user"));
        return start;
    }
    @RequestMapping("/messages")
    public ModelAndView messages(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView start = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            start.setViewName("errorHandle");
            request.setAttribute("error", "Not auth user");
            return start;
        }
        H2Connect connect = new H2Connect();
        Connection conn = null;
        PreparedStatement prSt = null;
        List<String> list = new ArrayList<>();
        try {
            conn = connect.getH2Connection();
            prSt = conn.prepareStatement("select * from messages where username = ?");
            prSt.setString(1, (String)session.getAttribute("user"));
            ResultSet rs = prSt.executeQuery();
            if(rs != null) {
                while(rs.next()) {
                    list.add(rs.getString("MESSAGE"));
                }
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            start.setViewName("errorHandle");
            return start;
        } finally {
            try {
                if(prSt != null) {
                    prSt.close();
                }
            } catch(Exception e){} finally {
                try {
                    if(conn != null) {
                        conn.close();
                    }
                } catch(Exception e) {}
            }
        }
        request.setAttribute("list", list);
        request.setAttribute("userName", (String)session.getAttribute("user"));
        start.setViewName("userPage");
        return start;
    }
}
