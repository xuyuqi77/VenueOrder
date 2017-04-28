<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>My Test</title>
	<link type="text/css" rel="stylesheet" href="css/users.css"/>
	<style type="text/css">
		body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
		.input_txt{width:200px;height:20px;line-height:20px;}
		.info{height:40px;line-height:40px;}
		.info th{text-align: right;width:65px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
		.info td{text-align:left;}
	</style>
</head>
<body>
<form action="save" name="orderForm" id="orderForm" target="result" method="post" onsubmit="return checkInfo();">
	<input type="hidden" name="order_id" id="order_id" value="${order.order_id }"/>
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>预定帐号:</th>
			<td><input type="text" name="login_name" id="login_name" class="input_txt" value="${order.login_name }"/></td>
		</tr>
		<tr class="info">
			<th>所属场馆:</th>
			<td>
				<select name="venue_id" id="venue_id" class="input_txt">
					<option value="">请选择</option>
					<c:forEach items="${venueList}" var="venue">
						<option value="${venue.venue_id }">${venue.venue_name }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr class="info">
			<th>项   目:</th>
			<td><input type="tet" name="sport_name" id="sport_name" class="input_txt"/></td>
		</tr>
		<tr class="info">
			<th>使用时间:</th>
			<td>
				<select name="order_time" id="order_time" class="input_txt">
					<option value="">请选择</option>
					<c:forEach items="${opentimeList}" var="time">
						<option value="${time }">${time }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
</form>
<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript">
    var dg;
    $(document).ready(function(){
        dg = frameElement.lhgDG;
        dg.addBtn('ok','保存',function(){
            $("#orderForm").submit();
        });
        if($("#login_name").val()!=""){
            $("#login_name").attr("readonly","readonly");
            $("#login_name").css("color","gray");
            var venueId = "${order.venue_id}";
            if(venueId!=""){
                $("#venue_id").val(venueId);
            }
        }
    });

    function checkInfo(){
        if($("#login_name").val()==""){
            alert("请输入预定帐号!");
            $("#login_name").focus();
            return false;
        }
        if($("#venue_id").val()==""){
            alert("请输入场馆!");
            $("#venue_id").focus();
            return false;
        }
        if($("#order_time").val()==""){
            alert("请输入使用时间!");
            $("#openning_times").focus();
            return false;
        }
        if($("#sport_name").val()==""){
            alert("请输入项目!");
            $("#sport_name").focus();
            return false;
        }
        return true;
    }

    function success(){
        if(dg.curWin.document.forms[0]){
            dg.curWin.document.forms[0].action = dg.curWin.location+"";
            dg.curWin.document.forms[0].submit();
        }else{
            dg.curWin.location.reload();
        }
        dg.cancel();
    }

    function failed(){
        alert("新增失败！");
        $("#login_name").select();
        $("#login_name").focus();
    }
</script>
</body>
</html>