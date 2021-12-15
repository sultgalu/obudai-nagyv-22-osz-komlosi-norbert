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
    
    <h2>My boxes</h2>
    
    <table>
    <tr>
    	<th>Id</th>
    	<th>Storage room id</th>
    	<th>Size</th>
    	<th>Materials</th>
    	<th>Categories</th>
    </tr>
    <c:forEach items="${boxes}" var="box">
    <tr>
    	<td>${box.id}</td>
    	<td>${box.getStorageRoom() != null ? box.getStorageRoom().getId() : ""}</td>
    	<td>${box.size.x * box.size.y} m2</td>
    	<td></td>
    	<td></td>
    	<td>
    		<form action="/removebox" method="post">
    			<input type="submit" value="Remove box">
    		</form>
    	</td>
    </tr>
    </c:forEach>
    </table>
     <form action="/newbox" method="post">
    	<label>Storage room id</label>
    	<input type="number" id="storageRoomId" name="storageRoomId"><br/>
    	<label>Size</label>
    	<input type="text" id="size" name="size" placeholder="1x1"><br/>
    	<input type="submit" value="Save">
    </form>
    
    </body>
</html>