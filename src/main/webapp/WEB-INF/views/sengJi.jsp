<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/resources/templates/include/menu2.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>생산지시서 등록</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>

<script type="text/javascript">
	function init() {
		location.href = "/sengJi";
	}

	function mnew() {
		location.href = "/sengJi/mnew";
	}
	
	function inquiry() {
		var form = document.getElementById("serchform");
		form.submit();
	}
	
	function jepumChange() {
		var jepum  = $("#jepum").val();
		var tx1 = "<option value='";
		var tx2 = "'>";
		var tx3 = "</option>";
		$.ajax({url : "/jepum/change",
				type : "POST",
				data : {jepum : jepum},
				success : function(data) {
					if (data.errorYN == "Y") { alert (data.msg); }
					$("#brandNm").val(data.brandNm);
					$("#giYYNm").val(data.giYYNm);
					$("#seasonNm").val(data.seasonNm);
					$("#pumNm").val(data.pumNm);
					
					var txall = tx1 + data.sBrand + tx2 + data.brandNm + tx3;
					$("#sBrand").append(txall);
					
					txall = tx1 + data.sGiYY + tx2 + data.giYYNm + tx3;
					$("#sGiYY").append(txall);
					
					txall = tx1 + data.sSeason + tx2 + data.seasonNm + tx3;
					$("#sSeason").append(txall);
					
					txall = tx1 + data.sPum + tx2 + data.pumNm + tx3;
					$("#sPumjong").append(txall);
					
					return;
			},
			error : function(err) {
				console.log (err);
				alert("DB 작업중 에러, 시스템에 문의 하세요.");
			}
		});
	}
	
	function selectJepum(b) {
		var sJepum = $(b).text();
		
		$.ajax({
			url : "/sengJi/selectJepum",
			type : "POST",
			data : {sJepum : sJepum},
			success : function(data) {		
				$("#jepum").val(data.jepum.jepum);
				$("#jepumNm").val(data.jepum.jepumNm);
				$("#saipPanYN").val(data.jepum.saipPanYN);
				$("#giWonjaje").val(data.jepum.giWonjaje.toLocaleString());
				$("#giBujaje").val(data.jepum.giBujaje.toLocaleString());
				$("#giImbong").val(data.jepum.giImbong.toLocaleString());
				$("#giSobi").val(data.jepum.giSobi.toLocaleString());
				$("#won").val(data.jepum.won.toLocaleString());
				$("#giWon").val(data.jepum.won.toLocaleString());
				$("#wonjaje").val(data.jepum.wonjaje.toLocaleString());
				$("#bujaje").val(data.jepum.bujaje.toLocaleString());
				$("#imbong").val(data.jepum.imbong.toLocaleString());
				$("#sobi").val(data.jepum.sobi.toLocaleString());
				
				$("#giIpgoDt").val(data.jepum.giIpgoDt);
				$("#giPanDt").val(data.jepum.giPanDt);				
				
				$("#brandNm").val(data.brandNm);
				$("#giYYNm").val(data.giYYNm);
				$("#seasonNm").val(data.seasonNm);
				$("#pumNm").val(data.pumNm);
				
				var tx1 = "<option>";
				var tx2 = "</option>";
				
				var txall = tx1 + data.jepumGb + tx2;
				$("#jepumGb").empty();
				$("#jepumGb").append(txall);
				
				txall = tx1 + data.majinGb + tx2;
				$("#majinGb").empty();
				$("#majinGb").append(txall);
				
				txall = tx1 + data.giGb + tx2;
				$("#giGb").empty();
				$("#giGb").append(txall);
				
				txall = tx1 + data.sojeGb + tx2;
				$("#sojeGb").empty();
				$("#sojeGb").append(txall);
				
				txall = tx1 + data.priceGb + tx2;
				$("#priceGb").empty();
				$("#priceGb").append(txall);
				
				txall = tx1 + data.sizGroup + tx2;
				$("#sizGroup").empty();
				$("#sizGroup").append(txall);
				
				txall = tx1 + data.sengHt + tx2;
				$("#sengHt").empty();
				$("#sengHt").append(txall);
				
				return;
			},
			error : function(err) {
				console.log (err);
				alert("DB 작업중 에러(1), 시스템에 문의 하세요.");
			}
		});
	}

	function insert() {
		if ($("#giWonjaje").val() == "") { $("#giWonjaje").val(0); }
		if ($("#giBujaje").val() == "" ) { $("#giBujaje").val(0); }
		if ($("#giImbong").val() == "")  { $("#giImbong").val(0); }
		if ($("#giSobi").val() == "")    { $("#giSobi").val(0); }
		
		$("#wonjaje").val(0);
		$("#bujaje").val(0);
		$("#imbong").val(0);
		$("#sobi").val(0);
		$("#won").val(0);
		$("#giWon").val(0);
		
		var jepum = $("#jepum").val();
		var giWonjaje = $("#giWonjaje").val();
		var giBujaje = $("#giBujaje").val();
		var giImbong = $("#giImbong").val();
		var giSobi = $("#giSobi").val();
		
		if (jepum.length != 7) {
			alert ("제품코드는 7 자리로 입력 합니다.");	return;
		}
		
		if (isNaN(giWonjaje) || isNaN(giBujaje)  || isNaN(giImbong)) {
			alert ("원가 사항은 숫자로 입력 합니다.");	return;
		}
		if (isNaN(giSobi)) {
			alert ("소비자가를 숫자로 입력 합니다.");	return;
		}
		
		$.ajax({
			url : "/sengJi/checkData",
			type : "POST",
			data : {jepum:jepum, giWonjaje:giWonjaje, giBujaje:giBujaje, giImbong:giImbong, giSobi:giSobi},
			success : function(data) {
				if (data != "OK") {
					alert (data);
					return;
				} else {
					var form = document.getElementById("mainform");
					form.submit();
				}		
			},
			error : function(err) {
				console.log (err);
				alert("12 작업중 에러, 시스템에 문의 하세요."); 
			}
		});			
	}
	
	
