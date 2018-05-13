function stopSpinner() {
    document.getElementById("spinner").style.display = "none";
}

function runSpinner() {
    document.getElementById("spinner").style.display = "block";
}

$(document).on("click",".parse-btn", function () {
    $(this).attr("href", this.href + "&parseType="+$(":radio[name=type]:checked").val());
});

function runLive() {
    var inputValues = $('.form-control').map(function() {
        return $(this).val();
    }).toArray();

    $.ajax({
        url: '/fork/startLive',
        type: 'POST',
        data: JSON.stringify(inputValues),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false
    });
}