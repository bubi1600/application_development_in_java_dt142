/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package package_db;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
@WebServlet("/EventBean")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class EventBean extends HttpServlet {
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
        String eventname = request.getParameter("eventname");
        String guestname = request.getParameter("guestname");
        String description = request.getParameter("description");
        String time = request.getParameter("time");
        String date = request.getParameter("date");
         
        Part filePart=request.getPart("picture");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

         
        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
         
        try {
            // connects to the database
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
 
            // constructs SQL statement
            String sql = "INSERT INTO event(eventname,guestname,description,time,date,picture) values (?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, eventname);   
            statement.setString(2, guestname);
            statement.setString(3, description);
            statement.setString(4, time);
            statement.setString(5, date);
            statement.setString(6,fileName);
 
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