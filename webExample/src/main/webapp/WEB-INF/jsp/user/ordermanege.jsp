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
<link href="${pageContext.request.contextPath}/tan/css/orstyle.css"
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
					<c:forEach var="category" items="${categorys}">
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
				<div class="user-order">
					<div class="am-cf am-padding">
						<div class="am-fl am-cf">
							<strong class="am-text-danger am-text-lg">订单管理</strong> / <small>Order</small>
						</div>
					</div>
					<hr>

					<div class="am-tabs am-tabs-d2 am-margin" data-am-tabs="">

						<ul class="am-avg-sm-5 am-tabs-nav am-nav am-nav-tabs">
							<li class="am-active"><a href="">所有订单</a></li>
						</ul>
						<div class="am-tabs-bd">
							<div class="am-tab-panel am-fade am-in am-active" id="tab1">
								<div class="order-top">
									<div class="th th-item">
										<td class="td-inner">商品</td>
									</div>
									<div class="th th-price">
										<td class="td-inner">单价</td>
									</div>
									<div class="th th-number">
										<td class="td-inner">数量</td>
									</div>
									<div class="th th-operation">
										<td class="td-inner">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
									</div>
									<div class="th th-amount">
										<td class="td-inner">合计</td>
									</div>
									<div class="th th-status">
										<td class="td-inner">交易状态</td>
									</div>
									<div class="th th-change">
										<td class="td-inner">交易操作</td>
									</div>
								</div>

								<div class="order-main">

									<div class="order-list">
										<c:forEach var="order" items="${list }">
											<div class="order-status5">
												<div class="order-title">
													<div class="dd-num">
														订单编号：<a href="javascript:;">${order.orderCode }</a>
													</div>
													<span>成交时间：</span>${order.createTime }
												</div>
												<div class="order-content">
													<div class="order-left">
													<c:forEach var="g" items="${order.goods }">
														<ul class="item-list">
															<li class="td td-item">
																<div class="item-pic">
																	<a href="#" class="J_MakePoint"> <img
																		src="${pageContext.request.contextPath}/tan/images/${g.goods.pic }"
																		class="itempic J_ItemImg">
																	</a>
																</div>
																<div class="item-info">
																	<div class="item-basic-info">
																		<a href="#">
																			<p>${g.goods.name }</p>
																		</a>
																	</div>
																</div>
															</li>
															<li class="td td-price">
																<div class="item-price">${g.goods.salePrice }</div>
															</li>
															<li class="td td-number">
																<div class="item-number">
																	<span>×</span>${g.buyCount }
																</div>
															</li>
															<li class="td td-operation">
																<div class="item-operation"></div>
															</li>
														</ul>
														</c:forEach>
													</div>
													<div class="order-right">
														<li class="td td-amount">
															<div class="item-amount">
																合计：<span>${order.total }</span>
																<p>
																	优惠：<span>${order.desc }</span>
																</p>
															</div>
														</li>
														<div class="move-right">
															<li class="td td-status">
																<div class="item-status">
																<c:choose>
																<c:when test="${order.status==0 }">
																<p class="Mystatus">待确认付款</p>
																</c:when>
																<c:when test="${order.status==1 }">
																<p class="Mystatus">已支付</p>
																</c:when>
																<c:when test="${order.status==2 }">
																<p class="Mystatus">待收货</p>
																</c:when>
																<c:when test="${order.status==3 }">
																<p class="Mystatus">已完成</p>
																</c:when>
																</c:choose>
																	<p class="order-info">
																		<a href="/order/detail?id=${order.id }">订单详情</a>
																	</p>
																	
																</div>
															</li>
															<li >
																<a href="/order/del?id=${order.id }">删除订单</a>
															</li>
														</div>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<aside class="menu">
		<ul>
			<li class="person"><a href="#">个人中心</a></li>
			<ul>
				<li><a href="/user/persion?id=${user.id }">个人信息</a></li>
				<li class="active"><a href="/address/?id=${user.id }">收货地址</a></li>
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
		function cart() {
			debugger;
			var username = "${user==null?'':user.username}";
			if (username = null || username == '') {
				var c = confirm("您还未登录，请先登录");
				if (c != null) {
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