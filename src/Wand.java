public class Wand extends Weapon {

    public Wand(String name, int weight, int value,double damage) {
        super(name, weight, value, damage);
        super.type = "wand";
        this.category = "weapon";
    }

    public int heal(Character character){
        return character.getIntelligence()/3;
    }

}
