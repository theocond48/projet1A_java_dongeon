import java.util.ArrayList;

public class TrapManager implements Engine {
    private ArrayList<Trap> trapList;
    private DynamicSprite hero;

    public TrapManager(DynamicSprite hero) {
        this.trapList = new ArrayList<>();
        this.hero = hero;
    }

    /**
     * Ajouter un piège à la liste
     */
    public void addTrap(Trap trap) {
        if (!trapList.contains(trap)) {
            trapList.add(trap);
        }
    }

    /**
     * Ajouter plusieurs pièges à la fois
     */
    public void addTraps(ArrayList<Trap> traps) {
        for (Trap trap : traps) {
            addTrap(trap);
        }
    }

    /**
     * Vérifier les collisions avec tous les pièges
     */
    @Override
    public void update() {
        for (Trap trap : trapList) {
            trap.checkCollisionWithHero(hero);
        }
    }

    /**
     * Obtenir la liste des pièges
     */
    public ArrayList<Trap> getTrapList() {
        return trapList;
    }

    /**
     * Réinitialiser tous les pièges
     */
    public void resetAllTraps() {
        for (Trap trap : trapList) {
            trap.reset();
        }
    }
}