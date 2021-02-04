package com.company;
import java.lang.ClassNotFoundException ;
import java.sql.*;
import java.util.Date;

//connecting java with mysql steps:
//1. Loading Driver (download zip file sql java connector from website extract them in any folder than in intellij press ctrl + alt + shift + S then under project settings select libraries than click on + sign than select the .jar file from where you extract that folder and press ok than load drivers)
//2. Establishing Connection
//3. Preparing Statements e.g queries
//4. Executing Statements
//5. Getting Results
//6. Closing Database Connection

public class Database {
    Connection connection = null;
    Boolean connect(){
        try {
            //loading drivers
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
            //establishing connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/VOTING_SYSTEM?autoReconnect=true&useSSL=false","root","#include<iostream>");//getConnection returns a connection object
            System.out.println("Connection Established");
            return true;
        }catch(ClassNotFoundException e){
            System.out.println("Error loading driver " + e.getMessage()); //exception may arise when Class not found in Class.forName while loading drivers
        }catch(SQLException e){
            System.out.println("SQL Exception " + e.getMessage());//exception may arise while Driver.getConnection
        }
        return false;
    }

    ResultSet runRetrievingQueries(PreparedStatement statement){
        try{
            //executing statements
            return statement.executeQuery();//.executeQuery method id used to retrieve the data from database returns an result with data
        }catch(SQLException e){
            System.out.println("SQL Exception " + e.getMessage());
        }
        return null;
    }

    Integer runModificationQueries(PreparedStatement statement){
        try{
            //executing statements
            return statement.executeUpdate();//.executeUpdate method is used to INSERT/UPDATE/DELETE returns an int -> number of rows effected
        }catch(SQLException e){
            System.out.println("SQL Exception " + e.getMessage());
        }
        return null;
    }

    Connection getConObject(){
        return connection;
    }


    Boolean isElectionStarted(){
        try{
            String sql = "SELECT * FROM Election ORDER BY e_id DESC";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet result = runRetrievingQueries(statement);
            while(result.next()){
                Timestamp starting_date = result.getTimestamp("starting_date");
                Timestamp ending_date = result.getTimestamp("ending_date");
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                int i = starting_date.compareTo(timestamp);
                int j = ending_date.compareTo(timestamp);
                if(i <= 0 && j >= 0) return true;
                break;
            }
        }catch(SQLException e){
            System.out.println("SQL EXCEPTION: " + e.getMessage());
        }
        return false;
    }

    void close(){
        //closing database connection
        try{
            connection.close();
            System.out.println("Connection Closed");
        }catch(SQLException e){
            System.out.println("SQL Exception " + e.getMessage());
        }
    }
}
