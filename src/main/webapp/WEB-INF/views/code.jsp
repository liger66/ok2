<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/resources/templates/include/menu2.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>Main Memu</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<script type="text/javascript">
function snew(){
	$("#btn_store").attr('disabled', false);
	$("#gbCode").val("");
	$("#gbName").val("");
}

function store() {
	var gbCode = $("#gbCode").val();
	var gbName = $("#gbName").val();
	if(gbCode == ""){
		alert("구분코드를 입력해 주세요");
		$("#gbCode").focus();
		return;
	}
	if(gbName == ""){
		alert("구분 명을 입력해 주세요");
		$("#gbName").focus();
		return;
	}
	
	f.submit();
}
</script>

<body>
<div class="container">
 <div class="col-sm-8">
 	<h3 class="title text-center">공통 코드 등록</h3>	
 </div>
 <div class="col-sm-4">
 	<button type="button" id="btn_init" class="btn">초기화</button>
 	<button type="button" id="btn_serch" class="btn btn-primary">조회</button>
 	<button type="button" id="btn_new" class="btn btn-info" onclick="snew()">신규</button>
 	<button type="button" id="btn_store" class="btn btn-success" disabled="disabled"
 			onclick="store()" >저장</button>
 	<button type="button" id="btn_delete" class="btn btn-danger" disabled="disabled">삭제</button>
 </div>
</div>

<hr/>
 
<div class="container">
  <form class="form-inline" action="${pageContext.request.contextPath}/code/insertGb" method="post">    
      <div class="input-group col-sm-2">    
        <span class="input-group-addon">구분코드</span>
        <input id="gbCode" type="text" class="form-control" name="gbCode" >
      </div>      
      <div class="input-group col-sm-1"></div>
      
      <div class="input-group col-sm-5">    
        <span class="input-group-addon">구분 명</span>
        <input id="gbName" type="text" class="form-control" name="gbName" >
      </div>
  </form>
  
  <hr />
  
  <div class="form-group col-sm-3">
  	<h4 class="text-center">구분 코드</h4>  	
  <table class="table table-bordered">
    <thead>
      <tr>
        <th class="text-center col-sm-3">코드</th>
        <th class="text-center col-sm-9">명칭</th>
      </tr>
    </thead>
    <tbody>
    	<c:forEach var="bvo" items="${codegbList }">
	      <tr>
	        <td>${bvo.code }</td>
	        <td>${bvo.name }</td>
	      </tr>
	    </c:forEach>
    </tbody>
  </table>
  </div> 
  
  <div class="form-group col-sm-5">
  	<h4 class="text-center col-sm-10">대 분류</h4>
  	<button type="button" class="btn btn-success col-sm-2" disabled="disabled">저장</button>
  <table class="table table-bordered">
    <thead>
      <tr>
        <th class="text-center col-sm-3">코드</th>
        <th class="text-center col-sm-7">명칭</th>
        <th class="text-center col-sm-1">Y/N</th>
        <th class="text-center col-sm-1">Sort</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>John</td>
        <td>Doe</td>
        <td>y</td>
        <td>11</td>
      </tr>
      <tr>
        <td>Mary</td>
        <td>Moe</td>
        <td>y</td>
        <td>12</td>
      </tr>
      <tr>
        <td>July</td>
        <td>Dooley</td>
        <td>y</td>
        <td>13</td>
      </tr>
    </tbody>
  </table>
  </div> 
  
  <div class="form-group col-sm-4">
  	<h4 class="text-center col-sm-10">중 분류</h4>
  	<button type="button" class="btn btn-success col-sm-2" disabled="disabled">저장</button>
  <table class="table table-bordered">
    <thead>
      <tr>
        <th class="text-center col-sm-3">코드</th>
        <th class="text-center col-sm-7">명칭</th>
        <th class="text-center col-sm-1">Y/N</th>
        <th class="text-center col-sm-1">Sort</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>John</td>
        <td>Doe</td>
        <td>y</td>
        <td>11</td>
      </tr>
      <tr>
        <td>Mary</td>
        <td>Moe</td>
        <td>y</td>
        <td>12</td>
      </tr>
      <tr>
        <td>July</td>
        <td>Dooley</td>
        <td>y</td>
        <td>13</td>
      </tr>
    </tbody>
  </table>
  </div> 
</div>
</body>
</html>