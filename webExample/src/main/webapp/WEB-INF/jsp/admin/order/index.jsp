<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/tan/css/pintuer.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/tan/css/admin1.css" />
<script src="${pageContext.request.contextPath}/tan/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/tan/js/pintuer.js"></script>
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
<body>
	<form method="post" action="#" id="admin-form">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">订单管理</strong>
			</div>
			<div class="user-order">
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
									<td class="td-inner">商品操作</td>
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
														<p class="Mystatus">待确认支付</p>
													</c:when>
													<c:when test="${order.status==1 }">
														<p class="Mystatus">已支付</p>
													</c:when>
													<c:when test="${order.status==2 }">
														<p class="Mystatus">待收货</p>
													</c:when>
													<c:when test="${order.status==3 }">
														<div class="am-btn am-btn-danger anniu">
															<p class="Mystatus">已完成</p>
														</div>
													</c:when>
												</c:choose>
																<p class="order-info">
																	<a href="/admin/order/?id=${order.id }">订单详情</a>
																</p>

															</div>
														</li>
														<li><c:choose>
													<c:when test="${order.status==0 }">
														<div class="am-btn am-btn-danger anniu">
															<a href="/conget">待支付确认</a>
														</div>
													</c:when>
													<c:when test="${order.status==1 }">
														<a href="fahuo?id=${order.id }">发货</a>
													</c:when>
													<c:when test="${order.status==2 }">
														<div class="am-btn am-btn-danger anniu">
															<a href="/conget">待收货</a>
														</div>
													</c:when>
													<c:when test="${order.status==3 }">
														<div class="am-btn am-btn-danger anniu">
															<a href="/conget">已完成</a>
														</div>
													</c:when>
												</c:choose></li>
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
	</form>
	<script type="text/javascript" th:inline="javascript">
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
					console.log(ids);
					console.log(ids.join(","));
					location.href = "delMuti?ids=" + ids.join(",");
				}
			} else {
				alert("请选择您要删除的内容!");
				return false;
			}
		}
	</script>
</body>
</html>