<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>场馆预定</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/about.css" rel="stylesheet" type="text/css" media="all" />
    <script src="js/jquery.min.js"></script>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
                        <li><a href="order">场馆预订</a></li>
                        <li><a href="picture">相册</a></li>
                        <li class="active"><a href="about">相关</a></li>
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
    <h2>关于</h2>
</div>

</br>
</br>
<div id="about">
    <div class="about-word">
        此系统为徐煜企的毕业设计
        联系方式:601026825@qq.com
    </div>
</div>
</br>
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
            <div class="clearfix"> </div>
        </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function() {
            $().UItoTop({ easingType: 'easeOutQuart' });
        });
    </script>
    <a href="#to-top" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
</div>

</body>
</html>
