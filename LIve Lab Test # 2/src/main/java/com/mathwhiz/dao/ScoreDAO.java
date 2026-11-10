package com.mathwhiz.dao;

import com.mathwhiz.model.GameScore;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAO {
    public void saveScore(String name, int score, String difficulty) throws SQLException {
        String sql = "INSERT INTO game_scores (player_name, score, difficulty) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, score);
            ps.setString(3, difficulty);
            ps.executeUpdate();
        }
    }

    public List<GameScore> getTopScores() throws SQLException {
        List<GameScore> scores = new ArrayList<>();
        String sql = "SELECT player_name, score, difficulty, played_at FROM game_scores ORDER BY score DESC, played_at ASC LIMIT 10";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                scores.add(new GameScore(rs.getString("player_name"), rs.getInt("score"),
                        rs.getString("difficulty"), rs.getTimestamp("played_at")));
            }
        }
        return scores;
    }
}
