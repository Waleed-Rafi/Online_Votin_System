package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin {
    Scanner input = new Scanner(System.in); //to take the input from the user
    String sParty = ""; //storing at global because we have to use this later
    JTextField partyNameInput,startingDateInput,endingDateInput; //golbal because we want text inside textfields(by method)
    void selectedParty(String partyName){ //to assign the selected party (in radio button) to golbal variable
        sParty = partyName;
    }
    private void startElectionHandler(Database d,JFrame f){ //GUI component when start election button will pressed
        f.dispose(); //closing the current frame and opening a new frame
        //------------------------------------- JFRAME -------------------------------------
        JFrame frame = new JFrame();
        frame.setLayout(null);//currently i don't want any layout like grid layout or any other layout
        frame.setVisible(true); //is frame visible to the screen.
        frame.setResizable(false);//user are not able to change the size of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //by default when we click X button of frame it doesn't close the application (minimize) so we have to close
        frame.setSize(new Dimension(1000,700)); //setting size of frame
        frame.setTitle("VOTING SYSTEM"); //setting title of the frame which shows on the top not inside
        frame.getContentPane().setBackground(new Color(17,120,100)); //set the background color of content area
        frame.getContentPane().setForeground(Color.WHITE); //set the text or foreground of frame.

        //-------------------------------------- JPANEL ---------------------------
        JPanel titlePanel = new JPanel();//look like a container
        titlePanel.setBackground(new Color(204, 209, 209 )); //setting background color of container
        titlePanel.setBounds(0,0,1000,70);//setting position and size of container

        //--------------------------------------- JLABEL FOR TITLE ------------------------------
        JLabel label = new JLabel(); //creating new instance
        label.setText("ONLINE VOTING SYSTEM"); //set title
        label.setForeground(new Color(52, 73, 94 ));
        label.setFont(new Font("Serif",Font.ITALIC,40)); //setting font,style,size of title
        titlePanel.add(label);//adding label to the container(panel)
        frame.add(titlePanel); //adding titlePanel or container to the frame

        //---------------------------------------- MENU ------------------------------------------
        JPanel menuPanel = new JPanel();//look like a container
        menuPanel.setBackground(new Color(26, 82, 118)); //setting background color of container;
        menuPanel.setBounds(250,150,500,50);//setting position and size of container

        JLabel menuLabel = new JLabel(); //creating new instance
        menuLabel.setText("START ELECTION"); //set title
        menuLabel.setForeground(Color.WHITE);//setting the text color
        menuLabel.setFont(new Font("Serif",Font.ITALIC,30)); //setting font,style,size of title
        menuPanel.add(menuLabel);//adding label to the container(panel)
        frame.add(menuPanel); //adding menuPanel or container to the frame

        startingDateInput = new JTextField();
        startingDateInput.setBackground(new Color(84, 153, 199));//setting background color to button
        startingDateInput.setForeground(new Color(214, 219, 223));//setting the text color
        startingDateInput.setBounds(340,260,300,40); //setting position and size of button
        startingDateInput.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        startingDateInput.setText("Starting Date (Pattern 2021-01-21)");
        startingDateInput.setHorizontalAlignment(JTextField.CENTER);
        frame.add(startingDateInput);

        endingDateInput = new JTextField();
        endingDateInput.setBackground(new Color(84, 153, 199));//setting background color to button
        endingDateInput.setForeground(new Color(214, 219, 223));//setting the text color
        endingDateInput.setBounds(340,330,300,40);//setting position and size of button
        endingDateInput.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        endingDateInput.setText("Ending Date (Pattern 2021-01-27)");
        endingDateInput.setHorizontalAlignment(JTextField.CENTER);
        frame.add(endingDateInput);

        JButton startBtn = new JButton("Start");
        startBtn.setBackground(new Color(35, 155, 86 ));//setting background color to button
        startBtn.setForeground(new Color(255, 255, 255));//setting the text color
        startBtn.setBounds(340,400,300,40); //setting position and size of button
        startBtn.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        startBtn.setFocusPainted(false);//by default when we click any button a border will appear to prevent that border
        startBtn.addActionListener(e -> this.startElections(d));
        frame.add(startBtn);
    }
    private void publicResultHandler(Database d,JFrame f){
        f.dispose();
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
        menuLabel.setText("RESULTS"); //set title
        menuLabel.setForeground(Color.WHITE);//setting the text color
        menuLabel.setFont(new Font("Serif",Font.ITALIC,30)); //setting font,style,size of title
        menuPanel.add(menuLabel);//adding label to the container(panel)
        frame.add(menuPanel); //adding titlePanel or container to frame

        //Start New Elections Button in menu
        JPanel partyNamePanel = new JPanel(); //creating and giving text to button
        partyNamePanel.setBackground(new Color(84, 153, 199));//setting background color to button
        partyNamePanel.setBounds(200, 230, 300, 40); //setting position and size of button

        //Start New Elections Button in menu
        JPanel partyVotesPanel = new JPanel(); //creating and giving text to button
        partyVotesPanel.setBackground(new Color(84, 153, 199));//setting background color to button
        partyVotesPanel.setForeground(new Color(214, 219, 223));//setting the text color
        partyVotesPanel.setBounds(500, 230, 300, 40); //setting position and size of button

        JLabel partyNameLabel = new JLabel("Party Name");
        partyNameLabel.setForeground(new Color(214, 219, 223));
        partyNameLabel.setFont(new Font("SchoolHouse", Font.BOLD, 17));//setting font family, font weight,font size
        partyNamePanel.add(partyNameLabel);

        JLabel partyVotesLabel = new JLabel("Party Votes");
        partyVotesLabel.setForeground(new Color(214, 219, 223));
        partyVotesLabel.setFont(new Font("SchoolHouse", Font.BOLD, 17));//setting font family, font weight,font size
        partyVotesPanel.add(partyVotesLabel);

        frame.add(partyNamePanel);
        frame.add(partyVotesPanel);

        this.publishResult(d,frame);
    }
    private void removePartyHandler(Database d,JFrame f){
        f.dispose();
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
        menuLabel.setText("DELETE PARTY"); //set title
        menuLabel.setForeground(Color.WHITE);//setting the text color
        menuLabel.setFont(new Font("Serif",Font.ITALIC,30)); //setting font,style,size of title
        menuPanel.add(menuLabel);//adding label to the container(panel)
        frame.add(menuPanel); //adding titlePanel or container to frame

        ButtonGroup group = new ButtonGroup();
        JPanel p = new JPanel();
        p.setBounds(350,280,300,50);
        JRadioButton radioBtn = new JRadioButton();
        try {
            Connection con = d.getConObject();

            //preparing statements
            String sql = "Select * from parties";
            PreparedStatement statement = con.prepareStatement(sql);

            //getting results
            ResultSet result = d.runRetrievingQueries(statement);//this method is encapsulated in Database class
            int i = 0;

            while (result.next()) {
                String partyName = result.getString("p_name");
                radioBtn = new JRadioButton(partyName);
                radioBtn.addActionListener(e -> this.selectedParty(partyName));
                group.add(radioBtn);
                p.add(radioBtn);
            }
            frame.add(p);
        }catch (SQLException e){
            System.out.println("SQL Exception " + e.getMessage());
        }

        JButton voteBtn = new JButton("Delete");
        voteBtn.setBackground(new Color(176, 58, 46));
        voteBtn.setFont(new Font("Serif",Font.BOLD,25));
        voteBtn.setForeground(Color.WHITE);
        voteBtn.setBounds(400,365,200,45);
        voteBtn.setFocusPainted(false);
        voteBtn.addActionListener(e -> this.removeParty(d));
        frame.add(voteBtn);
    }
    private void registerNewPartyHandler(Database d,JFrame f){
        f.dispose();
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
        menuLabel.setText("REGISTER NEW PARTY"); //set title
        menuLabel.setForeground(Color.WHITE);//setting the text color
        menuLabel.setFont(new Font("Serif",Font.ITALIC,30)); //setting font,style,size of title
        menuPanel.add(menuLabel);//adding label to the container(panel)
        frame.add(menuPanel); //adding titlePanel or container to the frame

        partyNameInput = new JTextField();
        partyNameInput.setBackground(new Color(84, 153, 199));//setting background color to button
        partyNameInput.setForeground(new Color(214, 219, 223));//setting the text color
        partyNameInput.setBounds(340,260,300,40); //setting position and size of button
        partyNameInput.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        partyNameInput.setText("Email");
        partyNameInput.setHorizontalAlignment(JTextField.CENTER);
        frame.add(partyNameInput);

        JButton loginBtn = new JButton("Register");
        loginBtn.setBackground(new Color(118, 68, 138));//setting background color to button
        loginBtn.setForeground(new Color(255, 255, 255));//setting the text color
        loginBtn.setBounds(340,330,300,40); //setting position and size of button
        loginBtn.setFont(new Font("SchoolHouse",Font.BOLD,17));//setting font family, font weight,font size
        loginBtn.setFocusPainted(false);//by default when we click any button a border will appear to prevent that border
        loginBtn.addActionListener(e -> this.registerNewParty(d));
        frame.add(loginBtn);
    }

    Admin(Database d){
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
        menuLabel.setText("ADMIN FEATURES"); //set title
        menuLabel.setForeground(Color.WHITE);//setting the text color
        menuLabel.setFont(new Font("Serif",Font.ITALIC,30)); //setting font,style,size of title
        menuPanel.add(menuLabel);//adding label to the container(panel)
        frame.add(menuPanel); //adding titlePanel or container to the frame

        Boolean answer = d.isElectionStarted();
        if(!answer) {
            //Start New Elections Button in menu
            JButton startNewElectionsBtn = new JButton("Start New Elections"); //creating and giving text to button
            startNewElectionsBtn.setBackground(new Color(86, 101, 115));//setting background color to button
            startNewElectionsBtn.setForeground(new Color(214, 219, 223));//setting the text color
            startNewElectionsBtn.setBounds(340, 260, 300, 40); //setting position and size of button
            startNewElectionsBtn.setFont(new Font("SchoolHouse", Font.BOLD, 17));//setting font family, font weight,font size
            startNewElectionsBtn.setFocusPainted(false);//by default when we click any button a border will appear to prevent that border
            startNewElectionsBtn.addActionListener(e -> this.startElectionHandler(d,frame));
            frame.add(startNewElectionsBtn); //adding to the frame

            //Publish Results Button in menu
            JButton publishResultsBtn = new JButton("Publish Results");//creating and giving text to button
            publishResultsBtn.setBackground(new Color(214, 137, 16));//setting background color to button
            publishResultsBtn.setForeground(new Color(214, 219, 223));//setting the text color
            publishResultsBtn.setBounds(340, 330, 300, 40);//setting position and size of button
            publishResultsBtn.setFont(new Font("SchoolHouse", Font.BOLD, 17));//setting font family, font weight,font size
            publishResultsBtn.setFocusPainted(false);//by default when we click any button a border will appear to prevent that border
            publishResultsBtn.addActionListener(e -> this.publicResultHandler(d,frame));
            frame.add(publishResultsBtn);//adding to frame

            //Register New Party button in menu
            JButton registerNewPartyBtn = new JButton("Register New Party"); //creating and giving text to button
            registerNewPartyBtn.setBackground(new Color(84, 153, 199));//setting background color to button
            registerNewPartyBtn.setForeground(new Color(214, 219, 223));//setting the text color
            registerNewPartyBtn.setBounds(340, 400, 300, 40); //setting position and size of button
            registerNewPartyBtn.setFont(new Font("SchoolHouse", Font.BOLD, 17));//setting font family, font weight,font size
            registerNewPartyBtn.setFocusPainted(false);//by default when we click any button a border will appear to prevent that border
            registerNewPartyBtn.addActionListener(e -> this.registerNewPartyHandler(d,frame));
            frame.add(registerNewPartyBtn); //adding to the frame

            //Delete Party button in menu
            JButton deletePartyBtn = new JButton("Delete Party"); //creating and giving text to button
            deletePartyBtn.setBackground(new Color(203, 67, 53));//setting background color to button
            deletePartyBtn.setForeground(new Color(214, 219, 223));//setting the text color
            deletePartyBtn.setBounds(340, 470, 300, 40); //setting position and size of button
            deletePartyBtn.setFont(new Font("SchoolHouse", Font.BOLD, 17));//setting font family, font weight,font size
            deletePartyBtn.setFocusPainted(false);//by default when we click any button a border will appear to prevent that border
            deletePartyBtn.addActionListener(e -> this.removePartyHandler(d,frame));
            frame.add(deletePartyBtn); //adding to the frame
        }else{
            JLabel message = new JLabel("You are not allowed to make changes in ongoing elections");
            message.setFont(new Font("Serif",Font.PLAIN,30));
            message.setForeground(new Color(169, 50, 38));
            message.setBounds(150,300,800,50);
            frame.add(message);
        }
    }

    void startElections(Database d) {
        String starting_date = startingDateInput.getText() + " 00:00:00";
        String ending_date = endingDateInput.getText() + " 12:12:12";
        try {
            Connection con = d.getConObject();
            String sql = "INSERT INTO election(starting_date,ending_date) VALUES(?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, starting_date);
            statement.setString(2, ending_date);

            Integer result = d.runModificationQueries(statement);
            if (result == null) {
                JOptionPane.showMessageDialog(null, "Error in starting elections try again!");
            } else {
                JOptionPane.showMessageDialog(null, "Successfully Started!!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in starting elections try again!");

        }
    }
    void registerNewParty(Database d){
        String partyName = partyNameInput.getText();
        try {
            Connection con = d.getConObject();

            //preparing statements
            String sql = "INSERT INTO parties(p_name) VALUES(?);";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, partyName);

            //getting results
            Integer result = d.runModificationQueries(statement);
            if(result == null){
                JOptionPane.showMessageDialog(null,"Error in registering Try Again!!");
            }
            if(result == 1) {
                JOptionPane.showMessageDialog(null, "Successfully Registered");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error in registering Try Again!!");
        }
    }
    void removeParty(Database d){
        String partyName = sParty;
        try{
            Connection con = d.getConObject();

            //preparing statements
            String sql = "DELETE FROM parties WHERE p_name = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, partyName);

            //getting results
            Integer result = d.runModificationQueries(statement);
            JOptionPane.showMessageDialog(null,"Successfully Deleted!");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error in deleting party!");
        }
    }
    void publishResult(Database d,JFrame frame){
        try{
            Connection con = d.getConObject();

            //preparing statements
            String sql = "SELECT p_name , COUNT(*) as t_votes FROM votes INNER JOIN parties WHERE votes.p_id = parties.p_id GROUP BY(votes.p_id)";
            PreparedStatement statement = con.prepareStatement(sql);

            //getting results
            ResultSet result = d.runRetrievingQueries(statement);
            int i = 60;
            while(result.next()){
                //Start New Elections Button in menu
                JPanel partyNamePanel = new JPanel(); //creating and giving text to button
                partyNamePanel.setBackground(new Color(93, 109, 126));//setting background color to button
                partyNamePanel.setBounds(200, 230 + i, 300, 35); //setting position and size of button

                //Start New Elections Button in menu
                JPanel partyVotesPanel = new JPanel(); //creating and giving text to button
                partyVotesPanel.setBackground(new Color(93, 109, 126));//setting background color to button
                partyVotesPanel.setBounds(500, 230 + i, 300, 35); //setting position and size of button

                JLabel partyNameLabel = new JLabel(result.getString("p_name"));
                partyNameLabel.setForeground(new Color(214, 219, 223));
                partyNameLabel.setFont(new Font("SchoolHouse", Font.BOLD, 17));//setting font family, font weight,font size
                partyNamePanel.add(partyNameLabel);

                JLabel partyVotesLabel = new JLabel(result.getString("t_votes"));
                partyVotesLabel.setForeground(new Color(214, 219, 223));
                partyVotesLabel.setFont(new Font("SchoolHouse", Font.BOLD, 17));//setting font family, font weight,font size
                partyVotesPanel.add(partyVotesLabel);

                frame.add(partyNamePanel);
                frame.add(partyVotesPanel);
                i = i + 40;
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error in publishing Try Again!!");
        }
    }
}
