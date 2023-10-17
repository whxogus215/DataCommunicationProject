package com.nft.reservation.domain.user.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class H2JdbcAdminRepository implements JdbcAdminRepository {

    private final DataSource dataSource;

    @Override
    public Optional<Admin> findAdminById(String Id) {
        String sql = "select * from admin where admin_loginid = ?";

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, Id);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                String loginId = rs.getString(2);
                String loginPassword = rs.getString(4);

                Admin findAdmin = new Admin(loginId, loginPassword);
                return Optional.of(findAdmin);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    Connection getConnection() {
        Connection con = DataSourceUtils.getConnection(dataSource);
        return con;
    }

    private void close(Connection con, PreparedStatement pstmt, ResultSet resultSet) {
        JdbcUtils.closeResultSet(resultSet);
        JdbcUtils.closeStatement(pstmt);
        DataSourceUtils.releaseConnection(con, dataSource);
    }
}
