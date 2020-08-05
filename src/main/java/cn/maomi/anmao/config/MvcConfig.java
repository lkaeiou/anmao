package cn.maomi.anmao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login/index");
        registry.addViewController("/login").setViewName("login/index");
//        registry.addViewController("/admin.html").setViewName("admin/index");
        registry.addViewController("/main.html").setViewName("main/index");
/*        registry.addViewController("/f").setViewName("file/file");
        registry.addViewController("/ff").setViewName("file/multifile");*/
        registry.addViewController("/DRP").setViewName("login/DontRemeberPassword");
        registry.addViewController("/LAR/DRP").setViewName("login/DontRemeberPassword");
        registry.addViewController("/upload1").setViewName("main/upload");
        //registry.addViewController("/user").setViewName("main/user");
        registry.addViewController("/person").setViewName("personal/index");
        registry.addViewController("/photoWall").setViewName("main/photoWall");


//以下为临时配置：
        registry.addViewController("/s").setViewName("main/shop");
        registry.addViewController("/t").setViewName("main/test");
        registry.addViewController("/t").setViewName("LAR/login");
        registry.addViewController("/p").setViewName("player/demo/index");
        registry.addViewController("/ppp").setViewName("admin/public");

    }
}
