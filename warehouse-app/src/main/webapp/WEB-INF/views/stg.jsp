<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="_header.jsp"/>
    <div class="content">
    <table style="margin-left: auto; margin-right: auto; margin-top: 30px;">
    <tr>
    	<th style="color: white">Storage rooms</th>
    </tr>
    <tr>
    <td>
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
    <td>
    </tr>
    </table>
    
    <table style="margin-left: auto; margin-right: auto; margin-top: 10px;">
    <tr>
    <th style="color: white">Storage room renting</th>
    </tr>
    <tr>
    <td>
		<form action="/rent" method="post">
    	<label>The id of the room you want to rent:</label>
    	<input type="number" id="id" name="id">
    	<input type="submit" value="Rent">
    </form>
	</td>
    </tr>
    </table >
     
    </div>
    

<jsp:include page="_footer.jsp"/>