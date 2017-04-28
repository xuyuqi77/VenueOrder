<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色</title>
<link type="text/css" rel="stylesheet" href="css/users.css"/>
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="main_table">
		<tr class="main_head">
			<th>序号</th>
			<th>角色名称</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty venueList}">
				<c:forEach items="${venueList}" var="venue" varStatus="vs">
				<tr class="main_info">
				<td>${vs.index+1}</td>
				<td id="venueNameTd${venue.venue_id }">${venue.venue_name }</td>
				<td>
					<a href="javascript:editVenue(${venue.venue_id });">修改</a>
					| <a href="javascript:delVenue(${venue.venue_id });">删除</a>
				</td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
				<td colspan="3">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn">
		<div>
			<a href="javascript:addVenue();" class="myBtn"><em>新增</em></a>
		</div>
	</div>
	
	<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		
		function addVenue(){
			var dg = new $.dialog({
				title:'新增场馆',
				id:'venue_new',
				width:300,
				height:130,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
                html:'<div style="width:100%;height:40px;line-height:40px;text-align:center;"><span style="color: #4f4f4f;font-size: 13px;font-weight: bolder;display:inline-block;vertical-align:middle;">场馆名称：</span><input type="text" name="venueName" id="venueName" style="vertical-align: middle;"/></div>'
            });
    		dg.ShowDialog();
    		dg.addBtn('ok','保存',function(){
                var url = "bsvenue/save.html";
                var postData = {venueId:'-1',venueName:$("#venueName").val()};
                $.post(url,postData,function(data){
                    if(data=='success'){
                        dg.curWin.location.reload();
                        dg.cancel();
                    }else{
                        alert('场馆名重复，保存失败！');
                        $("#venueName").focus();
                        $("#venueName").select();
                    }
                });
    		});
		}
		
		function editVenue(venueId){
			var venueName = $("#venueNameTd"+venueId).text();
			var dg = new $.dialog({
				title:'修改角色',
				id:'venue_edit',
				width:300,
				height:130,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				html:'<div style="height:40px;line-height:40px;text-align:center;"><span style="color: #4f4f4f;font-size: 13px;font-weight: bold;display:inline-block;vertical-align:middle;">场馆名称：</span><input type="text" name="venueName" id="venueName" value="'+venueName+'" style="vertical-align: middle;"/></div>'
				});
    		dg.ShowDialog();
    		dg.addBtn('ok','保存',function(){
                var url = "bsvenue/save.html";
                var postData = {venueId:venueId,venueName:$("#venueName").val()};
                $.post(url,postData,function(data){
                    if(data=='success'){
                        dg.curWin.location.reload();
                        dg.cancel();
                    }else{
                        alert('场馆名重复，保存失败！');
                        $("#venueName").focus();
                        $("#venueName").select();
                    }
                });
    		});
		}

        function delVenue(venueId){
            if(confirm("确定要删除该记录？")){
                var url = "bsvenue/delete?venueId="+venueId;
                $.get(url,function(data){
                    if(data=="success"){
                        document.location.reload();
                    }
                });
            }
        }
	</script>
</body>
</html>