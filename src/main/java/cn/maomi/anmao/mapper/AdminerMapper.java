package cn.maomi.anmao.mapper;

import cn.maomi.anmao.domain.Multimedia;
import cn.maomi.anmao.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface AdminerMapper {
    int UpdateAdmin(User u);

    int BlackHouse(int id);//关小黑屋

    int openBlackHouse(int id);//小黑屋解禁

    List<User> SelectUserList(String user);//根据用户名模糊查询

    User SelectUserById(Integer id);//根据id查询

    List<User> selectNotBan();//显示正常用户

    List<User> selectBan();//显示小黑屋用户

    List<User> selectAll();//显示所有用户

    List<Multimedia> newVideo();//显示未审核的视频

    List<Multimedia> allVideo();//显示所有视频

    int auditOk(Integer id);//视频审核通过

    List<Multimedia> allAuditOkVideo();//显示所有审核通过的视频

    int deleteVideo(Integer id);//删除违规视频

}
