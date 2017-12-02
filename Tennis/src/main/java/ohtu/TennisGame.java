package ohtu;

public class TennisGame {
    
    private int m_score1;
    private int m_score2;
    private String player1Name;
    private String player2Name;
    private static final int INITIALSCORE = 0;
    private static final int WINNABLESCORELIMIT = 4;
    private static final int WINDIFFERENCE = 2;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        m_score1 = INITIALSCORE;
        m_score2 = INITIALSCORE;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            m_score1 += 1;
        } else {
            m_score2 += 1;
        }
    }

    public String getScore() {
        String score = "";
        if (m_score1 == m_score2) {
            score = parseEvenScore(m_score1);
        } else if (m_score1 >= WINNABLESCORELIMIT || m_score2 >= WINNABLESCORELIMIT) {
            score = parseWinnableScore(m_score1, m_score2);
        } else {
            score = parseScore(m_score1) + "-" + parseScore(m_score2);
        }
        return score;
    }

    private String parseEvenScore(int score) {
        switch (m_score1) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
            default:
                return "Deuce";               
            }
    }

    private String parseWinnableScore(int player1Score, int player2Score) {
        int difference = player1Score - player2Score;
            if (difference == 1) {
                return "Advantage player1";
            } else if (difference == -1) {
                return "Advantage player2";
            } else if (difference >= WINDIFFERENCE) { 
                return "Win for player1";
            } else {
                return "Win for player2";
            }
    }

    private String parseScore(int score) {
        switch(score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return null;
        }
    }
}