<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
    	<meta charset="UTF-8">
    	<link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Sign in</title>
    </head>
    <body>
    
    <h2>Error page</h2>
    <div class="message">
    	<c:if test="${not empty errorCode}">
    				${errorCode} : ${errorMessage}
    	</c:if>
    	<c:if test="${empty errorCode}">
    				System error.
    	</c:if>
    </div>
    </body>
</html>