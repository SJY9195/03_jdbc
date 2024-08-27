package com.ohgiraffers.section01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {

        // 사원번호에 해당하는 사원의 정보를 조회한다.
        // 1. connection
        // 2. statement 쿼리 만들기
        // 3. 쿼리를 날린다.
        // 4. resultSet 으로 결과를 받는다.

        Connection con = getConnection();

        Statement stmt = null;
        ResultSet rset = null;

        try {
            stmt = con.createStatement();
            String empId = "200";

            rset = stmt.executeQuery("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = " + empId);

            while (rset.next()) { // 다음 결과가 있으면 true
                System.out.println(rset.getString("EMP_ID") + " " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            close(con);
            close(stmt);
            close(rset);

        }



    }
}
