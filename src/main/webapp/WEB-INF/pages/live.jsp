<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../pages/css/style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Fork</title>
  </head>
  <body>

    <div class="container">
      <div class="starter-template">
        <h1>Fork Application (Live)</h1>
      </div>
      <br>

      <div class="pull-left">
        <button type="button" class="btn btn-success"
                onclick="location.href='/fork/start'">Start</button>
      </div>

      <div class="pull-left" style="margin-left: 5px">
        <button type="button" class="btn btn-info"
                onclick="location.href='/fork'">Fork</button>
      </div>
      <br><br>

    </div>

    <!--script type="text/javascript">
        window.setInterval(function(){
            $.ajax({url: "/fork/start", success: function(result){
                //$('#cnt').html();
            }});
        }, 3000);
    </script-->

  </body>
</html>
