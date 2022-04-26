package org.group07.tourplanner.dal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourLog implements Serializable {
    private int id;
    private float date;
    private String comment;
    private String difficulty;
    private float time;
    private int rating;
}
