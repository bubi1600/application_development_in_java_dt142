/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package package_db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
@WebServlet("/EmployeeBean")
public class EmployeeBean extends HttpServlet {

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
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String phonenr = request.getParameter("phonenr");

      
        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
         
        try {
            // connects to the database
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
 
            // constructs SQL statement
            String sql = "INSERT INTO employee(fname,lname,phonenr) values (?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, fname);   
            statement.setString(2, lname);
            statement.setString(3, phonenr);
 
 
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
           response.sendRedirect("admin.xhtml");
        }
    }
}
