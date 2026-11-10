<%@ page import="java.util.List,com.mathwhiz.model.Question" contentType="text/html;charset=UTF-8" %>
<%
List<Question> questions = (List<Question>) session.getAttribute("questions");
if (questions == null) { response.sendRedirect(request.getContextPath() + "/"); return; }
%>
<!DOCTYPE html><html><head><title>Math Whiz - Game</title><link rel="stylesheet" href="css/style.css"></head><body>
<main class="card"><h1>Math Whiz Challenge</h1><p>Player: <b><%= session.getAttribute("playerName") %></b> | Difficulty: NORMAL</p>
<form action="submit" method="post">
<% for (int i = 0; i < questions.size(); i++) { %>
<section class="question"><h3>Question <%= i + 1 %> (<%= i < 2 ? 2 : 3 %> marks)</h3>
<p><%= questions.get(i).getText() %></p>
<input name="answer<%= i %>" type="number" step="any" required>
</section>
<% } %><button type="submit">Submit Answers</button></form></main></body></html>
