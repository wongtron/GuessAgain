# GuessAgain

In summer 2016, I took a 3-month "Android App and Java Development" training at the Chinese Culture University in Taipei, and this class was sponsored by the Labor Department of the Taiwan government.

One class I took is to develop web applications. On the client side we learned about web page design with HTML, CSS, and Bootstrap.  On  server side we learned about server side programming with Java Servlet and JSP.

Teacher Hank Tom assigned us a homework to develop a web application to do the following:

   * Create a random number between 1 and 10.  Allow the user to guess the number either with unlimited number of guesses, or to limit the number of guesses to three times.
   
   
The code to implement this web application is included in this repository.

Even though this web application is simple, but I think it is very useful for the pedagogical purpose.

One important lesson I learned from this assignment is to use the "Session" to memorize the number to guess which is created randomly for each new game.

The game will consist of multiple HTTP request/response exchanges between the browser (client) and server (servlet).  Each request/response is independent of other request/response exchanges.  This is the "stateless" design of the HTTP protocol.

Also the game has to support multiple users at any time.

So for this game, the random number to be guessed, and user guess history, will have to be memorized in some way.  

There are multiples ways to do this.  The two ways that I know of is to keep the "state variables" in a "session", or in the "cookie".

Even though each HTTP request/response exchanged between the browser/server is independent, but as long as the browser is not exited, the entire HTTP request/response exchanges form a "session".

On the server side, the servlet can take advantage of this and keep the state variables in the Session.  The number to be guessed is kept in a session.  When the browser sends each newly guessed number to the server, the server can compare this number against the number to be guessed in the session.

You may wonder why we do not keep the number to be guessed in a global variable on the server side so we do not have to worry about this Session deal.  The problem is that the server has to support multiple users, so one global variable is not enough.  As each user has a separate Session, so it is best to keep the number in the Session.

Also if the user makes multiple guesses in a game, and suppose we want to keep the history of the user guesses so we can show it to the user, then it is best to use the Session to keep the user guess history.

Another way to keep the state variables is to use the "cookie".  My understanding is that the cookie is maintained on the browser side.  When necessary, the server requests the browser to send over the cookie.

In this particular implementation, I used the Session to keep the state variables.  When there is the chance, I will try the cookie approach the next time.

Another design approach worth mentioning is described below. In the servlet class there are the "doGet()" and "doPost()" methods.  At the start of each game, the doGet() method will create the random number to be guessed, and then write this number into the session.  Each subsequent user guess will invoke the doPost() method.  Only after a user correctly guesses a number, or when the number of guess limit is reached, so a new game starts, then the program flow goes back to the doGet() method to create a new random number for the new game.

I took the above approach since the program logic was not clear to me when I first started working on this application. It was not clear to me how to design the web page/server programming logic such that I know a games ends and a new game starts so I have to recreate the random number.  After some experimentation, the current design now consists of a home web page (index.jsp) and a game web page (guess.jsp).  On the home web page the user selects the type of game to play by clicking on a button, which causes a GET request to send to the servlet, and the doGet() method will handle this request.  The doGet() method creates a new random number, and write this number into the session, and then presents the game web page (guess.jsp) to the browser.  

On the game web page (guess.jsp) the user makes the guess, and the number is sent to the servlet as a  POST request. The servlet doPost() method will then handle the POST request.  If the guess is wrong, the game web page (guess.jsp) is sent back to the browser to ask the user to guess again.  If the guess is correct, the home web page (index.jsp) is sent back to the browser to inform the user is guess is correct, and ask the user to start a new game.

Another note is that I implemented the web pages with Bootstrap to support responsive design.  The game was tested on a couple of Android mobile phones.  Even though the game is currently playable on a mobile phone, the UI design has to be carefully re-examined since there is still some scrolling up and down for such a simple game, and the user experience (UX) can be improved.



