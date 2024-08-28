package com.ohgiraffers.section02.update;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application01 {
    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        System.out.println("변경하실 메뉴 이름을 입력 해주세요");
        String a = scr.nextLine();
        System.out.println("어떤 메뉴 이름으로 변경 하시겠습니까?");
        String b = scr.nextLine();
        System.out.println("바꿀 메뉴의 가격을 입력 해주세요");
        int c = scr.nextInt();
        System.out.println("바꿀 메뉴의 타입을 선택 해주세요");
        System.out.println("4.한식 , 5.중식, 6.일식, 7.퓨전");
        int d = scr.nextInt();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("updateMenu"));

            pstmt.setString(4,a);
            pstmt.setString(1,b);
            pstmt.setInt(2,c);
            pstmt.setInt(3,d);

            result = pstmt.executeUpdate();

            if(result == 1){
                System.out.println("성공");
            } else {
                System.out.println("실패");
            }

            }
         catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
        throw new RuntimeException(e);
    } finally {
            close(con);
            close(pstmt);

        }
    }

    }