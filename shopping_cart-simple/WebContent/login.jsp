<body>
	<h3>Welcome to XYZ online Shopping</h3>
	<form name="f1" action="LoginServlet" onSubmit="return validate()" method="post">
		Username:<input type="text" name="username"/><br>
		Password:<input type="password" name="password"/><br>
		<input type="submit" value="Login"/>
	</form>
	<a href="Register.jsp">Click here</a> to Register
	<script>
		function validate()
		{
			var x1=document.f1.username.value;
			if (x1==null || x1=="")
				{
					alert("Username should not be empty");
					return false;
				}
			var x2 = document.f1.password.value;	
			if (x2==null || x2=="")
				{
					alert("Password should not be empty");
					return false;
				}
			return true;
		}
	</script>
</body>
</html>