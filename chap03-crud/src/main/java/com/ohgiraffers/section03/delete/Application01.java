package com.ohgiraffers.section03.delete;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {
    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        System.out.println("제거하실 메뉴 이름을 적어주세요 : ");

        String a = scr.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            try {
                pstmt = con.prepareStatement(prop.getProperty("deleteMenu"));
                pstmt.setString(1, a);

                result = pstmt.executeUpdate();  // SELECT 만 executequery 고 나머지는 다 Update이다!!  DELETE는 쿼리문에서 해준다!!

                if (result == 0) {         //DELETE 하면 지워지므로 result가 0이 나와야 DELETE 된것이다!!
                    System.out.println("성공");
                } else {
                    System.out.println("실패");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }


    }
}