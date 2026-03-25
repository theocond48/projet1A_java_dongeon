import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    JFrame displayZoneFrame;

    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicEngine physicEngine;
    TrapManager trapManager;

    public Main() throws Exception{
        displayZoneFrame = new JFrame("Java Labs");
        displayZoneFrame.setSize(400,600);
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        DynamicSprite hero = new DynamicSprite(200,300,
                ImageIO.read(new File("./img/heroTileSheetLowRes.png")),48,50);

        renderEngine = new RenderEngine(displayZoneFrame);
        physicEngine = new PhysicEngine();
        gameEngine = new GameEngine(hero);
        trapManager = new TrapManager(hero);
        gameEngine.setTrapManager(trapManager);

        renderEngine.setGameEngine(gameEngine);

        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(50,(time)-> physicEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);

        Playground level = new Playground("./data/level1.txt");
        //SolidSprite testSprite = new DynamicSprite(100,100,test,0,0);
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(level.getSolidSpriteList());
        // HUD de vie
        HUD hud = new HUD(hero.getHealthManager());
        renderEngine.addToRenderList(hud);

        // SYSTÈME DE PIÈGES
        Trap trap1 = new Trap(150, 400,
                ImageIO.read(new File("./img/trap.png")), 32, 32);

        Trap trap2 = new Trap(250, 200,
                ImageIO.read(new File("./img/trap.png")), 32, 32);

        // Ajouter les pièges au TrapManager
        trapManager.addTrap(trap1);
        trapManager.addTrap(trap2);

        // Ajouter les pièges au rendu
        renderEngine.addToRenderList(trap1);
        renderEngine.addToRenderList(trap2);


        displayZoneFrame.addKeyListener(gameEngine);
    }

    public static void main (String[] args) throws Exception {
	// write your code here
        Main main = new Main();
    }
}
