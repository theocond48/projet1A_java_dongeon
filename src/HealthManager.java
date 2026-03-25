public class HealthManager {
    private int maxHealth;
    private int currentHealth;

    public HealthManager(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void reset() { this.currentHealth = maxHealth; }
}


