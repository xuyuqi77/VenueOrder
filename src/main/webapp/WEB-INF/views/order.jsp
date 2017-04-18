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
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
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
</br>
<div class="title-choose">
    <div class="title-choose-venue">
        场馆选择:
        <a href="/vo/afterchoose?c_venue=北体育馆">北体育馆</a>
        <a href="/vo/afterchoose?c_venue=北篮球场">北篮球场</a>
    </div>
    <div class="title-choose-sport">
        项目选择:
        <a href="/vo/afterchoose?c_sport=羽毛球">羽毛球</a>
        <a href="/vo/afterchoose?c_sport=篮球">篮球</a>
    </div>
</div>
</br>
<div class="order-all">
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

    <div>
        <c:if test="${sessionScope.c_venue != null and sessionScope.c_sport != null}">
            <select id="order_choose_time">
                <c:forEach items="${requestScope.orderTable}" var="a">
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
</div>
<br>
<br>
<div class="footer">
    <div class="container">
        <div class="footer-bottom-at">
            <div class="col-md-6 footer-grid">
                <h3>Rugby</h3>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
            </div>
            <div class="col-md-6 footer-grid-in">
                <p class="footer-class">Copyright &copy; 2015.Company name All rights reserved.More Templates</p>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
    <!---->
    <script type="text/javascript">
        $(document).ready(function() {
            /*
             var defaults = {
             containerID: 'toTop', // fading element id
             containerHoverID: 'toTopHover', // fading element hover id
             scrollSpeed: 1200,
             easingType: 'linear'
             };
             */
            $().UItoTop({ easingType: 'easeOutQuart' });
        });
    </script>
    <a href="#to-top" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
    <!---->
</div>

</body>
</html>
