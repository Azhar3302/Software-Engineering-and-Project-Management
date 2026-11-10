<%@ page import="java.util.List,com.mathwhiz.model.Question" contentType="text/html;charset=UTF-8" %>
<%
boolean[] correct = (boolean[]) request.getAttribute("correct");
List<Question> questions = (List<Question>) request.getAttribute("questions");
%>
<!DOCTYPE html><html><head><title>Math Whiz - Result</title><link rel="stylesheet" href="css/style.css"></head><body>
<main class="card"><h1>Game Result</h1><h2><%= request.getAttribute("playerName") %></h2>
<p class="score">Your score: <%= request.getAttribute("score") %> / 10</p>
<% for (int i = 0; i < correct.length; i++) { %>
<p>Question <%= i + 1 %>: <strong class="<%= correct[i] ? "success" : "error" %>"><%= correct[i] ? "Correct" : "Incorrect" %></strong>
<% if (!correct[i]) { %> — Correct answer: <%= questions.get(i).getAnswer() %><% } %></p>
<% } %>
<a href="<%= request.getContextPath() %>/">Play Again</a> | <a href="leaderboard">Top 10 Leaderboard</a>
</main></body></html>
