<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>项目</title>
	<link type="text/css" rel="stylesheet" href="css/users.css"/>
</head>
<body>
<form action="bsorder" method="post" name="orderForm" id="orderForm">
	<div class="search_div">
		预定帐号：<input type="text" name="loginName" value="${order.login_name }"/>
		项目：<input type="text" name="sportName" value="${order.sport_name }"/>
		预定场馆：<select name="venueId" id="venueId" style="vertical-align: middle;">
		<option value="">请选择</option>
		<c:forEach items="${venueList}" var="venue">
			<option value="${venue.venue_id }" <c:if test="${order.venue_id==venue.venue_id}">selected</c:if>>${venue.venue_name }</option>
		</c:forEach>
	</select>
		<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="main_table">
		<tr class="main_head">
			<th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllSport()"/></th>
			<th>序号</th>
			<th>预定账号</th>
			<th>场馆</th>
			<th>项目</th>
			<th>使用时间</th>
			<th>下单时间</th>
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
							<a href="javascript:editOrder(${order.order_id });">修改</a>
							| <a href="javascript:delOrder(${order.order_id });">删除</a>
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
			<a href="javascript:addOrder();" class="myBtn"><em>新增</em></a>
			<a href="javascript:exportOrder();" class="myBtn"><em>导出</em></a>
		</div>
		<div class="pagemove" style="float:left;margin-left: 30px;">
			<a href="bsorder?pagemove=1"><em>上一页</em></a>
			&nbsp;${sessionScope.orderpage.currentPage }/${sessionScope.orderpage.totalPage }&nbsp;
			<a href="bsorder?pagemove=2"><em>下一页</em></a>
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

    function sltAllOrder(){
        if($("#sltAll").attr("checked")){
            $("input[name='orderIds']").attr("checked",true);
        }else{
            $("input[name='orderIds']").attr("checked",false);
        }
    }

    function addOrder(){
        //$(".shadow").show();
        var dg = new $.dialog({
            title:'新增订单',
            id:'order_new',
            width:330,
            height:300,
            iconTitle:false,
            cover:true,
            maxBtn:false,
            xButton:true,
            resize:false,
            page:'bsorder/add'
        });
        dg.ShowDialog();
    }

    function editOrder(orderId){
        var dg = new $.dialog({
            title:'修改订单',
            id:'order_edit',
            width:330,
            height:300,
            iconTitle:false,
            cover:true,
            maxBtn:false,
            resize:false,
            page:'bsorder/edit?orderId='+orderId
        });
        dg.ShowDialog();
    }

    function delOrder(orderId){
        if(confirm("确定要删除该记录？")){
            var url = "bsorder/delete?orderId="+orderId;
            $.get(url,function(data){
                if(data=="success"){
                    document.location.reload();
                }
            });
        }
    }

    function search(){
        $("#orderForm").submit();
    }

    function exportOrder(){
        document.location = "bsorder/excel";
    }
</script>
</body>
</html>