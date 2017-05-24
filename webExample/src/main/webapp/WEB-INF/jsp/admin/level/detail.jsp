<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/tan/css/bootstrap.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/tan/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/tan/css/font-awesome.css"
	rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
<div class="panel admin-panel">
		<div class="panel-head">
			<strong><span class="icon-key"></span> 编辑等级经验信息</strong>
		</div>
		</div>
	<div class="container">
		<div class="public-content-cont">
			<form action="${detail.id == null? 'create':'update' }" method="post" enctype="multipart/form-data"
				style="margin: 0 auto; width: 50%">
				<input type="hidden" value="${detail.id }" name="id" />
				<div class="field">
						<label style="line-height: 33px;" th:text="${detail.id}">
						</label> <input type="hidden" name="id" value="${detail.id}" />
					</div>
				
				<div class="form-group">
					<label for="">等级名称</label> <input class="form-input-txt"
						type="text" name="leaveName"
						value="${ detail != null ? detail.leaveName:'' }" />
				</div> 
				<div class="form-group">
					<label for="">最小经验</label> <input class="form-input-txt"
						type="text" name="minPoint"
						value="${ detail != null ? detail.minPoint:'' }" />
				</div>
				<div class="form-group">
					<label for="">优惠折扣</label>
					<input class="form-input-txt"
						type="text" name="baifen"
						value="${ detail != null ? detail.baifen:'' }" />
				</div>
				<div id="large"></div>
				<div class="form-group" style="margin-left: 150px;">
					<input type="submit" class="sub-btn" id="btn" value="提  交" /> <input
						type="reset" class="sub-btn" value="重  置" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>