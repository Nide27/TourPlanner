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
    private double duration;
    private String content;

    @Override
    public String toString() {
        return name;
    }
}
