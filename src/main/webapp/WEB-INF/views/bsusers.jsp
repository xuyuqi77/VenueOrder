<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="css/users.css"/>
</head>
<body>
	<form action="bsuser" method="post" name="userForm" id="userForm">
	<div class="search_div">
		用户名：<input type="text" name="login_name" value="${user.login_name }"/>
		角色：<select name="role_id" id="role_id" style="vertical-align: middle;">
			<option value="">请选择</option>
			<c:forEach items="${roleList}" var="venue">
				<option value="${venue.role_id }" <c:if test="${user.role_id==venue.role_id}">selected</c:if>>${venue.role_name }</option>
			</c:forEach>
			</select>
		<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="main_table">
		<tr class="main_head">
			<th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()"/></th>
			<th>序号</th>
			<th>用户名</th>
			<th>昵称</th>
			<th>角色</th>
			<th>预定状态</th>
			<th width="160">最近登录</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty userList}">
				<c:forEach items="${userList}" var="user" varStatus="vs">
				<tr class="main_info">
				<td><input type="checkbox" name="userIds" id="userIds${user.user_id }" value="${user.user_id }"/></td>
				<td>${vs.index+1}</td>
				<td>${user.login_name }</td>
				<td>${user.user_name }</td>
				<td>${user.role.role_name }</td>
				<td>${user.ordered }</td>
				<td>${user.lastlogintime }</td>
				<td>
					<a href="javascript:editUser(${user.user_id });">修改</a>
					| <a href="javascript:delUser(${user.user_id });">删除</a>
					| <a href="javascript:editRights(${user.user_id });">权限</a>
				</td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="7">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn">
		<div class="addandexport">
			<a href="javascript:addUser();" class="myBtn"><em>新增</em></a>
			<a href="javascript:exportUser();" class="myBtn"><em>导出</em></a>
		</div>
        <div class="pagemove" style="float:left;margin-left: 30px;">
            <a href="bsuser?pagemove=1"><em>上一页</em></a>
            &nbsp;${sessionScope.userpage.currentPage }/${sessionScope.userpage.totalPage }&nbsp;
            <a href="bsuser?pagemove=2"><em>下一页</em></a>
        </div>
	</div>
	</form>
	<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		
		function sltAllUser(){
			if($("#sltAll").attr("checked")){
				$("input[name='userIds']").attr("checked",true);
			}else{
				$("input[name='userIds']").attr("checked",false);
			}
		}
		
		function addUser(){
			//$(".shadow").show();
			var dg = new $.dialog({
				title:'新增用户',
				id:'user_new',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'bsuser/add'
				});
    		dg.ShowDialog();
		}
		
		function editUser(userId){
			var dg = new $.dialog({
				title:'修改用户',
				id:'user_edit',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'bsuser/edit?userId='+userId
				});
    		dg.ShowDialog();
		}
		
		function delUser(userId){
			if(confirm("确定要删除该记录？")){
				var url = "bsuser/delete?userId="+userId;
				$.get(url,function(data){
					if(data=="success"){
						document.location.reload();
					}
				});
			}
		}
		
		function editRights(userId){
			var dg = new $.dialog({
				title:'用户授权',
				id:'auth',
				width:280,
				height:370,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'bsuser/auth?userId='+userId
				});
    		dg.ShowDialog();
		}
		
		function search(){
			$("#userForm").submit();
		}
		
		function exportUser(){
			document.location = "bsuser/excel";
		}
	</script>
</body>
</html>