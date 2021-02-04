package com.company;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Votes {
    Scanner input = new Scanner(System.in);
    private String sParty = "";
    void selectedParty(String partyName){
        sParty = partyName;
    }
    Votes(Database d,String user){
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
        menuLabel.setText("CAST YOUR VOTE"); //set title
        menuLabel.setForeground(Color.WHITE);//setting the text color
        menuLabel.setFont(new Font("Serif",Font.ITALIC,30)); //setting font,style,size of title
        menuPanel.add(menuLabel);//adding label to the container(panel)
        frame.add(menuPanel); //adding titlePanel or container to the frame

        Boolean answer = d.isElectionStarted();
        if(answer) {
            ButtonGroup group = new ButtonGroup();
            JPanel p = new JPanel();
            p.setBounds(350, 280, 300, 50);
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
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Not able to fetch data run your application again");
            }

            JButton voteBtn = new JButton("Cast Vote");
            voteBtn.setBackground(new Color(244, 208, 63));
            voteBtn.setFont(new Font("Serif", Font.BOLD, 25));
            voteBtn.setForeground(Color.darkGray);
            voteBtn.setBounds(400, 365, 200, 45);
            voteBtn.setFocusPainted(false);
            voteBtn.addActionListener(e -> this.castVote(d, user));
            frame.add(voteBtn);
        }else{
            JLabel message = new JLabel("Election is not started yet!");
            message.setFont(new Font("Serif",Font.PLAIN,30));
            message.setForeground(new Color(169, 50, 38));
            message.setBounds(300,300,500,50);
            frame.add(message);
        }
    }
    private Integer checkValidParty(String p_name,Database d){
        try {
            Connection con = d.getConObject();

            //preparing statements
            String sql = "Select * from parties WHERE p_name = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,p_name);
            //getting results
            ResultSet result = d.runRetrievingQueries(statement); //this method is encapsulated in Database class
            while(result.next()){
                return result.getInt("p_id"); //RETURNING THE id of party that you voted for
            }
        }catch (SQLException e){
            System.out.println("SQL Exception " + e.getMessage());
        }
        return -9999; //sentinal value
    }

    private Boolean isSuccessfullyCasted(int p_id,Database d,String cnic){
        try {
            Connection con = d.getConObject();
            //preparing statements
            String sql = "INSERT INTO votes(p_id,voter_cnic) VALUES(?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,p_id);
            statement.setString(2,cnic);

            //getting results
            Integer result = d.runModificationQueries(statement); //this method is encapsulated in Database class
            if(result == null) return false;
            return true;
        }catch (SQLException e){
            System.out.println("SQL Exception " + e.getMessage());
        }
        return false;
    }

    //GENERIC COLLECTIONS is implemented here to retrieve data from database and store data in memory (generic collections)
    void castVote(Database d,String user){
        List<String> items = new LinkedList(); //GENERIC COLLECTIONS
        try {
            Connection con = d.getConObject();

            //preparing statements
            String sql = "Select * from parties";
            PreparedStatement statement = con.prepareStatement(sql);

            //getting results
            ResultSet result = d.runRetrievingQueries(statement);//this method is encapsulated in Database class
            int i = 0;
            while(result.next()){
                items.add(result.getString("p_name")); //putting data in linklist of p_name column
            }
            String p_name = this.sParty;
            Integer validParty = checkValidParty(p_name,d);
            if(validParty != -9999){
                Boolean isSC = isSuccessfullyCasted(validParty,d,user);
                JOptionPane alert = new JOptionPane();
                if(!isSC) {
                    JOptionPane.showMessageDialog(null,"ERROR IN CASTING YOUR VOTE MAYBE YOUR ALREADY CASTED ELSE TRY AGAIN");
                }
                else {
                    JOptionPane.showMessageDialog(null,"YOUR VOTE SUCCESSFULLY CASTED THANKS!");
                }
            }else{
                JOptionPane.showMessageDialog(null,"INVALID PARTY");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Not able to cast your vote run your application again");
        }
    }
}
