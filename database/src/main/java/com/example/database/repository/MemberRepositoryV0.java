package com.example.database.repository;

import com.example.database.connection.DBConnectionUtil;
import com.example.database.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class MemberRepositoryV0 {

    public Member save(Member member) throws SQLException{
        String sql = "insert into member(member_id, money) values(?,?)";

        //finally에서 호출해야하기 때문에 null로 초기화
        Connection con = null;
        PreparedStatement state = null; // 파라미터 바인딩을 제공

        try{
            con = getConnection();
            state = con.prepareStatement(sql);
            //파라미터 바인딩 (바인딩 안하면 sql Injection 공격을 받을 수 있다)
            state.setString(1, member.getMemberId());
            state.setInt(2, member.getMoney());
            state.executeUpdate();
            return member;
        } catch(SQLException e){
            log.error("db error",e);
            throw e;
        } finally { // 안닫아주면 리소스 누수 발생
            close(con,state,null);
        }

    }

    public Member findById(String memberId) throws SQLException{
        String sql = "select * from member where member_id = ?";

        Connection con = null;
        PreparedStatement state = null;
        ResultSet rs = null;

        try {
            // con-state-rs 순으로 얻고 close할때는 반대 순서
            con = getConnection();
            state = con.prepareStatement(sql);
            state.setString(1, memberId);

            rs = state.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                throw new NoSuchElementException("member not found memberId = " + memberId);
            }
        }catch(SQLException e){
                log.error("db error",e);
                throw e;
            }finally {
            close(con,state,rs);
        }
    }
    public void update(String memberId, int money) throws SQLException{
        String sql = "update member set money =? where member_id =?";

        //finally에서 호출해야하기 때문에 null로 초기화
        Connection con = null;
        PreparedStatement state = null; // 파라미터 바인딩을 제공

        try{
            con = getConnection();
            state = con.prepareStatement(sql);
            //파라미터 바인딩 (바인딩 안하면 sql Injection 공격을 받을 수 있다)
            state.setInt(1, money);
            state.setString(2, memberId);
            int resultSize = state.executeUpdate();
            log.info("resultSize={}",resultSize);
        } catch(SQLException e){
            log.error("db error",e);
            throw e;
        } finally { // 안닫아주면 리소스 누수 발생
            close(con,state,null);
        }

    }
    public void delete(String memberId) throws SQLException{
        String sql = "delete from member where member_id=?";

        //finally에서 호출해야하기 때문에 null로 초기화
        Connection con = null;
        PreparedStatement state = null; // 파라미터 바인딩을 제공

        try{
            con = getConnection();
            state = con.prepareStatement(sql);
            //파라미터 바인딩 (바인딩 안하면 sql Injection 공격을 받을 수 있다)
            state.setString(1, memberId);
            state.executeUpdate();
        } catch(SQLException e){
            log.error("db error",e);
            throw e;
        } finally { // 안닫아주면 리소스 누수 발생
            close(con,state,null);
        }

    }
    // 순서 보장 필요!
    private void close(Connection con, Statement stat, ResultSet ret) {
        if(ret != null){
            try {
                ret.close();
            } catch (SQLException throwables) {
                log.info("error",throwables);
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException throwables) {
                log.info("error", throwables);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException throwables) {
                log.info("error",throwables);
            }
        }
    }
    private Connection getConnection(){
        return DBConnectionUtil.getConnection();
    }
}
