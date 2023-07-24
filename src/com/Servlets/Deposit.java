package com.Servlets;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.DB.DbConnection;

public class Deposit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Deposit() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	
            Connection conn = DbConnection.connect();
            String sql = "SELECT * FROM accounts WHERE accNo = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, User.getAccNo());

            ResultSet rs = st.executeQuery();
            int amount = Integer.parseInt(request.getParameter("amount"));

            if (rs.next()) {
                // Update balance
                int oldBalance = User.getBalance();
                int newBalance = oldBalance + amount;

                String updateSql = "UPDATE accounts SET balance = ? WHERE accNo = ?";
                PreparedStatement st1 = conn.prepareStatement(updateSql);
                st1.setInt(1, newBalance);
                st1.setInt(2, User.getAccNo());
                
                int i = st1.executeUpdate();

                if (i == 1) {
                    response.sendRedirect("dashboard.jsp");
                    User.setBalance(newBalance);
                } else {
                    response.sendRedirect("index.html");
                }

            } 
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
