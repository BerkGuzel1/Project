public class Sword extends Weapon {
    public Sword(String name, int weight, int value,double damage) {
        super(name, weight, value,damage);
        super.type = "sword";
        this.category = "weapon";
    }
    public long throwAway(Character character){
        return Math.round(character.getStrength()/6);
    }
}
