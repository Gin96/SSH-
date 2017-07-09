<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- DOCTYPE html这行代码去掉就是h5 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/js/EasyUI/themes/default/easyui.css"></link>
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/EasyUI/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/js/jqueryValidator/jquery.validate.min.js"></script>
	<script type="text/javascript">
		$(function (){
			$("#login").validate({
				rules:{
					uname:{
						required:true,
						minlength:7,
						maxlength:21
					},
					upass:{
						required:true,
						minlength:7,
						maxlength:21
					},
					loginCode:{
						required:true
					}
				},
				messages:{
					uname:{
						required:"用户名不可为空",
						minlength:"用户名长度最低7位",
						maxlength:"用户名长度最高21位"
					},
					upass:{
						required:"密码不可为空",
						minlength:"密码长度最低7位",
						maxlength:"密码长度最高21位"
					},
					loginCode:{
						required:"认证码不可为空"
					}
				}
				,
  				submitHandler:function(form){
  					$.post("${pageContext.request.contextPath}/login.htmlx?"+$("#login").serialize(),function (data){
//   						console.log(data+"++++++++");
// 						confirm("提交表单");
						$(form)[0].submit();
  					});
 					return false;//false是ajax手动提交，true是提交表单本身的action
 				} 
			})
		});
	</script>
</head>
<body>
	<div class="easyui-panel" title="用户登录系统" style="width: 400px;">
		<c:forEach var="item" items="${errors }">
			<p>${item.context }</p>
		</c:forEach>
		<form id="login" action="${pageContext.request.contextPath}/login.htmlx" method="post">
			<table>
				<tr>
					<td>用户名</td>
					<td><input class="easyui-validatebox" type="text" name="uname"/></td>
				</tr>
				<tr>
					<td>登录密码</td>
					<td><input class="easyui-validatebox" type="password" name="upass"/></td>
				</tr>
				<tr>
					<td>认证码</td>
					<td>
						<input class="easyui-validatebox" type="text" id="loginCode" name="loginCode"/>
						<img id="vcode" src="${pageContext.request.contextPath}/code/login.htmlx"/> <a href="javascript:void(document.getElementById('vcode').src='${pageContext.request.contextPath}/code/login.htmlx?v='+new Date());" >看不清楚？</a>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="登录"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>