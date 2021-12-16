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
    
    <div class="header">
    <ul>
  <li><a href="/">WareHouse</a></li>
  <li><a href="stg">Storage Rooms</a></li>
  <li><a href="mystg">My Storage Rooms</a></li>
  <li><a href="boxes">Boxes</a></li>
  <li style="float:right">
  <form:form align="center" action="/logout">
  	<input class="button" type="submit" value="logout"/>
  </form:form>
  </li>
  </ul>
    </div>
    