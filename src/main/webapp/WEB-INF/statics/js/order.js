function getOptionValue() {
    alert("1");
    var value = document.getElementById("order_choose_time").value;
    window.location="http://localhost:8080/vo";
    window.location.href="submitOrder?option_value=" + value;
}
