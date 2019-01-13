<%@  page isELIgnored="false" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="updateAccountOne">
	<table>
		<tr>
			<th>Account Number</th>
			<th><a href="sortByName.mm">Holder Name</a></th>
			<th>Account Balance</th>
			<th>Salary</th>
			<th>Over Draft Limit</th>
			<th>Type Of Account</th>
		</tr>
		<jstl:if test="${account!=null}">
			<tr>
				<td><input type="text" name ="accountNumber" required="required" value="${account.bankAccount.accountHolderName}" readonly/></td>
				<td><input type="text" name="newName" value="${account.bankAccount.accountHolderName}"/></td>
				<td><input type="text" name="accountBalance" value="${account.bankAccount.accountBalance}" readonly/></td>
				<td>
					<select name="newSalaried">
					<option selected>${account.salary==true?"Yes":"No"}</option>
					<option selected>${account.salary==true?"No":"Yes"}</option>
					</select>
				</td>
				<td>${"N/A"}</td>
				<td><input type="text" name="accountType" value= "Savings" readonly></td>
			</tr>
				<input type ="submit" value="update">
		</jstl:if>
		<jstl:if test="${accounts!=null}">
			<jstl:forEach var="account" items="${accounts}">
				<tr>
					<td>${account.bankAccount.accountNumber}</td>
					<td>${account.bankAccount.accountHolderName }</td>
					<td>${account.bankAccount.accountBalance}</td>
					<td>${account.salary==true?"Yes":"No"}</td>
					<td>${"N/A"}</td>
					<td>${"Savings"}</td>
				</tr>
					
			</jstl:forEach>
		</jstl:if>
	</table>
	<%-- <div>
		<jsp:include page="home.jsp"></jsp:include>
	</div> --%>
</form>
</body>
</html>