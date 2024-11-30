/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;

/**
 *
 * @author ACER
 */
public class JDBCConnection {
    private String dbUrl="jdbc:mySQL://localhost:3306/csdlnc";
    private String username="root";
    private String password="123456";
    private Connection con;
    
    // Intialize connection to database
    public boolean openConnection() {
		try {
			// Đăng ký MySQL Driver với DriverManager. Dùng khi code ở IDE NetBeans
			//DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			con = DriverManager.getConnection(dbUrl, username, password);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
    
    
    // Close connection
    public void closeConnection(){
        try{
            if(con!=null)
                con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    // 
    public Connection getConnection(){
        return con;
    }
}
