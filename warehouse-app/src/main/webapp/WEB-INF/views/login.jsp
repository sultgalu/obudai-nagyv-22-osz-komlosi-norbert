<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
    	<meta charset="UTF-8">
    	<link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Sign in</title>
    </head>
    <body>
    
    <form action="/login" method="post">
    	<h1>Please sign in</h1><br/>
    	<input type="text" id="username" name="username" placeholder="username"><br/>
    	<input type="password" id="password" name="password"><br/>
    	<input type="submit" value="Sign in">
    </form>
    </body>
</html>