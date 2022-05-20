package org.group07.tourplanner.dal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourSummary implements Serializable {
    private String name;
    private double avgTime;
    private double avgDifficulty;
    private double avgRating;
}
