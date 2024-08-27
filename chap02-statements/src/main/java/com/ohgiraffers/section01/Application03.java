package com.ohgiraffers.section01;




import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application03 {
    public static void main(String[] args) {

        // 이름을 입력받아서 해당 사원 아이디와 이름 조회
        // 쿼리문도 변수로 따로 만들어서 넣어주세요
        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        Scanner scr = new Scanner(System.in);

        try {
            stmt = con.createStatement();
            System.out.println("이름을 입력해주세요 : ");
            String name = scr.nextLine();
            String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_NAME = '" + name + "'"; // 이래야 query에서 name을 문자열로 인식한다!

            rset = stmt.executeQuery(query);

            while(rset.next()){
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
