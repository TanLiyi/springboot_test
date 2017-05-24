<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/tan/css/pintuer.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/tan/css/admin1.css" />
<script src="${pageContext.request.contextPath}/tan/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/tan/js/pintuer.js"></script>
</head>
<body>
	<form method="post" action="#" id="admin-form">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder">商品管理</strong>
			</div>
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
						</button> <a href="detail" class="button button-little border-blue"> 添加
					</a> <input type="hidden" name="page" value="${currentPage}" /> <span
						style="margin-left: 450px;"> <input type="text"
							placeholder="请输入搜索关键字" name="search" class="input"
							style="width: 250px; line-height: 17px; display: inline-block" />
							<button class="button border-main icon-search" type="submit">搜索</button>
					</span>
					</li>
				</ul>
			</div>
			<table class="table table-hover text-center">
				<tr>
					<th width="120">ID</th>
					<th>名称</th>
					<th>图片</th>
					<th>描述</th>
					<th>状态</th>
					<th align="center">操作</th>
				</tr>
				<c:forEach var="good" items="${page.recordList}">
					<tr>
						<td><input type="checkbox" name="id[]" value="${good.id}" />
							<span th:text="${good.id}"></span></td>
						<td>${good.name}</td>
					<td>	<img title=" " height=50" width="50" alt=" "
							src="${pageContext.request.contextPath}/tan/images/${good.pic}" /></td>
						<td>${good.desc}</td>
						<td>${good.isOnShelf}</td>
						<td align="center">
							<div class="button-group">
								<a class="button button-little" href="del?id=${good.id }"><span
									class="icon-trash-o"></span> 删除</a><a class="button button-little"
									href="detail?id=${good.id}"><span class="icon-save"></span>
									修改</a>
							</div>
						</td>
					</tr>
				</c:forEach>

				<tr>
					<td colspan="8"><div class="recordList">
							<a href="index?page=${page.currentPage=0}"> 首页</a>
							<c:if test="${currentPage>0}">
								<a href="index?page=${currentPage-1}"> 上一页</a>
							</c:if>
							<a href="index?page=${currentPage+1}"> 下一页</a> <a
								href="index?page=${page.pageCount-1}"> 尾页</a>
							<p>共${page.recordCount }页</p>
						</div></td>
				</tr>
			</table>
		</div>
	</form>
	<script type="text/javascript" th:inline="javascript">
		function setPage(obj) {
			$("input[name='page']").val(obj.title / 1);
			$("#admin-form").submit();
			//alert(obj.title);
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