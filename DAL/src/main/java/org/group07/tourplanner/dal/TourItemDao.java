package org.group07.tourplanner.dal;

import org.group07.tourplanner.dal.model.TourItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourItemDao implements Dao<TourItem> {

    private final Connection conn;

    public TourItemDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<TourItem> get(int id) throws SQLException {

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
                    rs.getDouble(7),
                    rs.getString(8)
            ));
        }

        return tourItem;
    }

    @Override
    public List<TourItem> getAll() throws SQLException {

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
                                rs.getDouble(7),
                                rs.getString(8)
            ));
        }

        return tourItems;
    }

    @Override
    public void create(TourItem tourItem) throws SQLException {

        String sql = "INSERT INTO items (name, description, departure, destination, transport, distance, estimate) VALUES (?,?,?,?,?,?,?);";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, tourItem.getName());
        stmt.setString(2, tourItem.getDescription());
        stmt.setString(3, tourItem.getDeparture());
        stmt.setString(4, tourItem.getDestination());
        stmt.setString(5, tourItem.getTransport());
        stmt.setDouble(6, tourItem.getDistance());
        stmt.setString(7, tourItem.getEstimate());

        stmt.execute();
    }

    @Override
    public void update(TourItem tourItem) throws SQLException {

        String sql = "UPDATE items SET name = ?, description = ?, departure = ?, destination = ?, transport = ?, distance = ?, estimate = ? WHERE id = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, tourItem.getName());
        stmt.setString(2, tourItem.getDescription());
        stmt.setString(3, tourItem.getDeparture());
        stmt.setString(4, tourItem.getDestination());
        stmt.setString(5, tourItem.getTransport());
        stmt.setDouble(6, tourItem.getDistance());
        stmt.setString(7, tourItem.getEstimate());
        stmt.setInt(8, tourItem.getId());

        stmt.execute();
    }

    @Override
    public void delete(TourItem tourItem) throws SQLException {

        String sql = "DELETE FROM items WHERE id=?;";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, tourItem.getId());

        stmt.execute();
    }
}
