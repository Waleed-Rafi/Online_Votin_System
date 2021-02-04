/*
    NAME: WALEED RAFI
    REG NO: 4006-BSCS-F18-A
    PROJECT: ONLINE VOTING SYSTEM
*/
package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Database d = new Database();//this object is used every where all the database related logics are encapsulated here.
        Boolean isSuccessfullyConnected = d.connect();//is project successfully connected with database.

        if(isSuccessfullyConnected){
            //------------------------------------- SETTING FRAME -------------------------------------
            JFrame frame = new JFrame();
            frame.setLayout(null);//currently i don't want any layout like grid layout or any other layout
            frame.setVisible(true); //is frame visible to the screen.
            frame.setResizable(false);//user are not able to change the size of frame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //by default when we click X button of frame it doesn't close the application (minimize) so we have to close
            frame.setSize(new Dimension(1000,700)); //setting size of frame
            frame.setTitle("VOTING SYSTEM"); //setting title of the frame which shows on the top not inside
            frame.getContentPane().setBackground(new Color(17,120,100)); //set the background color of content area
            frame.getContentPane().setForeground(Color.WHITE); //set the text or foreground of frame.

            //-------------------------------------- SETTING JPANELS HERE ---------------------------
            JPanel titlePanel = new JPanel();//look like a container
            titlePanel.setBackground(new Color(204, 209, 209 )); //setting background color of container
            titlePanel.setBounds(0,0,1000,70);//setting position and size of container

            //--------------------------------------- SETTING JLABEL FOR TITLE ------------------------------
            JLabel label = new JLabel(); //creating new instance
            label.setText("ONLINE VOTING SYSTEM"); //set title
            label.setForeground(new Color(52, 73, 94 ));
            label.setFont(new Font("Serif",Font.ITALIC,40)); //setting font,style,size of title
            titlePanel.add(label);//adding label to the container(panel)
            frame.add(titlePanel); //adding titlePanel or container to the frame

            //---------------------------------------- SETTING MENU ------------------------------------------
            JPanel menuPanel = new JPanel();//look like a container
            menuPanel.setBackground(new Color(26, 82, 118)); //setting background color of container;
            menuPanel.setBounds(250,150,500,50);//setting position and size of container

            JLabel menuLabel = new JLabel(); //creating new instance
            menuLabel.setText("MENU"); //set title
            menuLabel.setForeground(Color.WHITE);//setting the text color
            menuLabel.setFont(new Font("Serif",Font.ITALIC,30)); //setting font,style,size of title
            menuPanel.add(menuLabel);//adding label to the container(panel)
            frame.add(menuPanel); //adding titlePanel or container to the frame

            //Login Button in menu
            JButton loginBtn = new JButton("Login To Your Account"); //creating and giving text to button
            loginBtn.setBackground(new Color(84, 153, 199));//setting background color to button
            loginBtn.setForeground(new Color(214, 219, 223));//setting the text color
            loginBtn.setBounds(340,260,300,40); //setting position and size of button
            loginBtn.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
            loginBtn.setFocusPainted(false);//by default when we click any button a border will appear to prevent that border
            loginBtn.addActionListener(e -> {
                frame.dispose();//to close all previous open windows and then open a new window (not actually calls the constructor)
                Login login = new Login(d);
            });
            frame.add(loginBtn); //adding to the frame

            //Register Button in menu
            JButton registerBtn = new JButton("Register New Account");//creating and giving text to button
            registerBtn.setBackground(new Color(169, 50, 38));//setting background color to button
            registerBtn.setForeground(new Color(214, 219, 223));//setting the text color
            registerBtn.setBounds(340,330,300,40);//setting position and size of button
            registerBtn.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
            registerBtn.setFocusPainted(false);//by default when we click any button a border will appear to prevent that border
            registerBtn.addActionListener(e -> {
                frame.dispose();//to close all previous open windows and then open a new window (not actually calls the constructor)
                Register register = new Register(d);
            });
            frame.add(registerBtn);//adding to frame

            //Login as Admin button in menu
            JButton loginAdminBtn = new JButton("Login As Admin"); //creating and giving text to button
            loginAdminBtn.setBackground(new Color(118, 68, 138 ));//setting background color to button
            loginAdminBtn.setForeground(new Color(214, 219, 223));//setting the text color
            loginAdminBtn.setBounds(340,400,300,40); //setting position and size of button
            loginAdminBtn.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
            loginAdminBtn.setFocusPainted(false);//by default when we click any button a border will appear to prevent that border
            loginAdminBtn.addActionListener(e -> {
                frame.dispose();//to close all previous open windows and then open a new window (not actually calls the constructor)
                AdminLogin adminLogin = new AdminLogin(d);
            });
            frame.add(loginAdminBtn); //adding to the frame
        }else{
            JOptionPane.showMessageDialog(null,"Not able to connect with database run your application again");
        }
    }
}