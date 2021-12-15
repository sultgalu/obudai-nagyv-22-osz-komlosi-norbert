<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
    	<meta charset="UTF-8">
    	<link rel="stylesheet" type="text/css" href="css/style.css">
        <title>View Books</title>
    </head>
    <body>
    
    <ul>
  <li><a href="stg">Storage Rooms</a></li>
  <li><a href="mystg">My Storage Rooms</a></li>
  <li><a href="boxes">Boxes</a></li>
</ul>
    
    <h2>My storage rooms</h2>
    <table>
    <tr>
    	<th>Id</th>
    	<th>Size</th>
    	<th>Owner</th>
    	<th>Is Free</th>
    	<th>Number of boxes</th>
    </tr>
    <c:forEach items="${srs}" var="sr">
    <tr>
    	<td>${sr.id}</td>
    	<td>${sr.size.x * sr.size.y} m2</td>
    	<td>${sr.owner != null ? sr.owner.username : ""}</td>
    	<td>${sr.isFree() ? "yes" : "no"}</td>
    	<td>${sr.boxes.size()}</td>
    </tr>
    </c:forEach>
    </table>
    <form action="/cancel_rent" method="post">
    	<label>The id of the room you want to stop renting:</label>
    	<input type="number" id="id" name="id"><br/>
    	<input type="submit" value="Rent">
    </form>
   
    
    </body>
</html>