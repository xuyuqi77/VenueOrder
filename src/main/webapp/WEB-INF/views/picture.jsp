order.jsp<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/14
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>场馆预定</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/picture.css" rel="stylesheet" type="text/css" media="all" />
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
                        <li><a href="order">场馆预订</a></li>
                        <li class="active"><a href="image">相册</a></li>
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
    <h2>相册</h2>
</div>


<div id="wrapper" style="margin-top:20px;">
    <div class="sub-content clear marginb20" style="height: 800px;">

        <div class="sub-nav" id="sub-nav">
            <dl>
                <dt>按场馆浏览</dt>
                <dd><a href="" class="cur"><span>气膜馆</span></a></dd>
                <dd><a href="" ><span>综合体育馆</span></a></dd>
                <dd><a href="" ><span>西体育馆</span></a></dd>
                <dd><a href="" ><span>体育场</span></a></dd>
            </dl>
        </div>


        <div class="sub-content-con clear" style="height: 600px;">
            <div class="position-bar">当前位置：<a href="">首页</a>
                > <a href="">气膜馆</a>
                > 相册</div>
            <div class="title-box">气膜馆相册</div>

            <form id="phForm" action="" method="post">
                <input type="hidden" value="cg" name="listmode"/>
                <input type="hidden" value="index" name="fromPage"/>
                <input type="hidden" value="3998000" name="cgid"/>
            </form>
            <div class="list-content">
                <div class="pic_small">
                    <a href=""  target="_blank"><img alt="" src="" title="姜来老师上课" width="215px" height="138px"></a>
                    <p><a href="" title="姜来老师上课" target="_blank">姜来老师上课</a></p>
                </div>
            </div>
        </div>
        <div class="badoo" style="margin-left: 20px;display: inline;">
            <div class='A53DB362D4C16471DB13E901CC9F471C4'>
                <span>共27条记录</span>
                <span class='disabled'>首页</span>
                <span class='disabled'>前一页</span>
                <span class='current' title='1'>1</span>
                <a href='' title='2'>2</a>
                <a href='' title='3'>3</a>
                <a href='' title='2'>后一页</a>
                <a href='' title='3'>末页</a>
                <span>当前[1/3]页</span>
            </div>
            <script type="text/javascript">
            $(function(){
                $(".A53DB362D4C16471DB13E901CC9F471C4 a").click(function(){
                    var $pf=$("form#phForm");
                    var currentPage=$(this).attr("title");
                    var varPageSize="<input type='hidden' name='pageUtil.pageSize' value='9' id='pageSize'/>";
                    var varCurrPage="<input type='hidden' name='pageUtil.currentPage' value='"+currentPage+"' id='currentPage' class='currentPage'/>";
                    $pf.append(varPageSize);
                    $pf.append(varCurrPage);
                    $pf.submit();
                    return false;
                });

                $("form#phForm").submit(function(){
                    var $pf=$(this);
                    var callback=$pf.attr("callback");
                    if(callback) {
                        var ret=window[callback].call(window,this);
                        if(!ret) return false;
                    }
                    var m=$pf.attr("method");
                    if(m.toLowerCase()=="post"){
                        return true;
                    }else{
                        var url=$pf.attr("action");
                        var includes="";
                        if(includes==""){
                            var param=$pf.serialize();
                            param=(param=="") ? "" : "?"+param;
                            location.href=url + param;
                        }else{
                            var $form=$("<form></form>",{"method":"post","action":""}).appendTo("body");
                            var arrIncludes=includes.split(",");
                            var $mmpf=$pf.clone(false);
                            for(var s in arrIncludes){
                                var filter="input[name='"+arrIncludes[s]+"']";
                                filter+=",select[name='"+arrIncludes[s]+"']";
                                filter+=",textarea[name='"+arrIncludes[s]+"']";
                                $form.append($mmpf.find(filter).hide());
                                $mmpf.find(filter).remove();
                            }
                            var param=$mmpf.serialize();
                            param=(param=="") ? "" : "?"+param ;
                            $form.attr("action",url+param);
                            $form.submit();
                        }
                        return false;
                    }
                });
            });
        </script>
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
    <script type="text/javascript">
        $(document).ready(function() {
            $().UItoTop({ easingType: 'easeOutQuart' });
        });
    </script>
    <a href="#to-top" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
</div>
</body>
</html>
