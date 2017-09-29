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
                onclick="location.href='/fork/startLive'">Start</button>
      </div>

      <div class="pull-left" style="margin-left: 5px">
        <button type="button" class="btn btn-success"
                onclick="location.href='/fork/stopLive'">Stop</button>
      </div>

      <div class="pull-left" style="margin-left: 5px">
        <button type="button" class="btn btn-primary"
                onclick="location.href='/fork/getLives'">Lives</button>
      </div>

      <div class="pull-left" style="margin-left: 5px">
        <button type="button" class="btn btn-warning"
                onclick="location.href='/fork/clearLives'">Clear</button>
      </div>

      <div class="pull-left" style="margin-left: 5px">
        <button type="button" class="btn btn-info"
                onclick="location.href='/fork'">Fork</button>
      </div>
      <br><br>

    </div>
    <br><br>

    <div class="well">Count: ${lives.size()}</div>

    <div>
      <table class="table table-striped">
        <thead>
        <tr>
          <th>Time</th>

          <th>Bet</th>

          <th>1</th>
          <th>x</th>
          <th>2</th>

          <th>Rate</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="live" items="${lives}">
          <tr class="success">
            <td>${live.timeStr}</td>

            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>

          <c:forEach var="fork" items="${live.forks}">
            <c:forEach var="bet" items="${fork.bets}">
              <tr>
                <td></td>

                <td>${bet.bookMaker}</td>

                <td>${bet.left}</td>
                <td>${bet.center}</td>
                <td>${bet.right}</td>

                <td></td>
              </tr>
            </c:forEach>
            <tr class="info">
              <td></td>

              <td>${fork.forkBet.name}</td>
              <td>${fork.forkBet.left}</td>
              <td>${fork.forkBet.center}</td>
              <td>${fork.forkBet.right}</td>

              <td>${fork.rate}</td>
            </tr>
          </c:forEach>
        </c:forEach>
        </tbody>
      </table>
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
