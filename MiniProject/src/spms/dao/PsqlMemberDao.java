package spms.dao;

import spms.annotation.Component;
import spms.vo.Member;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component("memberDao")
public class PsqlMemberDao implements MemberDao {

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Member> selectList() throws Exception {
        String query = "SELECT no, id, password, email, name, locationCode" +
                " from members" +
                " order by no";
        Connection connection = dataSource.getConnection();
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            ArrayList<Member> members = new ArrayList<>();

            while (rs.next()) {
                members.add(new Member()
                        .setNo(rs.getInt("no"))
                        .setId(rs.getString("id"))
                        .setName(rs.getString("name"))
                        .setEmail(rs.getString("email"))
                        .setLocationCode(rs.getInt("locationCode"))
                );
            }
            return members;
        }
    }

    @Override
    public int insert(Member member) throws Exception {
        String query = "INSERT INTO members (no, id, password, email, name, locationCode) " +
                "VALUES (DEFAULT, ?, ?, ?, ?, NULL)";
        int success;
        Connection connection = dataSource.getConnection();

        try (
                PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getName());
            success = pstmt.executeUpdate();
        }
        return success;
    }

    @Override
    public int delete(int no) throws Exception {
        String query = "DELETE " +
                "FROM members " +
                "WHERE no=?";
        int success;
        Connection connection = dataSource.getConnection();

        try (
                PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            pstmt.setInt(1, no);
            success = pstmt.executeUpdate();
        }
        return success;
    }

    @Override
    public Member selectOne(int no) throws Exception {
        String query = "SELECT no, id, password, email, name, locationCode " +
                "FROM members " +
                "WHERE no=?";
        Connection connection = dataSource.getConnection();
        Member returnMember;
        try (
                PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            pstmt.setInt(1, no);
            ResultSet rs = pstmt.executeQuery();
            rs.next();

            returnMember = new Member()
                    .setNo(no)
                    .setId(rs.getString("id"))
                    .setPassword(rs.getString("password"))
                    .setEmail(rs.getString("email"))
                    .setLocationCode(rs.getInt("locationCode"));
        }
        return returnMember;
    }

    @Override
    public int update(Member member) throws Exception {
        int success = 0;
        String query = "UPDATE members " +
                "SET locationCode=? " +
                "WHERE no=?";
        Connection connection = dataSource.getConnection();

        try (
                PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            pstmt.setInt(1, member.getLocationCode());
            pstmt.setInt(2, member.getNo());
            success = pstmt.executeUpdate();
        }
        return success;
    }

    @Override
    public int exist(String id, String password) throws Exception {
        int no = 0;
        String query = "SELECT no " +
                "FROM members " +
                "WHERE id=? AND password=?";
        Connection connection = dataSource.getConnection();
        try (
                PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                no = rs.getInt("no");
            }
        }
        return no;
    }

    @Override
    public boolean checkOverlapEmail(Member member) throws Exception {
        String query = "SELECT email " +
                "FROM members " +
                "WHERE email=?";
        Connection connection = dataSource.getConnection();

        try (
                PreparedStatement pstmt = connection.prepareStatement(query)
        ) {
            pstmt.setString(1, member.getEmail());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Overlap 되어있음
                return true;
            }
        }
        // Overlap 안되어 있음
        return false;
    }
}
