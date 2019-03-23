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
      Count: ${forks.size()} |
      Date: ${forks.size() > 0 ? forks.get(0).getParsDateStr() : null}
      <div class="dropdown pull-right">
        <button class="btn btn-default dropdown-toggle" type="button" id="menu3"
                data-toggle="dropdown">Export</button>

        <ul class="dropdown-menu" role="menu" aria-labelledby="menu3">
          <li role="presentation">
            <a role="menuitem" tabindex="-1" onclick="location.href='/exportForksToExcel.xlsx'">Excel</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" onclick="location.href='/exportForksToPdf.pdf'">PDF</a>
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
            <th>Forks</th>
            <th>Parse</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="fork" items="${forks}">
            <c:forEach var="match" items="${fork.matches}">
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
                <td></td>
                <td></td>
              </tr>
            </c:forEach>
            <tr class="info">
              <td></td>
              <td></td>
              <td></td>

              <td>${fork.forkBet.left}</td>
              <td>${fork.forkBet.center}</td>
              <td>${fork.forkBet.right}</td>

              <td>${fork.rate}</td>
              <td>${fork.percent}%</td>

              <td></td>
              <td></td>
              <td><a onclick="location.href='/parseMatch?fork=fork'" class="btn btn-default">Forks</a></td>
              <td><a onclick="location.href='/parseMatch?fork=fork'" class="btn btn-default">Parse</a></td>
            </tr>
            <tr class="info">
              <td></td>
              <td></td>
              <td></td>

              <td>${fork.percentBet.left}%</td>
              <td>${fork.percentBet.center}%</td>
              <td>${fork.percentBet.right}%</td>

              <td>${fork.sumPercentBet}%</td>
              <td></td>

              <td></td>
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
