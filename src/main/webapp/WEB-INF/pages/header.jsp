<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <body>

    <div class="container">
      <div class="starter-template">
        <h1>Fork Application</h1>
      </div>
      <br>

      <div class="dropdown pull-left" style="margin-right: 5px">
        <button class="btn btn-success dropdown-toggle" type="button" id="menu1"
                data-toggle="dropdown">Parse</button>

        <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/parseAll">All</a>
          </li>
          <li role="presentation" class="divider"></li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/parseBookMaker?type=WilliamHill">WilliamHill</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/parseBookMaker?type=BWin">BWin</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/parseBookMaker?type=Bet365">Bet365</a>
          </li>
        </ul>
      </div>

      <div class="pull-left" style="margin-right: 5px">
        <button type="button" class="btn btn-success"
                onclick="location.href='/fork/countUp'">CountUp</button>
        <button type="button" class="btn btn-primary"
                onclick="location.href='/fork/getForks'">Forks</button>
        <button type="button" class="btn btn-primary"
                onclick="location.href='/fork/getTwoOfThree'">TwoOfThree</button>
      </div>

      <div class="dropdown pull-left">
        <button class="btn btn-success dropdown-toggle" type="button" id="menu2"
                data-toggle="dropdown">BookMakers</button>

        <ul class="dropdown-menu" role="menu" aria-labelledby="menu2">
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/getMatches?type=WilliamHill">WilliamHill</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/getMatches?type=BWin">BWin</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/getMatches?type=Bet365">Bet365</a>
          </li>
        </ul>
      </div>

    </div>
    <br>

  </body>
</html>
