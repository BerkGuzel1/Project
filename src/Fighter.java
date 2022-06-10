import java.util.Random;

public class Fighter extends Character {

    private static Random random = new Random();

    public Fighter(String name,Weapon handledWeapon, Armor wornArmor) {
        super(name,handledWeapon,wornArmor);
        setStrength();
        super.strength = getStrength();
        setIntelligence();
        super.intelligence = getIntelligence();
        setVitality();
        super.vitality = getVitality();
        setHP();
        super.HP = getHP();
    }

    @Override
    public void setStrength() {
        super.strength = random.nextInt(6,10);
    }

    @Override
    public void setVitality() {
        super.vitality = random.nextInt(3,7);
    }
    @Override
    public void setIntelligence() {
        super.intelligence = random.nextInt(1,5);
    }
}