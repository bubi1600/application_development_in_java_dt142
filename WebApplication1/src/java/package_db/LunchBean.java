/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package package_db;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
@WebServlet("/LunchBean")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class LunchBean extends HttpServlet {
     List<Lunch> list = new ArrayList<>();
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
        String dayid = request.getParameter("dayid");
        String lunchname = request.getParameter("lunchname");
        String description = request.getParameter("description");
        float price = Integer.parseInt(request.getParameter("price"));
        int time = Integer.parseInt(request.getParameter("time"));
     
        Part filePart=request.getPart("picture");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.

        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
         
        try {
            // connects to the database
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
 
            // constructs SQL statement
            String sql = "INSERT INTO lunch(dayid,lunchname,description,price,time,picture) values (?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, dayid);
            statement.setString(2, lunchname);
            statement.setString(3, description);
            statement.setFloat(4, price);
            statement.setInt(5, time); 
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