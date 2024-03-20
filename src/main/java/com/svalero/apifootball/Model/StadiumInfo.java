package com.svalero.apifootball.Model;

import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StadiumInfo {

    private String name;
    private int capacity;
    private String surface;
    private Image image;
}
