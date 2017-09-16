<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
      <br>

      <button type="button" class="btn btn-success"
              onclick="location.href='/fork/parseAll'">Parse</button>
      <button type="button" class="btn btn-success"
              onclick="location.href='/fork/countUp'">CountUp</button>
      <button type="button" class="btn btn-primary"
              onclick="location.href='/fork/getForks'">Forks</button>
      <button type="button" class="btn btn-primary"
              onclick="location.href='/fork/getTwoOfThree'">TwoOfThree</button>
      <button type="button" class="btn btn-info"
              onclick="location.href='/fork/getMatches?type=WilliamHill'">WilliamHill</button>
      <button type="button" class="btn btn-info"
              onclick="location.href='/fork/getMatches?type=BWin'">BWin</button>
    </div>
    <br>

  </body>
</html>
