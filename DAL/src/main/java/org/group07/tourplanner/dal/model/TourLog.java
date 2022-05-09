package org.group07.tourplanner.dal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourLog implements Serializable {
    private int tourid;
    private LocalDate date;
    private String comment;
    private int difficulty;
    private int duration;
    private int rating;
}
