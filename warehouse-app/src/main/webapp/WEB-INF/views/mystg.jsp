<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="_header.jsp"/>
<div class="content">
<table style="margin-left: auto; margin-right: auto; margin-top: 30px;">
	<tr style="background-color: var(--themeColor)">
    	<th style="color: white">My storage rooms</th>
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
    			<td>
 					<form:form action="/cancel_rent/${sr.id}">
    					<input type="submit" value="Cancel renting">
    				</form:form>
				</td>
    		</tr>
    	</c:forEach>
    	</table>
    	</td>
	</tr>
</table>
</div>

    
<jsp:include page="_footer.jsp"/>