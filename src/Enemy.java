import java.util.Random;

public class Enemy extends Character {
    boolean block = false;
    private int stun;
    private static Random random = new Random();

    public Enemy(String name,Weapon handledWeapon, Armor wornArmor) {
        super(name,handledWeapon,wornArmor);
        setStrength();
        setVitality();
        setIntelligence();
        super.vitality = getVitality();
        super.intelligence = getIntelligence();
        super.strength = getStrength();
        setHP();
        super.HP = getHP();
    }

    public int getStun() {
        return stun;
    }

    public void setStun(int stun) {
        this.stun = stun;
    }

    @Override
    public void setStrength() {
        super.strength = random.nextInt(1,5);
    }

    @Override
    public void setIntelligence() {
        super.intelligence = random.nextInt(1,5);
    }

    @Override
    public void setVitality() {
        super.vitality = random.nextInt(1,5);
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }
}
