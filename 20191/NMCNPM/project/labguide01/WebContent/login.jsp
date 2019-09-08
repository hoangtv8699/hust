<!DOCTYPE html>
<html>
  <head>
  	<meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="login.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js" charset="utf-8"></script>
  </head>
<body>
		
	<form action="Hello" class="login-form">
		<h1>Submit</h1>
		
		<div class="txtb">
			<input type="text" name="username">
			<span data-placeholder="Username"></span>
		</div>

		<div class="txtb">
			<input type="text" name="user-email">
			<span data-placeholder="User-email"></span>
		</div>

		<input type="submit" class="logbtn" value="Submit">

		<script type="text/javascript">
			$(".txtb input").on("focus",function(){
				$(this).addClass("focus");
			})

			$(".txtb input").on("blur",function(){
				if($(this).val() == "")
				$(this).removeClass("focus");
			})
		</script>
	</form>
 


</body>
</html>