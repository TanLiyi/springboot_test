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
<title>后台管理中心</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/tan/css/pintuer.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/tan/css/admin1.css" />
<script src="${pageContext.request.contextPath}/tan/js/jquery.js"></script>
</head>
<body style="background-color: #f2f9fd;">
	<div class="header bg-main">
		<div class="logo margin-big-left fadein-top">
			<h1>
				<img src="${pageContext.request.contextPath}/tan/images/y.jpg" class="radius-circle rotate-hover"
					height="50" alt="" />网络在线购物系统管理系统
			</h1>
		</div>
		<div class="head-l">
			 <a class="button button-little bg-red" href="logout"><span
				class="icon-power-off"></span> 退出登录</a>
		</div>
	</div>
	<div class="leftnav">
		<div class="leftnav-title">
			<strong><span class="icon-list"></span>导航栏</strong>
		</div>
		<h2>
			<span class="icon-user"></span>会员管理
		</h2>
		<ul style="">
			<li><a href="member/index" target="right"><span
					class="icon-caret-right"></span>会员管理</a></li>
		</ul>
		<h2>
			<span class="icon-pencil-square-o"></span>商品管理
		</h2>
		<ul>
			<li><a href="good/index" target="right"><span
					class="icon-caret-right"></span>商品管理</a></li>
			<li><a href="category/index" target="right"><span
					class="icon-caret-right"></span>分类管理</a></li>	
		</ul>
		<h2>
			<span class="icon-pencil-square-o"></span>订单管理
		</h2>
		<ul>
			<li><a href="/admin/order/list" target="right"><span
					class="icon-caret-right"></span>订单管理</a></li>
		</ul>
		<h2>
			<span class="icon-database"></span>基本设置
		</h2>
		<ul>
			<li><a href="level/list" target="right"><span
					class="icon-caret-right"></span>经验等级管理</a></li>
		</ul>
	</div>
	<script type="text/javascript">
		$(function() {
			$(".leftnav h2").click(function() {
				$(this).next().slideToggle(200);
				$(this).toggleClass("on");
			})
			$(".leftnav ul li a").click(function() {
				$("#a_leader_txt").text($(this).text());
				$(".leftnav ul li a").removeClass("on");
				$(this).addClass("on");
			})
		});
	</script>
	<ul class="bread">
		<li><a href="#" target="right" class="icon-home">
				首页</a></li>
	</ul>
	<div class="admin">
		<iframe scrolling="auto" rameborder="0" src="" name="right"
			width="100%" height="100%"></iframe>
	</div>
</body>
</html>