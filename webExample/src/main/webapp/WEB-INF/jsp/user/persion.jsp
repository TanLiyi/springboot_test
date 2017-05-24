<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="keywords" content="" />
<title>个人资料</title>
<link href="${pageContext.request.contextPath}/tan/css/bootstrap.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/tan/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/tan/css/font-awesome.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/tan/css/admin.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/tan/css/amazeui.css"
	rel="stylesheet" type="text/css">

<link href="${pageContext.request.contextPath}/tan/css/personal.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/tan/css/infstyle.css"
	rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/tan/css/jquery.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/tan/css/amazeui.js"
	type="text/javascript"></script>
</head>
<script type="text/javascript">
	var message="${message}";
	if(message!=null&&message!=""){
		alert(message);
	}
</script>
<body>
	<div class="agileits_header">
		<div class="container">
			<div class="w3l_offers"></div>
			<div class="agile-login">
				<ul>
					<c:choose>
						<c:when test="${user.id ==null}">
							<li><a href="/registered"> 注册 </a></li>
							<li><a href="/login">登录</a></li>
						</c:when>
						<c:when test="${user.id !=null}">
							<li><a href="/user/persion?id=${user.id}">${user.username}</a></li>
							<li><a href="/loginout">注销</a></li>
						</c:when>
					</c:choose>
				</ul>
			</div>

			<div class="product_list_header">
				<form action="/cart/" method="post" onsubmit="return cart();">
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
					<a href="index">网络在线购物商城</a>
				</h1>
			</div>
			<div class="w3l_search">
				<form action="/products" method="post">
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
					<li class="active"><a href="/index" class="act">主页</a></li>
					<c:forEach var="/category" items="${categorys}">
						<li class="active"><a href="/products?id=${category.id }"
							class="act">${category.name }</a></li>

					</c:forEach>
				</ul>
			</div>
			</nav>
		</div>
	</div>
	<div class="center">
		<div class="col-main">
			<div class="main-wrap">
				<div class="user-info">
					<!--标题 -->
					<div class="am-cf am-padding">
						<div class="am-fl am-cf">
							<strong class="am-text-danger am-text-lg">个人资料</strong>
						</div>
					</div>
					<hr>

					<div class="user-infoPic">
						<div class="filePic">
							<input type="file" class="inputPic"
								allowexts="gif,jpeg,jpg,png,bmp" accept="image/*"> <img
								class="am-circle am-img-thumbnail"
								src="${pageContext.request.contextPath}/tan/images/getAvatar.do.jpg"
								alt="">
						</div>

						<p class="am-form-help">头像</p>

						<div class="info-m">
							<div>
								<b>用户名：<i>${info.username }</i></b>
							</div>
							<div class="u-level">
								<span class="rank r2"> <s class="vip1"></s><a
									class="classes" href="#">等级&nbsp;${info.leaveName }</a>
								</span>
							</div>
							<div class="u-safety">
								<a href="#"> 拥有积分&nbsp;${info.point } <span
									class="u-profile"><i class="bc_ee0000"
										style="width: 60px;" width="0"></i></span>
								</a>
							</div>
						</div>
						<div class="filePic"></div>
						<div class="filePic"></div>
						<div class="filePic"></div>
						<div class="filePic">
							<h5>今日签到:</h5>
							<c:choose>
								<c:when test="${info.status==0 }">
									<div class="filePic">
										<a href="sigup?id=${info.id }"><img
											class="am-circle am-img-thumbnail"
											src="${pageContext.request.contextPath}/tan/images/qiadao.jpg"
											alt=""></a>
									</div>
								</c:when>
								<c:when test="${info.status==1 }">
									<div class="filePic">
										<input type="file" class="inputPic"
											allowexts="gif,jpeg,jpg,png,bmp" accept="image/*"> <img
											class="am-circle am-img-thumbnail"
											src="${pageContext.request.contextPath}/tan/images/sigup.jpg"
											alt="">
									</div>
								</c:when>
							</c:choose>
						</div>
					</div>
					<div class="info-main">
						<form class="am-form am-form-horizontal" action="update"
							method="post">
							<input type="hidden" name="id" value="${info.id }">
							<div class="am-form-group">
								<label for="user-name2" class="am-form-label">昵称</label>
								<div class="am-form-content">
									<input type="text" name="name" value="${info.username }"
										placeholder="用户名">

								</div>
							</div>
							<div class="am-form-group">
								<label class="am-form-label">性别</label>
								<div class="am-form-content sex">
									<label class="am-radio-inline"> <input type="radio"
										name="sex" value="1" data-am-ucheck="" class="am-ucheck-radio"><span
										class="am-ucheck-icons"><i class="am-icon-unchecked"></i><i
											class="am-icon-checked"></i></span> 男
									</label> <label class="am-radio-inline"> <input type="radio"
										name="sex" value="0" data-am-ucheck="" class="am-ucheck-radio"><span
										class="am-ucheck-icons"><i class="am-icon-unchecked"></i><i
											class="am-icon-checked"></i></span> 女
									</label>
								</div>
							</div>

							<div class="am-form-group">
								<label for="tel" class="am-form-label">电话</label>
								<div class="am-form-content">
									<input id="user-phone" name="tel" value="${info.tel }"
										placeholder="11位的电话号码" type="tel">
								</div>
							</div>
							<div class="info-btn">
								<input type="submit" value="保存修改" />
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
		<aside class="menu">
		<ul>
			<li class="person"><a href="#">个人中心</a></li>
			<ul>
				<li class="active"><a href="/user/persion?id=${user.id }">个人信息</a></li>
				<li><a href="/address/?id=${user.id }">收货地址</a></li>
			</ul>
			</li>
			<li class="person"><a href="/information">我的交易</a>
				<ul>
					<li><a href="/orders">订单管理</a></li>
				</ul></li>
		</ul>
		</aside>
	</div>
	<div class="clearfix"></div>
	<div class="footer-botm">
		<div class="container">
			<div class="w3layouts-foot">
				<ul>
					<li><a href="#" class="w3_agile_vimeo"><i
							class="fa fa-vimeo" aria-hidden="true"></i></a></li>
				</ul>
			</div>
			<div class="payment-w3ls">
				<img src="${pageContext.request.contextPath}/tan/images/card.png"
					alt=" " class="img-responsive">
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/tan/js/bootstrap.min.js"></script>
	<script type="${pageContext.request.contextPath}/tan/text/javascript">
		$(document).ready(function() {
								
			$().UItoTop({ easingType: 'easeOutQuart' });
								
			});
	</script>
	<script src="${pageContext.request.contextPath}/tan/js/minicart.min.js"></script>
	<script>
		// Mini Cart
		paypal.minicart.render({
			action : '#'
		});

		if (~window.location.search.indexOf('reset=true')) {
			paypal.minicart.reset();
		}
		
	</script>
	<script
		src="${pageContext.request.contextPath}/tan/js/skdslider.min.js"></script>
	<link href="${pageContext.request.contextPath}/tan/css/skdslider.css"
		rel="stylesheet">
	<script type="text/javascript">
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
	</script>
</body>
</html>