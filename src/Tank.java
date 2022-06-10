import java.util.Random;

public class Tank extends Character {
    private static Random random = new Random();

    public Tank(String name,Weapon handledWeapon,Armor wornArmor){
        super(name,handledWeapon, wornArmor);
        setStrength();
        setIntelligence();
        setVitality();
        super.intelligence = getIntelligence();
        super.vitality = getVitality();
        super.strength = getStrength();
        setHP();
        super.HP = getHP();
    }
    @Override
    public void setStrength() {
        super.strength = random.nextInt(1,5);
    }

    @Override
    public void setVitality() {
        super.vitality = random.nextInt(6,10);
    }

    @Override
    public void setIntelligence() {
        super.intelligence = random.nextInt(3,7);
    }
}
