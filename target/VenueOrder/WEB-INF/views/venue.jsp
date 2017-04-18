<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/14
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>场馆</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <script src="js/jquery.min.js"></script>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/venue.css" rel="stylesheet" type="text/css" media="all" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Rugby Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
            Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <link rel="stylesheet" type="text/css" href="css/style5.css" />
    <script src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/move-top.js"></script>
    <script type="text/javascript" src="js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            $(".scroll").click(function(event){
                event.preventDefault();
                $('html,body').animate({scrollTop:$(this.hash).offset().top},900);
            });
        });
    </script>

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
                        <li><a href="index">首页</a></li>
                        <li class="active"><a href="venue">场馆</a></li>
                        <li><a href="order">场馆预订</a></li>
                        <li><a href="picture">相册</a></li>
                        <li><a href="about">相关</a></li>
                    </ul>
                    <script>
                        $("span.menu").click(function(){
                            $(".top-nav ul").slideToggle(500, function(){
                            });
                        });
                    </script>

                </div>

                <div class="clearfix"> </div>
            </div>
        </div>
    </div>
</div>
<div class="banner banner5">
    <h2>场馆</h2>
</div>
<div id="wrapper" style="margin-top:20px;">
    <div class="sub-content clear marginb20">
        <div class="sub-nav" id="sub-nav">
            <dl>
                <dt>全部场馆</dt>
                <dd><a href="venue?pic=1&word=1"><span>体育馆</span></a></dd>
                <dd><a href="venue?pic=2&word=2" ><span>北体育场</span></a></dd>
                <dd><a href="venue?pic=3&word=3" ><span>北篮球场</span></a></dd>
                <dd><a href="venue?pic=4&word=4" ><span>南篮球场</span></a></dd>
                <dd><a href="venue?pic=5&word=5" ><span>南足球场</span></a></dd>
            </dl>
        </div>
        <div class="sub-content-con clear">
            <div class="position-bar">当前位置：<a href="index">首页</a> > 场馆</div>
            <div class="list-content">
                <div style="width:100%;height:200px;">
                    <div class="img-box" style="padding-left:10px;padding-top:15px;display:inline;float:left;">
                        <img src="images/venues/${sessionScope.venue_pic}" width="300" height="200" />
                    </div>
                    <div style="width:450px;padding-top:13px;float:right">
                        ${sessionScope.venue_word}
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


</div>
</br>
</br>
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
