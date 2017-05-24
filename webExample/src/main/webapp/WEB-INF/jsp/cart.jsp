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
<!-- start-smoth-scrolling -->
</head>

<body>

	<!-- header -->
	<div class="agileits_header">
		<div class="container">
			<div class="w3l_offers"></div>
			<div class="agile-login">
				<ul>
					<c:choose>
						<c:when test="${user ==null}">
							<li><a href="user/registered"> 注册 </a></li>
							<li><a href="/login">登录</a></li>
						</c:when>
						<c:when test="${user!=null&&user.id !=null}">
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
					<a href="index">网上购物商城</a>
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
					<li class="active"><a href="/index" class="act">主页</a></li>
					<!-- Mega Menu -->
					<c:forEach var="category" items="${categorys}">
						<li class="active"><a href="/products?id=${category.id }"
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
				<li><a href="/index"><span
						class="glyphicon glyphicon-home" aria-hidden="true"></span>返回首页</a></li>
				<li class="active">购物车</li>
			</ol>
		</div>
	</div>
	<div class="checkout">
		<div class="container">
			<h2>
				你的购物车有: <span>${cart.total } 件商品</span>
			</h2>
			<div class="checkout-right">
				<table class="timetable_sub">
					<thead>
						<tr>
							<th>排序</th>
							<th>商品图片</th>
							<th>商品数量</th>
							<th>商品名称</th>
							<th>售价</th>
							<th>操作</th>
						</tr>
					</thead>
					<c:forEach var="cartDto" items="${cart.cartDto}">
						<tr class="rem1">
							<td><input type="checkbox" name="id[]" value="${cartDto.id}" />
								<span th:text="${cartDto.id}"></span></td>
							<td class="invert-image"><a
								href="single?id=${cartDto.goodId }"><img 
									src="${pageContext.request.contextPath}/tan/images/${cartDto.pic}" height="50"
													    height="150" width="150" alt=" " class="img-responsive" /></a></td>
							<td class="invert">
								<div class="quantity">
									<form action="/cart/update" method="post">
										<div class="quantity-select">
											<button class="w3view-cart" type="submit" name="submit"
												value="">
												<div class="entry value-minus">&nbsp;</div>
											</button>
												<div class="entry value">
													<span>${cartDto.count }</span>
												</div>
											<button class="w3view-cart" type="submit" name="submit"
												value="">
												<div class="entry value-plus active">&nbsp;</div>
												<div>
													<input type="hidden" name="count" value=${cartDto.count+1 } />
													<input type="hidden" name="id" value=${cartDto.id } />
												</div>
											</button>
										</div>
									</form>
								</div>
							</td>
							<td class="invert">${cartDto.goodName }</td>
							<td class="invert">${cartDto.price }</td>
							<td class="invert">
								<div class="rem">
								<a href="del?id=${cartDto.id }"><img
									src="${pageContext.request.contextPath}/tan/images/close_1.png" alt=" " class="img-responsive" /></a> 
								</div>

							</td>
						</tr>
					</c:forEach>
					<div class="checkout-left">
						<div class="clearfix"></div>
					</div>
				</table>
			</div>
			<div class="checkout-left">
			<div class="padding head-l">
				<ul class="search">
					<li>
						<button type="button" class="button button-little border-green"
							id="checkall">
							<span class="icon-check"></span> 全选
						</button>
						<button type="button" class="button button-little border-red"
							onclick="DelSelect()">
							<span class="icon-trash-o"></span> 批量删除
						</button>
					</li>
				</ul>
			</div>
				<div class="checkout-left-basket">
					<!-- <h4>返回</h4> -->
					<li>共计 <i>-</i> <span>${cart.totalPrice }</span></li>
				</div>
				<div class="checkout-right-basket">
					<button type="button" class="button button-little border-red"
							onclick="Pay()">
							<span class="icon-trash-o"></span> 结算
						</button>
					</div>
				<div class="clearfix"></div>
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
				<img src="${pageContext.request.contextPath}/tan/images/card.png"
					alt=" " class="img-responsive">
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/tan/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$().UItoTop({
				easingType : 'easeOutQuart'
			});

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
	<!-- main slider-banner -->
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
		$("#checkall").click(function() {
			$("input[name='id[]']").each(function() {
				if (this.checked) {
					this.checked = false;
				} else {
					this.checked = true;
				}
			});
		})
		function DelSelect() {
			var Checkbox = false;
			var ids = [];
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
					ids.push(this.value);
				}
			});
			if (Checkbox) {
				var t = confirm("您确认要删除选中的内容吗？");
				if (t == false) {
					return false;
				} else {
					location.href = "delMuti?ids=" + ids.join(",");
				}
			} else {
				alert("请选择您要删除的内容!");
				return false;
			}
		}
		function Pay() {
			var Checkbox = false;
			var ids = [];
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
					ids.push(this.value);
				}
			});
			if (Checkbox) {
				location.href = "/bycart?ids=" + ids.join(",");
		}
		}
		function check() {
			debugger;
			/* var user=confirm(${user==null}); */
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