<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Musicmeta</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
<header>
<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: grey">
			
				<a href="https://www.xadmin.net" class="navbar-brand"> Song Application </a>
	
			
		</nav>
		</header>
		<br><br>
 <div class="container">
  <h3>Login</h3>
  <div class="card" >
   <div class="card-body">
<form action="authenticate">
 <c:if test="${failed !=null}">
					<label>Wrong Credentials.... Try again!</label>
				</c:if>
  <div class="form-group row">
      <label for="Email" class="col-sm-2 col-form-label">Email:</label>
      <div class="col-sm-7">
       <input type="email" class="form-control" name="usr_email"
        placeholder="Enter Email" required >
      </div>
     </div>
 <div class="form-group row">
      <label for="Password" class="col-sm-2 col-form-label">Password:</label>
      <div class="col-sm-7">
       <input type="password" class="form-control" name="usr_password"
        placeholder="Enter Password" required>
      </div>
     </div>
     <div align="center">
     <button type="submit" class="btn btn-primary">Login</button><br>
				<a href="register" class="navbar-brand" font-color="blue"> New User?Register </a></div>
   </form>
</div>
</div>
</div>
</body>
</html>
 
 
