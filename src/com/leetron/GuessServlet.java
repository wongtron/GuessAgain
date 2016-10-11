package com.leetron;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GuessServlet
 */

@WebServlet("/guess")
public class GuessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuessServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		HttpSession session = request.getSession();
		ArrayList<String> list = new ArrayList<String>();
		session.setAttribute("gamehistory", list);
		String gametype = request.getParameter("gametype");
		session.setAttribute("gametype", gametype);
		session.setAttribute("numberofguesses", 0);

		// Create a random number between 1 and 10
		Random rand = new Random();

		int random = rand.nextInt(10) + 1;

		// write the number into session
		session.setAttribute("random", random);
		
		//request.setAttribute("errorMessage", "Welcome to the Guess Number Game!");

		// Forward to the JSP view page
		request.getRequestDispatcher("Guess.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// restore the session variables
		HttpSession session = request.getSession();
		ArrayList<String> list = (ArrayList<String>) session.getAttribute("gamehistory");
		int random = (int) session.getAttribute("random");
		int numberOfGuesses = (int) session.getAttribute("numberofguesses");
		String gametype = (String) session.getAttribute("gametype");

		int guessedNumber = -1;

		// get the guess from the user
		String guess = request.getParameter("guess");

		// clear the message first
		String errorMessage = "";
		request.setAttribute("errorMessage", errorMessage);

		// TODO: consider the error that the user input is not a number
		// shall we make the check here or using a Javascript to do the check
		try {
			guessedNumber = Integer.parseInt(guess);
			if ((guessedNumber < 1) || (guessedNumber > 10)) {
				errorMessage = "Please enter a number between 1 and 10!";
			}
		} catch (NumberFormatException e) {

			errorMessage = "Please enter a number between 1 and 10!";
		}

		if (!errorMessage.equals("")) {
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("steps", "Your guesses are: " +list.toString());
			request.getRequestDispatcher("Guess.jsp").forward(request, response);
			return;
		}

		System.out.println(guessedNumber);
		System.out.println(random);

		numberOfGuesses++; // increase the number of guesses
		session.setAttribute("numberofguesses", numberOfGuesses);  // memorize in session
		list.add(String.valueOf(guessedNumber));

		if (guessedNumber == random) {
			// the number is guessed correctly
			errorMessage = "Your guess is correct. ";
			if (gametype.equals("Limit")) {
				if (numberOfGuesses == 1) {
					// guessed correct the first time
					errorMessage += "Excellent job. ";
				} else if (numberOfGuesses == 2) {
					// took two guesses
					errorMessage += "Nice job. ";
				}
			}

			errorMessage += "Congratulations. ";

			errorMessage += "Please start a new game";

			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("steps", 
					"The number was " + guessedNumber +
					". Your guesses were: " + list.toString());
			// request if the user wants to start a new game
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		if (guessedNumber < random) {
			errorMessage = "Your guess is too low. Make your guess larger!";
		}

		if (guessedNumber > random) {
			errorMessage = "Your guess is too high. Make your guess larger!";
		}
		
		if (gametype.equals("Limit") && numberOfGuesses >= 3) {
			// game over for the LIMIT game. Tell the user, and allow the user
			// to select
			// a new game
			errorMessage = "You have made three guesses already without finding the correct number. Please start a new game!";
			request.setAttribute("errorMessage", errorMessage);
			request.setAttribute("steps", 
					"The number was " + random +
					". Your guesses were: " + list.toString());
			// request if the user wants to start a new game
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		request.setAttribute("errorMessage", errorMessage);
		String stepMessage =  "Your guesses are: " + list.toString();
		if (gametype.equals("Limit")) {
			stepMessage += ".  Remaining number of guesses: " + (3-numberOfGuesses);
		}
		request.setAttribute("steps", stepMessage);
		// send to the JSP view page
		request.getRequestDispatcher("Guess.jsp").forward(request, response);

	} // doPost()
}
