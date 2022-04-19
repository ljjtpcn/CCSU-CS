package service.impl;

import req.StudentRequest;
import res.RowDO;
import res.TableDTO;
import service.StudentService;
import util.DBUtil;

import java.sql.*;
import java.util.Vector;

/**
 * @description:
 * @author: 李佳骏
 * @date: 2021-12-12 15:33
 * @version: 1.0
 * @email: ljjtpcn@163.com
 */
public class StudentServiceImpl implements StudentService {
    @Override
    public TableDTO retrieveStudents(StudentRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from students ").append("where username = '").append(request.getUser()).append("' ");

        if (request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())) {
            sql.append("and ").append("(classify like '%").append(request.getSearchKey()).append("%' or ")
                    .append("note like '%").append(request.getSearchKey()).append("%')");

        }
        sql.append("order by modifyDate desc  limit ").append(request.getStart()).append(",").append(request.getPageSize());

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO returnDTO = new TableDTO();

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            returnDTO.setData(getVectors(rs));

            sql.setLength(0);
            sql.append("select count(id) from students ").append("where username = '" + request.getUser() + "' ");
            if (request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())) {
                sql.append("and ").append("classify like '").append(request.getSearchKey()).append("'");
            }
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                int cnt = rs.getInt(1);
                returnDTO.setTotalCount(cnt);
            }
            return returnDTO;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeRes(rs);
            DBUtil.closePre(ps);
            DBUtil.closeConnection(conn);
        }
        return null;
    }


    @Override
    public TableDTO totalIncomeStudents(StudentRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from students where changeMoney > 0 and username = ? order by modifyDate desc");
        String username = request.getUser();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO returnDTO = new TableDTO();

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, request.getUser());
            rs = ps.executeQuery();

            returnDTO.setData(getVectors(rs));

            //条数
            sql.setLength(0);
            sql.append("select count(id) from students ").append("where username = '" + request.getUser() + "' ");
            if (request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())) {
                sql.append("and ").append("classify like '").append(request.getSearchKey()).append("'");
            }
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                int cnt = rs.getInt(1);
                returnDTO.setTotalCount(cnt);
            }
            //上周
            sql.setLength(0);
            sql.append("SELECT SUM(changeMoney) AS lastWeek FROM students WHERE YEARWEEK(DATE_FORMAT(modifyDate,'%Y-%m-%d')) = YEARWEEK(NOW())-1 AND changeMoney > 0 AND username = ?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                double lastWeek = rs.getDouble("lastWeek");
                returnDTO.setLastWeekTotal(lastWeek);
            }
            //本周
            sql.setLength(0);
            sql.append("SELECT SUM(changeMoney) AS thisWeek FROM students WHERE YEARWEEK(DATE_FORMAT(modifyDate,'%Y-%m-%d')) = YEARWEEK(NOW()) AND changeMoney > 0 AND username = ?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                double thisWeek = rs.getDouble("thisWeek");
                returnDTO.setThisWeekTotal(thisWeek);
            }
            //上月
            sql.setLength(0);
            sql.append("SELECT SUM(changeMoney) AS lastMonth FROM students WHERE DATE_FORMAT(modifyDate,'%Y-%m')=DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH),'%Y-%m') AND changeMoney > 0 AND username = ?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                double lastMonth = rs.getDouble("lastMonth");
                returnDTO.setLastMonthTotal(lastMonth);
            }
            //本月
            sql.setLength(0);
            sql.append("SELECT SUM(changeMoney) AS thisMonth FROM students WHERE DATE_FORMAT(modifyDate,'%Y-%m')=DATE_FORMAT(NOW(),'%Y-%m') AND changeMoney > 0 AND username = ?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                double thisMonth = rs.getDouble("thisMonth");
                returnDTO.setThisMonthTotal(thisMonth);
            }
            //去年
            sql.setLength(0);
            sql.append("SELECT SUM(changeMoney) AS lastYear FROM students WHERE YEAR(modifyDate)=YEAR(DATE_SUB(NOW(),INTERVAL 1 YEAR)) AND changeMoney > 0 AND username = ?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                double lastYear = rs.getDouble("lastYear");
                returnDTO.setLastYear(lastYear);
            }
            //今年
            sql.setLength(0);
            sql.append("SELECT SUM(changeMoney) AS thisYear FROM students WHERE YEAR(modifyDate)=YEAR(NOW()) AND changeMoney > 0 AND username = ?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                double thisYear = rs.getDouble("thisYear");
                returnDTO.setThisYear(thisYear);
            }

            return returnDTO;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeRes(rs);
            DBUtil.closePre(ps);
            DBUtil.closeConnection(conn);
        }
        return null;
    }

    @Override
    public TableDTO totalExpendStudents(StudentRequest request) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from students where changeMoney < 0 and username = ? order by modifyDate desc");
        String username = request.getUser();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO returnDTO = new TableDTO();

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, request.getUser());
            rs = ps.executeQuery();

            returnDTO.setData(getVectors(rs));

            //条数
            sql.setLength(0);
            sql.append("select count(id) from students ").append("where username = '" + request.getUser() + "' ");
            if (request.getSearchKey() != null && !"".equals(request.getSearchKey().trim())) {
                sql.append("and ").append("classify like '").append(request.getSearchKey()).append("'");
            }
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                int cnt = rs.getInt(1);
                returnDTO.setTotalCount(cnt);
            }
            //上周
            sql.setLength(0);
            sql.append("SELECT SUM(changeMoney) AS lastWeek FROM students WHERE YEARWEEK(DATE_FORMAT(modifyDate,'%Y-%m-%d')) = YEARWEEK(NOW())-1 AND changeMoney < 0 AND username = ?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                double lastWeek = rs.getDouble("lastWeek");
                returnDTO.setLastWeekTotal(lastWeek);
            }
            //本周
            sql.setLength(0);
            sql.append("SELECT SUM(changeMoney) AS thisWeek FROM students WHERE YEARWEEK(DATE_FORMAT(modifyDate,'%Y-%m-%d')) = YEARWEEK(NOW()) AND changeMoney < 0 AND username = ?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                double thisWeek = rs.getDouble("thisWeek");
                returnDTO.setThisWeekTotal(thisWeek);
            }
            //上月
            sql.setLength(0);
            sql.append("SELECT SUM(changeMoney) AS lastMonth FROM students WHERE DATE_FORMAT(modifyDate,'%Y-%m')=DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 MONTH),'%Y-%m') AND changeMoney < 0 AND username = ?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                double lastMonth = rs.getDouble("lastMonth");
                returnDTO.setLastMonthTotal(lastMonth);
            }
            //本月
            sql.setLength(0);
            sql.append("SELECT SUM(changeMoney) AS thisMonth FROM students WHERE DATE_FORMAT(modifyDate,'%Y-%m')=DATE_FORMAT(NOW(),'%Y-%m') AND changeMoney < 0 AND username = ?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                double thisMonth = rs.getDouble("thisMonth");
                returnDTO.setThisMonthTotal(thisMonth);
            }
            //去年
            sql.setLength(0);
            sql.append("SELECT SUM(changeMoney) AS lastYear FROM students WHERE YEAR(modifyDate)=YEAR(DATE_SUB(NOW(),INTERVAL 1 YEAR)) AND changeMoney < 0 AND username = ?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                double lastYear = rs.getDouble("lastYear");
                returnDTO.setLastYear(lastYear);
            }
            //今年
            sql.setLength(0);
            sql.append("SELECT SUM(changeMoney) AS thisYear FROM students WHERE YEAR(modifyDate)=YEAR(NOW()) AND changeMoney < 0 AND username = ?");
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                double thisYear = rs.getDouble("thisYear");
                returnDTO.setThisYear(thisYear);
            }

            return returnDTO;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeRes(rs);
            DBUtil.closePre(ps);
            DBUtil.closeConnection(conn);
        }


        return null;
    }

    @Override
    public RowDO getBySelectedId(RowDO request) {

        StringBuilder sql = new StringBuilder();
        sql.append("select * from students where  username = ? and id = ?");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        RowDO returnDTO = new RowDO();

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, request.getUsername());
            ps.setString(2, request.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                double changeMoney = rs.getDouble("changeMoney");
                String classify = rs.getString("classify");
                Date modifyDate = rs.getDate("modifyDate");
                String note = rs.getString("note");
                returnDTO.setId(String.valueOf(id));
                returnDTO.setUsername(username);
                returnDTO.setMoney(String.valueOf(changeMoney));
                returnDTO.setType(classify);
                returnDTO.setDate(String.valueOf(modifyDate));
                returnDTO.setRemark(note);
            }
            return returnDTO;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.closeRes(rs);
            DBUtil.closePre(ps);
            DBUtil.closeConnection(conn);
        }
        return null;
    }

    private Vector<Vector<Object>> getVectors(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            double changeMoney = rs.getDouble("changeMoney");
            String classify = rs.getString("classify");
            Date modifyDate = rs.getDate("modifyDate");
            String note = rs.getString("note");

            Vector<Object> rows = new Vector<>();
            rows.addElement(id);
            rows.addElement(username);
            rows.addElement(changeMoney);
            rows.addElement(classify);
            rows.addElement(modifyDate);
            rows.addElement(note);
            data.add(rows);
        }
        return data;
    }

}


