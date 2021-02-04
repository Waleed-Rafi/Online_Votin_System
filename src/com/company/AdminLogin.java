package com.company;

import javax.swing.*;
import java.awt.*;

public class AdminLogin extends Auth {
    private JTextField emailInput,passwordInput;
    AdminLogin(Database d){
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
        menuLabel.setText("ADMIN LOGIN"); //set title
        menuLabel.setForeground(Color.WHITE);//setting the text color
        menuLabel.setFont(new Font("Serif",Font.ITALIC,30)); //setting font,style,size of title
        menuPanel.add(menuLabel);//adding label to the container(panel)
        frame.add(menuPanel); //adding titlePanel or container to the frame

        emailInput = new JTextField();
        emailInput.setBackground(new Color(84, 153, 199));//setting background color to button
        emailInput.setForeground(new Color(214, 219, 223));//setting the text color
        emailInput.setBounds(340,260,300,40); //setting position and size of button
        emailInput.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        emailInput.setText("Email");
        emailInput.setHorizontalAlignment(JTextField.CENTER);
        frame.add(emailInput);

        passwordInput = new JTextField();
        passwordInput.setBackground(new Color(84, 153, 199));//setting background color to button
        passwordInput.setForeground(new Color(214, 219, 223));//setting the text color
        passwordInput.setBounds(340,330,300,40);//setting position and size of button
        passwordInput.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        passwordInput.setText("password");
        passwordInput.setHorizontalAlignment(JTextField.CENTER);
        frame.add(passwordInput);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(118, 68, 138));//setting background color to button
        loginBtn.setForeground(new Color(255, 255, 255));//setting the text color
        loginBtn.setBounds(340,410,300,40); //setting position and size of button
        loginBtn.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        loginBtn.setFocusPainted(false);//by default when we click any button a border will appear to prevent that border
        loginBtn.addActionListener(e -> this.checkLogin(d,frame));
        frame.add(loginBtn);
    }

    void checkLogin(Database d,JFrame frame){
        Boolean isLoggedIn = this.adminLogin(d);
        System.out.println("isLoggedIn " + isLoggedIn);
        if(!isLoggedIn){
            int answer = JOptionPane.showConfirmDialog(null,"Invalid! Do you want to try again?","ERROR",JOptionPane.YES_NO_OPTION,JOptionPane.ERROR_MESSAGE); //yes return 1, no return 0 , cancel return -1;
            if(answer == 0){
                System.out.println("Invalid Try again");
            }else{
                System.exit(0);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Successfully LoggedIn","Dialogue",JOptionPane.INFORMATION_MESSAGE); //yes return 1, no return 0 , cancel return -1;
            frame.dispose();
            Admin admin = new Admin(d);
        }
    }
    @Override
    Boolean adminLogin(Database d){
        System.out.println("\t\t\t\t **************************** ADMIN **********************");
        String email = emailInput.getText();
        password = passwordInput.getText(); //password and input are data members of parent (Auth) class -> inherited
        if(email.equals("election.commision@gmail.com") && password.equals("pakistan123")){
            return true; //above hard-coded email is provided to election commision there is an alternative also where we can use isAdmin property to true of false in database while creating users which is more flexible but for now (shortage of time)
        }
        return false;
    }
}
