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
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
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

	<!-- //navigation -->
	<!-- breadcrumbs -->
	<div class="breadcrumbs">
		<div class="container">
			<ol class="breadcrumb breadcrumb1 animated wow slideInLeft"
				data-wow-delay=".5s">
				<li><a href="index.html"><span
						class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>
				<li class="active">商品详情</li>
			</ol>
		</div>
	</div>
	<div class="products">
		<div class="container">
			<div class="agileinfo_single">
				<div class="col-md-4 agileinfo_single_left">
					<img id="example"
						src="${pageContext.request.contextPath}/tan/images/${detail.pic }"
						alt=" " class="img-responsive">
				</div>
				<div class="col-md-8 agileinfo_single_right">
					<h2>${detail.name }</h2>
					<div class="w3agile_description">
						<h4>商品详情 :</h4>
						<p>${detail.desc }</p>
					</div>
					<div class="w3agile_description">
						<p>剩余库存：&nbsp;${detail.totalStockQty }</p>
					</div>
					<div class="snipcart-item block">
						<div class="snipcart-thumb agileinfo_single_right_snipcart">
							<h4 class="m-sing">￥&nbsp;${detail.salePrice }元</h4>
						</div>
						<div class="clearfix"></div>
						<div class="snipcart-details agileinfo_single_right_details">
							<form id="indexForm" action="cart/single" method="post"
								onsubmit="return check();">
								<fieldset>
									<input type="hidden" name="buyCount" value="1"> <input
										type="hidden" name="goodId" value="${detail.id }"> <input
										type="hidden" name="price" value="${detail.salePrice }">
									<input type="submit" name="submit" value="加入购物车" class="button">
								</fieldset>
							</form>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- <div class="newproducts-w3agile">
		<div class="container">
			<h3>New offers</h3>
			<div class="agile_top_brands_grids">
				<div class="col-md-3 top_brand_left-1">
					<div class="hover14 column">
						<div class="agile_top_brand_left_grid">
							<div class="agile_top_brand_left_grid_pos">
								<img src="images/offer.png" alt=" " class="img-responsive">
							</div>
							<div class="agile_top_brand_left_grid1">
								<figure>
								<div class="snipcart-item block">
									<div class="snipcart-thumb">
										<a href="products.html"><img title=" " alt=" "
											src="images/14.png"></a>
										<p>Fried-gram</p>
										<div class="stars">
											<i class="fa fa-star blue-star" aria-hidden="true"></i> <i
												class="fa fa-star blue-star" aria-hidden="true"></i> <i
												class="fa fa-star blue-star" aria-hidden="true"></i> <i
												class="fa fa-star blue-star" aria-hidden="true"></i> <i
												class="fa fa-star gray-star" aria-hidden="true"></i>
										</div>
										<h4>
											$35.99 <span>$55.00</span>
										</h4>
									</div>
									<div class="snipcart-details top_brand_home_details">
										<form action="#" method="post">
											<fieldset>
												<input type="hidden" name="cmd" value="_cart"> <input
													type="hidden" name="add" value="1"> <input
													type="hidden" name="business" value=" "> <input
													type="hidden" name="item_name"
													value="Fortune Sunflower Oil"> <input type="hidden"
													name="amount" value="35.99"> <input type="hidden"
													name="discount_amount" value="1.00"> <input
													type="hidden" name="currency_code" value="USD"> <input
													type="hidden" name="return" value=" "> <input
													type="hidden" name="cancel_return" value=" "> <input
													type="submit" name="submit" value="Add to cart"
													class="button">
											</fieldset>
										</form>
									</div>
								</div>
								</figure>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 top_brand_left-1">
					<div class="hover14 column">
						<div class="agile_top_brand_left_grid">
							<div class="agile_top_brand_left_grid_pos">
								<img src="images/offer.png" alt=" " class="img-responsive">
							</div>
							<div class="agile_top_brand_left_grid1">
								<figure>
								<div class="snipcart-item block">
									<div class="snipcart-thumb">
										<a href="products.html"><img title=" " alt=" "
											src="images/15.png"></a>
										<p>Navaratan-dal</p>
										<div class="stars">
											<i class="fa fa-star blue-star" aria-hidden="true"></i> <i
												class="fa fa-star blue-star" aria-hidden="true"></i> <i
												class="fa fa-star blue-star" aria-hidden="true"></i> <i
												class="fa fa-star blue-star" aria-hidden="true"></i> <i
												class="fa fa-star gray-star" aria-hidden="true"></i>
										</div>
										<h4>
											$30.99 <span>$45.00</span>
										</h4>
									</div>
									<div class="snipcart-details top_brand_home_details">
										<form action="#" method="post">
											<fieldset>
												<input type="hidden" name="cmd" value="_cart"> <input
													type="hidden" name="add" value="1"> <input
													type="hidden" name="business" value=" "> <input
													type="hidden" name="item_name" value="basmati rise">
												<input type="hidden" name="amount" value="30.99"> <input
													type="hidden" name="discount_amount" value="1.00">
												<input type="hidden" name="currency_code" value="USD">
												<input type="hidden" name="return" value=" "> <input
													type="hidden" name="cancel_return" value=" "> <input
													type="submit" name="submit" value="Add to cart"
													class="button">
											</fieldset>
										</form>
									</div>
								</div>
								</figure>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 top_brand_left-1">
					<div class="hover14 column">
						<div class="agile_top_brand_left_grid">
							<div class="agile_top_brand_left_grid_pos">
								<img src="images/offer.png" alt=" " class="img-responsive">
							</div>
							<div class="agile_top_brand_left_grid_pos">
								<img src="images/offer.png" alt=" " class="img-responsive">
							</div>
							<div class="agile_top_brand_left_grid1">
								<figure>
								<div class="snipcart-item block">
									<div class="snipcart-thumb">
										<a href="products.html"><img src="images/16.png" alt=" "
											class="img-responsive"></a>
										<p>White-peasmatar</p>
										<div class="stars">
											<i class="fa fa-star blue-star" aria-hidden="true"></i> <i
												class="fa fa-star blue-star" aria-hidden="true"></i> <i
												class="fa fa-star blue-star" aria-hidden="true"></i> <i
												class="fa fa-star blue-star" aria-hidden="true"></i> <i
												class="fa fa-star gray-star" aria-hidden="true"></i>
										</div>
										<h4>
											$80.99 <span>$105.00</span>
										</h4>
									</div>
									<div class="snipcart-details top_brand_home_details">
										<form action="#" method="post">
											<fieldset>
												<input type="hidden" name="cmd" value="_cart"> <input
													type="hidden" name="add" value="1"> <input
													type="hidden" name="business" value=" "> <input
													type="hidden" name="item_name" value="Pepsi soft drink">
												<input type="hidden" name="amount" value="80.00"> <input
													type="hidden" name="discount_amount" value="1.00">
												<input type="hidden" name="currency_code" value="USD">
												<input type="hidden" name="return" value=" "> <input
													type="hidden" name="cancel_return" value=" "> <input
													type="submit" name="submit" value="Add to cart"
													class="button">
											</fieldset>
										</form>
									</div>
								</div>
								</figure>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3 top_brand_left-1">
					<div class="hover14 column">
						<div class="agile_top_brand_left_grid">
							<div class="agile_top_brand_left_grid_pos">
								<img src="images/offer.png" alt=" " class="img-responsive">
							</div>
							<div class="agile_top_brand_left_grid1">
								<figure>
								<div class="snipcart-item block">
									<div class="snipcart-thumb">
										<a href="products.html"><img title=" " alt=" "
											src="images/17.png"></a>
										<p>Channa-dal</p>
										<div class="stars">
											<i class="fa fa-star blue-star" aria-hidden="true"></i> <i
												class="fa fa-star blue-star" aria-hidden="true"></i> <i
												class="fa fa-star blue-star" aria-hidden="true"></i> <i
												class="fa fa-star blue-star" aria-hidden="true"></i> <i
												class="fa fa-star gray-star" aria-hidden="true"></i>
										</div>
										<h4>
											$35.99 <span>$55.00</span>
										</h4>
									</div>
									<div class="snipcart-details top_brand_home_details">
										<form action="#" method="post">
											<fieldset>
												<input type="hidden" name="cmd" value="_cart"> <input
													type="hidden" name="add" value="1"> <input
													type="hidden" name="business" value=" "> <input
													type="hidden" name="item_name"
													value="Fortune Sunflower Oil"> <input type="hidden"
													name="amount" value="35.99"> <input type="hidden"
													name="discount_amount" value="1.00"> <input
													type="hidden" name="currency_code" value="USD"> <input
													type="hidden" name="return" value=" "> <input
													type="hidden" name="cancel_return" value=" "> <input
													type="submit" name="submit" value="Add to cart"
													class="button">
											</fieldset>
										</form>
									</div>
								</div>
								</figure>
							</div>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div> -->
	<!-- //new -->
	<!-- //footer -->

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
	<!-- //footer -->
	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/tan/js/bootstrap.min.js"></script>
	<!-- top-header and slider -->
	<!-- here stars scrolling icon -->
	<script type="text/javascript">
		$(document).ready(function() {
								
			$().UItoTop({ easingType: 'easeOutQuart' });
								
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
	</script>
	<!-- //here ends scrolling icon -->
	<script src="js/minicart.min.js"></script>
	<script>
	// Mini Cart
	paypal.minicart.render({
		action: '#'
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
		jQuery(document).ready(function(){
			jQuery('#demo1').skdslider({'delay':5000, 'animationSpeed': 2000,'showNextPrev':true,'showPlayButton':true,'autoSlide':true,'animationType':'fading'});
						
			jQuery('#responsive').change(function(){
			  $('#responsive_wrapper').width(jQuery(this).val());
			});
			
		});
		function check() {
			debugger;
			/* var user=confirm(${user==null}); */
			var username="${user==null?'':user.username}";
			if( username=null||username=='') {
				var c=confirm("您还未登录，请先登录");
				if(c!=null){
					return false;
				}
			}else{
				alter("成功加入购物车");
			}
		}
</script>
</body>
</html>