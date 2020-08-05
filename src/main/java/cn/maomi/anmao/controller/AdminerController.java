package cn.maomi.anmao.controller;

import cn.maomi.anmao.domain.Multimedia;
import cn.maomi.anmao.domain.User;
import cn.maomi.anmao.mapper.AdminerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminerController {
    @Autowired
    private AdminerMapper adminmapper;

    //将用户关小黑屋——role置为0
    @RequestMapping("/updateblackhouse")
    public String updateblackhouse(@RequestParam("id") int id) {
        int i = adminmapper.BlackHouse(id);
        if (i == 0) {
            return "error";
        }
        return "redirect:/admin/selectNotBan";
    }

    //将用户解禁
    @RequestMapping("/openblackhouse")
    public String openblackhouse(@RequestParam("id") int id) {
        int i = adminmapper.openBlackHouse(id);
        if (i == 0) {
            return "error/error";
        }
        return "redirect:/admin/selectBan";
    }

    //根据用户名模糊查询用户
    @RequestMapping("/selectname")
    public String selectn(@RequestParam("user") String user, Model model) {
        List<User> u = adminmapper.SelectUserList(user);
        if (u != null) {
            model.addAttribute("user", u);
        } else {
            System.out.println("error/error");
        }
        return "admin/adminAllUser";
    }

    //根据id模糊查询用户
    @RequestMapping("/selectid")
    public String selecti(@RequestParam("id") Integer id, Model model) {
        User u = adminmapper.SelectUserById(id);
        if (u != null) {
            model.addAttribute("user", u);
        } else {
            System.out.println("error/error");
        }
        return "admin/adminAllUser";
    }

    @RequestMapping("/selectNotBan")
    public String selectNotBan(Model model) throws ParseException {
        List<User> u = adminmapper.selectNotBan();
        if (u != null) {
            model.addAttribute("user", u);
        } else {
            return "";
        }
        return "admin/adminNotBanUser";
    }

    @RequestMapping("/selectBan")
    public String selectBan(Model model) throws ParseException {
        List<User> u = adminmapper.selectBan();
        if (u != null) {
            model.addAttribute("user", u);
        } else {
            return "";
        }
        return "admin/adminBanUser";
    }

    @RequestMapping("/selectAll")
    public String selectAll(Model model) throws ParseException {
        List<User> u = adminmapper.selectAll();
        if (u != null) {
            model.addAttribute("user", u);
        } else {
            return "";
        }
        return "admin/adminAllUser";
    }


    @RequestMapping("/videoList")
    public String videoList(Model model) {
        List<Multimedia> multimedia = adminmapper.allAuditOkVideo();
        model.addAttribute("multimedia", multimedia);
        return "main/shop";
    }
//    @RequestMapping("/videoList1")
////    public String videoList1(Model model) {
////        List<Multimedia> multimedia = adminmapper.allAuditOkVideo();
////        model.addAttribute("multimedia", multimedia);
////        return "main/index";
////    }

    //显示未审核视频
    @RequestMapping("/adminNewVideo")
    public String AdminNewVideo(Model model) {
        List<Multimedia> multimedia = adminmapper.newVideo();
        if (multimedia != null) {
            model.addAttribute("multimedia", multimedia);
        } else {
            return "admin/error";
        }
        return "admin/adminNewVideo";
    }

    //显示所有视频
    @RequestMapping("/adminAllVideo")
    public String AdminAllVideo(Model model) {
        List<Multimedia> multimedia = adminmapper.allVideo();
        if (multimedia != null) {
            model.addAttribute("multimedia", multimedia);
        } else {
            return "admin/error";
        }
        return "admin/adminAllVideo";
    }

    //审核通过
    @RequestMapping("/adminupdateaudit")
    public String updateaudit(@RequestParam("vid") int vid) {
        int i = adminmapper.auditOk(vid);
        if (i != 0) {
            return "redirect:/admin/adminNewVideo";
        }
        return "admin/error";
    }

    //删除视频
    @RequestMapping("/deleteVideoAndImage")
    public String deletevai(@RequestParam("vid") int vid, @RequestParam("label") String label) {
        int i = adminmapper.deleteVideo(vid);
        if (label.equals("all")) {
            return "redirect:/admin/adminAllVideo";
        } else if (label.equals("new")) {
            return "redirect:/admin/adminNewVideo";
        } else {
            return "admin/error";
        }
    }

    //打开播放器
    @RequestMapping("/player")
    public String player(@RequestParam("va") String va, Model model) {
        System.out.println(va);
        model.addAttribute("va", va);
        return "player/demo/index";
    }
}
