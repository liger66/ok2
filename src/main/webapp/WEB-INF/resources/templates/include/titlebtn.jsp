<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
  <h2>Button Styles</h2>
  <button type="button" class="btn">초기화</button>  
  <button type="button" class="btn btn-primary">조회</button>
  <button type="button" class="btn btn-info">신규</button>
  <button type="button" class="btn btn-success">저장</button>
  <button type="button" class="btn btn-danger">삭제</button>
</div>

</body>
</html>




<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.btn {
    border: none;
    color: white;
    padding: 10px 18px;
    font-size: 16px;
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
</head>
<body>

<button class="btn default">초기화</button>
<button class="btn success">조회</button>
<button class="btn info">신규</button>
<button class="btn warning">저장</button>
<button class="btn danger">삭제</button>


</body>
</html>