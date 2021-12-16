<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
    <head>
    	<meta charset="UTF-8">
    	<link rel="stylesheet" type="text/css" href="css/style.css">
        <title>View Books</title>
    </head>
    <body> 
    
    <ul class="header">
  <li><a href="stg">Storage Rooms</a></li>
  <li><a href="mystg">My Storage Rooms</a></li>
  <li><a href="boxes">Boxes</a></li>
  <li>
  <form:form action="/logout">
  	<input type="submit" value="logout"/>
  </form:form>
  User: <sec:authentication property="name"/>
</li>
</ul>