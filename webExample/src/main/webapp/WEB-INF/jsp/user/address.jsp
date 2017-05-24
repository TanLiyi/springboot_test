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
<link href="${pageContext.request.contextPath}/tan/css/addstyle.css"
	rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/tan/css/jquery.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/tan/css/amazeui.js"
	type="text/javascript"></script>
</head>
<script type="text/javascript">
	var message = "${message}";
	if (message != null && message != "") {
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
					<c:forEach var="category" items="${categorys}">
						<li class="active"><a href="/products?id=${category.id }"
							class="act">${category.name }</a></li>

					</c:forEach>
				</ul>
			</div>
			</nav>
		</div>
	</div>
	<b class="line"></b>
	<div class="center">
		<div class="col-main">
			<div class="main-wrap">
				<div class="user-address">
					<div class="am-cf am-padding">
						<div class="am-fl am-cf">
							<strong class="am-text-danger am-text-lg">我的地址</strong>
						</div>
					</div>
					<hr>
					<ul class="am-avg-sm-1 am-avg-md-3 am-thumbnails">
						<c:forEach var="address" items="${list}">
							<c:choose>
								<c:when test="${address.isDefault==1 }">
									<li class="user-addresslist defaultAddr">
								</c:when>
								<c:when test="${address.isDefault==0 }">
									<li class="user-addresslist">
								</c:when>
							</c:choose>
							<span class="new-option-r"><i class="am-icon-check-circle"></i>
							<a href="/address/default?id=${address.id }">默认地址</span>
							<p class="new-tit new-p-re">
								<span class="new-txt">${ address.nickName}</span> <span
									class="new-txt-rd2">${address.tel }</span>
							</p>
							<div class="new-mu_l2a new-p-re">
								<p class="new-mu_l2cw">
									<span class="title">地址：</span> <span class="province">${address.pri }</span>
									<span class="city">${address.ciyt }</span> <span class="dist">${address.town }</span>
									<span class="street">${address.street }</span>
								</p>
							</div>
							<div class="new-addr-btn">
								<a href="/address/updateDrup?id=${address.id }"><i
									class="am-icon-edit"></i>编辑</a> <span class="new-addr-bar">|</span>
								<a href="/address/del?id=${address.id }"><i
									class="am-icon-trash"></i>删除</a>
							</div>
							</li>
						</c:forEach>
					</ul>
					<div class="clear"></div>
					<a class="new-abtn-type"
						data-am-modal="{target: &#39;#doc-modal-1&#39;, closeViaDimmer: 0}">编辑地址</a>
					<div class="" id="doc-modal-1">
						<div class="add-dress">
							<div class="am-cf am-padding">
								<div class="am-fl am-cf">
									<strong class="am-text-danger am-text-lg">编辑地址</strong>
								</div>
							</div>
							<hr>
							<div class="am-u-md-12 am-u-lg-8" style="margin-top: 20px;">
								<form class="am-form am-form-horizontal"
									action="/address/${add==null?'save':'update' }" method="post">

									<div class="am-form-group">
										<label for="user-name" class="am-form-label">收货人</label>
										<div class="am-form-content">
											<input type="text" name="nickName" value="${detail.nickName}"
												placeholder="收件人姓名">
										</div>
									</div>

									<div class="am-form-group">
										<label for="user-phone" class="am-form-label">手机号码</label>
										<div class="am-form-content">
											<input id="tel" value="${detail.tel}" name="tel"
												placeholder="手机号必填" type="text">
										</div>
									</div>
									<div class="am-form-group">
										<label for="user-name" class="am-form-label">省份</label>
										<div class="am-form-content">
											<input type="text" name="pri" value="${detail.pri}"
												placeholder="所属省份">
										</div>
									</div>
									<div class="am-form-group">
										<label for="user-name" class="am-form-label">城市</label>
										<div class="am-form-content">
											<input type="text" name="ciyt" name="${detail.ciyt}"
												placeholder="所属城市">
										</div>
									</div>
									<div class="am-form-group">
										<label for="user-name" class="am-form-label">区域</label>
										<div class="am-form-content">
											<input type="text" name="town" value="${detail.town}"
												placeholder="所属区域">
										</div>
									</div>

									<div class="am-form-group">
										<label for="user-intro" class="am-form-label">详细地址</label>
										<div class="am-form-content">
											<textarea class="" rows="3" name="street"
												value="${detail.street}" placeholder="输入详细地址"></textarea>
										</div>
									</div>
									<div>
										<input type="hidden" name="userId" value="${user.id}">
									</div>
									<div>
										<input type="hidden" name="id" value="${id}">
									</div>
									<div class="am-form-group">
										<div class="am-u-sm-9 am-u-sm-push-3">
											<input type="submit" vaule="保存"> <a
												href="javascript: void(0)"
												class="am-close am-btn am-btn-danger" data-am-modal-close="">取消</a>
										</div>
									</div>
								</form>
							</div>

						</div>

					</div>

				</div>
				<div class="clear"></div>
			</div>
		</div>
		<aside class="menu">
		<ul>
			<li class="person"><a href="#">个人中心</a></li>
				<ul>
					<li><a href="/user/persion?id=${user.id }">个人信息</a></li>
					<li class="active"><a href="/address/?id=${user.id }">收货地址</a></li>
				</ul></li>
			<li class="person"><a href="#">我的交易</a>
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
	<script
		src="${pageContext.request.contextPath}/tan/js/skdslider.min.js"></script>
	<link href="${pageContext.request.contextPath}/tan/css/skdslider.css"
		rel="stylesheet">
	<script type="text/javascript">
		console
	</script>
</body>
</html>