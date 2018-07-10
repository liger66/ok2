<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/resources/templates/include/menu2.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>제품 코드 등록</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>

<script type="text/javascript">
	function snew() {
		$("#btn_store").attr('disabled', false);
		$("#gubunCd").val("");
		$("#gubunNm").val("");
	}

	function insert() {
		var gubunCd = $("#gubunCd").val();
		var gubunNm = $("#gubunNm").val();
		if (gubunCd == "") {
			alert("구분코드를 입력해 주세요");
			$("#gubunCd").focus();
			return;
		}
		if (gubunNm == "") {
			alert("구분 명을 입력해 주세요");
			$("#gubunNm").focus();
			return;
		}

		$.ajax({
			url : "${pageContext.request.contextPath}/code/checkcode",
			type : "POST",
			data : {gubunCd : gubunCd},
			success : function(data) {
				if (data == "Y") {
					alert("이미 등록된 코드 구분 입니다.");
					$("#gubunCd").focus();
					return;
				} else {
					document.getElementById("gbForm").submit();
					//$("#gbForm").submit();
				}
			},
			error : function() { alert("DB 작업중 에러, 시스템에 문의 하세요."); }
		});
	}

	function insertLGroup() {
		var gubunCd = $("#hgubunCd").val();

		var lGroupCd = $("#lGroupCd").val();
		var lGroupNm = $("#lGroupNm").val();
		var lOrderBy = $("#lOrderBy").val();

		if (lGroupCd == "" || lGroupNm == "" || lOrderBy == "") {
			alert("입력 항목을 채워 주세요.");
			$("#lGroupCd").focus();
			return;
		}

		$.ajax({
			url : "${pageContext.request.contextPath}/code/insertLGroup",
			type : "POST",
			data : {
				gubunCd : gubunCd,
				lGroupCd : lGroupCd,
				lGroupNm : lGroupNm,
				lOrderBy : lOrderBy
			},
			success : function(data) {
				if (data == "Y") {
					alert("중복된 코드가 있습니다.");
					return;
				}
			},
			error : function() { alert("DB 작업중 에러, 시스템에 문의 하세요."); }
		});
	}

	function insertMGroup() {
		var gubunCd = $("#hgubunCd").val();
		var lGroupCd = $("#hLGroupCd").val();

		var mGroupCd = $("#mGroupCd").val();
		var mGroupNm = $("#mGroupNm").val();
		var mOrderBy = $("#mOrderBy").val();

		if (mGroupCd == "" || mGroupNm == "" || mOrderBy == "") {
			alert("입력 항목을 채워 주세요.");
			$("#mGroupCd").focus();
			return;
		}

		$.ajax({
			url : "${pageContext.request.contextPath}/code/insertMGroup",
			type : "POST",
			data : {
				gubunCd : gubunCd,
				lGroupCd : lGroupCd,
				mGroupCd : mGroupCd,
				mGroupNm : mGroupNm,
				mOrderBy : mOrderBy
			},
			success : function(data) {
				if (data == "Y") {
					alert("중복된 코드가 있습니다.");
					return;
				}
			},
			error : function() { alert("DB 작업중 에러, 시스템에 문의 하세요."); }
		});
	}

	function inquiry() {
		$("#btn_store").attr('disabled', true);
		var gubunCd = $("#gubunCd").val();
		var gubunNm = $("#gubunNm").val();

		location.href = "${pageContext.request.contextPath}/code/gbList?gubunCd="
						+ gubunCd + "&gubunNm=" + gubunNm;
	}

	function selectgubunCd(a) {
		$("#btn_lGroup").attr('disabled', false);

		var gubunCd = $(a).text();

		$("#hgubunCd").val(gubunCd);
		$("#lGroupCd").val("");
		$("#lGroupNm").val("");
		$("#lOrderBy").val("");

		$.ajax({
			url : "${pageContext.request.contextPath}/code/lGroupList",
			type : "POST",
			data : {gubunCd : gubunCd},
			success : function(data) {
				$("#lGroup").empty();
				for (var i = 0; i < data.length; i++) {
					var tx = "<tr class='text-c'> <td> ";
					tx += "<a href='#' onclick='selectLGroup(this); return false;'>";
					tx += data[i].lGroupCd + "</a> </td>";

					tx += "<td class='text-l'>" + data[i].lGroupNm
							+ "</td>";
					tx += "<td>" + data[i].orderBy + "</td> </tr>";

					$("#lGroup").append(tx);
				}
			},
			error : function() { alert("DB 작업중 에러, 시스템에 문의 하세요."); }
		});
	}

	function selectLGroup(b) {
		$("#btn_mGroup").attr('disabled', false);

		var gubunCd = $("#hgubunCd").val();
		var lGroupCd = $(b).text();
		alert("lGroupCd : " + lGroupCd);
		$("#hLGroupCd").val(lGroupCd);

		$("#mGroupCd").val("");
		$("#mGroupNm").val("");
		$("#mOrderBy").val("");

		$.ajax({
			url : "${pageContext.request.contextPath}/code/mGroupList",
			type : "POST",
			data : {
				gubunCd : gubunCd,
				lGroupCd : lGroupCd
			},
			success : function(data) {
				$("#mGroup").empty();
				for (var i = 0; i < data.length; i++) {
					var tx = "<tr class='text-c'> <td> ";
					tx += data[i].mGroupCd + "</td>";

					tx += "<td class='text-l'>" + data[i].mGroupNm + "</td>";
					tx += "<td>" + data[i].orderBy + "</td> </tr>";

					$("#mGroup").append(tx);
				}
			},
			error : function() { alert("DB 작업중 에러, 시스템에 문의 하세요."); }
		});
	}
