import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class RenderEngine extends JPanel implements Engine {
    private ArrayList<Displayable> renderList;
    private GameEngine gameEngine;
    private long lastTime = System.nanoTime();
    private double fps = 0;

    public RenderEngine(JFrame jFrame) {
        renderList = new ArrayList<>();
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public void addToRenderList(Displayable displayable) {
        if (!renderList.contains(displayable)) {
            renderList.add(displayable);
        }
    }

    public void addToRenderList(ArrayList<Displayable> displayables) {
        renderList.addAll(displayables);
    }

    @Override
    public void paint(Graphics g) {
        long currentTime = System.nanoTime();
        double gap = (currentTime - lastTime) / 1_000_000_000.0;
        lastTime = currentTime;
        if (gap > 0) {
            fps = 1.0 / gap;
        }

        super.paint(g);

        if (gameEngine == null) return;

        if (gameEngine.getCurrentState() == GameEngine.GameState.TITLE) {
            drawTitleScreen(g);
        }
        else if (gameEngine.getCurrentState() == GameEngine.GameState.GAMEOVER) {
            drawGameOverScreen(g);
        }
        else {
            for (Displayable renderObject : renderList) {
                renderObject.draw(g);
            }
            drawTimer(g);
        }
        drawFPS(g);
    }

    private void drawTitleScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("DUNGEON CRAWLER", getWidth()/2 - 150, 200);

        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Appuyez sur ENTRÉE pour commencer", getWidth()/2 - 160, 300);
    }

    private void drawGameOverScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("GAME OVER", getWidth()/2 - 110, getHeight()/2);
    }

    private void drawTimer(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("Temps : " + gameEngine.getTimerValue() + "s", 20, 60);
    }

    private void drawFPS(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("FPS: " + (int)fps, getWidth() - 60, 20);
    }

    @Override
    public void update() {
        this.repaint();
    }
}