</script>
<body>

<div class="container">
	<div class="col-sm-7">
		<h3 class="title text-center">생산지시서 등록</h3>
	</div>
	<div class="col-sm-5" style="majin-right:0px; padding-right:0px; text-align: right; ">
		<button type="button" id="btn_init" class="btn" onclick="init()">초기화</button>
		<button type="button" id="btn_serch" class="btn btn-primary"
				onclick="inquiry()">조회</button>
		<button type="button" id="btn_new" class="btn btn-info"
				onclick="mnew()">신규</button>
		<button type="button" id="btn_store" class="btn btn-success"
			<c:if test="${!mnew}">disabled="disabled"</c:if> onclick="insert()">저장</button>
		<button type="button" id="btn_delete" class="btn btn-danger"
				disabled="disabled">삭제</button>
	</div>
</div>

<hr />

<div>
<div class="container col-sm-3">
	<form id="serchform" action="/jepum/list" method="get" class="form-horizontal ">
		<div class="form-group form-inline">
			<label class="col-sm-6 control-label">브랜드 코드</label> 
			<select id="sBrand" name="sBrand" class="form-control" style="width: 120px;">
				<c:forEach var="sb" items="${sBrandList }">
					<option value="${sb.lGroupCd}"
						<c:if test="${tUser.brand == sb.lGroupCd}"> selected</c:if>>${sb.lGroupNm}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group form-inline">
			<label class="col-sm-6 control-label">기획 년도</label> 
			<select id="sGiYY" name="sGiYY" class="form-control" style="width: 120px;">
				<c:forEach var="sg" items="${sGiYYList }">
					<option value="${sg.lGroupCd}"
						<c:if test="${tUser.giYY == sg.lGroupCd}"> selected</c:if>>${sg.lGroupNm}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group form-inline">
			<label class="col-sm-6 control-label">시 즌</label> 
			<select id="sSeason" name="sSeason" class="form-control" style="width: 120px;">
				<c:forEach var="ss" items="${sSeasonList }">
					<option value="${ss.lGroupCd}"
						<c:if test="${tUser.season == ss.lGroupCd}"> selected</c:if>>${ss.lGroupNm}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group form-inline">
			<label class="col-sm-6 control-label">품 종</label> <select
				   id="sPumjong" name="sPumjong" class="form-control" style="width: 120px;">
				<option value="all">전체</option>
				<c:forEach var="sp" items="${sPumjongList }">
					<option value="${sp.pumCd}">${sp.pumNm}</option>
				</c:forEach>
			</select>
		</div>
	</form>
	<div style="overflow-y:auto; max-height:460px;">
	<table class="table table-bordered ">
		<thead>
			<tr>
				<th class="text-center col-sm-5">제품코드</th>
				<th class="text-center col-sm-2">차수</th>
				<th class="text-center col-sm-5">생산처</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="jp" items="${sJepumList }">
				<tr>
					<td class="text-c"><a href="#"
						onclick="selectJepum(this); return false;">${jp.jepum}</a></td>
					<td>"${jp.sengCha}"</td>
					<td>"${jp.sengNm}"</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</div>

