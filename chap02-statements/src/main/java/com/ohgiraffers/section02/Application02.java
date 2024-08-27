package com.ohgiraffers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;


        /*
        * preparedStatement
        * - 완성된 쿼리문과 미완성된 쿼리문을 모두 사용할 수 있다.
        * - 미완성 쿼리 = 위치홀더를 사용한 쿼리문 (?)
        * */

        try {
            pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ?");

            pstmt.setString(1, "200");

            rset = pstmt.executeQuery();

            while (rset.next()) {
                System.out.println(rset.getString(1) + " " + rset.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }


    }
}
