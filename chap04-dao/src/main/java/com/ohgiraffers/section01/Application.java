package com.ohgiraffers.section01;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {
    public static void main(String[] args) {

        Connection con = getConnection();
        Properties prop = new Properties();

        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        int result = 0;

        ResultSet rset1 = null;
        ResultSet rset2 = null;
        List<Map<Integer,String>> categoryList = null;

        int result3 = 0;

        Scanner scr = new Scanner(System.in);

        System.out.println("원하는 메뉴 코드를 입력 해주세요 : ");

        Integer menucode = scr.nextInt();

        System.out.println("원하는 메뉴 이름을 입력해주세요 : ");

        scr.nextLine();

        String menuname = scr.nextLine();

        System.out.println("원하는 메뉴 가격을 입력해주세요 : ");

        Integer menuprice = scr.nextInt();

        System.out.println("원하는 카테고리 코드를 입력해주세요 : ");

        scr.nextLine();

        Integer categorycode2 = scr.nextInt();

        System.out.println("주문상태를 입력해주세요(Y,N 중 하나 택1) : ");

        scr.nextLine();

        String orderstatus = scr.nextLine();


        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            String query = prop.getProperty("selectLastMenuCode");
            String query2 = prop.getProperty("selectAllCategoryList");
            String query3 = prop.getProperty("insertMenu");

            pstmt1 = con.prepareStatement(query);
            pstmt2 = con.prepareStatement(query2);
            pstmt3 = con.prepareStatement(query3);

            pstmt3 = con.prepareStatement(query3);                   //menu_code가 null이므로 menu_code는 빼고 넣어줘야한다! null값 확인!
            pstmt3.setString(1, menuname);
            pstmt3.setInt(2, menuprice);
            pstmt3.setInt(3, categorycode2);
            pstmt3.setString(4, orderstatus);

            result3 = pstmt3.executeUpdate();

            if (result3 == 1){
                System.out.println("성공");
            } else{
                System.out.println("실패");
            }


            rset1 = pstmt1.executeQuery();

            if(rset1.next()) {
                result = rset1.getInt("MAX(MENU_CODE)");
            }
            System.out.println("최신 메뉴 코드 : " + result);

            rset2 = pstmt2.executeQuery();
            categoryList = new ArrayList<>();
            while(rset2.next()) {
                Map<Integer, String> category = new HashMap<>();
                category.put(rset2.getInt("CATEGORY_CODE"), rset2.getString("CATEGORY_NAME"));
                categoryList.add(category);
            }

            System.out.println("categoryList = " + categoryList);


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt1);
            close(pstmt2);
            close(pstmt3);
            close(rset1);
            close(rset2);
        }


    }
}
