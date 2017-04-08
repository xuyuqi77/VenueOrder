function getOptionValue() {
    alert("111");
    var value = document.getElementById("order_choose_time").value;
    alert("2222");
    window.location="http://localhost:8080/vo";
    alert("3333");
    window.location.href="submitOrder?option_value=" + value;
    alert("4444");
}
