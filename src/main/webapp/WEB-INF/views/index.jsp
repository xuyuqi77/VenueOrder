<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*,java.text.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <script src="js/jquery.min.js"></script>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Rugby Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
            Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
    <%--<link href='http://fonts.useso.com/css?family=Open+Sans:400,300,400italic,300italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>--%>
    <%--<link href='http://fonts.useso.com/css?family=Audiowide' rel='stylesheet' type='text/css'>--%>
    <script src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 900);
            });
        });
    </script>
    <link href="css/popuo-box.css" rel="stylesheet" type="text/css" media="all"/>
    <script src="js/jquery.magnific-popup.js" type="text/javascript"></script>
</head>
<br>
<!--header-->
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
                        <!--
                        <option value="2">中文</option>
                        <option value="3">中文</option>
                        -->
                    </select>
                    <div class="clearfix"></div>
                </div>
                <div class="header-top-login">
                    <c:if test="${sessionScope.loginresult == 'fail' or null == sessionScope.loginresult}">
                        <a href="login">登录</a>
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
                        <li class="active"><a href="index">首页</a></li>
                        <li><a href="venue">场馆</a></li>
                        <li><a href="order">场馆预订</a></li>
                        <li><a href="image">相册</a></li>
                        <li><a href="about">相关</a></li>
                    </ul>
                    <script>
                        $("span.menu").click(function () {
                            $(".top-nav ul").slideToggle(500, function () {
                            });
                        });
                    </script>

                </div>

                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>
<div class="banner">
    <script src="js/responsiveslides.min.js"></script>
    <script>
        $(function () {
            $("#slider").responsiveSlides({
                auto: true,
                nav: true,
                speed: 500,
                namespace: "callbacks",
                pager: true,
            });
        });
    </script>
    <div class="slider">
        <div class="container">
            <div class="callbacks_container">
                <ul class="rslides" id="slider">
                    <li>
                        <h3>Mini footy</h3>
                        <p>Mini rugby league is a 9-a-side adapted version of the sport. It includes full contact but
                            does not feature scrums and limits kicking to converting tries</p>
                        <a href="about.html">Read More<i class="glyphicon glyphicon-arrow-right"> </i></a>
                    </li>
                    <li>
                        <h3>Mod league</h3>
                        <p>Mod league follows on from mini footy; it introduces laws more common to the full
                            international laws of rugby league, whilst also keeping the theme league.</p>
                        <a href="about.html">Read More<i class="glyphicon glyphicon-arrow-right"> </i></a>
                    </li>
                    <li>
                        <h3>Tag rugby</h3>
                        <p>Tag rugby, or flag rugby, is a non-contact team game in which each player wears a belt that
                            has two velcro tags attached to it, or shorts with velcro patch</p>
                        <a href="about.html">Read More<i class="glyphicon glyphicon-arrow-right"> </i></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="welcome">
    <div class="container">
        <h2>欢迎使用场馆预订系统</h2>
        <p>14 teams qualify for the 2013 Rugby League World Cup: Australia, England, New Zealand, Samoa, Wales, Fiji,
            France, Papua New Guinea, Ireland, Scotland, Tonga, Cook Islands, Italy and United States of America. The
            Tri-Nations series is expanded to include Argentina, and is renamed The Rugby Championship.</p>
    </div>
</div>
<div class="liebiaotou" align="center">
    <table class= "bt" align="center">
        <thead>
            <tr class="bt_tr">
                <th width="352px" bgcolor="#CDCD9A">
                    <%
                        SimpleDateFormat sdf =
                                new SimpleDateFormat ("yyyy-MM-dd");
                        out.print(sdf.format(new Date()));
                    %>
                </th>
                <th width="271px" bgcolor="#CDCD9A">开放时间</th>
                <th bgcolor="#CDCD9A">剩余数量</th>
            </tr>
        </thead>
    </table>
</div>
<div class="liebiao" align="center">
    <table class="bzt" border="1" align="center">
        <c:forEach items="${sessionScope.vp_lists}" var="a">
        <tr style="text-align:center">
            <c:forEach items="${a}" var="b" varStatus="b_flag">
                <td>
                    ${b}
                    <c:if test="${b_flag.last}">
                        &nbsp;<a href="order">查看</a>
                    </c:if>
                </td>
            </c:forEach>
        </tr>
        </c:forEach>
    </table>
</div>
</br>
<div class="footer">
    <div class="container">
        <div class="footer-bottom-at">
            <div class="col-md-6 footer-grid">
                <h3>Rugby</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore
                    et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut
                    aliquip ex ea commodo consequat.</p>
            </div>
            <div class="col-md-6 footer-grid-in">
                <ul class="footer-nav">
                    <li class="active"><a href="index">首页</a></li>
                    <li><a href="venue">场馆</a></li>
                    <li><a href="order">场馆预订</a></li>
                    <li><a href="image">相册</a></li>
                    <li><a href="about">相关</a></li>
                </ul>
                <p class="footer-class">Copyright &copy; 2015.Company name All rights reserved.More Templates</p>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function () {
            $().UItoTop({easingType: 'easeOutQuart'});
        });
    </script>
    <a href="#to-top" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
</div>
</body>
</html>