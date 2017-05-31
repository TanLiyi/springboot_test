<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
var message="${message}";
if(message!=null && message!=''){
	  alter(message); 
   }
</script>
<body>

<form action="test" method="post">
  username: <input type="text" value="" name="usernaem">
  password: <input type="password" name="password" value=""> 
            <input type="submit" name="submit">
</form>
</body>
</html>