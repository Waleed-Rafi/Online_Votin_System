package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends Auth {
    private JTextField cnicInput,passwordInput;
    Login(Database d){
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
        menuLabel.setText("LOGIN"); //set title
        menuLabel.setForeground(Color.WHITE);//setting the text color
        menuLabel.setFont(new Font("Serif",Font.ITALIC,30)); //setting font,style,size of title
        menuPanel.add(menuLabel);//adding label to the container(panel)
        frame.add(menuPanel); //adding titlePanel or container to the frame

        cnicInput = new JTextField();
        cnicInput.setBackground(new Color(84, 153, 199));//setting background color to button
        cnicInput.setForeground(new Color(214, 219, 223));//setting the text color
        cnicInput.setBounds(340,260,300,40); //setting position and size of button
        cnicInput.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        cnicInput.setText("CNIC (no dashes)");
        cnicInput.setHorizontalAlignment(JTextField.CENTER);
        frame.add(cnicInput);

        passwordInput = new JTextField();
        passwordInput.setBackground(new Color(84, 153, 199));//setting background color to button
        passwordInput.setForeground(new Color(214, 219, 223));//setting the text color
        passwordInput.setBounds(340,330,300,40);//setting position and size of button
        passwordInput.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        passwordInput.setText("password");
        passwordInput.setHorizontalAlignment(JTextField.CENTER);
        frame.add(passwordInput);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(35, 155, 86 ));//setting background color to button
        loginBtn.setForeground(new Color(255, 255, 255));//setting the text color
        loginBtn.setBounds(340,400,300,40); //setting position and size of button
        loginBtn.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        loginBtn.setFocusPainted(false);//by default when we click any button a border will appear to prevent that border
        loginBtn.addActionListener(e -> this.checkLogin(d,frame));
        frame.add(loginBtn);
    }

    void checkLogin(Database d,JFrame frame){
        String isLoggedIn = this.login(d);
        if(isLoggedIn == "null"){
            int answer = JOptionPane.showConfirmDialog(null,"Invalid! Do you want to register new account?","ERROR",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE); //yes return 1, no return 0 , cancel return -1;
            if(answer == 0){
                frame.dispose(); // to hide all other screens
                Register register = new Register(d); //register screen will appear
            }else if(answer == 2){
                System.exit(0);
            }else{
                System.out.println("Invalid Credentials");
            }
        }else{
            System.out.println("Successfully LoggedIn");
            Votes votes = new Votes(d,isLoggedIn);
        }
    }

    @Override
    String login(Database d) {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t **************************** LOGIN **********************");
        cnic = cnicInput.getText(); //cnic and input are data members of parent (Auth) class -> inherited
        password = passwordInput.getText(); //password and input are data members of parent (Auth) class -> inherited

        try {
            Connection con = d.getConObject();

            //preparing statements
            String sql = "Select * from VOTERS WHERE cnic = ? AND v_password = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,cnic);
            statement.setString(2,password);

            //getting results
            ResultSet result = d.runRetrievingQueries(statement);//run retrievingQueries method is encapsulated in Database class.
            while(result.next()){
                return result.getString("cnic"); //get the whole column named as cnic
            }
            if(!result.next() || result == null) return "null"; //return null if we get no results

        }catch (SQLException e){
            System.out.println("SQL Exception " + e.getMessage());
        }
        return "null"; //in case of any exception
    }
}
