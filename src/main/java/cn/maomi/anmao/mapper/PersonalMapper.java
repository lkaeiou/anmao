package cn.maomi.anmao.mapper;

import cn.maomi.anmao.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PersonalMapper {
    User userAll(Integer i);//个人信息
    void updateProfile(User user);//更新个人简介
}
