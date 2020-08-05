package cn.maomi.anmao.controller;

import cn.maomi.anmao.domain.User;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

@Component
public class tool {
    public User sessionTool(HttpSession session){
        User re=(User)session.getAttribute("user");
        return re;
    }
}
