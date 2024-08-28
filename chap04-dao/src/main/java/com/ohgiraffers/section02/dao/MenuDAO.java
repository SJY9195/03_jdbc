package com.ohgiraffers.section02.dao;

import com.ohgiraffers.section02.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class MenuDAO {
// 데이터 엑세스 오브젝트 - 데이터베이스와 상호작용을 할 클래스  //DTO와 차이 : 직접 데이터베이스와 연결되서 상호작용, DTO는 데이터베이스에서 옮긴걸 가져와서 쓰는 것.


    private Properties prop = new Properties();

    public MenuDAO(String url) { // MenuDAO 생성자의 역할 : 객체 생성할 때 String url을 넣어달라는 뜻!!
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public int selectLastMenuCode(Connection con) {   // 여기선 싱글톤을 쓰면 동시성 문제가 난다!
        Statement stmt = null;
        ResultSet rset = null;
        int maxCode = 0;

        String query = prop.getProperty("selectLastMenuCode");


        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if (rset.next()) {
                maxCode = rset.getInt("MAX(MENU_CODE)");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(stmt);
            close(rset);

        }

        return maxCode;
    }

    public List<Map<Integer, String>> selectAllCategory(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<Map<Integer, String>> categoryList = null;

        try {
            pstmt = con.prepareStatement(prop.getProperty("selectAllCategoryList")); // key 값을 입력하여 query문을 실행시켜주는 명령문!!
            rset = pstmt.executeQuery();
            categoryList = new ArrayList<>();
            while (rset.next()) {
                Map<Integer, String> category = new HashMap<>();
                category.put(rset.getInt("CATEGORY_CODE"), rset.getString("CATEGORY_NAME"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {              // 혹시 어떤 오류가 날지 모르고, 메모리가 낭비되므로 닫아주는게 좋다!!!
            close(con);
            close(pstmt);
            close(rset);
        }
    return categoryList;
    }

    public int insertMenu(Connection con, MenuDTO menuDTO){
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("insertMenu");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, menuDTO.getName());
            pstmt.setInt(2, menuDTO.getPrice());
            pstmt.setInt(3, menuDTO.getCategoryCode());
            pstmt.setString(4, menuDTO.getStatus());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("잘못된 값이 입력됨...");
        } finally {
            close(con);
            close(pstmt);
        }
        return result;
    }
}
