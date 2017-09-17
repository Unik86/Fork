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

    <jsp:include page="../pages/header.jsp" />

    <div>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Time</th>
            <th>Left</th>
            <th>Right</th>
            <th>1</th>
            <th>x</th>
            <th>2</th>
            <th>Url</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="match" items="${matches}">
            <tr>
              <td>${match.time}</td>

              <td>${match.playerLeft}</td>
              <td>${match.playerRight}</td>

              <td>${match.winner.left}</td>
              <td>${match.winner.center}</td>
              <td>${match.winner.right}</td>

              <td><a href="${match.url}" class="btn btn-default">Url</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

  </body>
</html>
