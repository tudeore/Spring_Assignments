<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="deleteForm">
		<label>Enter account number<input type ="number" name="accountNumber"></label>
		<label><input type="submit" value="submit"></label>
	</form>
	
	<div>
		<jsp:include page="homeLink.jsp"></jsp:include>
	</div>	
</body>
</html>