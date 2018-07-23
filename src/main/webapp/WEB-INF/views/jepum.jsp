<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
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
	function init() {
		location.href = "/jepum";
	}

	function mnew() {
		location.href = "/jepum/mnew";
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
					if (data.errorYN == "Y") { alert (data.msg); return; }
					
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
			url : "/jepum/selectJepum",
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
			url : "/jepum/checkData",
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
		<h3 class="title text-center">제품 코드 등록</h3>
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
					<th class="text-center col-sm-6">제품코드</th>
					<th class="text-center col-sm-6">소비자 가</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="jp" items="${sJepumList }">
					<tr>
						<td class="text-c"><a href="#"
							onclick="selectJepum(this); return false;">${jp.jepum}</a></td>
						<td style="text-align:right; padding-right: 30px;">
							<fmt:formatNumber value="${jp.sobi}" pattern="#,###,###"/> </td>
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
			<div class="row">
				<div class="form-group col-sm-4">
					<div class="form-group form-inline">
						<label class="col-sm-6 hlabel">브랜드</label> 
						<input id="brandNm" name="brandNm" class="form-control" style="width: 120px;"
							   value="${brandNm}" readonly="readonly">
						<input type="hidden" name="brand" value="${brand}" >
					</div>
					<div class="form-group form-inline ">
						<label class="col-sm-6 hlabel">제품구분</label> 
						<select id="jepumGb" name="jepumGb" class="form-control" style="width: 120px;">
							<c:forEach var="mj" items="${jepumGbList }">
								<option value="${mj.lGroupCd}">${mj.lGroupNm}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group form-inline ">
						<label class="col-sm-6 hlabel">마진구분</label> 
						<select id="majinGb" name="majinGb" class="form-control" style="width: 120px;">
							<c:forEach var="mm" items="${majinGbList }">
								<option value="${mm.lGroupCd}">${mm.lGroupNm}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group col-sm-4">
					<div class="form-group form-inline ">
						<label class="col-sm-6 hlabel">기획년도</label> 
						<input id="giYYNm" name="giYYNm" class="form-control" style="width: 120px;"
							   value="${giYYNm}" readonly="readonly">
						<input type="hidden" name="giYY" value="${giYY}" >
					</div>
					<div class="form-group form-inline ">
						<label class="col-sm-6 hlabel">기획구분</label> 
						<select id="giGb" name="giGb" class="form-control" style="width: 120px;">
							<c:forEach var="mg" items="${giGbList }">
								<option value="${mg.lGroupCd}">${mg.lGroupNm}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group form-inline ">
						<label class="col-sm-6 hlabel">소재구분</label> 
						<select id="sojeGb" name="sojeGb" class="form-control" style="width: 120px;">
							<c:forEach var="ms" items="${sojeGbList }">
								<option value="${ms.lGroupCd}">${ms.lGroupNm}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group col-sm-4">
					<div class="form-group form-inline ">
						<label class="col-sm-6 hlabel">계 절</label> 
						<input id="seasonNm" name="seasonNm" class="form-control" readonly="readonly"
							   value="${seasonNm}" style="width: 120px;" >
						<input type="hidden" name="season" value="${season}" >
					</div>
					<div class="form-group form-inline ">
						<label class="col-sm-6 hlabel">품 종</label> 
						<input id="pumNm" name="pumNm" class="form-control" style="width: 120px;"
							value="${pumNm}" readonly="readonly">
						<input type="hidden" name="pum" value="${pum}" >
					</div>
					<div class="form-group form-inline ">
						<label class="col-sm-6 hlabel">가격구분</label> 
						<select id="priceGb" name="priceGb" class="form-control" style="width: 120px;">
							<c:forEach var="mp" items="${priceGbList }">
								<option value="${mp.lGroupCd}">${mp.lGroupNm}</option>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>

			<div class="form-group col-sm-4">
				<div class="form-group form-inline ">
					<label class="col-sm-6 hlabel">사이즈 그룹</label> 
					<select id="sizGroup" name="sizGroup" class="form-control" style="width: 120px;">
						<c:forEach var="mz" items="${sizGrList }">
							<option value="${mz.lGroupCd}">${mz.lGroupNm}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group form-inline ">
					<label class="col-sm-6 hlabel">생산형태</label> 
					<select id="sengHt" name="sengHt" class="form-control" style="width: 120px;">
						<c:forEach var="mh" items="${sengHtList }">
							<option value="${mh.lGroupCd}">${mh.lGroupNm}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group form-inline ">
					<label class="col-sm-6 hlabel">사입판매여부</label> 
					<select id="saipPanYN" name="saipPanYN" class="form-control" style="width: 120px;">
						<option value="N">No</option>
						<option value="Y">Yes</option>
					</select>
				</div>
				<div class="form-group form-inline ">
					<label class="col-sm-5 hlabel" style="font-size: 12px">기획입고일</label> 
					<input id="giIpgoDt" name="giIpgoDt" class="form-control" type="date" 
						   value="${jepum.giIpgoDt}" style="width: 150px;">
				</div>
				<div class="form-group form-inline ">
					<label class="col-sm-5 hlabel" style="font-size: 12px">기획판매일</label> 
					<input id="giPanDt" name="giPanDt" class="form-control" type="date" 
						   value="${jepum.giPanDt}" style="width: 150px;">
				</div>
			</div>
			<div class="form-group col-sm-8">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th class="text-c col-sm-4 tbcolor">구분</th>
							<th class="text-c col-sm-4 tbcolor">기획</th>
							<th class="text-c col-sm-4 tbcolor">획정</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td class="text-c tbcolor pt13">원자재 비</td>
							<td><input id="giWonjaje" name="giWonjaje" class="form-control" style="width: 160px; text-align:right; padding-right: 30px;"
								value="${giWonjaje}" > </td>
							<td><input id="wonjaje" name="wonjaje" class="form-control text-l" style="width: 160px; text-align:right; padding-right: 30px;"
								value="${wonjaje}" readonly="readonly" >  </td>
						</tr>
						<tr>
							<td class="text-c tbcolor pt13">부자재 비</td>
							<td><input id="giBujaje" name="giBujaje" class="form-control" style="width: 160px; text-align:right; padding-right: 30px;" 
								value="${giBujaje }"></td>
							<td><input id="bujaje" name="bujaje" class="form-control" style="width: 160px; text-align:right; padding-right: 30px;" 
								value="${bujaje }" readonly="readonly"></td>
						</tr>
						<tr>
							<td class="text-c tbcolor pt13">임봉료 비</td>
							<td><input id="giImbong" name="giImbong" class="form-control" style="width: 160px; text-align:right; padding-right: 30px;" 
								value="${giImbong }"></td>
							<td><input id="imbong" name="imbong" class="form-control" style="width: 160px; text-align:right; padding-right: 30px;" 
								value="${imbong }" readonly="readonly"></td>
						</tr>
						<tr>
							<td class="text-c tbcolor pt13">원가 합계</td>
							<td><input id="giWon" name="giWon" class="form-control" style="width: 160px; text-align:right; padding-right: 30px;"
								value="${giWon }" readonly="readonly"></td>
							<td><input id="won" name="won" class="form-control" style="width: 160px; text-align:right; padding-right: 30px;" 
								value="${won }" readonly="readonly"></td>
						</tr>
						<tr>
							<td class="text-c tbcolor pt13">소비자가</td>
							<td><input id="giSobi" name="giSobi" class="form-control" style="width: 160px; text-align:right; padding-right: 30px;" 
							    value="${giSobi}"> </td>
							<td><input id="sobi" name="sobi" class="form-control" style="width: 160px; text-align:right; padding-right: 30px;" 
								value="${sobi}" readonly="readonly"> </td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>

</div>
</body>
</html>