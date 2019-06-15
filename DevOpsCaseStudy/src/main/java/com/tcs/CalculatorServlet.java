package com.tcs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculate")
public class CalculatorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int input_1 = Integer.parseInt(request.getParameter("number_1"));
		int input_2 = Integer.parseInt(request.getParameter("number_2"));
		String calculateValue = request.getParameter("action");

		Calculator calculator = new Calculator();

		if (calculateValue.equals("add")) {
			out.println(calculator.add(input_1, input_2));
		} else if (calculateValue.equals("sub")) {
			out.println(calculator.subtract(input_1, input_2));
		} else if (calculateValue.equals("mul")) {
			out.println(calculator.multiply(input_1, input_2));
		} else {
			out.println(calculator.divide(input_1, input_2));
		}
	}

}
