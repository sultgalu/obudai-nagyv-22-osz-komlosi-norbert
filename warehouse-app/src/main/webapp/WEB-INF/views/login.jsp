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
    
    <c:if test="${param.error != null}">
    	Invalid username or password
    </c:if>
    <c:if test="${param.logout != null}">
    	You have been logged out.
    </c:if>
    
    <p class="message">${message}</p>
    <form:form action="/login" method='POST'>
    	<h2>Please sign in</h2><br/>
    	<input type="text" name="username" placeholder="username"/><br/>
    	<input type="password" name="password"/><br/>
    	<input type="submit" value="Sign in"/>
    </form:form>
    </body>
</html>