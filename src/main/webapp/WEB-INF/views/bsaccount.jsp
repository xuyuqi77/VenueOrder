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
	<form action="bsaccount" method="post" name="accountForm" id="accountForm">
	<div class="search_div">
		用户名：<input type="text" name="login_name" value="${user.login_name }"/>
		<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="main_table">
		<tr class="main_head">
			<th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()"/></th>
			<th>序号</th>
			<th>用户名</th>
			<th>余额</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty accountList}">
				<c:forEach items="${accountList}" var="account" varStatus="vs">
				<tr class="main_info">
				<td><input type="checkbox" name="accountIds" id="accountIds${account.account_id }" value="${account.account_id }"/></td>
				<td>${vs.index+1}</td>
				<td>${account.user.login_name }</td>
				<td>${account.money }</td>
				<td>
					<a href="javascript:editAccount(${account.account_id });">修改</a>
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
			<a href="javascript:addAccount();" class="myBtn"><em>新增</em></a>
		</div>
        <div class="pagemove" style="float:left;margin-left: 30px;">
            <a href="bsaccount?pagemove=1"><em>上一页</em></a>
            &nbsp;${sessionScope.accountpage.currentPage }/${sessionScope.accountpage.totalPage }&nbsp;
            <a href="bsaccount?pagemove=2"><em>下一页</em></a>
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
		
		function sltAllAccount(){
			if($("#sltAll").attr("checked")){
				$("input[name='accountIds']").attr("checked",true);
			}else{
				$("input[name='accountIds']").attr("checked",false);
			}
		}
		
		function addAccount(){
			//$(".shadow").show();
			var dg = new $.dialog({
				title:'新增账户',
				id:'account_new',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'bsaccount/add'
				});
    		dg.ShowDialog();
		}

		function editAccount(accountId){
			var dg = new $.dialog({
				title:'修改账户',
				id:'account_edit',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'bsaccount/edit?accountId='+accountId
				});
    		dg.ShowDialog();
		}

		function search(){
			$("#accountForm").submit();
		}

	</script>
</body>
</html>