<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./header.jspf"%>
<!--  
<%@ include file="./navigation.jspf"%>
-->

<div class="container">

	<div class="alert alert-info">
		<h3>${errorMessage}</h3>
	</div>
	<div class="alert alert-danger">
		<h3>${steps}</h3>
	</div>
 <br/><br/>
	<h2>Select a game:</h2>
	<br/>
	
<!-- 
	<a class="btn btn-lg btn-danger" href="guess?gametype=Regular">Regular
		game</a>
	<h3>You can make unlimited number of guesses.</h3>
	<a class="btn btn-lg btn-danger" href="guess?gametype=Limit">Limit
		to three guesses</a>
	<h3>You can only make three guesses per game.</h3>
-->

<table class="table">

	<tbody>
		<tr class="success">

			<td><a class="btn btn-lg btn-danger"
				href="guess?gametype=Regular">Regular game</a></td>
			<td><h3>You can make unlimited number of guesses</h3></td>
		</tr>
		<tr class="danger">

			<td><a class="btn btn-lg btn-danger" href="guess?gametype=Limit">Limit
					to three guesses</a></td>
			<td><h3>You can only make three guesses per game</h3></td>
		</tr>

	</tbody>
</table>

</div>


<!-- 
<br/><br/>

<%@ include file="./footer.jspf"%>
-->