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

    <jsp:include page="../pages/header.jsp" />

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

            <th>Sum 1</th>
            <th>Sum x</th>
            <th>Sum 2</th>

            <th>Win Sum 1</th>
            <th>Win Sum x</th>
            <th>Win Sum 2</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="fork" items="${forks}">
            <tr>
              <td>playerLeft</td>
              <td>playerRight</td>

              <td>${fork.bet.left}</td>
              <td>${fork.bet.center}</td>
              <td>${fork.bet.right}</td>

              <td>${fork.forkRate}</td>

              <td>${fork.sumRight}</td>
              <td>${fork.sumCenter}</td>
              <td>${fork.sumLeft}</td>

              <td>${fork.winSumRight}</td>
              <td>${fork.winSumCenter}</td>
              <td>${fork.winSumLeft}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

  </body>
</html>
