package com.mathwhiz.controller;

import com.mathwhiz.dao.ScoreDAO;
import com.mathwhiz.model.Question;
import com.mathwhiz.service.MathService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet(urlPatterns = {"/start", "/submit", "/leaderboard"})
public class GameServlet extends HttpServlet {
    private final Random random = new Random();
    private final ScoreDAO scoreDAO = new ScoreDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getServletPath().equals("/leaderboard")) {
            try {
                request.setAttribute("scores", scoreDAO.getTopScores());
                request.getRequestDispatcher("/leaderboard.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Could not load leaderboard. Check database settings.", e);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();

        if (path.equals("/start")) {
            String name = request.getParameter("playerName");
            if (name == null || name.trim().isEmpty() || name.trim().length() > 100) {
                request.setAttribute("error", "Enter a name (maximum 100 characters).");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                return;
            }
            HttpSession session = request.getSession();
            session.setAttribute("playerName", name.trim());
            session.setAttribute("questions", makeQuestions());
            request.getRequestDispatcher("/game.jsp").forward(request, response);
        } else if (path.equals("/submit")) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("questions") == null) {
                response.sendRedirect(request.getContextPath() + "/");
                return;
            }
            @SuppressWarnings("unchecked")
            List<Question> questions = (List<Question>) session.getAttribute("questions");
            String[] answers = new String[4];
            for (int i = 0; i < 4; i++) answers[i] = request.getParameter("answer" + i);

            int score = 0;
            int[] marks = {2, 2, 3, 3};
            boolean[] correct = new boolean[4];
            for (int i = 0; i < 4; i++) {
                try {
                    if (i < 2) {
                        int entered = Integer.parseInt(answers[i].trim());
                        correct[i] = entered == (int) questions.get(i).getAnswer();
                    } else {
                        double entered = Double.parseDouble(answers[i].trim());
                        correct[i] = Double.isFinite(entered)
                                && Math.abs(entered - questions.get(i).getAnswer()) <= 0.01;
                    }
                    if (correct[i]) score += marks[i];
                } catch (Exception ignored) {
                    correct[i] = false;
                }
            }
            String playerName = (String) session.getAttribute("playerName");
            try {
                scoreDAO.saveScore(playerName, score, "NORMAL");
            } catch (SQLException e) {
                throw new ServletException("Could not save score. Check database settings.", e);
            }
            request.setAttribute("playerName", playerName);
            request.setAttribute("score", score);
            request.setAttribute("correct", correct);
            request.setAttribute("questions", questions);
            session.removeAttribute("questions");
            request.getRequestDispatcher("/result.jsp").forward(request, response);
        }
    }

    private List<Question> makeQuestions() {
        List<Question> q = new ArrayList<>();
        int prime = 10 + random.nextInt(41);
        q.add(new Question("Is " + prime + " a prime number? Enter 1 for prime or 0 for not prime.", "Prime", MathService.isPrime(prime)));

        int a = 20 + random.nextInt(81);
        int b = 2 + random.nextInt(9);
        q.add(new Question("Calculate " + a + " % " + b + ".", "Modulus", MathService.calculateMod(a, b)));

        String[] operations = {"sin", "cos", "tan"};
        String op1 = operations[random.nextInt(operations.length)];
        int angle1 = op1.equals("tan") ? new int[]{15, 30, 45, 60}[random.nextInt(4)] : new int[]{0, 30, 45, 60, 90}[random.nextInt(5)];
        q.add(new Question("Calculate " + op1 + "(" + angle1 + "°). Give your answer to 2 decimal places.", "Trigonometry", trig(op1, angle1)));

        String op2 = operations[random.nextInt(operations.length)];
        int angle2 = op2.equals("tan") ? new int[]{15, 30, 45, 60}[random.nextInt(4)] : new int[]{0, 30, 45, 60, 90}[random.nextInt(5)];
        q.add(new Question("Calculate " + op2 + "(" + angle2 + "°). Give your answer to 2 decimal places.", "Trigonometry", trig(op2, angle2)));
        return q;
    }

    private double trig(String op, double angle) {
        return switch (op) {
            case "sin" -> MathService.calculateSin(angle);
            case "cos" -> MathService.calculateCos(angle);
            default -> MathService.calculateTan(angle);
        };
    }
}
