<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>场馆预定</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/order.css" rel="stylesheet" type="text/css" media="all"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script src="js/order.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/move-top.js"></script>
    <script src="js/easing.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.magnific-popup.js" type="text/javascript"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 900);
            });
        });
    </script>


</head>
<br>
<div class="header">
    <div class="header-top">
        <div class="container">
            <div class="header-top-top">
                <div class=" header-top-left">
                    <p>安徽大学: <span>计算机科学与技术学院</span></p>
                </div>
                <div class=" header-top-right">
                    <select class="drop-down drop-down-in">
                        <option value="1">中文</option>
                    </select>
                    <div class="clearfix"></div>
                </div>
                <div class="header-top-login">
                    <c:if test="${sessionScope.loginresult == 'fail' or null == sessionScope.loginresult}">
                        <a href="login">用户登录</a>
                    </c:if>
                    <c:if test="${sessionScope.loginresult == 'success'}">
                        <a href="login">欢迎，${sessionScope.username}</a>
                    </c:if>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
    <div class="header_bottom">
        <div class="container">
            <div class="header-bottom-top">
                <div class="logo">
                    <a href="index.html"><h1>场馆预订系统</h1></a>
                </div>
                <div class="top-nav">
                    <span class="menu"><img src="images/menu.png" alt=""> </span>
                    <ul>
                        <li><a href="index">首页</a></li>
                        <li><a href="venue">场馆</a></li>
                        <li class="active"><a href="order">场馆预订</a></li>
                        <li><a href="picture">相册</a></li>
                        <li><a href="about">相关</a></li>
                    </ul>
                </div>
                <div class="clearfix"> </div>
            </div>
        </div>
    </div>
</div>
<div class="banner banner5">
    <h2>场馆预定</h2>
</div>
</br>
<div class="title-choose">
    <div class="title-choose-venue">
        <span>场馆选择:</span>
        ${sessionScope.c_v_back}
    </div>
    <div class="title-choose-sport">
        <span>项目选择:</span>
        ${sessionScope.c_s_back}
    </div>
    <div class="title-choose-sport">
        <span>预定日期:</span>
        <span>
            <%
                SimpleDateFormat sdf =
                        new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DATE,1);
                out.print(sdf.format(cal.getTime()));
            %>
            <span style="color:red;">&nbsp;注:目前只可预定第二天的场馆</span>
        </span>
    </div>
</div>
</br>
<div class="order-all">
    <!-- 显示选择了场馆与项目后的列表 -->
    <c:if test="${(sessionScope.c_venue != null or sessionScope.c_sport != null) and sessionScope.orderTable != null}">
        <div class="order-title">
            <table class= "order-title-table">
                <thead>
                <tr class="">
                    <th width="271px" bgcolor="#CDCD9A">开放时间</th>
                    <th bgcolor="#CDCD9A">剩余数量</th>
                </tr>
                </thead>
            </table>
        </div>
    </c:if>
    <div class="orderlist" align="center">
        <table class="ordertable" border="1" align="center">
            <c:forEach items="${sessionScope.orderTable}" var="a">
                <tr style="text-align:center">
                    <c:forEach items="${a}" var="b" varStatus="b_flag">
                        <td>
                            ${b}
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
    </div>
    <!-- 显示预定的时间和预定按钮 -->
    <div class="order-submit">
        <c:if test="${sessionScope.c_venue != null and sessionScope.c_sport != null and sessionScope.orderTable != null}">
            </br>
            <span>预定时间：</span>
            <select id="order_choose_time">
                <c:forEach items="${sessionScope.orderTable}" var="a">
                    <c:forEach items="${a}" var="b" varStatus="b_flag">
                        <c:if test="${b_flag.first}">
                            <option value ="${b}">${b}</option>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </select>
            <button onclick="getOptionValue()">预定</button>
        </c:if>
    </div>

    <!-- 如果还没有选择场馆与项目 -->
    <c:if test="${sessionScope.c_venue == null and sessionScope.c_sport == null}">
        <div class="order-point">
            <span>请先选择场馆与项目再进行预定。</span>
        </div>
    </c:if>
</div>
<br>
<br>
<div class="footer">
    <div class="container">
        <div class="footer-bottom-at">
            <div class="col-md-6 footer-grid">
            </div>
            <div class="col-md-6 footer-grid-in">
                <ul class="footer-nav">
                    <li class="active"><a href="index">首页</a></li>
                    <li><a href="venue">场馆</a></li>
                    <li><a href="order">场馆预订</a></li>
                    <li><a href="picture">相册</a></li>
                    <li><a href="about">相关</a></li>
                </ul>
                <p class="footer-class">
                    Copyright &copy; 2017 安徽大学徐煜企版权所有 |
                    <a href="bslogin">后台登录</a>
                </p>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function () {
            $().UItoTop({easingType: 'easeOutQuart'});
        });
    </script>
    <a href="#to-top" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
</div>
<c:if test="${orderresult == 'success'}">
    <script>
        alert("预定成功！");
    </script>
</c:if>
<c:if test="${orderresult == 'fail'}">
    <script>
        alert("预定失败！");
    </script>
</c:if>
<c:if test="${orderresult == 'unenough'}">
    <script>
        alert("预定项目余量不足！");
    </script>
</c:if>
<c:if test="${orderresult == 'userordered'}">
    <script>
        alert("该用户已预定项目，无法预定！");
    </script>
</c:if>
</body>
</html>
