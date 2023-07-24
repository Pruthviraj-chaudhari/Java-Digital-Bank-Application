package com.Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.DbConnection;

/**
 * Servlet implementation class Signup
 */
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Signup() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String city = request.getParameter("city");
		String accType = request.getParameter("type");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int initialBalance = Integer.parseInt(request.getParameter("balance"));
		int customerId = 0;
		int accNo=0;
		
		try {
			Connection conn = DbConnection.connect();

			String qry1 = "INSERT INTO customers (name, username, password, city, mobile) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement st1 = conn.prepareStatement(qry1, Statement.RETURN_GENERATED_KEYS);
			st1.setString(1, name);
			st1.setString(2, username);
			st1.setString(3, password);
			st1.setString(4, city);
			st1.setString(5, mobile);

			int rowsAffected = st1.executeUpdate();

			if (rowsAffected > 0) {

				ResultSet keys = st1.getGeneratedKeys();

				if (keys.next()) {
					customerId = keys.getInt(1);
				}


				String qry2 = "INSERT INTO accounts (cust_id, accType, balance) VALUES (?, ?, ?)";
				PreparedStatement st2 = conn.prepareStatement(qry2, Statement.RETURN_GENERATED_KEYS);
				st2.setInt(1, customerId);
				st2.setString(2, accType);
				st2.setInt(3, initialBalance);
				int i = st2.executeUpdate();

				if (i == 1) {
					
					ResultSet keys1 = st2.getGeneratedKeys();
					
					if (keys1.next()) {
						accNo = keys.getInt(1);
					}
					
					System.out.println("\nAccount Created Successfully!");

					User.setAccNo(accNo);
					User.setAccType(accType);
					User.setBalance(initialBalance);
					User.setCity(city);
					User.setId(customerId);
					User.setMobile(mobile);
					User.setName(name);
					User.setPassword(password);
					User.setUsername(username);
					
					request.setAttribute("username", name); // send to JSP page
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
					dispatcher.forward(request, response);

				} else {
					response.sendRedirect("index.html");
				}
			} else {
				System.out.println("Failed to create account.");
			}

			
		} catch (SQLException e) {
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
