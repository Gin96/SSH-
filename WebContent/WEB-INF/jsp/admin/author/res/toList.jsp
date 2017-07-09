<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://name/sp" prefix="sp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>资源树界面</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap.min.css"></link>
</head>
<body>
<body>
	<div class="container">
		<!-- 新增节点 -->
		<a href="${pageContext.request.contextPath}/res/toAdd.htmlx">新增根节点</a>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>资源ID</th>
					<th>资源名字</th>
					<th>资源父节点</th>
					<th>资源路径</th>
					<th>排序</th>
					<th>资源录入时间</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${pageVo.list}" varStatus="status">
					<tr>
						<td>${item.id}</td>
						<th>${item.name}，<a href="${pageContext.request.contextPath}/res/toList.htmlx?params['parentId']=${item.id}">查看子节点</a></th>
						<td>${item.parentId}</td>
						<td>${item.path }</td>
						<td>${item.rorder }</td>
						<td>${item.createTs }</td>
						<td>${item.note }</td>
						<td>
							<a href="${pageContext.request.contextPath}/res/toAdd.htmlx?res.parentId=${item.id}">增加子节点</a>
							<a href="${pageContext.request.contextPath}/res/delete.htmlx?res.id=${item.id}">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="8">
						 <nav>
					      <ul class="pagination pagination-sm">
					      	<c:choose>
					      		<c:when test="${page==1 }">
					      			<li class="disabled">
							        	<a href="javascript:void(query(1));" aria-label="Previous">
							        		<span aria-hidden="true">&laquo;</span>
							        	</a>
					       		    </li>
					      		</c:when>
					      		<c:otherwise>
					      			<li class="Previous">
							        	<a href="javascript:void(query(${page-1 }));" aria-label="Previous">
							        		<span aria-hidden="true">&laquo;</span>
							        	</a>
					       		    </li>
					      		</c:otherwise>
					      	</c:choose>
					        <c:forEach begin="1" end="${pageVo.totalPage }" step="1" var="item">
					        	<li><a href="javascript:void(query(${item }));">${item}</a></li>
					        </c:forEach>
					        <c:choose>
					        	<c:when test="${page==totalPage }">
							        <li  class="disabled">
							        	<a href="javascript:void(query(${page }));" aria-label="Next">
							        		<span aria-hidden="true">&raquo;</span>
							        	</a>
							        </li>
					       		</c:when>
					       		<c:otherwise>
					       			 <li  class="Previous">
							        	<a href="javascript:void(query(${page+1 }));" aria-label="Next">
							        		<span aria-hidden="true">&raquo;</span>
							        	</a>
							        </li>
					       		</c:otherwise>
					        </c:choose>
					      </ul>
					    </nav>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function query(page){
		$("#page").val(page);
		$("#query").submit();
	}
</script>
</html>