package cn.maomi.anmao.mapper;

import cn.maomi.anmao.domain.Login;
import cn.maomi.anmao.domain.Rigester;
import cn.maomi.anmao.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginAndRegister {
    int register(Rigester regester);//注册

    Object userExist(String user);//验证注册用户名是否重复

    User login(User user);//登录

    int loginBan(int id);//验证用户登录时是否被禁言

    int DontRemeberPassword(Rigester rigester);//忘记密码 修改密码
}
