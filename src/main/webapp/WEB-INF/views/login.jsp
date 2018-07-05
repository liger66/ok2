<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <title>Login</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<style>
	.header {
		padding-top: 10px;
		height: 100px;
		background: #777;
		color: white;
	}
	.body {
		padding: 50px;
		height: 500px;
		border: 2px solid brue;
	}
</style>

<script>
function check (f) {
	if (f.id.value == "") {
		alert("아이디를 입력 해 주세요.");
		f.id.focus();
		return;
	}
	
	if (f.pass.value == "") {
		alert("패스워드를 입력 해 주세요.");
		f.pass.focus();
		return;
	}
	
	var id = $("#id").val();
	var pass = $("#pass").val();

	f.submit();
	
	if ('${errorYN}' == 'Y') {
		alert('${msg}');
	}
}
</script>

<body>
	<div class="container">
	<div class="row">
		<div class="header">
			<h2 class="text-center">환 영  합 니 다. ^^</h2>
		</div>

		<form action="${pageContext.request.contextPath}/login/in" method="post">
		<div class="body">
			<div class="form-group text-right">
				<label class="col-sm-offset-2 col-xs-3 control-label" for="id">아이디</label>
				<div class="col-sm-3">
					<input type="text" id="id" name="id" class="form-control" />
				</div>
			</div>
			<br /><br />
			<div class="form-group text-right">
				<label class="col-sm-offset-2 col-xs-3 control-label" for="pass">패스워드</label>
				<div class="col-sm-3">
					<input type="password" id="pass" name="pass" class="form-control" />
				</div>
			</div>
			<br /><br /> <br /><br />
			<div class="text-center">
				<button type="button" class="btn btn-success btn-lg" onclick="check(this.form)">Login</button>
				<button type="button" class="btn btn-warning btn-lg"
					onclick="location.href='${pageContext.request.contextPath}/hello2'">취소</button>
			</div>

		</div>
		</form>
	</div>
	</div>
</body>
</html>