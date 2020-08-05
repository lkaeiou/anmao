package cn.maomi.anmao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Component
@Data
public class Rigester implements Serializable {
    private Integer id;
    private String user;
    private String password;
    private String repassword;
}
