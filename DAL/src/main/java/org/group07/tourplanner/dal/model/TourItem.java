package org.group07.tourplanner.dal.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourItem implements Serializable {
    private int id;
    private String name;
    private String description;
    private String departure;
    private String destination;
    private String transport;
    private float distance;
    private float estimate;

    @Override
    public String toString() {
        return name;
    }
}
