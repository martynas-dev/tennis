package com.cognizant.tennis;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class TennisTest {

    private Player player1;
    private Player player2; 
    
    @Before
    public void init() {
        player1 = new Player("First Player");
        player2 = new Player("Second Player");
    }
    
    @Test
    public void testGameWinPlayer1() {
        Game game = new Game(player1, player2);
        
        game.winBall(1);
        
        assertEquals(player1.getScore(), Score.FIFTEEN);
        assertEquals(player2.getScore(), Score.LOVE);
        assertEquals(0, game.getAdvantageForPlayer());
        assertEquals(0, game.getWonPlayer());
        assertEquals(false, game.getDeuce());
        
        game.winBall(1);
        game.winBall(1);
        game.winBall(2);
        game.winBall(1);
        
        assertEquals(player1.getScore(), Score.FORTY);
        assertEquals(player2.getScore(), Score.FIFTEEN);
        assertEquals(0, game.getAdvantageForPlayer());
        assertEquals(1, game.getWonPlayer());
        assertEquals(false, game.getDeuce());
    }
    
    @Test
    public void testGameWinPlayer2() {
        Game game = new Game(player1, player2);
        
        game.winBall(2);
        game.winBall(2);
        game.winBall(2);
        game.winBall(2);
        
        assertEquals(player1.getScore(), Score.LOVE);
        assertEquals(player2.getScore(), Score.FORTY);
        assertEquals(0, game.getAdvantageForPlayer());
        assertEquals(2, game.getWonPlayer());
        assertEquals(false, game.getDeuce());
    }
    
    @Test
    public void testGameDeuceAndvantageWin() {
        Game game = new Game(player1, player2);
        
        game.winBall(1);
        game.winBall(2);
        game.winBall(1);
        game.winBall(2);
        game.winBall(1);
        game.winBall(2);
        
        assertEquals(player1.getScore(), Score.FORTY);
        assertEquals(player2.getScore(), Score.FORTY);
        assertEquals(0, game.getAdvantageForPlayer());
        assertEquals(0, game.getWonPlayer());
        assertEquals(true, game.getDeuce());
        
        game.winBall(1);
        
        assertEquals(player1.getScore(), Score.FORTY);
        assertEquals(player2.getScore(), Score.FORTY);
        assertEquals(1, game.getAdvantageForPlayer());
        assertEquals(0, game.getWonPlayer());
        assertEquals(false, game.getDeuce());
        
        game.winBall(2);
        
        assertEquals(player1.getScore(), Score.FORTY);
        assertEquals(player2.getScore(), Score.FORTY);
        assertEquals(0, game.getAdvantageForPlayer());
        assertEquals(0, game.getWonPlayer());
        assertEquals(true, game.getDeuce());
        
        game.winBall(2);
        
        assertEquals(player1.getScore(), Score.FORTY);
        assertEquals(player2.getScore(), Score.FORTY);
        assertEquals(2, game.getAdvantageForPlayer());
        assertEquals(0, game.getWonPlayer());
        assertEquals(false, game.getDeuce());
        
        game.winBall(2);
        
        assertEquals(player1.getScore(), Score.FORTY);
        assertEquals(player2.getScore(), Score.FORTY);
        assertEquals(0, game.getAdvantageForPlayer());
        assertEquals(2, game.getWonPlayer());
        assertEquals(false, game.getDeuce());
    }
    
}
