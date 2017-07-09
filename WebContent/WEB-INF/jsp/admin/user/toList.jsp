<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://name/sp" prefix="sp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员主界面</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap.min.css"></link>
</head>
<body>
<body>
	<div class="container">
		<!-- 查询信息传递 -->
		<form id="query" action="${pageContext.request.contextPath}/user/toList.htmlx">
			<input type="hidden" id="page" name="page">
			<div class="row">
			  <div class="col-xs-3 col-md-3">
			  	 <div class="form-group">
					 <label for="exampleInputName2">用户名</label>
					 <input name="params['uname']" value="${pageVo.params['uname'] }" type="text" class="form-control" id="exampleInputName2" placeholder="Jane Doe">
				 </div>
			  </div>
			  <div class="col-xs-3 col-md-3">
			  	 <div class="form-group">
					    <label for="exampleInputName2">Email</label>
					    <input name="params['email']" value="${pageVo.params['email'] }" type="text" class="form-control" id="exampleInputName2" placeholder="Jane Doe">
				 </div>
			  </div>
			  <div class="col-xs-3 col-md-3">
			  	 <div class="form-group">
					    <label for="exampleInputName2">手机号</label>
					    <input name="params['phone']" value="${pageVo.params['phone'] }" type="text" class="form-control" id="exampleInputName2" placeholder="Jane Doe">
				 </div>
			  </div>
			   <div class="col-xs-3 col-md-3">
			  	 <div class="form-group">
					    <label for="exampleInputName2">用户类型</label>
					    <select>
						    <c:forEach var="item" items="${sp:getCodeByType('100')}">
						    	<option value="${item.value }">${item.value }</option>
						    </c:forEach>
					    </select>
				 </div>
			  </div>
			  <div class="col-xs-3 col-md-3">
			  	 <div class="form-group">
			  	 	<button type="submit" class="btn btn-default">查询</button>
				 </div>
			  </div>
			</div>
			
		</form>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>#</th>
					<th>用户名</th>
					<th>用户类型</th>
					<th>Email</th>
					<th>手机号</th>
					<th>登录状态</th>
					<th>家庭地址信息</th>
					<th>注册时间</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${pageVo.list}" varStatus="status">
					<tr>
						<td>${status.index}</td>
						<th>${item.uname}</th>
						<td>${sp:formatSimpleCode(item.userType)}</td>
						<td>${item.email }</td>
						<td>${item.phone }</td>
						<td>${item.status }</td>
						<td>${item.detail }</td>
						<td>${item.registerDate }</td>
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