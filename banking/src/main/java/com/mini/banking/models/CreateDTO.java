package com.mini.banking.models;

public class CreateDTO {
    private String name;
    private String password;
    private String accountType;
  

    public CreateDTO(String name, String password, String accountType) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
    }

    public CreateDTO(){}

    public CreateDTO(String name , String password){
        this.name = name ;
        this.password= password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
 

    

}
