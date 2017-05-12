<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>订单详情</title>
	<link type="text/css" rel="stylesheet" href="css/users.css"/>
</head>
<body>
<form action="oderList" method="post" name="orderForm" id="orderForm">
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="main_table">
		<tr class="main_head">
			<th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllSport()"/></th>
			<th>序号</th>
			<th>预定账号</th>
			<th>场馆</th>
			<th>项目</th>
			<th>使用时间</th>
			<th>下单时间</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty orderList}">
				<c:forEach items="${orderList}" var="order" varStatus="vs">
					<tr class="main_info">
						<td><input type="checkbox" name="orderIds" id="orderIds${order.order_id }" value="${order.order_id }"/></td>
						<td>${vs.index+1}</td>
						<td>${order.user.login_name }</td>
						<td>${order.venue.venue_name }</td>
						<td>${order.sport_name }</td>
						<td>${order.order_time }</td>
						<td>${order.order_date}</td>
						<td>
							<a href="javascript:delOrder(${order.order_id });">取消订单</a>
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
</form>
<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $(".main_info:even").addClass("main_table_even");
    });

    function sltAllOrder(){
        if($("#sltAll").attr("checked")){
            $("input[name='orderIds']").attr("checked",true);
        }else{
            $("input[name='orderIds']").attr("checked",false);
        }
    }

    function delOrder(orderId,orderTime){
        if(confirm("确定要删除该记录？")){
            var url = "deleteorder?orderId="+orderId;
            $.get(url,function(data){
                if(data=="datefalse"){
                    alert("只能取消今日订单");
				}
                if(data=="success"){
                    alert("取消成功");
                    document.location.reload();
                }
            });
        }
    }
</script>
</body>
</html>