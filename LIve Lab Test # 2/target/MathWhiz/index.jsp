<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html><head><title>Math Whiz</title><link rel="stylesheet" href="css/style.css"></head>
<body><main class="card">
<h1>YOUR NAME – MATH WHIZ</h1>
<p>Answer four math questions and earn up to 10 points.</p>
<% if (request.getAttribute("error") != null) { %><p class="error"><%= request.getAttribute("error") %></p><% } %>
<form action="start" method="post">
<label for="playerName">Player name</label>
<input id="playerName" name="playerName" maxlength="100" required>
<button type="submit">Start Game</button>
</form>
<a href="leaderboard">View Top 10 Leaderboard</a>
</main></body></html>
