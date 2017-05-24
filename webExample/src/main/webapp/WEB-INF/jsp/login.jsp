<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="text/javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="${pageContext.request.contextPath}/tan/css/bootstrap.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/tan/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/tan/css/font-awesome.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/tan/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/tan/js/move-top.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/tan/js/easing.js"></script>

<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
	$("#toTopHover").UItoTop({ easingType: 'easeOutQuart' });
	
	function cart() {
		debugger;
		var username="${user==null?'':user.username}";
		if( username=null||username=='') {
			var c=confirm("您还未登录，请先登录");
			if(c!=null){
				return false;
			}
		}
	}
	var messsage="${message}";
	console.log(messsage);
	if(messsage!=null&&messsage!=""){
		alert(JSON.stringify(messsage));
	}
	
	function check() {
		var username = regForm.username.value;
		var password = regForm.password.value;
		debugger;
		if(username.length > 16) {
			alert("用户名不能超过16字符长度！");
			return false;
		}
		if ( password.length>16) { 
			alert("密码长度最大限制为16！");
			return false;
		}
	}
	
	
 </script>
</head>

<body>
	<div class="agileits_header">
		<div class="container">
			<div class="w3l_offers"></div>
			<div class="agile-login">
				<ul>
					<c:choose>
						<c:when test="${user ==null}">
							<li><a href="user/registered"> 注册 </a></li>
							<li><a href="login">登录</a></li>
						</c:when>
						<c:when test="${user!=null&&user.id !=null}">
							<li><a href="user/persion?id=${user.id}">${user.username}</a></li>
							<li><a href="loginout">注销</a></li>
						</c:when>
					</c:choose>
				</ul>
			</div>

			<div class="product_list_header">
				<form action="/cart/" method="post"  onsubmit="return cart();">
					<button class="w3view-cart" type="submit" name="submit" value="">
						<i class="fa fa-cart-arrow-down" aria-hidden="true"></i>
					</button>
				</form>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>

	<div class="logo_products">
		<div class="container">
			<div class="w3ls_logo_products_left1"></div>
			<div class="w3ls_logo_products_left">
				<h1>
					<a href="index">网上购物在线购物商城</a>
				</h1>
			</div>
			<div class="w3l_search">
				<form action="products" method="post">
					<input type="search" name="search" placeholder="请输入商品名称"
						required="">
					<button type="submit" class="btn btn-default search"
						aria-label="Left Align">
						<i class="fa fa-search" aria-hidden="true"> </i>
					</button>
					<div class="clearfix"></div>
				</form>
			</div>

			<div class="clearfix"></div>
		</div>
	</div>
	<div class="navigation-agileits">
		<div class="container">
			<nav class="navbar navbar-default"> <!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header nav_2">
				<button type="button" class="navbar-toggle collapsed navbar-toggle1"
					data-toggle="collapse" data-target="#bs-megadropdown-tabs">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
				<ul class="nav navbar-nav">
					<li class="active"><a href="index" class="act">主页</a></li>
					<!-- Mega Menu -->
					<c:forEach var="category" items="${categorys}">
						<li class="active"><a href="products?id=${category.id }"
							class="act">${category.name }</a></li>

					</c:forEach>
				</ul>
			</div>
			</nav>
		</div>
	</div>

	<div class="breadcrumbs">
		<div class="container">
			<ol class="breadcrumb breadcrumb1 animated wow slideInLeft"
				data-wow-delay=".5s">
				<li><a href="index.html"><span
						class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>
				<li class="active">Login Page</li>
			</ol>
		</div>
	</div>
	<!-- login -->
	<div class="login">
		<div class="container">
			<h2>Login</h2>

			<div class="login-form-grids animated wow slideInUp"
				data-wow-delay=".5s">
				<form id="myForm" action="/login" method="post"
					onsubmit="return check();">
					<input type="text" name="username" placeholder="请输入用户名"
						required=" "> <input type="password" name="password"
						placeholder="请输入密码" required=" "> <input type="submit"
						value="登录">
				</form>
			</div>
			<h4>For New People</h4>
			<p>
				<a href="registered.html">Register Here</a> (Or) go back to <a
					href="index.html">Home<span
					class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></a>
			</p>
		</div>
	</div>
	<div class="footer">
		<div class="container">
			<div class="w3_footer_grids">
				<div class="col-md-3 w3_footer_grid">
					<h3>Contact</h3>

					<ul class="address">
						<li><i class="glyphicon glyphicon-map-marker"
							aria-hidden="true"></i>1234k Avenue, 4th block, <span>New
								York City.</span></li>
						<li><i class="glyphicon glyphicon-envelope"
							aria-hidden="true"></i><a href="mailto:info@example.com">info@example.com</a></li>
					</ul>
				</div>
				<div class="col-md-3 w3_footer_grid">
					<h3>Information</h3>
					<ul class="info">
						<li><i class="fa fa-arrow-right" aria-hidden="true"></i><a
							href="products.html">Special Products</a></li>
					</ul>
				</div>
				<div class="col-md-3 w3_footer_grid">
					<h3>Category</h3>
					<ul class="info">
						<li><i class="fa fa-arrow-right" aria-hidden="true"></i><a
							href="beverages.html">Beverages</a></li>
					</ul>
				</div>
				<div class="col-md-3 w3_footer_grid">
					<h3>Profile</h3>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>

		<div class="footer-copy">

			<div class="container">
				<p>
					Copyright &copy; 2017.Company name All rights reserved.<a
						target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>
				</p>
			</div>
		</div>

	</div>
	<div class="footer-botm">
		<div class="container">
			<div class="w3layouts-foot">
				<ul>
					<li><a href="#" class="w3_agile_facebook"><i
							class="fa fa-facebook" aria-hidden="true"></i></a></li>
					<li><a href="#" class="agile_twitter"><i
							class="fa fa-twitter" aria-hidden="true"></i></a></li>
					<li><a href="#" class="w3_agile_dribble"><i
							class="fa fa-dribbble" aria-hidden="true"></i></a></li>
					<li><a href="#" class="w3_agile_vimeo"><i
							class="fa fa-vimeo" aria-hidden="true"></i></a></li>
				</ul>
			</div>
			<div class="payment-w3ls">
				<img src="${pageContext.request.contextPath}/tan/images/card.png" alt=" " class="img-responsive">
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
</body>
</html>