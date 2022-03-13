package spms.dao;

import spms.annotation.Component;
import spms.vo.Location;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component("locationDao")
public class PsqlLocationDao implements LocationDao {
    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Location selectOne(int locationCode) throws Exception {
        Location location = null;
        String query = "SELECT locationCode, localLevel1, localLevel2, localLevel3, x, y " +
                "FROM location " +
                "WHERE locationCode=?";
        Connection connection = dataSource.getConnection();
        try (
                PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            pstmt.setInt(1, locationCode);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                location = new Location()
                        .setLocationCode(rs.getInt("locationCode"))
                        .setX(rs.getString("x"))
                        .setY(rs.getString("y"))
                        .setLocalLevel1(rs.getString("localLevel1"))
                        .setLocalLevel2(rs.getString("localLevel2"))
                        .setLocalLevel3(rs.getString("localLevel3"));
            }
        }
        return location;
    }

    @Override
    public int findLocationCode(String x, String y) throws Exception {
        String query = "SELECT locationCode " +
                "FROM location " +
                "WHERE x=? AND y=?";
        Connection connection = dataSource.getConnection();
        int locationCode = 0;
        try (
                PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            pstmt.setString(1, x);
            pstmt.setString(2, y);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                locationCode = rs.getInt("locationCode");
            }
        }
        return locationCode;
    }

    @Override
    public int findLocationCode(String localLevel1, String localLevel2, String localLevel3) throws Exception {
        String query = "SELECT locationCode " +
                "FROM location " +
                "WHERE localLevel1=? AND localLevel2=? AND localLevel3=?";
        Connection connection = dataSource.getConnection();
        int locationCode = 0;
        try (
                PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            pstmt.setString(1, localLevel1);
            pstmt.setString(2, localLevel2);
            pstmt.setString(3, localLevel3);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                locationCode = rs.getInt("locationCode");
            }
        }
        return locationCode;
    }

    public Location findLocationCoordinate(String localLevel1, String localLevel2, String localLevel3) throws Exception {
        Location location = null;
        String query = "SELECT locationCode, localLevel1, localLevel2, localLevel3, x, y " +
                "FROM location " +
                "WHERE localLevel1=? AND localLevel2=? AND localLevel3=?";
        Connection connection = dataSource.getConnection();

        try (
                PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            pstmt.setString(1, localLevel1);
            pstmt.setString(2, localLevel2);
            pstmt.setString(3, localLevel3);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                location = new Location()
                        .setLocationCode(rs.getInt("locationCode"))
                        .setX(rs.getString("x"))
                        .setY(rs.getString("y"))
                        .setLocalLevel1(rs.getString("localLevel1"))
                        .setLocalLevel2(rs.getString("localLevel2"))
                        .setLocalLevel3(rs.getString("localLevel3"));
            }
        }
        return location;

    }


//    @Override
//    public Location selectOne(int locationCode) throws Exception {
//        Location location = null;
//        String query = "SELECT locationCode, x, y, pty, reh, rn1, t1h, uuu, vvv, vec, wsd " +
//                "FROM member_location " +
//                "WHERE locationCode=?";
//        Connection connection = dataSource.getConnection();
//        try (
//                PreparedStatement pstmt = connection.prepareStatement(query)
//        ) {
//            pstmt.setInt(1, locationCode);
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                location = new Location()
//                        .setLocationCode(rs.getInt("locationCode"))
//                        .setX(rs.getInt("x"))
//                        .setY(rs.getInt("y"))
//                        .setPTY((rs.getDouble("pty")))
//                        .setREH((rs.getDouble("reh")))
//                        .setRN1((rs.getDouble("rn1")))
//                        .setT1H((rs.getDouble("t1h")))
//                        .setUUU((rs.getDouble("uuu")))
//                        .setVVV((rs.getDouble("vvv")))
//                        .setVEC((rs.getDouble("vec")))
//                        .setWSD((rs.getDouble("wsd")));
//            }
//        }
//        return location;
//    }
//
//    @Override
//    public int insert(Location location) throws Exception {
//        int success;
//        String query = "INSERT INTO member_location(locationCode, x, y, pty, reh, rh1, t1h, uuu, vvv, vec, wsd) " +
//                "VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
//        Connection connection = dataSource.getConnection();
//
//        try (
//                PreparedStatement pstmt = connection.prepareStatement(query)
//        ) {
//            pstmt.setInt(1, location.getX());
//            pstmt.setInt(2, location.getY());
//            pstmt.setDouble(3, location.getPTY());
//            pstmt.setDouble(4, location.getREH());
//            pstmt.setDouble(5, location.getRN1());
//            pstmt.setDouble(6, location.getT1H());
//            pstmt.setDouble(7, location.getUUU());
//            pstmt.setDouble(8, location.getVVV());
//            pstmt.setDouble(9, location.getVEC());
//            pstmt.setDouble(10, location.getWSD());
//            success = pstmt.executeUpdate();
//        }
//        return success;
//    }
//
//    // x, y는 같고 값만 바꿈
//    @Override
//    public int update(Location location) throws Exception {
//        int success;
//        String query = "UPDATE member_location " +
//                "SET pty=?, reh=?, rh1=?, t1h=?, uuu=?, vvv=?, vec=?, wsd=? " +
//                "WHERE locationCode=?";
//        Connection connection = dataSource.getConnection();
//        try (
//                PreparedStatement pstmt = connection.prepareStatement(query)
//        ) {
//            pstmt.setDouble(1, location.getPTY());
//            pstmt.setDouble(2, location.getREH());
//            pstmt.setDouble(3, location.getRN1());
//            pstmt.setDouble(4, location.getT1H());
//            pstmt.setDouble(5, location.getUUU());
//            pstmt.setDouble(6, location.getVVV());
//            pstmt.setDouble(7, location.getVEC());
//            pstmt.setDouble(8, location.getWSD());
//            pstmt.setInt(9, location.getLocationCode());
//
//            success = pstmt.executeUpdate();
//        }
//        return success;
//    }
//
//    @Override
//    public int delete(int locationCode) throws Exception {
//        int success;
//        String query = "DELETE FROM members " +
//                "WHERE locationCode=?";
//        Connection connection = dataSource.getConnection();
//
//        try (
//                PreparedStatement pstmt = connection.prepareStatement(query)
//        ) {
//            pstmt.setInt(1, locationCode);
//            success = pstmt.executeUpdate();
//        }
//        return success;
//
//    }
//
//    @Override
//    public Location exist(int x, int y) throws Exception {
//        Location location = null;
//        String query = "SELECT locationCode, x, y, pty, reh, rn1, t1h, uuu, vvv, vec, wsd " +
//                "FROM member_location " +
//                "WHERE x=? AND y=?";
//        Connection connection = dataSource.getConnection();
//
//        try (
//                PreparedStatement pstmt = connection.prepareStatement(query)
//        ) {
//            pstmt.setInt(1, x);
//            pstmt.setInt(2, y);
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                location = new Location()
//                        .setLocationCode(rs.getInt("locationCode"))
//                        .setX(rs.getInt("x"))
//                        .setY(rs.getInt("y"))
//                        .setPTY((rs.getDouble("pty")))
//                        .setREH((rs.getDouble("reh")))
//                        .setRN1((rs.getDouble("rn1")))
//                        .setT1H((rs.getDouble("t1h")))
//                        .setUUU((rs.getDouble("uuu")))
//                        .setVVV((rs.getDouble("vvv")))
//                        .setVEC((rs.getDouble("vec")))
//                        .setWSD((rs.getDouble("wsd")));
//            }
//        }
//        return location;
//    }
}
