<body>
<h3>Registration Page</h3>
<form name="f2" action="RegisterServlet"  onSubmit="return validate1()" method="post">
	Name:<input type="text" name="name"/><br>
	Username:<input type="text" name="username"/><br>
	Password:<input type="password" name="password"/><br>
	Retype Password:<input type="password" name="repassword"/><br>
	<input type="submit" value="Register"/>
</form>
<script>
function validate1()
{
	var x1 = document.f2.name.value;
	var x2 = document.f2.username.value;
	var x3 = document.f2.password.value;
	var x4 = document.f2.repassword.value;
	if (x1==null || x1=="")
		{
			alert("Name should not be empty");
			return false;
		}
	
	if (x2==null || x2=="")
		{
			alert("Username should not be empty");
			return false;
		}
	
	if (x3==null || x3=="")
		{
			alert("Password should not be empty");
			return false;
		}
	if (x3!=x4)
		{
			alert("Password should match");
			return false;
		}
		
	
	return true;
}
</script>
</body>
