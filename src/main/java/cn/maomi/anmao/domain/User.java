package cn.maomi.anmao.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Component
@Data
public class User implements Serializable {
    private Integer id;
    private String user;
    private String password;
    private String profile;
    private Integer role;
    private List<Multimedia> multimediaList;
    private Role roleList;
}