<div class="container col-sm-9">
<form id="mainform" action="${pageContext.request.contextPath}/jepum/insert" method="post" >
	<div class="row">  
		<div class="form-group col-sm-4">
			<div class="form-group form-inline ">
				<label class="col-sm-6 hlabel">제품 코드</label>
				<input name="jepum" id="jepum" class="form-control iup" onchange="jepumChange()"
					   value="${jepum.jepum }" style="width:120px; font-weight:bold;">
				<input type="hidden" name="hJepum" value="${hJepum}" >
			</div>
		</div>
		<div class="form-group col-sm-8">
			<div class="form-group form-inline ">
				<label class="col-sm-3 hlabel">제품 명</label> <input id="jepumNm" 
					   name="jepumNm" class="form-control" style="width: 300px;">
			</div>
		</div>
	</div>
	
	<div class="row" >
		<div class="form-group col-sm-4">
			<div class="form-group form-inline">
				<label class="col-sm-6 hlabel">브랜드</label> 
				<input id="brandNm" name="brandNm" class="form-control" style="width: 120px;"
					   value="${brandNm}" readonly="readonly">
			</div>
		</div>
		<div class="form-group col-sm-4">
			<div class="form-group form-inline ">
				<label class="col-sm-6 hlabel">기획년도</label> 
				<input id="giYYNm" name="giYYNm" class="form-control" style="width: 120px;"
					   value="${giYYNm}" readonly="readonly">
			</div>
		</div>
		<div class="form-group col-sm-4">
			<div class="form-group form-inline ">
				<label class="col-sm-6 hlabel">계 절</label> 
				<input id="seasonNm" name="seasonNm" class="form-control" readonly="readonly"
					   value="${seasonNm}" style="width: 120px;" >
			</div>
		</div>
	</div>
	
	<div >  
		<div class="form-group col-sm-4" >
			<div class="form-group form-inline ">
				<label class="col-sm-6 hlabel">생산차수</label>
				<input name="sebgCha" id="sengCha" class="form-control iup" 
					   value="${sengJisi.sengCha }" style="width:120px;">
			</div>
		</div>
		<div class="form-group col-sm-8" class="row" >
			<div class="form-group form-inline ">
				<label class="col-sm-3 hlabel">생산처</label> 					
				<div class="input-group">
			         <input id="sengCd" name="sengCd" class="form-control iup" 
					   value="${sengJisi.sengCd}" style="width:80px;"> &nbsp;
			         <div class="input-group-btn">
			            <button class="btn btn-default" type="submit" onclick="sengSerch()">
			            	<i class="glyphicon glyphicon-search"></i></button>
			    	 </div> 
	    		</div> &nbsp; &nbsp; &nbsp; &nbsp;
	    		<input id="sengNm" name="sengNm" class="form-control iup" readonly="readonly"
					   value="${sengJisi.sengNm}" style="width: 250px;">
			</div>
		</div>
	</div>
	
	<div class="row" >
		<div class="form-group col-sm-4">
			<div class="form-group form-inline">
				<label class="col-sm-6 hlabel">생산지시일자</label> 
				<input id="baljuDt" name="baljuDt" class="form-control" style="width: 130px;"
					   value="${baljuDt}" type="date" >
			</div>
		</div>
		<div class="form-group col-sm-4">
			<div class="form-group form-inline ">
				<label class="col-sm-6 hlabel">납기일자</label> 
				<input id="napgiDt" name="napgiDt" class="form-control" style="width: 130px;"
					   value="${napgiDt}" type="date" >
			</div>
		</div>
		<div class="form-group col-sm-4">
			<div class="form-group form-inline ">
				<label class="col-sm-6 hlabel">임봉료 단가</label> 
				<input id="dan" name="dan" class="form-control" style="width: 120px;"
					   value="${dan}" >
			</div>
		</div>
	</div>
	
	<div class="row" >
		<table class="table table-bordered">
			<thead>
				<tr id="tHead">
					<th class="text-c col-sm-1 tbcolor">칼라</th>
					<th class="text-c col-sm-2 tbcolor">Image</th>
				</tr>
			</thead>

			<tbody>
				<tr id="tBody1">
					<td>
						<select id="color1" name="color1" class="form-control" >
						<c:forEach var="mc" items="${colorList }">
							<option value="${mc.lGroupCd}">${mc.lGroupNm}</option>
						</c:forEach>
						</select>
					</td>
					<td><input type="file" name="fileNm1"></td>
				</tr>
				<tr id="tBody2">
					<td>
						<select id="color2" name="color2" class="form-control" >
						<c:forEach var="mc" items="${colorList }">
							<option value="${mc.lGroupCd}">${mc.lGroupNm}</option>
						</c:forEach>
						</select>
					</td>
					<td><input type="file" name="fileNm2"></td>
				</tr>
				
			</tbody>
		</table>
	</div>
</form>
</div>

</div>
</body>
</html>