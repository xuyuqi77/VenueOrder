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
<form action="bssport" method="post" name="sportForm" id="sportForm">
	<div class="search_div">
		项目名：<input type="text" name="sportName" value="${sport.sport_name }"/>
		场馆：<select name="venueId" id="venueId" style="vertical-align: middle;">
		<option value="">请选择</option>
		<c:forEach items="${venueList}" var="venue">
			<option value="${venue.venue_id }" <c:if test="${sport.venue_id==venue.venue_id}">selected</c:if>>${venue.venue_name }</option>
		</c:forEach>
	</select>
		<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="main_table">
		<tr class="main_head">
			<th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllSport()"/></th>
			<th>序号</th>
			<th>项目名</th>
			<th>所属场馆</th>
			<th>开放时间</th>
			<th>剩余数量</th>
		</tr>
		<c:choose>
			<c:when test="${not empty sportList}">
				<c:forEach items="${sportList}" var="sport" varStatus="vs">
					<tr class="main_info">
						<td><input type="checkbox" name="sportIds" id="sportIds${sport.sport_id }" value="${sport.sport_id }"/></td>
						<td>${vs.index+1}</td>
						<td>${sport.sport_name }</td>
						<td>${sport.venue.venue_name }</td>
						<td>${sport.openning_times }</td>
						<td>${sport.sport_num }</td>
						<td>
							<a href="javascript:editSport(${sport.sport_id });">修改</a>
							| <a href="javascript:delSport(${sport.sport_id });">删除</a>
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
			<a href="javascript:addSport();" class="myBtn"><em>新增</em></a>
			<a href="javascript:exportSport();" class="myBtn"><em>导出</em></a>
		</div>
		<div class="pagemove" style="float:left;margin-left: 30px;">
			<a href="bssport?pagemove=1"><em>上一页</em></a>
			&nbsp;${sessionScope.sportpage.currentPage }/${sessionScope.sportpage.totalPage }&nbsp;
			<a href="bssport?pagemove=2"><em>下一页</em></a>
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

    function sltAllSport(){
        if($("#sltAll").attr("checked")){
            $("input[name='sportIds']").attr("checked",true);
        }else{
            $("input[name='sportIds']").attr("checked",false);
        }
    }

    function addSport(){
        //$(".shadow").show();
        var dg = new $.dialog({
            title:'新增项目',
            id:'sport_new',
            width:330,
            height:300,
            iconTitle:false,
            cover:true,
            maxBtn:false,
            xButton:true,
            resize:false,
            page:'bssport/add'
        });
        dg.ShowDialog();
    }

    function editSport(sportId){
        var dg = new $.dialog({
            title:'修改项目',
            id:'sport_edit',
            width:330,
            height:300,
            iconTitle:false,
            cover:true,
            maxBtn:false,
            resize:false,
            page:'bssport/edit?sportId='+sportId
        });
        dg.ShowDialog();
    }

    function delSport(sportId){
        if(confirm("确定要删除该记录？")){
            var url = "bssport/delete?sportId="+sportId;
            $.get(url,function(data){
                if(data=="success"){
                    document.location.reload();
                }
            });
        }
    }

    function search(){
        $("#sportForm").submit();
    }

    function exportSport(){
        document.location = "bssport/excel";
    }
</script>
</body>
</html>