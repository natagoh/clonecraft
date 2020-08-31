package game;

import engine.GameEngine;
import engine.IGameLogic;
 
public class Main {
 
    public static void main(String[] args) {
        try {
            boolean vSync = true;
            IGameLogic gameLogic = new Clonecraft();
            GameEngine gameEngine = new GameEngine("Clonecraft", 600, 480, vSync, gameLogic);
            gameEngine.run();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }
}