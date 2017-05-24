<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/tan/css/pintuer.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/tan/css/admin1.css" />
<script src="${pageContext.request.contextPath}/tan/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/tan/js/pintuer.js"></script>
</head>
<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong><span class="icon-key"></span> 会员信息</strong>
		</div>
		<div class="body-content">
			<form method="post" class="form-x"
				action="${user!=null?'update':'create'}">
				<div class="form-group">
					<c:if test="${user.id != null}">
						<div class="field">
							<label style="line-height: 33px;" th:text="${user.id}"> </label>
							<input type="hidden" name="id" value="${user.id}" />
						</div>
					</c:if>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="sitename">用户名 ：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" name="username" size="50"
							value="${user!=null?user.username:''}" placeholder="请输入用户名"
							data-validate="required:请输入用户名" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label>性别：</label>
					</div>
					<div class="field">
						<div class="button-group radio">
							<label><input type="radio" name="sex"
								checked="${(user!=null and user.sex eq 1)?'true':'false'}"
								value="1" />男</label> 
							<label><input type="radio" name="sex"
								checked="${(user!=null and user.sex eq 0)?'true':'false'}"
								value="0" />女</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="sitename">联系电话 ：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" name="tel" size="50"
							value="${user!=null?user.tel:''}" placeholder="请输入联系电话"
							data-validate="required:请输入联系电话" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label></label>
					</div>
					<div class="field">
						<button class="button button-little icon-check-square-o"
							type="submit">提交</button>
						<a class="button button-little border-green"
							href="javascript:history.back()"> <span class="icon-backward"></span>
							返回
						</a>
					</div>
				</div>
		</div>

		</form>
	</div>
	</div>
</body>
</html>