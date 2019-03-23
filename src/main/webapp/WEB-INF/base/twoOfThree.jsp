<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Fork</title>
  </head>
  <body>

    <jsp:include page="../header.jsp" />

    <div class="well">
      Count: ${twoOfThrees.size()} |
      Date: ${twoOfThrees.size() > 0 ? twoOfThrees.get(0).getParsDateStr() : null}
      <div class="dropdown pull-right">
        <button class="btn btn-default dropdown-toggle" type="button" id="menu3"
                data-toggle="dropdown">Export</button>

        <ul class="dropdown-menu" role="menu" aria-labelledby="menu3">
          <li role="presentation">
            <a role="menuitem" tabindex="-1" onclick="location.href='/exportTwoOfThreesToExcel.xlsx'">Excel</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" onclick="location.href='/exportTwoOfThreesToPdf.pdf'">PDF</a>
          </li>
        </ul>
      </div>
    </div>

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

            <th>Rate</th>
            <th>Percent</th>

            <th>BookMaker</th>
            <th>Url</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="twoOfThree" items="${twoOfThrees}">
            <c:forEach var="match" items="${twoOfThree.matches}">
              <tr>
                <td>${match.time}</td>
                <td>${match.playerLeft}</td>
                <td>${match.playerRight}</td>

                <td>${match.winner.left}</td>
                <td>${match.winner.center}</td>
                <td>${match.winner.right}</td>

                <td></td>
                <td></td>

                <td>${match.bookMaker}</td>
                <td><a href="${match.url}" class="btn btn-default">Url</a></td>
              </tr>
            </c:forEach>
            <tr class="info">
              <td></td>
              <td></td>
              <td></td>

              <td>${twoOfThree.bet.left}</td>
              <td>${twoOfThree.bet.center}</td>
              <td>${twoOfThree.bet.right}</td>

              <td>${twoOfThree.rate}</td>
              <td>${twoOfThree.percent}%</td>

              <td></td>
              <td></td>
            </tr>
            <tr class="info">
              <td></td>
              <td></td>
              <td></td>

              <td>${twoOfThree.percentBet.left}%</td>
              <td>${twoOfThree.percentBet.center}%</td>
              <td>${twoOfThree.percentBet.right}%</td>

              <td>${twoOfThree.sumPercentBet}%</td>
              <td></td>

              <td></td>
              <td></td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

  </body>
</html>
