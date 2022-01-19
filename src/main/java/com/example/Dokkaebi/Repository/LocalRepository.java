package com.example.Dokkaebi.Repository;

import com.example.Dokkaebi.domain.Battery;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.sql.*;

//@Slf4j
//@Repository
public class LocalRepository{
//    private final DataSource dataSource;
//
//    public LocalRepository(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//    private Connection getConnection() {
//        return DataSourceUtils.getConnection(dataSource);
//    }
//
//    @Override
//    public void save(Battery battery) {
//        String sql = "INSERT INTO Stable (id, bike, stat, soc, volt, temp, lat, lon, pow, shoc) values (" +
//                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            conn = getConnection();
//            pstmt = conn.prepareStatement(sql,
//                    Statement.RETURN_GENERATED_KEYS);
//            pstmt.setLong(1,battery.getId());
//            pstmt.setShort(2,battery.getBike());
//            pstmt.setString(3, battery.getStat());
//            pstmt.setBytes(4, battery.getSoc());
//            pstmt.setBigDecimal(5,battery.getVolt());
//            pstmt.setBytes(6, battery.getTemp());
//            pstmt.setBigDecimal(7, battery.getLat());
//            pstmt.setBigDecimal(8, battery.getLon());
//            pstmt.setBoolean(9, battery.getPow());
//            pstmt.setBoolean(10, battery.getShoc());
//            pstmt.executeUpdate();
//            rs = pstmt.getGeneratedKeys();
//            if (rs.next()) {
//                battery.setId(rs.getLong(1));
//            } else {
//                throw new SQLException("id 조회 실패");
//            }
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        } finally {
//            close(conn, pstmt, rs);
//        }
//    }
//    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
//    {
//        try {
//            if (rs != null) {
//                rs.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            if (pstmt != null) {
//                pstmt.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            if (conn != null) {
//                close(conn);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    private void close(Connection conn) throws SQLException {
//        DataSourceUtils.releaseConnection(conn, dataSource);
//    }
}
