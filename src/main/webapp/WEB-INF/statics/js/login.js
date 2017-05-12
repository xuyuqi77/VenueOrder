$(function(){

    $('#switch_qlogin').click(function(){
        $('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
        $('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
        $('#switch_bottom').animate({left:'0px',width:'70px'});
        $('#qlogin').css('display','none');
        $('#web_qr_login').css('display','block');

    });
    $('#switch_login').click(function(){

        $('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
        $('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
        $('#switch_bottom').animate({left:'154px',width:'70px'});

        $('#qlogin').css('display','block');
        $('#web_qr_login').css('display','none');
    });
    if(getParam("a")=='0')
    {
        $('#switch_login').trigger('click');
    }

});

function sleep(d){
    for(var t = Date.now();Date.now() - t <= d;);
}

function logintab(){
    scrollTo(0);
    $('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
    $('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
    $('#switch_bottom').animate({left:'154px',width:'96px'});
    $('#qlogin').css('display','none');
    $('#web_qr_login').css('display','block');

}


//根据参数名获得该参数 pname等于想要的参数名
function getParam(pname) {
    var params = location.search.substr(1); // 获取参数 平且去掉？
    var ArrParam = params.split('&');
    if (ArrParam.length == 1) {
        //只有一个参数的情况
        return params.split('=')[1];
    }
    else {
        //多个参数参数的情况
        for (var i = 0; i < ArrParam.length; i++) {
            if (ArrParam[i].split('=')[0] == pname) {
                return ArrParam[i].split('=')[1];
            }
        }
    }
}


var reMethod = "GET",
    pwdmin = 6;

$(document).ready(function() {
    $('#reg').click(function() {
        if ($('#lgname').val() == "") {
            $('#lgname').focus().css({
                border: "1px solid red",
                boxShadow: "0 0 2px red"
            });
            $('#userCue').html("<font color='red'><b>×用户名不能为空</b></font>");
            return false;
        }
        if ($('#lgname').val().length < 3 || $('#lgname').val().length > 16) {

            $('#lgname').focus().css({
                border: "1px solid red",
                boxShadow: "0 0 2px red"
            });
            $('#userCue').html("<font color='red'><b>×用户名位3-16字符</b></font>");
            return false;
        }
        $.ajax({
            type: "GET",
            url: "user/loginname",
            data: "loginname=" + $("#lgname").val(),
            dataType: 'html',
            success: function(result) {
                if (result == "t"){
                    $('#lgname').focus().css({
                        border: "1px solid red",
                        boxShadow: "0 0 2px red"
                    });
                    $('#userCue').html("<font color='red'><b>用户名已存在</b></font>");
                    return false;
                }
            }
        });

        sleep(1000);

        if ($('#passwd').val().length < pwdmin) {
            $('#passwd').focus();
            $('#userCue').html("<font color='red'><b>×密码不能小于" + pwdmin + "位</b></font>");
            return false;
        }
        if ($('#passwd2').val() != $('#passwd').val()) {
            $('#passwd2').focus();
            $('#userCue').html("<font color='red'><b>×两次密码不一致！</b></font>");
            return false;
        }
        if (false) {
            $('#nickname').focus().css({
                border: "1px solid red",
                boxShadow: "0 0 2px red"
            });
        } else {
            $('#nickname').css({
                border: "1px solid #D7D7D7",
                boxShadow: "none"
            });
        }
        $('#regUser').submit();
    });
});

