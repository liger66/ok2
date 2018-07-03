<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {margin:0;font-family:Arial}

.topnav {
  overflow: hidden;
  background-color: #333;
}

.topnav a {
  float: left;
  display: block;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.active {
  background-color: #ff0000;
  color: white;
}

.topnav .icon {
  display: none;
}

.dropdown {
    float: left;
    overflow: hidden;
}

.dropdown .dropbtn {
    font-size: 17px;    
    border: none;
    outline: none;
    color: white;
    padding: 14px 16px;
    background-color: inherit;
    font-family: inherit;
    margin: 0;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    float: none;
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
}

.topnav a:hover, .dropdown:hover .dropbtn {
  background-color: #0000e6;
  color: white;
}

.dropdown-content a:hover {
    background-color: #ddd;
    color: black;
}

.dropdown:hover .dropdown-content {
    display: block;
}

@media screen and (max-width: 600px) {
  .topnav a:not(:first-child), .dropdown .dropbtn {
    display: none;
  }
  .topnav a.icon {
    float: right;
    display: block;
  }
}

@media screen and (max-width: 600px) {
  .topnav.responsive {position: relative;}
  .topnav.responsive .icon {
    position: absolute;
    right: 0;
    top: 0;
  }
  .topnav.responsive a {
    float: none;
    display: block;
    text-align: left;
  }
  .topnav.responsive .dropdown {float: none;}
  .topnav.responsive .dropdown-content {position: relative;}
  .topnav.responsive .dropdown .dropbtn {
    display: block;
    width: 100%;
    text-align: left;
  }
}

</style>
</head>
<body>

<div class="topnav" id="myTopnav">
  <a href="#home" class="active">Home</a>
  <div class="dropdown">
    <button class="dropbtn">기초정보 관리
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#">사용자 정보 등록</a>
      <a href="#">공통코드 등록</a>
      <a href="#">품종정보 등록</a>
      <a href="#">사업자정보 등록</a>
      <a href="#">매장 정보 등록</a>
      <a href="#">매장 마진 등록</a>
      <a href="#">생산처 정보 등록</a>
    </div>
  </div>
  
  <div class="dropdown">
    <button class="dropbtn">제품정보 관리
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#">제품 정보 등록</a>
      <a href="#">생산지시 등록</a>
      <a href="#">제품가격 확정 등록</a>
      <a href="#">할인 정보 등록</a>
      <a href="#">제품 실적 현황</a>
    </div>
  </div>
  
  <div class="dropdown">
    <button class="dropbtn">물류 관리
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#">제품 정보 등록</a>
      <a href="#">생산지시 등록</a>
      <a href="#">제품가격 확정 등록</a>
      <a href="#">할인 정보 등록</a>
      <a href="#">제품 실적 현황</a>
    </div>
  </div> 
  
  <div class="dropdown">
    <button class="dropbtn">영업 관리
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#">제품 정보 등록</a>
      <a href="#">생산지시 등록</a>
      <a href="#">제품가격 확정 등록</a>
      <a href="#">할인 정보 등록</a>
      <a href="#">제품 실적 현황</a>
    </div>
  </div> 
  
  <div class="dropdown">
    <button class="dropbtn">판매 관리
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#">제품 정보 등록</a>
      <a href="#">생산지시 등록</a>
      <a href="#">제품가격 확정 등록</a>
      <a href="#">할인 정보 등록</a>
      <a href="#">제품 실적 현황</a>
    </div>
  </div> 
  
  <div class="dropdown">
    <button class="dropbtn">고객 관리
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#">제품 정보 등록</a>
      <a href="#">생산지시 등록</a>
      <a href="#">제품가격 확정 등록</a>
      <a href="#">할인 정보 등록</a>
      <a href="#">제품 실적 현황</a>
    </div>
  </div> 
  
  <div class="dropdown">
    <button class="dropbtn">수불실적 관리
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="#">제품 정보 등록</a>
      <a href="#">생산지시 등록</a>
      <a href="#">제품가격 확정 등록</a>
      <a href="#">할인 정보 등록</a>
      <a href="#">제품 실적 현황</a>
    </div>
  </div> 
  <a href="#about">About</a>
  <a href="javascript:void(0);" style="font-size:15px;" class="icon" onclick="myFunction()">&#9776;</a>
</div>

<script>
function myFunction() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}
</script>

</body>
</html>
