package com.ohgiraffers.understand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.*;  //JDBCTemplate.* → JDBCTemplate 에 있는 모든 메소드를 호출(임포트)하겠다는 뜻!! Connection과 close를 따로 임포트할 필요 없다!

public class Application {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;


        try {
            pstmt = con.prepareStatement("Select E.EMP_ID, E.EMP_NAME, E.EMAIL, E.PHONE, J.JOB_NAME FROM EMPLOYEE E JOIN JOB J ON J.JOB_CODE = E.JOB_CODE ORDER BY SALARY DESC LIMIT 1");
            // 쿼리문이 문제일 때는 워크벤치에 실행시켜 보는 습관을 들이는게 좋다!
            rset = pstmt.executeQuery();
            while (rset.next()) {
                System.out.println(rset.getString(1) + " " + rset.getString(2) + " " + rset.getString(3) + " " + rset.getString(4) + " " + rset.getString(5));
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
