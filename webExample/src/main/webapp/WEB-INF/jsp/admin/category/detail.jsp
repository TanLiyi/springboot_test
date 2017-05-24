<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit" />
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/tan/css/pintuer.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/tan/css/admin1.css" />
<script src="${pageContext.request.contextPath}/tan/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/tan/js/pintuer.js"></script>
</head>

<body>
	<div class="panel admin-panel">
		<div class="panel-head">
			<strong><span class="icon-key"></span> 编辑分类信息</strong>
		</div>
		<div class="body-content">
			<form method="post" class="form-x" action="${category!=null?'update':'create'}" method="post">
				<div class="form-group" >
					<c:if test="${category.id != null}">
					<div class="field">
						<label style="line-height: 33px;" th:text="${category.id}">
						</label> <input type="hidden" name="id" value="${category.id}" />
					</div>
					</c:if>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="sitename">名称 ：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" name="name" size="50"
							value="${category!=null?category.name:''}" placeholder="请输入名称"
							data-validate="required:请输入名称" />
					</div>
				</div>
				<div class="form-group">
					<div class="label">
						<label for="sitename">描述 ：</label>
					</div>
					<div class="field">
						<input type="text" class="input w50" name="desc" size="100"
							value="${category!=null?category.desc:''}" placeholder="请输入分类描述"
							data-validate="required:请输入分类描述" />
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
			</form>
		</div>
	</div>
</body>
</html>