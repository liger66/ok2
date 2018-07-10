<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

<nav class="navbar navbar-inverse">
  <div class="container-fluid" >
    <div class="navbar-header">
      <a class="navbar-brand" href="#">WebSiteName</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">기초정보 관리 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">사용자 정보 등록</a></li>
          <li><a href="/code">공통코드 등록</a></li>
          <li><a href="#">품종정보 등록</a></li>
          <li><a href="#">사업자정보 등록</a></li>
          <li><a href="#">매장 정보 등록</a></li>
          <li><a href="#">매장 마진 등록</a></li>
          <li><a href="#">생산처 정보 등록</a></li>
        </ul>
      </li>
      
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">제품정보 관리 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="/jepum">제품 정보 등록</a></li>
          <li><a href="#">생산지시 등록</a></li>
          <li><a href="#">제품가격 확정 등록</a></li>
          <li><a href="#">할인 정보 등록</a></li>
          <li><a href="#">제품 실적 현황</a></li>
        </ul>
      </li>
      
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">영업 관리 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">제품 정보 등록</a></li>
          <li><a href="#">생산지시 등록</a></li>
          <li><a href="#">제품가격 확정 등록</a></li>
          <li><a href="#">할인 정보 등록</a></li>
          <li><a href="#">제품 실적 현황</a></li>
        </ul>
      </li>
      
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">판매 관리 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">제품 정보 등록</a></li>
          <li><a href="#">생산지시 등록</a></li>
          <li><a href="#">제품가격 확정 등록</a></li>
          <li><a href="#">할인 정보 등록</a></li>
          <li><a href="#">제품 실적 현황</a></li>
        </ul>
      </li>
      
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">고객 관리 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">제품 정보 등록</a></li>
          <li><a href="#">생산지시 등록</a></li>
          <li><a href="#">제품가격 확정 등록</a></li>
          <li><a href="#">할인 정보 등록</a></li>
          <li><a href="#">제품 실적 현황</a></li>
        </ul>
      </li>
      
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">실적 관리 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">제품 정보 등록</a></li>
          <li><a href="#">생산지시 등록</a></li>
          <li><a href="#">제품가격 확정 등록</a></li>
          <li><a href="#">할인 정보 등록</a></li>
          <li><a href="#">제품 실적 현황</a></li>
        </ul>
      </li>
      
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      <li><a href="#"><span class="glyphicon glyphicon-user"></span> Logout</a></li>
    </ul>
  </div>
</nav>

</body>
</html>