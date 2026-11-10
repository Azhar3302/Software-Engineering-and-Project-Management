package com.mathwhiz.model;

import java.sql.Timestamp;

public class GameScore {
    private final String playerName;
    private final int score;
    private final String difficulty;
    private final Timestamp playedAt;

    public GameScore(String playerName, int score, String difficulty, Timestamp playedAt) {
        this.playerName = playerName;
        this.score = score;
        this.difficulty = difficulty;
        this.playedAt = playedAt;
    }
    public String getPlayerName() { return playerName; }
    public int getScore() { return score; }
    public String getDifficulty() { return difficulty; }
    public Timestamp getPlayedAt() { return playedAt; }
}
