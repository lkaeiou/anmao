package cn.maomi.anmao.controller;

import cn.maomi.anmao.domain.Multimedia;
import cn.maomi.anmao.domain.User;
import cn.maomi.anmao.mapper.PersonalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/person")
public class PersonalController {
    @Autowired
    PersonalMapper personalMapperMapper;
    @Autowired
    User user;
    @Autowired
    tool tool;

    @RequestMapping("/all")
    //@ResponseBody
    public String userAll(Model model1, Model model2,HttpSession session) {
        User person = personalMapperMapper.userAll(tool.sessionTool(session).getId());
        model1.addAttribute("person", person);

        List<Multimedia> multimedia = person.getMultimediaList();
        model2.addAttribute("multimedia", multimedia);

        return "personal/index";
    }

    @RequestMapping("/update")
    public String personUpdate(@RequestParam("profile") String profile,HttpSession session) {
        user.setId(tool.sessionTool(session).getId());
        user.setProfile(profile);
        personalMapperMapper.updateProfile(user);
        return "redirect:/person/all";
    }


}