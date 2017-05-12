function getOptionValue() {
    var value = document.getElementById("order_choose_time").value;
    var venue = "${sessionScope.c_venue}";
    var sport = "${sessionScope.c_sport}";
    if(confirm(venue+" "+sport+" "+value+"\n确定预定吗?")){
        window.location="http://localhost:8080/vo";
        window.location.href="submitOrder?option_value=" + value;
    }
}
