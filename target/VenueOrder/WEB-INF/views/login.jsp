<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
    <link href="css/login2.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<h1>预订系统登录<sup>V2017</sup></h1>
<div class="login" style="margin-top:50px;">
    <div class="header">
        <div class="switch" id="switch">
            <a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">
                快速登录
            </a>
            <a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">
                快速注册
            </a>
            <div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;">
            </div>
        </div>
    </div>

    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">
        <!--登录-->
        <div class="web_login" id="web_login">
            <div class="login-box">
                <div class="login_form">
                    <form action="user/verify" name="loginform" accept-charset="utf-8" id="login_form" method="post" class="loginForm">
                        <input type="hidden" name="did" value="0"/>
                        <input type="hidden" name="to" value="log"/>
                        <div class="uinArea" id="uinArea">
                            <label class="input-tips" for="u">帐号：</label>
                            <div class="inputOuter" id="uArea">
                                <input type="text" id="u" name="loginname" class="inputstyle"/>
                            </div>
                        </div>
                        <div class="pwdArea" id="pwdArea">
                            <label class="input-tips" for="p">密码：</label>
                            <div class="inputOuter" id="pArea">
                                <input type="password" id="p" name="password" class="inputstyle"/>
                            </div>
                        </div>
                        <div class="login_code" id="login_code">
                            <label style="float:left;margin-top:5px;width:60px;height:40px;font-size:14px;line-height:40px;" for="c">验证码：</label>
                            <div id="code" style="width:200px;height:42px;margin-top:5px;float:left;">
                                <input type="text" id="c" name="code" class="inputstyle" style="width:100px;"/>
                                &nbsp;&nbsp;<img id="codeImg" alt="点击更换" title="点击更换" src="" style=""/>
                            </div>
                        </div>

                        <div style="padding-left:50px;margin-top:2px;">
                            <input type="submit" value="登 录" style="width:150px;" class="button_blue"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--登录end-->
    </div>

    <!--注册-->
    <div class="qlogin" id="qlogin" style="display: none; ">
        <div class="web_login">
            <form name="form2" id="regUser" accept-charset="utf-8" action="user/add" method="post">
                <input type="hidden" name="to" value="reg"/>
                <input type="hidden" name="did" value="0"/>
                <ul class="reg_form" id="reg-ul">
                    <div id="userCue" class="cue">欢迎注册使用预订系统</div>
                    <li>
                        <label for="lgname" class="input-tips2">用户名：</label>
                        <div class="inputOuter2">
                            <input type="text" id="lgname" name="lgname" maxlength="16" class="inputstyle2"/>
                        </div>

                    </li>
                    <li>
                        <label for="passwd" class="input-tips2">密码：</label>
                        <div class="inputOuter2">
                            <input type="password" id="passwd" name="passwd" maxlength="16" class="inputstyle2"/>
                        </div>
                    </li>
                    <li>
                        <label for="passwd2" class="input-tips2">确认密码：</label>
                        <div class="inputOuter2">
                            <input type="password" id="passwd2" name="passwd2" maxlength="16" class="inputstyle2"/>
                        </div>
                    </li>
                    <li>
                        <label for="nickname" class="input-tips2">昵称：</label>
                        <div class="inputOuter2">
                            <input type="text" id="nickname" name="nickname" maxlength="10" class="inputstyle2"/>
                        </div>
                    </li>
                    <li>
                        <div class="inputArea">
                            <input type="button" id="reg" style="margin-top:10px;margin-left:85px;" class="button_blue" value="注册"/>
                        </div>
                    </li>
                    <div class="cl"></div>
                </ul>
            </form>
        </div>
    </div>
    <!--注册end-->
</div>
<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>
<script type="text/javascript">
    $(document).ready(function(){
        changeCode();
        $("#codeImg").bind("click",changeCode);
    });
    function genTimestamp(){
        var time = new Date();
        return time.getTime();
    }
    function changeCode(){
        $("#codeImg").attr("src","code.html?t="+genTimestamp());
    }
</script>
</body>
<c:if test="${loginresult == 'fail'}">
    <script>
        alert("帐号或密码错误！");
    </script>
</c:if>
<c:if test="${loginresult == 'wrongcode'}">
    <script>
        alert("验证码错误！");
    </script>
</c:if>
</html>
