function stopSpinner() {
    document.getElementById("spinner").style.display = "none";
}

function runSpinner() {
    document.getElementById("spinner").style.display = "block";
}

$(document).on("click",".parse-btn", function () {
    $(this).attr("href", this.href + "&parseType="+$(":radio[name=type]:checked").val());
});