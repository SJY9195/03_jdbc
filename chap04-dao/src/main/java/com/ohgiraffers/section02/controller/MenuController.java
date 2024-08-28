package com.ohgiraffers.section02.controller;

import com.ohgiraffers.section02.dao.MenuDAO;
import com.ohgiraffers.section02.dto.MenuDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection2;


public class MenuController {

    private MenuDAO menuDAO = new MenuDAO("src/main/resources/mapper/menu-query.xml");

    public void findMaxCode(){

        int result = menuDAO.selectLastMenuCode(getConnection2());   // 싱글톤이 x 연속해서 안쓰므로 // 여러명이 쓰게하려고 싱글톤을 안쓴다!!
        System.out.println("최신 메뉴 코드 : " + result);

    }

    public void showCategorycode(){

        List result2 = menuDAO.selectAllCategory(getConnection2());  // selectAllCategory 메소드가 리스트 이므로 리스트로 받아야한다!
        System.out.println(result2);

    }

    public void insertMenu() {
        Scanner scr = new Scanner(System.in);

        MenuDTO menuDTO = new MenuDTO();

        System.out.println("메뉴 이름을 입력 해주세요");
        menuDTO.menuName(scr.nextLine());
        System.out.println("메뉴 가격을 입력 해주세요");
        menuDTO.price(scr.nextInt());
        System.out.println("카테고리 번호를 입력 해주세요");
        menuDTO.categoryCode(scr.nextInt());
        System.out.println("판매 여부를 등록 해주세요");
        scr.nextLine();  // 엔터까지 생각해서 scr.nextLine()을 써준다. 안치면 밑에 거에 엔터가 들어간다.
        menuDTO.status(scr.nextLine());

        int result = menuDAO.insertMenu(getConnection2(), menuDTO);

        if(result > 0){
            System.out.println("메뉴 등록 완료");
        }else{
            System.out.println("메뉴 등록 실패");
        }

    }

}

0