<%@ page import="java.util.List,com.mathwhiz.model.GameScore" contentType="text/html;charset=UTF-8" %>
<% List<GameScore> scores = (List<GameScore>) request.getAttribute("scores"); %>
<!DOCTYPE html><html><head><title>Math Whiz - Leaderboard</title><link rel="stylesheet" href="css/style.css"></head><body>
<main class="card"><h1>Top 10 Leaderboard</h1>
<table><tr><th>Rank</th><th>Player</th><th>Score</th><th>Difficulty</th><th>Played At</th></tr>
<% int rank = 1; for (GameScore s : scores) { %>
<tr><td><%= rank++ %></td><td><%= s.getPlayerName() %></td><td><%= s.getScore() %>/10</td><td><%= s.getDifficulty() %></td><td><%= s.getPlayedAt() %></td></tr>
<% } %></table>
<% if (scores.isEmpty()) { %><p>No scores yet. Be the first player!</p><% } %>
<a href="<%= request.getContextPath() %>/">Back to Home</a></main></body></html>
