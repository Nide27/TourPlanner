package org.group07.tourplanner.dal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourReport implements Serializable {
    private TourItem tourItem;
    private List<TourLog> tourLogList;
}
