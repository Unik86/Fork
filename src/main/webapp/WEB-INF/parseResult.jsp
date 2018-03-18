<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Fork</title>
  </head>
  <body>

    <jsp:include page="header.jsp" />

    <div>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>BookMaker</th>
            <th>Parse Result</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="result" items="${results}">
            <tr>
              <td>${result.bookMakerName}</td>
              <td>${result.resultMessage}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

  </body>
</html>
