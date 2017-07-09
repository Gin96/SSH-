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
	   <form action="${pageContext.request.contextPath}/res/add.htmlx">
		<table>
			<tr>
				<td>资源ID</td>
				<td><input type="text" readonly="readonly" name="res.id" value="${res.id }"/></td>
			</tr>
			<tr>
				<td>资源名称</td>
				<td><input type="text" name="res.name" value="${res.name }"/></td>
			</tr>
			<tr>
				<td>资源父节点</td>
				<td><input type="text" readonly="readonly" name="res.parentId" value="${res.parentId }"/></td>
			</tr>
			<tr>
				<td>资源路径</td>
				<td><input type="text" name="res.path" value="${res.path }"/></td>
			</tr>
			<tr>
				<td>排序</td>
				<td><input type="text" name="res.rorder" value="${res.rorder }"/></td>
			</tr>
			<tr>
				<td>备注</td>
				<td><textarea rows="5" cols="70" name="res.note">${res.note }</textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="保存/更新"/>
				</td>
			</tr>
		</table>
	   </form>
	</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/bootstrap/js/bootstrap.min.js"></script>
</html>