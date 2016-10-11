<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./header.jspf"%>
<!--  
<%@ include file="./navigation.jspf"%>
-->

<!--  
<div class="container">
<p><font color="red">${sessionScope.random}</font></p>
<p><font color="blue">Please guess the number between 1 and 10</font></p>
<p><font color="red">${errorMessage}</font></p>
    <form action="guess" method="POST">
        Your guess : <input name="guess" type="text" /> 
        <input type="submit" />
    </form>
</div>
-->

<div class="container">

<div class="alert alert-info">
	<h3>${errorMessage}</h3>
</div>
<div class="alert alert-danger">
	<h3>${steps}</h3>
</div>
<br/><br/>
<h2>Your guess:</h2> 
<br/>
	<form role="form" action="guess" method="POST">
		<div class="form-group">
			
			<input type="text"
				name="guess" class="form-control input-lg" id="inputlg"
				placeholder="Please enter a number between 1 and 10">
		</div>
		<button type="submit" class="btn btn-lg btn-primary">Guess</button>
	</form>
</div>

<br/>
<div class="alert alert-warning">
	<strong>The number is: </strong>${sessionScope.random}
</div>


<!-- 
<br/><br/>

<%@ include file="./footer.jspf"%>
-->