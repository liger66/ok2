<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->

<html>
<style>
.btn {
    border: none;
    color: white;
    font-weight: bold;
    padding: 8px 15px;
    font-size: 15px;
    cursor: pointer;
    border-radius: 5px;
}

.success {background-color: #4CAF50;} /* Green */
.success:hover {background-color: #46a049;}

.info {background-color: #2196F3;} /* Blue */
.info:hover {background: #0b7dda;}

.warning {background-color: #ff9800;} /* Orange */
.warning:hover {background: #e68a00;}

.danger {background-color: #f44336;} /* Red */ 
.danger:hover {background: #da190b;}

.default {background-color: #e7e7e7; color: black;} /* Gray */ 
.default:hover {background: #ddd;}
</style>

<body>
	<div class="col-sm-7">
	
	</div>
	<div class="col-sm-5">
		<button class="btn default">초기화</button>
		<button class="btn success">조회</button>
		<button class="btn info">신규</button>
		<button class="btn warning">저장</button>
		<button class="btn danger">삭제</button>
	</div>
</body>
</html>