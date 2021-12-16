<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="_header.jsp"/>

    <div class="error">
   		 <c:if test="${not empty errorCode}">
   		 	<h1 style="font-size: xxlarge">HTTP ${errorCode}</h1>	
   		 	<p style="font-size: medium">${errorMessage}</p>	
    	</c:if>
    	
    	<c:if test="${empty errorCode}">
    				System error.
    	</c:if>
    </div>
<jsp:include page="_footer.jsp"/>