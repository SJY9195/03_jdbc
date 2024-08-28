package com.ohgiraffers.section01;

import java.sql.Connection;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Singleton {

    public static void main(String[] args) {

        // 싱글톤 객체 확인

        Connection con = getConnection();
        Connection con2 = getConnection();  // 출력되는건 주소값

        System.out.println(con);    // 두개가 값이 똑같이 출력되면된다! (싱글톤이기 때문에)
        System.out.println(con2);

        System.out.println("---------------------------");
        Connection con3 = getConnection2();
        Connection con4 = getConnection2();

        System.out.println(con3);      // 일반 객체로 만들면 싱글톤과 달리 주소값이 달라진다!
        System.out.println(con4);

    }

}
