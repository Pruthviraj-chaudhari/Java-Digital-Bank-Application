package com.Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.DbConnection;

public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sql = "SELECT * FROM customers WHERE username = ? AND password = ?;";
        

        try {
            Connection conn = DbConnection.connect();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
               
            	int id = rs.getInt("id");
                String name = rs.getString("name");
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                String city = rs.getString("city");
                String mobile = rs.getString("mobile");
                
                PreparedStatement st1 = conn.prepareStatement("SELECT * FROM accounts WHERE cust_id = ?");
                st1.setInt(1, id);
              
                ResultSet rs1 = st1.executeQuery();
                
                if(rs1.next()){
                	User.setAccNo(rs1.getInt("accNo"));
                	User.setBalance(rs1.getInt("balance"));
                }
                		
                User.setName(name);
                User.setUsername(username1);
                User.setPassword(password1);
                User.setCity(city);
                User.setMobile(mobile);
               
                
                response.sendRedirect("dashboard.jsp");
                
            } else {
                response.sendRedirect("index.html");
            }

            rs.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., display error message or redirect to an error page)
            response.sendRedirect("error.html");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