</script>

<body>
<div class="container">
	<div class="col-sm-8">
		<h3 class="title text-center">제품 코드 등록</h3>
	</div>
	<div class="col-sm-4">
		<button type="button" id="btn_init" class="btn">초기화</button>
		<button type="button" id="btn_serch" class="btn btn-primary"
				onclick="inquiry()">조회</button>
		<button type="button" id="btn_new" class="btn btn-info"
				onclick="snew()">신규</button>
		<button type="button" id="btn_store" class="btn btn-success" disabled="disabled" 
				onclick="insert()">저장</button>
		<button type="button" id="btn_delete" class="btn btn-danger"
				disabled="disabled">삭제</button>
	</div>
</div>

<hr />

<div>
<div class="container col-sm-3">	
	<form class="form-horizontal ">
		<div class="form-group form-inline">
	      <label class="col-sm-6 control-label">브랜드 코드</label>
	      <select class="form-control" style="width:120px;" id="brand">
	        <option>1</option>
	        <option>2</option>
	        <option>3</option>
	        <option>4</option>
	      </select>
	    </div> 
	    <div class="form-group form-inline">
	      <label class="col-sm-6 control-label">기획 년도</label>
	      <select class="form-control" style="width:120px;" id="giyy">
	        <option>18 년</option>
	        <option>2</option>
	        <option>3</option>
	        <option>4</option>
	      </select>
	    </div>
	    <div class="form-group form-inline">
	      <label class="col-sm-6 control-label">시 즌</label>
	      <select class="form-control" style="width:120px;" id="season">
	        <option>S 봄</option>
	        <option>2</option>
	        <option>3</option>
	        <option>4</option>
	      </select>
	    </div>
	    <div class="form-group form-inline">
	      <label class="col-sm-6 control-label">제품 명</label>
	      <input class="form-control" id="jepumNm" type="text" style="width:120px;">	      
	    </div>
    </form>
    
	<table class="table table-bordered">
		<thead>
			<tr>
				<th class="text-center col-sm-6">제품코드</th>
				<th class="text-center col-sm-6">소비자 가</th>
			</tr>
		</thead>
		
		<tbody>			
			<c:forEach var="jp" items="${jepumList }">
			<tr >
				<td class="text-c"><a href="#"
					onclick="selectgubunCd(this); return false;">${jp.jepumCd}</a>
				</td>
				<td>${jp.sobi}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div class="container col-sm-9">
	<div class="form-group col-sm-8">
		<div class="form-group form-inline col-sm-6">
	      <label class="col-sm-6 hlabel" >제품 코드</label>
	      <input class="form-control htext" id="jepum" type="text" style="width:120px; font-weight:bold;">	      
	    </div>
	    <div class="form-group form-inline col-sm-6">
	      <label class="col-sm-6 hlabel">제품 코드</label>
	      <input class="form-control" id="jepumNm" type="text" style="width:120px;">	      
	    </div> <br>
	    <div class="form-group form-inline col-sm-6">
	      <label class="col-sm-6 hlabel" >제품 코드</label>
	      <input class="form-control htext" id="jepum" type="text" style="width:120px; font-weight:bold;">	      
	    </div>
	    <div class="form-group form-inline col-sm-6">
	      <label class="col-sm-6 hlabel">제품 코드</label>
	      <input class="form-control" id="jepumNm" type="text" style="width:120px;">	      
	    </div> <br>
	    <div class="form-group form-inline col-sm-6">
	      <label class="col-sm-6 hlabel" >제품 코드</label>
	      <input class="form-control htext" id="jepum" type="text" style="width:120px; font-weight:bold;">	      
	    </div>
	    <div class="form-group form-inline col-sm-6">
	      <label class="col-sm-6 hlabel">제품 코드</label>
	      <input class="form-control" id="jepumNm" type="text" style="width:120px;">	      
	    </div> <br>
	    <div class="form-group form-inline col-sm-6">
	      <label class="col-sm-6 hlabel" >제품 코드</label>
	      <input class="form-control htext" id="jepum" type="text" style="width:120px; font-weight:bold;">	      
	    </div>
	    <div class="form-group form-inline col-sm-6">
	      <label class="col-sm-6 hlabel">제품 코드</label>
	      <input class="form-control" id="jepumNm" type="text" style="width:120px;">	      
	    </div> <br>
	</div>
	<div class="form-group col-sm-4" style="border-style:ridge; height:300px;">
	
	</div>
</div>

</div>

</body>
</html>