<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
	var message = "${message}";
	console.log(message);
	if (message != null && message != "") {
		alert(JSON.stringify(message));
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
						<c:when test="${user.id ==null}">
							<li><a href="user/registered"> 注册 </a></li>
							<li><a href="login">登录</a></li>
						</c:when>
						<c:when test="${user.id !=null}">
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
					<a href="index">网络在线购物商城</a>
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
	<!-- //header -->
	<!-- navigation -->
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
					<c:forEach var="category" items="${categorys}">
						<li class="active"><a href="/products?id=${category.id }"
							class="act">${category.name }</a></li>

					</c:forEach>
				</ul>
			</div>
			</nav>
		</div>
	</div>
				<div class="agile_top_brands_grids">
					<c:forEach var="list" items="${page.recordList}">
						<div class="col-md-4 top_brand_left">
							<div class="hover14 column">
								<div class="agile_top_brand_left_grid">
									<div class="agile_top_brand_left_grid1">
										<figure>
										<div class="snipcart-item block">
											<div class="snipcart-thumb">
												<a href="detail?id=${list.id }"><img title=" " height="150"
													width="150" alt=" "
													src="${pageContext.request.contextPath}/tan/images/${list.pic }"></a>
												<p>${list.name }</p>
												<h4>${list.salePrice }</h4>
												<p>浏览人数：${list.count }&nbsp;&nbsp;&nbsp; 成交记录：${list.totalSaleQty }</p>
											</div>
											<div class="snipcart-details top_brand_home_details">
												<form action="cart/product" method="post">
													<fieldset>
														<input type="hidden" name="buyCount" value="1"> <input
															type="hidden" name="goodId" value="${list.id }">
															<input type="hidden" name="id" value="${categoryId }">
														<input type="hidden" name="price" value="${list.salePrice }">
														<input type="submit" name="submit" value="加入购物车"
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
					</c:forEach>
				</div>
				<div class="clearfix"></div>
				<nav class="numbering">
				<ul class="pagination paging">
					<li><a href="#" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
					<a href="productPage?page=${page.currentPage=0}"> 首页</a>
							<c:if test="${currentPage>0}">
								<a href="productPage?page=${currentPage-1}"> 上一页</a>
							</c:if>
							<a href="productPage?page=${currentPage+1}"> 下一页</a> <a
								href="productPage?page=${page.pageCount-1}"> 尾页</a>
							<p>共${page.recordCount }条记录</p>
						</div>
				</ul>
				</nav>
				<div class="clearfix"></div>
			</div>
			
		</div>
	</div>
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
		jQuery(document).ready(function() {
			jQuery('#demo1').skdslider({
				'delay' : 5000,
				'animationSpeed' : 2000,
				'showNextPrev' : true,
				'showPlayButton' : true,
				'autoSlide' : true,
				'animationType' : 'fading'
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
			jQuery('#responsive').change(function() {
				$('#responsive_wrapper').width(jQuery(this).val());
			});

		});
	</script>
</body>
</html>