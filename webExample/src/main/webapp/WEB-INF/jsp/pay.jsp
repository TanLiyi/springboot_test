<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Single Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<link href="${pageContext.request.contextPath}/tan/css/bootstrap.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/tan/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/tan/css/amazeui.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/tan/css/demo.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/tan/css/cartstyle.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/tan/css/jsstyle.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/tan/js/address.js.下载"></script>
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
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
</head>

<body style="overflow: visible;">
	<div class="agileits_header">
		<div class="container">
			<div class="w3l_offers"></div>
			<div class="agile-login">
				<ul>
					<c:choose>
						<c:when test="${user ==null}">
							<li><a href="registered"> 注册 </a></li>
							<li><a href="/login">登录</a></li>
						</c:when>
						<c:when test="${user!=null&&user.id !=null}">
							<li><a href="user/persion?id=${user.id}">${user.username}</a></li>
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
					<a href="index">网上在线购物商城</a>
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
	<div class="am-container header">
		<div class="clear"></div>
		<div class="concent">
			<div class="paycont">
				<div class="address">
					<h3>确认收货地址</h3>
					<!-- <div class="control selected">
						<div class="tc-btn createAddr theme-login am-btn am-btn-danger">使用新地址</div>
					</div> -->
					<div class="clear"></div>
					<ul>
						<c:forEach var="address" items="${list }">
							<div class="per-border"></div>
							<c:choose>
								<c:when test="${sureId!=null  }">
								 <c:if test="${sureId == address.id }">
									<li class="user-addresslist defaultAddr"></c:if>
									<c:if test="${sureId != address.id }">
									<li class="user-addresslist"></c:if>
								</c:when>
								<c:when test="${sureId==null }">
									<li class="user-addresslist">
								</c:when>
							</c:choose>
							<div class="address-left">
								<div class="user DefaultAddr">
									<span class="buy-address-detail"> <span class="buy-user">
											<a href="/sureAddress?id=${address.id }">${address.nickName }</a>
									</span> <span c class="buy-phone">${address.tel }</span>
									</span>
								</div>
								<div class="default-address">
									<span class="buy-line-title buy-line-title-type">收货地址：</span> <span
										class="buy--address-detail"> <span class="province">${address.pri }</span>
										<span class="city">${address.ciyt }</span> <span class="dist">${address.town }</span><span
										class="street">${address.street }</span>
									</span>
								</div>
								<c:if test="${address.isDefault==1 }">
									<ins class="deftip">默认地址</ins>
								</c:if>
							</div>
							<div class="address-right">
								<a href="#"> <span class="am-icon-angle-right am-icon-lg"></span>
							</div>
							<div class="clear"></div>

							<div class="new-addr-btn">
								<span class="new-addr-bar hidden">|</span> <a
									href="/address/payUpdate?id=${address.id }">编辑</a> <span
									class="new-addr-bar">|</span> <a
									href="/address/payDel?id=${address.id }">删除</a>
							</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
			<div class="concent">
				<div id="payTable">
					<h3>确认订单信息</h3>
					<div class="cart-table-th">
						<div class="wp">

							<div class="th th-item">
								<div class="td-inner">商品信息</div>
							</div>
							<div class="th th-price">
								<div class="td-inner">单价</div>
							</div>
							<div class="th th-amount">
								<div class="td-inner">数量</div>
							</div>
							<div class="th th-sum">
								<div class="td-inner">金额</div>
							</div>
							<div class="th th-oplist">
								<div class="td-inner">配送方式</div>
							</div>

						</div>
					</div>
					<div class="clear"></div>

					<c:forEach var="good" items="${pay.goods }">
						<div class="bundle  bundle-last">
							<div class="bundle-main">
								<ul class="item-content clearfix">
									<div class="pay-phone">
										<li class="td td-item">
											<div class="item-pic">
												<a href="" class="J_MakePoint"> <img src="${pageContext.request.contextPath}/tan/images/${good.pic }"
													 height="150"  width="150"class="itempic J_ItemImg"></a>
											</div>
											<div class="item-info">
												<div class="item-basic-info">
													<a href="" class="item-title J_MakePoint"
														data-point="tbcart.8.11">${good.goodName }</a>
												</div>
											</div>
										</li>
										<li class="td td-price">
											<div class="item-price price-promo-promo">
												<div class="price-content">
													<em class="J_Price price-now">${good.single }</em>
												</div>
											</div>
										</li>
									</div>
									<li class="td td-amount">
										<div class="amount-wrapper ">
											<div class="item-amount ">
												<span class="phone-title">购买数量</span>
												<div class="sl">${good.buyCount }</div>
											</div>
										</div>
									</li>
									<li class="td td-sum">
										<div class="td-inner">
											<em tabindex="0" class="J_ItemSum number">${good.price }</em>
										</div>
									</li>
									<li class="td td-oplist">
										<div class="td-inner">
											<span class="phone-title">配送方式</span>
											<div class="pay-logis">包邮</div>
										</div>
									</li>
								</ul>
								<div class="clear"></div>
							</div>
							<div class="clear"></div>
						</div>
					</c:forEach>

					<div class="clear"></div>
					<div class="pay-total">
						<!--留言-->
						<div class="order-extra">
							<div class="order-user-info">
								<div id="holyshit257" class="memo">
									<label>优惠描述：</label> <input type="text" name="favourable"
										value="${pay.favourable }"
										class="memo-input J_MakePoint c2c-text-default memo-close"
										readonly>
								</div>
							</div>

						</div>
						<div class="clear"></div>
					</div>
					<div class="buy-point-discharge ">
						<p class="price g_price ">
							合计（含运费） <span>¥</span><em class="pay-sum"><input type="text"
								name="total" value="${pay.total }" readonly></em>
						</p>
						<p class="price g_price ">
							实付款（含运费） <span>¥</span><em class="pay-sum"><input type="text"
								name="total" value="${pay.realPrice }" readonly></em>
						</p>
					</div>
					<div class="order-go clearfix">
						<div class="pay-confirm clearfix">
							<form action="/pay" method="post" onsubmit="return pay();">
								<input type="hidden" name="orderId" value="${pay.orderId }">
								<input type="hidden" name="userId" value="${user.id }">
								<input type="hidden" name="addressId" value="${sureId}">
								<input type="submit" value="确认支付">
						</div>
						</form>
						<div class="clear"></div>
					</div>
				</div>
			</div>

			<div class="clear"></div>
		</div>
	</div>
	</div>
	<div class="theme-popover-mask" style="display: none; height: 638px;"></div>
	<div class="theme-popover" style="overflow: hidden; display: none;">

		<!--标题 -->
		<div class="am-cf am-padding">
			<div class="am-fl am-cf">
				<strong class="am-text-danger am-text-lg">新增地址</strong> / <small>Add
					address</small>
			</div>
		</div>
		<hr>

		<div class="am-u-md-12">
			<form class="am-form am-form-horizontal">

				<div class="am-form-group">
					<label for="user-name" class="am-form-label">收货人</label>
					<div class="am-form-content">
						<input type="text" id="user-name" placeholder="收货人">
					</div>
				</div>

				<div class="am-form-group">
					<label for="user-phone" class="am-form-label">手机号码</label>
					<div class="am-form-content">
						<input id="user-phone" placeholder="手机号必填" type="email">
					</div>
				</div>

				<div class="am-form-group">
					<label for="user-phone" class="am-form-label">所在地</label>
					<div class="am-form-content address">
						<select data-am-selected="">
							<option value="a">浙江省</option>
							<option value="b">湖北省</option>
						</select> <select data-am-selected="">
							<option value="a">温州市</option>
							<option value="b">武汉市</option>
						</select> <select data-am-selected="">
							<option value="a">瑞安区</option>
							<option value="b">洪山区</option>
						</select>
					</div>
				</div>

				<div class="am-form-group">
					<label for="user-intro" class="am-form-label">详细地址</label>
					<div class="am-form-content">
						<textarea class="" rows="3" id="user-intro" placeholder="输入详细地址"></textarea>
						<small>100字以内写出你的详细地址...</small>
					</div>
				</div>

				<div class="am-form-group theme-poptit">
					<div class="am-u-sm-9 am-u-sm-push-3">
						<div class="am-btn am-btn-danger">保存</div>
						<div class="am-btn am-btn-danger close">取消</div>
					</div>
				</div>
			</form>
		</div>

	</div>

	<div class="clear"></div>
	</div>
	<script
		src="${pageContext.request.contextPath}/tan/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {

			$().UItoTop({
				easingType : 'easeOutQuart'
			});

		});
		function cart() {
			debugger;
			var username = "${user==null?'':user.username}";
			if (username=null || username== '') {
				var c=confirm( "您还未登录，请先登录");
				if (c !=null) {
					returnfalse;
				}
			}
		}
	
									</script>
	<script
		src="${pageContext.request.contextPath}/tan/js/skdslider.min.js"></script>
	<link href="${pageContext.request.contextPath}/tan/css/skdslider.css"
		rel="stylesheet">
	<script type="text/javascript">
		jQuery(document).ready(function() {
			jQuery('#demo1').skdslider({
				'delay' : 5000,
				'animationSpeed' : 2000,
				'showNextPrev' : true,
				'showPlayButton' : true,
				'autoSlide' : true,
				'animationType' : 'fading'
			});

			jQuery('#responsive').change(function() {
				$('#responsive_wrapper').width(jQuery(this).val());
			});

		});
		function check() {
			debugger;
			var username = "${user==null?'':user.username}";
			if (username = null || username == '') {
				var c = confirm("您还未登录，请先登录");
				if (c != null) {
					return false;
				}
			} else {
				alter("成功加入购物车");
			}
		}
	</script>

</body>
</html>