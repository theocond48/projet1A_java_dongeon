import java.awt.*;

public class HUD implements Displayable {
    private HealthManager healthManager;
    private int heartSize = 20;
    private int heartSpacing = 5;
    private int hudPadding = 10;

    public HUD(HealthManager healthManager) {
        this.healthManager = healthManager;
    }

    @Override
    public void draw(Graphics g) {
        drawHealthBar(g);
    }

    private void drawHealthBar(Graphics g) {
        int maxHearts = healthManager.getMaxHealth();
        int currentHearts = healthManager.getCurrentHealth();

        // Dessiner les cœurs
        for (int i = 0; i < maxHearts; i++) {
            int x = hudPadding + (i * (heartSize + heartSpacing));
            int y = hudPadding;

            if (i < currentHearts) {
                // Cœur plein (rouge)
                drawFullHeart(g, x, y);
            } else {
                // Cœur vide (gris)
                drawEmptyHeart(g, x, y);
            }
        }
    }

    private void drawFullHeart(Graphics g, int x, int y) {
        g.setColor(new Color(255, 0, 0)); // Rouge
        g.fillRect(x, y, heartSize, heartSize);
        g.setColor(new Color(204, 0, 0)); // Bordure rouge foncé
        g.drawRect(x, y, heartSize, heartSize);
    }

    private void drawEmptyHeart(Graphics g, int x, int y) {
        g.setColor(new Color(100, 100, 100, 100));
        g.fillRect(x, y, heartSize, heartSize);
        g.setColor(new Color(100, 100, 100, 200));
        g.drawRect(x, y, heartSize, heartSize);
    }

    public void setHeartSize(int size) {
        this.heartSize = size;
    }
}