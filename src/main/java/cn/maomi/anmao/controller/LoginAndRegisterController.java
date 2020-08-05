package cn.maomi.anmao.controller;

import cn.maomi.anmao.domain.Multimedia;
import cn.maomi.anmao.domain.Rigester;
import cn.maomi.anmao.domain.User;
import cn.maomi.anmao.mapper.AdminerMapper;
import cn.maomi.anmao.mapper.LoginAndRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/LAR")

public class LoginAndRegisterController {
    @Autowired
    private LoginAndRegister loginAndRegister;
    @Autowired
    User user;
    @Autowired
    private AdminerMapper adminmapper;

    //注册
    @GetMapping("/register")
    public String UserRegister(Rigester rigester, @RequestParam("user") String user, Model model1, Model model2, Model model3) {
        if (rigester.getPassword().equals(rigester.getRepassword())) {
            Object re = loginAndRegister.userExist(user);
            if (re != null) {
                model2.addAttribute("userExist", "该用户已被注册，请换个用户名！");
                return "login/index";
            } else {
                loginAndRegister.register(rigester);
                model3.addAttribute("rigesterSuccess", "注册成功，您可以登录啦  喵~");
                return "login/index";
            }
        } else {
            model1.addAttribute("rigesterError", "两次密码不一致哦  喵喵~");
            return "login/index";
        }
    }

    //登录
    @RequestMapping("/login")

    public String UserLogin(User user,
                            Model model1, Model model2, Model model3, Model model4,
                            HttpSession session) {

        User re = loginAndRegister.login(user);

        if (re==null) {//登录失败
            model2.addAttribute("loginError", "用户名或密码错误哦，喵~~");
            return "login/index";
        } else if (re.getId() == 1 || re.getId() == 2 || re.getId() == 3) {//管理员登录
            return "admin/index";
        } else {//用户登录
            int role = loginAndRegister.loginBan(re.getId());
            if (role < 0) {
                model4.addAttribute("loginBan", "您的账号已被封禁，请解禁后再登录");
                return "login/index";
            } else {
                session.setAttribute("user",re);
                List<Multimedia> multimedia = adminmapper.allAuditOkVideo();
                model3.addAttribute("multimedia", multimedia);
                return "main/index";
            }
        }
    }



    //忘记秘密  修改密码
    @RequestMapping("/DRP")
    public String DontRemeberPassword(Rigester rigester,
                                      @RequestParam("user") String user,
                                      @RequestParam("password") String password,
                                      Model model1,
                                      Model model2,
                                      Model model3) {
        if (rigester.getPassword().equals(rigester.getRepassword())) {
            Object re = loginAndRegister.userExist(user);
            if (re != null) {
                loginAndRegister.DontRemeberPassword(rigester);
                model3.addAttribute("DRPsuccess", "修改成功，请记住密码哦 喵~");
                return "login/index";
            } else {
                model2.addAttribute("userNotExist", "该用户不存在哦，亲！");
                return "login/DontRemeberPassword";
            }
        } else {
            model1.addAttribute("rigesterError", "两次密码不一致哦  喵喵~");
            return "login/DontRemeberPassword";
        }
    }
//    登出
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

}
