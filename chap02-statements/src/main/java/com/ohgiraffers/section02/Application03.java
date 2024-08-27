package com.ohgiraffers.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application03 {
    public static void main(String[] args) {

        // 성씨를 입력 받아 해당 성을 가진 사원 조회
        // SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?, '%');    // CONCAT 문자를 붙여주는 명령어!!

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        Scanner scr = new Scanner(System.in);
        System.out.println("성을 입력해주세요 : ");
        String LASTNAME = scr.nextLine();


        try {
            pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?, '%')");  // % : 특정 패턴을 포함하는 문자열을 검색하는데 사용!!
            pstmt.setString(1, LASTNAME);
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
