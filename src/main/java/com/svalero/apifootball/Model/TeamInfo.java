package com.svalero.apifootball.Model;

import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamInfo {

    private String name;
    private String code;
    private int founded;
    private Image logo;
    private List<StadiumInfo> stadium;

}
