/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package package_db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
@WebServlet("/ReservationBean")
public class ReservationBean extends HttpServlet {
     List<Menu> list = new ArrayList<>();
    // database connection settings
    private String dbURL = "jdbc:derby://localhost:1527/project_db";
    private String dbUser = "test";
    private String dbPass = "test";
    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
     
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        // gets values of text fields
        int nrofpeople = Integer.parseInt(request.getParameter("nrofpeople"));
        String email = request.getParameter("email");
        String date = request.getParameter("date");
        String phonenr = request.getParameter("phonenr");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String time = request.getParameter("time");
         
         
        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
         
        try {
            // connects to the database
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
 
            // constructs SQL statement
            String sql = "INSERT INTO reservation(nrofpeople,email,date,phonenr,fname,lname,time) values (?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, nrofpeople);
            statement.setString(2, email);   
            statement.setString(3, date);
            statement.setString(4, phonenr);
            statement.setString(5, fname);
            statement.setString(6, lname);
            statement.setString(7, time);

            // sends the statement to the database server
            int row = statement.executeUpdate();
            if (row > 0) {
                message = "File uploaded and saved into database";
            }
        } catch (SQLException ex) {
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                // closes the database connection
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
               
            }
           response.sendRedirect("index.xhtml");
        }
    }
}