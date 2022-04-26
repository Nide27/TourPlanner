package org.group07.tourplanner.dal;

import lombok.SneakyThrows;
import org.group07.tourplanner.dal.model.TourItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TourItemDao implements Dao<TourItem> {

    private final Connection conn;

    private List<TourItem> tourItems = new ArrayList<>();
    private int nextId = 1;

    public TourItemDao(Connection conn) {
        this.conn = conn;
    }

    @SneakyThrows
    @Override
    public Optional<TourItem> get(int id) {

        String sql = "SELECT * FROM items WHERE id=?;";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, id);

        Optional<TourItem> tourItem = Optional.empty();
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            tourItem = Optional.of(new TourItem(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getFloat(7),
                    rs.getFloat(8)
            ));
        }

        return tourItem;
    }

    @SneakyThrows
    @Override
    public List<TourItem> getAll() {

        String sql = "SELECT * FROM items;";

        PreparedStatement stmt = conn.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        ArrayList<TourItem> tourItems = new ArrayList<>();

        while(rs.next()){

            tourItems.add(new TourItem(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getString(6),
                                rs.getInt(7),
                                rs.getFloat(8)
            ));
        }

        return tourItems;
    }

    @SneakyThrows
    @Override
    public void create(TourItem tourItem) {

        String sql = "INSERT INTO items (name, description, departure, destination, transport, distance, estimate) VALUES (?,?,?,?,?,?,?);";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, tourItem.getName());
        stmt.setString(2, tourItem.getDescription());
        stmt.setString(3, tourItem.getDeparture());
        stmt.setString(4, tourItem.getDestination());
        stmt.setString(5, tourItem.getTransport());
        stmt.setFloat(6, tourItem.getDistance());
        stmt.setFloat(7, tourItem.getEstimate());
    }

    @SneakyThrows
    @Override
    public void update(TourItem tourItem) {

        String sql = "UPDATE items SET name = ?, description = ?, departure = ?, destination = ?, transport = ?, distance = ?, estimate = ? WHERE id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, tourItem.getName());
        stmt.setString(2, tourItem.getDescription());
        stmt.setString(3, tourItem.getDeparture());
        stmt.setString(4, tourItem.getDestination());
        stmt.setString(5, tourItem.getTransport());
        stmt.setFloat(6, tourItem.getDistance());
        stmt.setFloat(7, tourItem.getEstimate());
        stmt.setInt(8, tourItem.getId());

        stmt.execute();
    }

    @SneakyThrows
    @Override
    public void delete(TourItem tourItem) {

        String sql = "DELETE FROM items WHERE id=?;";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, tourItem.getId());

        stmt.execute();
    }
}
