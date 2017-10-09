<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <body>

    <div class="container">
      <div class="starter-template">
        <h1>Fork Application ${sportType}</h1>
        <button type="button" class="btn btn-default"
                onclick="location.href='/fork/sportType?type=Football'">Football</button>
        <button type="button" class="btn btn-default"
                onclick="location.href='/fork/sportType?type=Tennis'">Tennis</button>
      </div>
      <br>

      <div class="dropdown pull-left">
        <button class="btn btn-success dropdown-toggle" type="button" id="menu1"
                data-toggle="dropdown">Parse</button>

        <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/parseAll">All</a>
          </li>
          <li role="presentation" class="divider"></li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/parseBookMaker?name=WilliamHill">WilliamHill</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/parseBookMaker?name=BWin">BWin</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/parseBookMaker?name=Bet365">Bet365</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/parseBookMaker?name=Pinnacle">Pinnacle</a>
          </li>
        </ul>
      </div>

      <div class="pull-left" style="margin-left: 5px">
        <button type="button" class="btn btn-success"
                onclick="location.href='/fork/countUp'">CountUp</button>
        <button type="button" class="btn btn-primary"
                onclick="location.href='/fork/getForks'">Forks</button>
        <button type="button" class="btn btn-primary"
                onclick="location.href='/fork/getTwoOfThree'">TwoOfThree</button>
      </div>

      <div class="dropdown pull-left" style="margin-left: 5px">
        <button class="btn btn-success dropdown-toggle" type="button" id="menu2"
                data-toggle="dropdown">BookMakers</button>

        <ul class="dropdown-menu" role="menu" aria-labelledby="menu2">
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/getMatches?name=WilliamHill">WilliamHill</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/getMatches?name=BWin">BWin</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/getMatches?name=Bet365">Bet365</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/getMatches?name=Pinnacle">Pinnacle</a>
          </li>
        </ul>
      </div>

      <div class="pull-left" style="margin-left: 5px">
        <button type="button" class="btn btn-info"
                onclick="location.href='/fork/live'">Live</button>
      </div>

    </div>
    <br>

  </body>
</html>
