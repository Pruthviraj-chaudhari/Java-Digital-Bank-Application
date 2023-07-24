package com.Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.DbConnection;

/**
 * Servlet implementation class MoneyTransfer
 */
public class MoneyTransfer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoneyTransfer() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String recName = request.getParameter("recipientName");
        int recAccount = Integer.parseInt(request.getParameter("recipientAccount"));
        int amount = Integer.parseInt(request.getParameter("amount"));

        try (Connection conn = DbConnection.connect()) {

            // Check if recipient account is present or not
            String recipientCheckQuery = "SELECT * FROM accounts WHERE accNo = ?";
            try (PreparedStatement recipientCheckStmt = conn.prepareStatement(recipientCheckQuery)) {
                recipientCheckStmt.setInt(1, recAccount);
                try (ResultSet recipientCheckResult = recipientCheckStmt.executeQuery()) {
                    if (recipientCheckResult.next()) {
                        int senderAccNo = User.getAccNo();

                        // Get sender balance
                        int senderBalance = 0;
                        String senderBalanceQuery = "SELECT balance FROM accounts WHERE accNo = ?";
                        try (PreparedStatement senderBalanceStmt = conn.prepareStatement(senderBalanceQuery)) {
                            senderBalanceStmt.setInt(1, senderAccNo);
                            try (ResultSet senderBalanceResult = senderBalanceStmt.executeQuery()) {
                                if (senderBalanceResult.next()) {
                                    senderBalance = senderBalanceResult.getInt("balance");
                                }
                            }
                        }

                        if (senderBalance < amount) {
                            System.out.println("\nInsufficient Account Balance !");
                            response.sendRedirect("viewbalance.jsp");
                        } else {
                            // Calculate new balances
                            int senderNewBalance = senderBalance - amount;
                            User.setBalance(senderNewBalance);
                            int receiverNewBalance = recipientCheckResult.getInt("balance") + amount;
                            
                           
                            // Update sender balance
                            String senderUpdateQuery = "UPDATE accounts SET balance = ? WHERE accNo = ?";
                            try (PreparedStatement senderUpdateStmt = conn.prepareStatement(senderUpdateQuery)) {
                                senderUpdateStmt.setInt(1, senderNewBalance);
                                senderUpdateStmt.setInt(2, senderAccNo);
                                senderUpdateStmt.executeUpdate();
                            }

                            // Update receiver balance
                            String receiverUpdateQuery = "UPDATE accounts SET balance = ? WHERE accNo = ?";
                            try (PreparedStatement receiverUpdateStmt = conn.prepareStatement(receiverUpdateQuery)) {
                                receiverUpdateStmt.setInt(1, receiverNewBalance);
                                receiverUpdateStmt.setInt(2, recAccount);
                                receiverUpdateStmt.executeUpdate();
                            }

                            String receiverName = null;
                            int receiverCustId = recipientCheckResult.getInt("cust_id");
                            String receiverNameQuery = "SELECT name FROM customers WHERE id = ?";
                            try (PreparedStatement receiverNameStmt = conn.prepareStatement(receiverNameQuery)) {
                                receiverNameStmt.setInt(1, receiverCustId);
                                try (ResultSet receiverNameResult = receiverNameStmt.executeQuery()) {
                                    if (receiverNameResult.next()) {
                                        receiverName = receiverNameResult.getString("name");
                                    }
                                }
                            }

                            System.out.println("\nMoney Successfully Transferred to " + receiverName + ".\n");
                            response.sendRedirect("viewbalance.jsp");
                        }
                    } else {
                        response.sendRedirect("dashboard.jsp");
                    }
                }
            }

        } catch (SQLException e) {
            // Handle any SQL errors here
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
