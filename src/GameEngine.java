import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    public enum GameState { TITLE, PLAYING, GAMEOVER }

    private GameState currentState = GameState.TITLE;
    private DynamicSprite hero;
    private long startTime;
    private long elapsedTime;

    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }

    public GameState getCurrentState() { return currentState; }
    public long getTimerValue() {
        if (currentState == GameState.PLAYING) {
            return (System.currentTimeMillis() - startTime) / 1000;
        }
        return elapsedTime;
    }

    @Override
    public void update() {
        if (currentState == GameState.PLAYING) {
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (currentState == GameState.TITLE) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                currentState = GameState.PLAYING;
                startTime = System.currentTimeMillis();
            }
            return;
        }

        if (currentState == GameState.GAMEOVER) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                currentState = GameState.TITLE;
            }
            return;
        }

        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:    hero.setDirection(Direction.NORTH); break;
            case KeyEvent.VK_DOWN:  hero.setDirection(Direction.SOUTH); break;
            case KeyEvent.VK_LEFT:  hero.setDirection(Direction.WEST);  break;
            case KeyEvent.VK_RIGHT: hero.setDirection(Direction.EAST);  break;
            case KeyEvent.VK_ESCAPE: currentState = GameState.GAMEOVER; break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}