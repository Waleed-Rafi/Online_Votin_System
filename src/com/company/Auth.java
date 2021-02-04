package com.company;
import java.util.Scanner;

public class Auth {
    public Scanner input = new Scanner(System.in); //because we don't want to re-create this in every class -> redundancy
    protected String cnic;
    protected String password;
    void display(){
        System.out.println("CNIC IS: " + cnic);
        System.out.println("PASSWORD IS: " + password);
    }
    String login(Database d){
        System.out.println("Login");
        return "null";
    }
    Boolean register(Database d){
        System.out.println("Register");
        return true;
    }
    Boolean adminLogin(Database d){
        System.out.println("Login");
        return true;
    }
}
