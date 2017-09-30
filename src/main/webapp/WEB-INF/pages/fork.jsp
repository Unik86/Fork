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

    <div class="well">Count: ${forks.size()}</div>

    <div>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Left</th>
            <th>Right</th>

            <th>1</th>
            <th>x</th>
            <th>2</th>

            <th>Rate</th>

            <th>BookMaker</th>
            <th>Url</th>
            <th>Forks</th>
            <th>Parse</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="fork" items="${forks}">
            <c:forEach var="match" items="${fork.matches}">
              <tr>
                <td>${match.playerLeft}</td>
                <td>${match.playerRight}</td>

                <td>${match.winner.left}</td>
                <td>${match.winner.center}</td>
                <td>${match.winner.right}</td>

                <td></td>

                <td>${match.bookMaker}</td>
                <td><a href="${match.url}" class="btn btn-default">Url</a></td>
                <td></td>
                <td></td>
              </tr>
            </c:forEach>
            <tr class="info">
              <td></td>
              <td></td>

              <td>${fork.forkBet.left}</td>
              <td>${fork.forkBet.center}</td>
              <td>${fork.forkBet.right}</td>

              <td>${fork.rate}</td>

              <td></td>
              <td></td>
              <td><a onclick="location.href='/fork/parseMatch?fork=fork'" class="btn btn-primary">Forks</a></td>
              <td><a onclick="location.href='/fork/parseMatch?fork=fork'" class="btn btn-success">Parse</a></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

  </body>
</html>
