package org.group07.tourplanner.dal;

import lombok.SneakyThrows;
import org.group07.tourplanner.dal.model.TourItem;
import org.group07.tourplanner.dal.model.TourLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TourLogDao implements Dao<TourLog> {

    private final Connection conn;

    public TourLogDao(Connection conn){ this.conn = conn; }

    @Override
    public Optional<TourLog> get(int id) { return Optional.empty(); }

    @Override
    public List<TourLog> getAll() { return new ArrayList<>(); }

    @SneakyThrows
    public List<TourLog> getAllById(int id){
        String sql = "SELECT * FROM logs WHERE tourid=?;";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, id);

        ArrayList<TourLog> tourLogList = new ArrayList<>();
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            tourLogList.add(new TourLog(
                    rs.getInt(1),
                    rs.getObject(2, LocalDateTime.class),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getFloat(5),
                    rs.getInt(6)
            ));
        }

        return tourLogList;
    }

    @Override
    public void create(TourLog tourLog) {
        return;
    }

    @Override
    public void update(TourLog tourLog) {

    }

    @SneakyThrows
    @Override
    public void delete(TourLog tourLog){
        String sql = "DELETE FROM logs WHERE tourid=? AND date=?;";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, tourLog.getTourid());
        stmt.setObject(2, tourLog.getDate());

        stmt.execute();
    }

}
