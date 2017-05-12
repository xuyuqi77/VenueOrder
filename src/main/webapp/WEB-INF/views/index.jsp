<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*,java.text.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/index.css" rel="stylesheet" type="text/css" media="all"/>
    <script src="js/jquery.min.js"></script>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Rugby Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
            Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design"/>
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
    <script>
        function logout(){
            if(confirm("确定要退出吗？")){
                var url = "user/loginout";
                $.get(url);
                document.location = "index";
            }
        }
        function lastmoney(){
            var loginname = "${sessionScope.loginname}";
            var url = "user/lastmoney?loginname="+loginname;
            $.get(url,function(data){
                if(data != null){
                    alert("余额:"+data);
                }
            });
        }
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
                <div class="header-top-login">
                    <c:if test="${sessionScope.loginresult == 'fail' or null == sessionScope.loginresult}">
                        <a href="login">用户登录</a>
                    </c:if>
                    <c:if test="${sessionScope.loginresult == 'success'}">
                        <a href="javascript:logout();">欢迎，${sessionScope.loginname}</a>
                        &nbsp;
                        <a href="orderList?loginname=${sessionScope.loginname}">
                            <button style="border:0px;background-color: rgba(0,0,0,0.2)">查询订单</button>
                        </a>
                        <a href="javascript:lastmoney();">查询余额</a>
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
                        <li><a href="picture">相册</a></li>
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
                        <h3>运动，就是这么简单</h3>
                        <p>Keep Moving</p>
                        <a href="order">立刻预定<i class="glyphicon glyphicon-arrow-right"> </i></a>
                    </li>
                    <li>
                        <h3>享受运动，享受生活</h3>
                        <p>Enjoy Yourself</p>
                        <a href="order">立刻预定<i class="glyphicon glyphicon-arrow-right"> </i></a>
                    </li>
                    <li>
                        <h3>既然静不下来，那就动起来</h3>
                        <p>Just Do it</p>
                        <a href="order">立刻预定<i class="glyphicon glyphicon-arrow-right"> </i></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="welcome">
    <div class="container">
        <h2>欢迎使用场馆预订系统</h2>
    </div>
</div>
<div class="liebiaotou" align="center">
    <table class= "bt" align="center">
        <thead>
            <tr class="bt_tr">
                <th width="352px" bgcolor="#CDCD9A">场馆项目</th>
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