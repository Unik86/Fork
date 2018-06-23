<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../fork/css/style.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="../fork/js/script.js"></script>
  </head>

  <body onload="stopSpinner()">

    <div class="container">
      <div class="starter-template">
        <a href="/fork" style="color: black;text-decoration: none;">
          <h1>Fork Application ${sportType}</h1>
        </a>
        <button type="button" class="btn btn-default"
                onclick="location.href='/fork/sportType?type=Football'">Football</button>
        <button type="button" class="btn btn-default"
                onclick="location.href='/fork/sportType?type=Tennis'">Tennis</button>
      </div>
      <br>

      <div>
        <input type="radio" id="type1" name="type" value="All">
        <label for="type1">All</label>

        <input type="radio" id="type2" name="type" value="OnlyLive">
        <label for="type2">Only Live</label>

        <input type="radio" id="type3" name="type" value="WithoutLive" checked="checked">
        <label for="type3">Without Live</label>
      </div>

      <div class="dropdown pull-left">
        <button class="btn btn-success dropdown-toggle" type="button" id="menu1"
                data-toggle="dropdown">Parse</button>

        <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
          <li role="presentation">
            <a class="parse-btn" role="menuitem" tabindex="-1" onclick="runSpinner()" href="/fork/parseAll?group=alex">
              All Alex
            </a>
          </li>
          <li role="presentation">
            <a class="parse-btn" role="menuitem" tabindex="-1" onclick="runSpinner()" href="/fork/parseAll?group=my">
              All My
            </a>
          </li>
          <li role="presentation" class="divider"></li>
          <li role="presentation">
            <a class="parse-btn" role="menuitem" tabindex="-1" onclick="runSpinner()" href="/fork/parseBookMaker?name=WilliamHill">
              WilliamHill
            </a>
          </li>
          <li role="presentation">
            <a class="parse-btn" role="menuitem" tabindex="-1" onclick="runSpinner()" href="/fork/parseBookMaker?name=BWin">
              BWin
            </a>
          </li>
          <li role="presentation">
            <a class="parse-btn" role="menuitem" tabindex="-1" onclick="runSpinner()" href="/fork/parseBookMaker?name=GameBookers">
              GameBookers
            </a>
          </li>
          <li role="presentation">
            <a class="parse-btn" role="menuitem" tabindex="-1" onclick="runSpinner()" href="/fork/parseBookMaker?name=Bet365">
              Bet365
            </a>
          </li>
          <li role="presentation">
            <a class="parse-btn" role="menuitem" tabindex="-1" onclick="runSpinner()" href="/fork/parseBookMaker?name=Bet10">
              10Bet
            </a>
          </li>
          <li role="presentation">
            <a class="parse-btn" role="menuitem" tabindex="-1" onclick="runSpinner()" href="/fork/parseBookMaker?name=Pinnacle">
              Pinnacle
            </a>
          </li>
          <li role="presentation">
            <a class="parse-btn" role="menuitem" tabindex="-1" onclick="runSpinner()" href="/fork/parseBookMaker?name=Unibet">
              Unibet
            </a>
          </li>
          <li role="presentation">
            <a class="parse-btn" role="menuitem" tabindex="-1" onclick="runSpinner()" href="/fork/parseBookMaker?name=Sport888">
              888Sport
            </a>
          </li>
          <li role="presentation">
            <a class="parse-btn" role="menuitem" tabindex="-1" onclick="runSpinner()" href="/fork/parseBookMaker?name=Parimatch">
              Parimatch
            </a>
          </li>
          <li role="presentation">
            <a class="parse-btn" role="menuitem" tabindex="-1" onclick="runSpinner()" href="/fork/parseBookMaker?name=TitanBet">
              TitanBet
            </a>
          </li>
          <li role="presentation">
            <a class="parse-btn" role="menuitem" tabindex="-1" onclick="runSpinner()" href="/fork/parseBookMaker?name=FanSport">
              FanSport
            </a>
          </li>
        </ul>
      </div>

      <div class="pull-left" style="margin-left: 5px">
        <button type="button" class="btn btn-warning"
                onclick="location.href='/fork/clearAll'">Clear all</button>
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
            <a role="menuitem" tabindex="-1" href="/fork/getMatches?name=GameBookers">GameBookers</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/getMatches?name=Bet365">Bet365</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/getMatches?name=Bet10">10Bet</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/getMatches?name=Pinnacle">Pinnacle</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/getMatches?name=Unibet">Unibet</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/getMatches?name=Sport888">888Sport</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/getMatches?name=Parimatch">Parimatch</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/getMatches?name=TitanBet">TitanBet</a>
          </li>
          <li role="presentation">
            <a role="menuitem" tabindex="-1" href="/fork/getMatches?name=FanSport">FanSport</a>
          </li>
        </ul>
      </div>

      <div class="pull-left" style="margin-left: 5px">
        <button type="button" class="btn btn-info"
                onclick="location.href='/fork/live'">Live</button>
      </div>

    </div>
    <br>

    <div id="spinner"></div>

  </body>
</html>
