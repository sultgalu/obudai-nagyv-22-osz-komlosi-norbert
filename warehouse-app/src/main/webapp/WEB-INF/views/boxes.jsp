<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="_header.jsp"/>
    
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
    	<td>
   		<c:forEach items="${box.getMaterials()}" var="mat">
   			<span>${mat}</span>
   		</c:forEach>
    	</td>
    	<td>
    	<c:forEach items="${box.getCategories()}" var="cat">
   			<span>${cat}</span>
   		</c:forEach>
    	</td>
    	<td>
    		<form action="/remove_box/${box.id}" method="get">
    			<input type="submit" value="Remove box">
    		</form>
    	</td>
    </tr>
    </c:forEach>
    </table>
    
    <form:form modelAttribute="box" action="/newbox">
    <form:label path="storageRoomId">Storage room id</form:label>
    <form:input path="storageRoomId"/><br/>  
    <form:label path="size">Size</form:label> 
    <form:input path="size" placeholder="1x1"/><br/>
    <form:label path="materials">Material</form:label>  
	<form:select path="materials">
		<form:options items="${materials}"/>
	</form:select><br/>
    <form:label path="categories">Categories</form:label>  
	<form:select path="categories">
		<form:options items="${categories}"/>
	</form:select><br/>
	<input type="submit" value="Save">
    </form:form>
    
    </body>
</html>