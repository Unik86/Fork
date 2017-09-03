<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Fork</title>
  </head>
  <body>

    <div class="container">
      <div class="starter-template">
        <h1>Fork Application</h1>
      </div>
      <button type="button" class="btn btn-primary" onclick="location.href='/parse'">Parse</button>
      <button type="button" class="btn btn-info" onclick="location.href='/getWillMatches'">WilliamHill</button>
      <button type="button" class="btn btn-info">FavBet</button>
    </div>
    <br>
    <div>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Left</th>
            <th>Right</th>
            <th>url</th>
            <th>1</th>
            <th>x</th>
            <th>2</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="match" items="${matches}">
            <tr>
              <td>${match.playerLeft}</td>
              <td>${match.playerRight}</td>
              <td>${match.winner.left}</td>
              <td>${match.winner.center}</td>
              <td>${match.winner.right}</td>
              <td><a href="${match.url}" class="btn btn-default">url</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

  </body>
</html>
