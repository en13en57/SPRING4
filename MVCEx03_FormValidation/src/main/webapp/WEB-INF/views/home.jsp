<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<title>Home</title>
	<link href="webjars/bootstrap/5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="webjars/bootstrap/5.1.3/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="webjars/jquery/3.6.0/dist/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			alert('Hello Webjars');
		});
	</script>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
