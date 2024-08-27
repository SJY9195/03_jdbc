package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application02 {
    public static void main(String[] args) {

        /*
        * 사용자가 원하는 메뉴를 등록할 수 있도록 만들어주세요.
        * 등록이 완료되면 성공, 실패하면 실패라고 출력 해주세요.
        * */

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        Scanner scr = new Scanner(System.in);

        System.out.println("원하는 메뉴이름을 입력 해주세요 : ");

        String menuname = scr.nextLine();

        System.out.println("원하는 가격을 입력 해주세요 : ");

        Integer menuprice = scr.nextInt();

        System.out.println("원하는 코드를 입력 해주세요 : ");

        Integer categorycode = scr.nextInt();

        System.out.println("주문 상태를 입력 해주세요(Y와 N중에 입력해주세요) : ");

        scr.nextLine();

        String orderstatus = scr.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("insertMenu"));
            pstmt.setString(1, menuname);
            pstmt.setInt(2, menuprice);
            pstmt.setInt(3,categorycode);
            pstmt.setString(4,orderstatus);

            result = pstmt.executeUpdate();   // mysql 쪽이 문제면은 result 가 0이나오고, java쪽에서 에러가 뜨면 빨간글씨가 나온다!!

            if (result == 1) {
                System.out.println("성공");
            } else if (result == 0) {
                System.out.println("실패");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }
    }
}
