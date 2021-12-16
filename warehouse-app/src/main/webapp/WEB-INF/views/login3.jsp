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
    
    <p class="message">${message}</p>
    <form:form modelAttribute="req" action="/login">
    	<h2>Please sign in</h2><br/>
    	<form:input path="username" placeholder="username"/><br/>
    	<form:errors cssClass="message" path="*"/>
    	<form:password path="password"/><br/>
    	<form:errors cssClass="message" path="password"/>
    	<input type="submit" value="Sign in"/>
    </form:form>
    </body>
</html>