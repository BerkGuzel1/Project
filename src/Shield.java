public class Shield extends Weapon {
    public Shield(String name, int weight, int value, double damage) {
        super(name, weight, value,damage);
        super.type = "shield";
        this.category = "weapon";
    }

    public long stun(Character character,Enemy enemy){
        return Math.round(character.attack()/enemy.getVitality());
    }

}
