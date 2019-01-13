<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="fundTransferDone">
	<label>Enter Sender Account Number: </label>
	<input type="number" name="accountNumberOne" />
	<br />
	<label>Enter Receiver Account Number: </label>
	<input type="number" name="accountNumberTwo" />
	<br />
	<label>Amount : </label>
	<input type="number" name="amount" />
	<br />
	<input type="submit" value="Submit" />
	<input type="reset" value="Clear" />
	</form>
</body>
</html>