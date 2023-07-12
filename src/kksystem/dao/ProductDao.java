/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kksystem.dao;

import kksystem.service.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import kksystem.controller.ProdectJFrame;

/**
 *
 * @author shini
 */
public class ProductDao {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rset = null;

    final String url = "jdbc:postgresql://localhost:5432/kaikei";
    final String user = "postgres";
    final String password = "postgres";

   
    public void insertProductInfo(Product p) {

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();

            String getSql = "SELECT NEXTVAL('seq_product_id')";
            rset = stmt.executeQuery(getSql);
            
            int seq = 0;
            while (rset.next()) {
                System.out.println(rset.getString(1));
                seq = Integer.valueOf(rset.getString(1));
                
            }
            
            LocalDateTime systemDate = LocalDateTime.now();
            

            String sql = "INSERT INTO product(product_id, product_name, price, quantity,purchases,delete_flag,create_date,update_date) \n"
                    + "VALUES (" + seq + ", '" + p.getProductname() + "', " + p.getPrice() + ", " + p.getQuantity() + "," + p.getQuantity() + " , '"+ 0 +"','"+systemDate+"' ,'"+systemDate+"')";

            System.out.println(sql);
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProdectJFrame.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
