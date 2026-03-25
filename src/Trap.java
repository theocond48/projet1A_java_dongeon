import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Trap implements Displayable {
    protected double x;
    protected double y;
    protected Image image;
    protected double width;
    protected double height;
    private boolean hasTriggered = false;

    public Trap(double x, double y, Image image, double width, double height) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int)x, (int)y, (int)(x + width), (int)(y + height),
                0, 0, image.getWidth(null), image.getHeight(null), null);
    }

    /**
     * Retourne la hitbox du piège
     */
    public Rectangle2D.Double getHitBox() {
        return new Rectangle2D.Double(x, y, width, height);
    }

    /**
     * Vérifie si le piège touche le héros et le tue
     */
    public void checkCollisionWithHero(DynamicSprite hero) {
        Rectangle2D heroBox = hero.getHitBox();
        Rectangle2D.Double trapBox = this.getHitBox();

        if (!hasTriggered && trapBox.intersects(heroBox)) {
            hero.getHealthManager().takeDamage(3);
            hasTriggered = true;
        }
    }
    /**
     * Réinitialiser le piège (utile si le héros respawn)
     */
    public void reset() {
        hasTriggered = false;
    }

    /**
     * Vérifier si le piège a été déclenché
     */
    public boolean isTriggered() {
        return hasTriggered;
    }
}