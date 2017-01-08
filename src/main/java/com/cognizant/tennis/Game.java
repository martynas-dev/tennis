package com.cognizant.tennis;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class Game {

    private Map<Integer, Player> players;
    private int advantageForPlayer;
    private int wonPlayer;
    private boolean deuce;
    
    public Game(Player player1, Player player2) {
        players = new HashMap<>();
        players.put(1, player1);
        players.put(2, player2);
        advantageForPlayer = 0;
        wonPlayer = 0;
        deuce = false;
    }
    
    public void winBall(int playerNo) {
        switch (new Boolean(wonPlayer != 0).toString()) {
            case "true": return;
        }
        
        Integer currentPlayerNo = new Integer(playerNo);
        Player currentPlayer = players.get(currentPlayerNo);
        Integer otherPlayerNo = players.entrySet()
                                        .stream()
                                        .filter(map -> !currentPlayerNo.equals(map.getKey()))
                                        .collect(Collectors.toList()).get(0).getKey();
        Player otherPlayer = players.get(otherPlayerNo);
        
        String isCurrentPlayerAdvantage = new Boolean(currentPlayer.getScore().equals(Score.FORTY) && advantageForPlayer == currentPlayerNo).toString();
        switch (isCurrentPlayerAdvantage) {
            case "true":     advantageForPlayer = 0;
                            wonPlayer = playerNo;
                            return;
        }
        
        String isOtherPlayerAdvantage = new Boolean(currentPlayer.getScore().equals(Score.FORTY) && advantageForPlayer == otherPlayerNo).toString();
        switch (isOtherPlayerAdvantage) {
            case "true":     advantageForPlayer = 0;
                            deuce = true;
                            return;
        }
        
        String isBothPlayersForty = new Boolean(currentPlayer.getScore().equals(Score.FORTY) && otherPlayer.getScore().equals(Score.FORTY)).toString();
        switch (isBothPlayersForty) {
            case "true":     advantageForPlayer = currentPlayerNo;
                            deuce = false;
                            return;
        }
        
        String isCurrentPlayersForty = new Boolean(currentPlayer.getScore().equals(Score.FORTY)).toString();
        switch (isCurrentPlayersForty) {
            case "true":     wonPlayer = playerNo;
                            return;
        }
        
        currentPlayer.incrScore();
        
        String isBothPlayersFortyAfterIncr = new Boolean(currentPlayer.getScore().equals(Score.FORTY) && otherPlayer.getScore().equals(Score.FORTY)).toString();
        switch (isBothPlayersFortyAfterIncr) {
            case "true":     deuce = true;
                            return;
        }
        
    }
    
    public String getGameInfo() {
        return String.format("\n-----\n1 player %s: %s\n2 player %s: %s\nDeuce: %s\nAdvantage for: %s\nGame won: %s\n-----\n",  
                        players.get(1).getName(), 
                        players.get(1).getScore(), 
                        players.get(2).getName(), 
                        players.get(2).getScore(),
                        deuce ? "Yes" : "No",
                        advantageForPlayer == 0 ? "-" : "player " + advantageForPlayer,
                        wonPlayer == 0 ? "-" : "player " + wonPlayer
                        );
    }

    public Map<Integer, Player> getPlayers() {
        return players;
    }

    public int getAdvantageForPlayer() {
        return advantageForPlayer;
    }

    public int getWonPlayer() {
        return wonPlayer;
    }
    
    public boolean getDeuce() {
        return deuce;
    }

}
