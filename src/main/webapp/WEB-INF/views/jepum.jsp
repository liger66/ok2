<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />
</head>

<script type="text/javascript">
	function init() {
		location.href = "${pageContext.request.contextPath}/jepum";
	}

	function mnew() {
		location.href = "${pageContext.request.contextPath}/jepum/mnew";
	}

	function insert() {
		var form = document.getElementById("mainform");
		form.submit();
	}
	<c:forEach var="error" items="${errors}">
		alert("${error.defaultMessage}");
	</c:forEach>
</script>
<body>

	<div class="container">
		<div class="col-sm-8">
			<h3 class="title text-center">제품 코드 등록</h3>
		</div>
		<div class="col-sm-4" style="majin-right: 0px; padding-right: 0px;">
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
			<form class="form-horizontal ">
				<div class="form-group form-inline">
					<label class="col-sm-6 control-label">브랜드 코드</label> <select
						id="sBrand" name="sBrand" class="form-control"
						style="width: 120px;">
						<c:forEach var="sb" items="${sBrandList }">
							<option value="${sb.lGroupCd}"
								<c:if test="${tUser.brand == sb.lGroupCd}"> selected</c:if>>${sb.lGroupNm}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group form-inline">
					<label class="col-sm-6 control-label">기획 년도</label> <select
						id="sGiYY" name="sGiYY" class="form-control" style="width: 120px;">
						<c:forEach var="sg" items="${sGiYYList }">
							<option value="${sg.lGroupCd}"
								<c:if test="${tUser.giYY == sg.lGroupCd}"> selected</c:if>>${sg.lGroupNm}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group form-inline">
					<label class="col-sm-6 control-label">시 즌</label> <select
						id="sSeason" name="sSeason" class="form-control"
						style="width: 120px;">
						<c:forEach var="ss" items="${sSeasonList }">
							<option value="${ss.lGroupCd}"
								<c:if test="${tUser.season == ss.lGroupCd}"> selected</c:if>>${ss.lGroupNm}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group form-inline">
					<label class="col-sm-6 control-label">품 종</label> <select
						id="sPumjong" name="sPumjong" class="form-control"
						style="width: 120px;">
						<option value="all" selected>전체</option>
						<c:forEach var="sp" items="${sPumjongList }">
							<option value="${sp.pumCd}">${sp.pumNm}</option>
						</c:forEach>
					</select>
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
						<tr>
							<td class="text-c"><a href="#"
								onclick="selectgubunCd(this); return false;">${jp.jepumCd}</a></td>
							<td>${jp.sobi}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="container col-sm-9">
			<f:form id="mainform" action="/jepum/insert" method="post"
				modelAttribute="jepum">
				<div class="row">
					<div class="form-group col-sm-4">
						<div class="form-group form-inline ">
							<label class="col-sm-6 hlabel">제품 코드</label>
							<f:input path="jepum" class="form-control iup"
								style="width:120px; font-weight:bold;" value="${jepum.jepum }" />
						</div>
						<div class="text-center">
							<f:errors path="jepum" class="error" />
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
							<label class="col-sm-6 hlabel">브랜드</label> <input id="brand"
								name="brand" class="form-control" style="width: 120px;"
								value="${brand}" readonly="readonly">
						</div>
						<div class="form-group form-inline ">
							<label class="col-sm-6 hlabel">제품구분</label> <select id="jepumGb"
								name="jepumGb" class="form-control" style="width: 120px;">
								<c:forEach var="mj" items="${jepumGbList }">
									<option value="${mj.lGroupCd}">${mj.lGroupNm}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group form-inline ">
							<label class="col-sm-6 hlabel">마진구분</label> <select id="majinGb"
								name="majinGb" class="form-control" style="width: 120px;">
								<c:forEach var="mm" items="${majinGbList }">
									<option value="${mm.lGroupCd}">${mm.lGroupNm}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group col-sm-4">
						<div class="form-group form-inline ">
							<label class="col-sm-6 hlabel">기획년도</label> <input id="giYY"
								name="giYY" class="form-control" style="width: 120px;"
								value="${giYY}" readonly="readonly">
						</div>
						<div class="form-group form-inline ">
							<label class="col-sm-6 hlabel">기획구분</label> <select id="giGb"
								name="giGb" class="form-control" style="width: 120px;">
								<c:forEach var="mg" items="${giGbList }">
									<option value="${mg.lGroupCd}">${mg.lGroupNm}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group form-inline ">
							<label class="col-sm-6 hlabel">소재구분</label> <select id="sojeGb"
								name="sojeGb" class="form-control" style="width: 120px;">
								<c:forEach var="ms" items="${sojeGbList }">
									<option value="${ms.lGroupCd}">${ms.lGroupNm}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group col-sm-4">
						<div class="form-group form-inline ">
							<label class="col-sm-6 hlabel">계 절</label> <input id="season"
								name="season" class="form-control" value="${season}"
								style="width: 120px;" readonly="readonly">
						</div>
						<div class="form-group form-inline ">
							<label class="col-sm-6 hlabel">품 종</label> <input id="pum"
								name="pum" class="form-control" style="width: 120px;"
								value="${pum}" readonly="readonly">
						</div>
						<div class="form-group form-inline ">
							<label class="col-sm-6 hlabel">가격구분</label> <select id="priceGb"
								name="priceGb" class="form-control" style="width: 120px;">
								<c:forEach var="mp" items="${priceList }">
									<option value="${mp.lGroupCd}">${mp.lGroupNm}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>



				<div class="form-group col-sm-4">
					<div class="form-group form-inline ">
						<label class="col-sm-6 hlabel">사이즈 그룹</label> <select id="sizGr"
							name="sizGr" class="form-control" style="width: 120px;">
							<c:forEach var="mz" items="${sizGrList }">
								<option value="${mz.lGroupCd}">${mz.lGroupNm}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group form-inline ">
						<label class="col-sm-6 hlabel">생산형태</label> <select id="sengHt"
							name="sengHt" class="form-control" style="width: 120px;">
							<c:forEach var="mh" items="${sengHtList }">
								<option value="${mh.lGroupCd}">${mh.lGroupNm}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group form-inline ">
						<label class="col-sm-6 hlabel">사입판매여부</label> <select id="saipYN"
							name="saipYN" class="form-control" style="width: 120px;">
							<option value="N" selected>No</option>
							<option value="Y">Yes</option>
						</select>
					</div>
					<div class="form-group form-inline ">
						<label class="col-sm-5 hlabel" style="font-size: 12px">기획
							입고일</label> <input id="giIpgoDt" name="giIpgoDt" class="form-control"
							type="date" value="${jepum.giIpgoDt}" style="width: 150px;">
					</div>
					<div class="form-group form-inline ">
						<label class="col-sm-5 hlabel" style="font-size: 12px">기획
							판매일</label> <input id="giPanDt" name="giPanDt" class="form-control"
							type="date" value="${jepum.giPanDt}" style="width: 150px;">
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
								<td><input id="jepum1" name="jepum1" class="form-control"
									style="width: 160px;" value="${giWonjaje }"></td>
								<td><input id="jepum2" name="jepum2" class="form-control"
									style="width: 160px;" value="${wonjaje }"></td>
							</tr>
							<tr>
								<td class="text-c tbcolor pt13">부자재 비</td>
								<td><input id="jepum3" name="jepum3" class="form-control"
									style="width: 160px;" value="${giBujaje }"></td>
								<td><input id="jepum4" name="jepum4" class="form-control"
									style="width: 160px;" value="${bujaje }"></td>
							</tr>
							<tr>
								<td class="text-c tbcolor pt13">임봉료 비</td>
								<td><input id="jepum5" name="jepum5" class="form-control"
									style="width: 160px;" value="${giImbong }"></td>
								<td><input id="jepum6" name="jepum6" class="form-control"
									style="width: 160px;" value="${imbong }"></td>
							</tr>
							<tr>
								<td class="text-c tbcolor pt13">원가 합계</td>
								<td><input id="jepum7" name="jepum7" class="form-control"
									style="width: 160px;" value="${giWon }"></td>
								<td><input id="jepum8" name="jepum8" class="form-control"
									style="width: 160px;" value="${won }"></td>
							</tr>
							<tr>
								<td class="text-c tbcolor pt13">소비자가</td>
								<td><input id="jepum9" name="jepum9" class="form-control"
									style="width: 160px;" value="${giSobi }"></td>
								<td><input id="jepum10" name="jepum10" class="form-control"
									style="width: 160px;" value="${sobi }"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</f:form>
		</div>

	</div>
</body>
</html>