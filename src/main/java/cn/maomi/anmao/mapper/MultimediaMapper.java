package cn.maomi.anmao.mapper;

import cn.maomi.anmao.domain.Multimedia;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MultimediaMapper {
    int multifileUpload(Multimedia multimedia);//视频上传

    List<Multimedia> findVideoByTitle(String m);//根据视频标题模糊查询
}
