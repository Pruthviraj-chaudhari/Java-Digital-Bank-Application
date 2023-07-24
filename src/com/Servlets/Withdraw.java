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
 * Servlet implementation class Withdraw
 */
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Withdraw() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

				if (oldBalance < amount) {
					response.sendRedirect("dashboard.jsp");
				} else {
					int newBalance = oldBalance - amount;

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

			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("error.html");
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
