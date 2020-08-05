package cn.maomi.anmao.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Multimedia implements Serializable {
    private Integer vid;
    private String iaddress;
    private String vaddress;
    private String title;
    private String brief;
    private String audit;
    private Integer uid;
}
