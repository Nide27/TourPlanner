package org.group07.tourplanner.dal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourLog implements Serializable {
    private int tourid;
    private LocalDateTime date;
    private String comment;
    private String difficulty;
    private float duration;
    private int rating;
}
