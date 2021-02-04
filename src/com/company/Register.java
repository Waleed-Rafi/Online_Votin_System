package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern; //Patter class is used to specify regex pattern (validation)
import java.util.regex.Matcher; //Matcher class is used to match the string with above pattern weather matches or not

public class Register extends Auth {

    private JTextField cnicInput,first_name_Input,last_name_Input,phone_number_Input,emailInput,passwordInput;

    Register(Database d){
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
        menuPanel.setBounds(250,120,500,50);//setting position and size of container

        JLabel menuLabel = new JLabel(); //creating new instance
        menuLabel.setText("REGISTER"); //set title
        menuLabel.setForeground(Color.WHITE);//setting the text color
        menuLabel.setFont(new Font("Serif",Font.ITALIC,30)); //setting font,style,size of title
        menuPanel.add(menuLabel);//adding label to the container(panel)
        frame.add(menuPanel); //adding titlePanel or container to the frame

        cnicInput = new JTextField();
        cnicInput.setBackground(new Color(84, 153, 199));//setting background color to button
        cnicInput.setForeground(new Color(214, 219, 223));//setting the text color
        cnicInput.setBounds(340,210,300,40);//setting position and size of button
        cnicInput.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        cnicInput.setText("CNIC (without spaces)");
        cnicInput.setHorizontalAlignment(JTextField.CENTER);
        frame.add(cnicInput);

        first_name_Input = new JTextField();
        first_name_Input.setBackground(new Color(84, 153, 199));//setting background color to button
        first_name_Input.setForeground(new Color(214, 219, 223));//setting the text color
        first_name_Input.setBounds(340,270,300,40);//setting position and size of button
        first_name_Input.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        first_name_Input.setText("First Name");
        first_name_Input.setHorizontalAlignment(JTextField.CENTER);
        frame.add(first_name_Input);

        last_name_Input = new JTextField();
        last_name_Input.setBackground(new Color(84, 153, 199));//setting background color to button
        last_name_Input.setForeground(new Color(214, 219, 223));//setting the text color
        last_name_Input.setBounds(340,330,300,40);//setting position and size of button
        last_name_Input.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        last_name_Input.setText("Last Name");
        last_name_Input.setHorizontalAlignment(JTextField.CENTER);
        frame.add(last_name_Input);

        phone_number_Input = new JTextField();
        phone_number_Input.setBackground(new Color(84, 153, 199));//setting background color to button
        phone_number_Input.setForeground(new Color(214, 219, 223));//setting the text color
        phone_number_Input.setBounds(340,390,300,40);//setting position and size of button
        phone_number_Input.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        phone_number_Input.setText("Phone Number (with spaces)");
        phone_number_Input.setHorizontalAlignment(JTextField.CENTER);
        frame.add(phone_number_Input);

        emailInput = new JTextField();
        emailInput.setBackground(new Color(84, 153, 199));//setting background color to button
        emailInput.setForeground(new Color(214, 219, 223));//setting the text color
        emailInput.setBounds(340,450,300,40); //setting position and size of button
        emailInput.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        emailInput.setText("Email");
        emailInput.setHorizontalAlignment(JTextField.CENTER);
        frame.add(emailInput);

        passwordInput = new JTextField();
        passwordInput.setBackground(new Color(84, 153, 199));//setting background color to button
        passwordInput.setForeground(new Color(214, 219, 223));//setting the text color
        passwordInput.setBounds(340,510,300,40);//setting position and size of button
        passwordInput.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        passwordInput.setText("Password");
        passwordInput.setHorizontalAlignment(JTextField.CENTER);
        frame.add(passwordInput);

        JButton registerBtn = new JButton("REGISTER");
        registerBtn.setBackground(new Color(169, 50, 38 ));//setting background color to button
        registerBtn.setForeground(new Color(255, 255, 255));//setting the text color
        registerBtn.setBounds(340,570,300,40); //setting position and size of button
        registerBtn.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        registerBtn.setFocusPainted(false);//by default when we click any button a border will appear to prevent that border
        registerBtn.addActionListener(e -> this.checkRegister(d));
        frame.add(registerBtn);
    }

    private void checkRegister(Database d){
        Boolean isRegistered = this.register(d);
        if (isRegistered) {
            Login login = new Login(d);
        } else {
            JOptionPane.showMessageDialog(null,"Problem in registering your account try again");
        }
    }

    //REGEX is implemented to check email and mobile number pattern
    private Boolean isValidEmail(String email){
        Pattern myPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");//Allowed pattern A-Z, a-z,0-9, . , _ , -
        //here ^ is start of regex and $ is end of regex it's compulsory every thing will be written inside,
        //pattern will be inside [], + indicates that similar characters may repeat,
        Matcher myMatch = myPattern.matcher(email);
        Boolean isValid = myMatch.find(); //returns a boolean check weather pattern matches or not
        if(!isValid) {
            System.out.println("INVALID EMAIL TRY AGAIN");
            return false;
        }
        return true;
    }

    private Boolean isValidPkMobileNumber(String MobileNumber){
        Pattern myPattern = Pattern.compile("^[0][3][0-9]{2}[-][0-9]{7}$");//{2} means meaning and maximum is same 2 characters are must neither less nor more;
        Matcher myMatch2 = myPattern.matcher(MobileNumber);
        Boolean isValid = myMatch2.find(); //returns a boolean check weather pattern matches or not
        if(!isValid) {
            System.out.println("INVALID PHONE NUMBER TRY AGAIN");
            return false;
        }
        return true;
    }

    @Override
    Boolean register(Database d) {
        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t **************************** REGISTERING **********************");
        cnic = cnicInput.getText();
        String f_name = first_name_Input.getText();
        String l_name = last_name_Input.getText();
        Boolean isValid = false;
        String phone_number = phone_number_Input.getText();
        isValid = isValidPkMobileNumber(phone_number);
        if(!isValid) return false;
        isValid = false;
        String email = emailInput.getText();
        isValid = isValidEmail(email);
        if(!isValid) return false;
        password = passwordInput.getText();

        try {
            Connection con = d.getConObject();
            String sql = "INSERT INTO VOTERS(cnic,first_name,last_name,phone_number,email,v_password) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,cnic);
            statement.setString(2,f_name);
            statement.setString(3,l_name);
            statement.setString(4,phone_number);
            statement.setString(5,email);
            statement.setString(6,password);

            Integer r = d.runModificationQueries(statement);//runModificationQueries is encapsulated in Database class.
            return r == null ? false : true;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Problem in registering user run your application again");
        }
        return false;
    }
}
