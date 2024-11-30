/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.DriverManager;
import java.sql.Connection;


/**
 *
 * @author ACER
 */
public class DatabaseConnection {
    private static final String dbUrl = "";
    private static final String username = "";
    private static final String password = "";
    Connection con;
    
    public boolean openConnection(){
        try{
            con = DriverManager.getConnection(dbUrl, username, password);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public void closeConnection(){
        try{
            if(con!=null)
                con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
                
    }
}   


/*
Connection: Là một interface trong gói java.sql, được sử dụng để thiết lập và quản lý kết nối đến cơ sở dữ liệu. Nó cung cấp các phương thức để tạo, quản lý các truy vấn SQL, cũng như kiểm soát giao dịch (transaction).
*/