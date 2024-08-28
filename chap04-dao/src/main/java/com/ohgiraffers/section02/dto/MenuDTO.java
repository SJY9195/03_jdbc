package com.ohgiraffers.section02.dto;

public class MenuDTO {

    private String Name;

    private int price;

    private int categoryCode;

    private String status;

    public MenuDTO() {
    }

    public MenuDTO(String name, int price, int categoryCode, String status) {
        Name = name;
        this.price = price;
        this.categoryCode = categoryCode;
        this.status = status;
    }

    public MenuDTO menuName(String name){   //void가 아니므로 return 값을 넣어줘야 한다!!  //set 직접 넣어줌
        this.Name = name;
        return this;
    }

    public MenuDTO price(int price){  //set 직접 넣어줌
      if(price <= 0){
          System.out.println("음수가 입력됨..");
      }else{
       this.price = price;
      }
      return this;
    }

    public MenuDTO categoryCode(int code){  //set 직접 넣어줌
        this.categoryCode = code;
        return this;
    }

    public MenuDTO status(String status){  //set 직접 넣어줌
        if(status.equals("예") || status.equals("Y")){
            this.status = "Y";
        } else if (status.equals("아니오") || status.equals("N")){
            this.status = "N";
        }else {
            System.out.println("잘못 입력됨..");
        }
        return this;
    }

    public String getName() {
        return Name;
    }

    public int getPrice() {
        return price;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "Name='" + Name + '\'' +
                ", price=" + price +
                ", categoryCode=" + categoryCode +
                ", status='" + status + '\'' +
                '}';
    }
}
