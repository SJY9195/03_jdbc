package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application02 {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/employee";
        String user = "gangnam";
        String password = "gangnam";

        Connection con = null;

        try {
            con = DriverManager.getConnection(url, user, password);

            System.out.println(con);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                con.close();   // DB와의 연결을 끊어주는 역할
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
