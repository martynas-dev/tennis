package com.cognizant.tennis;

import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

@Controller
@EnableAutoConfiguration
public class Tennis implements CommandLineRunner {
    
    private Game game;
    
    private Logger log = Logger.getLogger(Tennis.class);
    
    @Autowired
    private ApplicationContext appContext;
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Tennis.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        log.info("\nEnter first player name: ");
        String player1Name = scanner.nextLine();
        log.info("\nEnter second player name: ");
        String player2Name = scanner.nextLine();
        game = new Game(new Player(player1Name), new Player(player2Name));
        log.info("\nGame started");
        while(game.getWonPlayer() == 0) {
            log.info("\nEnter player number (1 or 2): ");
            String numStr = scanner.nextLine();
            if (numStr == null || StringUtils.isEmpty(numStr)) {
                log.info("\nError: player number is blank.");
                continue;
            }
            int num;
            try {
                num = Integer.parseInt(numStr);
                if (num != 1 && num != 2) {
                    log.info("\nError: wrong player.");
                    continue;
                }
            } catch (NumberFormatException nfe) {
                log.info("\nError: not number.");
                continue;
            }
            game.winBall(num);
            log.info(game.getGameInfo());
        }
        log.info("\nGame ended");
        scanner.close();
        SpringApplication.exit(appContext, () -> 0);
    }
}
