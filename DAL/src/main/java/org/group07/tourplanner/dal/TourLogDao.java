package org.group07.tourplanner.dal;

import lombok.SneakyThrows;
import org.group07.tourplanner.dal.model.TourLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
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
                    rs.getObject(2, LocalDate.class),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getInt(6)
            ));
        }

        return tourLogList;
    }

    @SneakyThrows
    @Override
    public void create(TourLog tourLog) {

        String sql = "INSERT INTO logs (tourid, date, comment, difficulty, duration, rating) VALUES (?,?,?,?,?,?);";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, tourLog.getTourid());
        stmt.setObject(2, tourLog.getDate());
        stmt.setString(3, tourLog.getComment());
        stmt.setInt(4, tourLog.getDifficulty());
        stmt.setInt(5, tourLog.getDuration());
        stmt.setInt(6, tourLog.getRating());

        stmt.execute();
    }

    @SneakyThrows
    @Override
    public void update(TourLog tourLog) {

        String sql = "UPDATE logs SET date = ?, comment = ?, difficulty = ?, duration = ?, rating = ? WHERE tourid = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setObject(1, tourLog.getDate());
        stmt.setString(2, tourLog.getComment());
        stmt.setInt(3, tourLog.getDifficulty());
        stmt.setInt(4, tourLog.getDuration());
        stmt.setInt(5, tourLog.getRating());
        stmt.setInt(6, tourLog.getTourid());

        stmt.execute();
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
