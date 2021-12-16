<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
        <title>View Books</title>
    </head>
    <body>
    <h1>HELLO</h1>
    <p>ASDASDASDASD</p>
    </body>
</html>

<form:form modelAttribute="request" action="/login">
    <h1>Please sign in</h1><br/>
    	<form:input path="username" placeholder="username"/><br/>
    	<form:password path="password"/><br/>
    	<input type="submit" value="Sign in">
    </form:form>
    
    
    
     <form action="/newbox" method="post">
    	<label>Storage room id</label>
    	<input type="number" id="storageRoomId" name="storageRoomId"><br/>
    	<label>Size</label>
    	<input type="text" id="size" name="size" placeholder="1x1"><br/>
    	<input type="submit" value="Save">
    </form>
    
    </table>
    <form:form action="/newbox">
    <form:label path="storageRoomId">Storage room id</form:label><br/>  
    <form:input path="storageRoomId"/>
    <form:label path="size">Size</form:label><br/>   
    <form:input path="size" placeholder="1x1"/>
    <form:label path="material">Material</form:label><br/>    
	<form:select path="material">
		<form:option value="" label="-- select material"/>
		<form:options items="${materials}"/>
	</form:select><br/>
    </form:form>
    
    
    .authorizeRequests()
      .antMatchers("/css/**").permitAll()
      .antMatchers("/h2-console/**").permitAll()
      .anyRequest().authenticated().and()
      .csrf().ignoringAntMatchers("/h2-console/**").and()
      .headers().frameOptions().sameOrigin().and()
      .formLogin().loginPage("/login").permitAll().and()
      .logout().permitAll();
    