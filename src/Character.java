import java.util.ArrayList;
import java.util.Scanner;

public abstract class Character implements Extra{
    protected long HP;
    protected String name;
    protected int vitality;
    protected int intelligence;
    protected int strength;
    protected ArrayList<Item> inventory;
    protected Weapon handledWeapon;
    protected Armor wornArmor;
    private final Scanner sc = new Scanner(System.in);

    public Character(String name, Weapon handledWeapon, Armor wornArmor) {
        this.name = name;
        this.handledWeapon = handledWeapon;
        this.wornArmor = wornArmor;
        this.inventory = new ArrayList<>();
    }


    public void printInfo(Character character) {
        System.out.println("Strength: " + character.getStrength());
        System.out.println("Vitality: " + character.getVitality());
        System.out.println("Intelligence: " + character.getIntelligence());
        System.out.println("HP: " + getHP());
        getHandledWeapon().printInfo();
        getWornArmor().printInfo();
        int counter1 = 0;
        for (int i = 0; i < inventory.size(); i++) {
            inventory.get(i).printInfo();
            System.out.println();
            counter1++;
        }
        if (counter1 == 0) {
            System.out.println("Inventory has no item.");
        }
        int counter2 = 0;
        for (int i = 0; i < inventory.size(); i++) {
            inventory.get(i).printInfo();
            System.out.println();
            counter2++;
        }
        if (counter2 == 0) {
            System.out.println("Inventory has no item.");
        }
    }

    public double attack() {
        if (getHandledWeapon() != null) {
            if (getHandledWeapon().getType().equals("sword")) {
                return getHandledWeapon().getDamage() * getStrength();
            } else if (getHandledWeapon().getType().equals("shield")) {
                return getHandledWeapon().getDamage() * getVitality();
            } else {
                return getHandledWeapon().getDamage() * getIntelligence();
            }
        } else {
            System.out.println("You did not handle a weapon");
        }
        return 0;
    }

    public double calculateWeight() {
        double total = 0;
        for (int i = 0; i < inventory.size(); i++) {
            total = total + inventory.get(i).getWeight();
        }
        total = total + handledWeapon.getWeight() + wornArmor.getWeight();
        return total;
    }

    public void printInventory() {
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ")" + inventory.get(i).getName() + " weights " + inventory.get(i).getWeight());
        }
    }

    public void add(Item item) {
        double total = calculateWeight();

        if (item.getCategory().equals("armor")) {
            if ((total + item.getWeight()) <= strength*2) {
                inventory.add(item);

                System.out.println(item.getName() + " picked");
            } else {
                boolean full = true;
                while (full) {
                    System.out.println("Inventory is full.You must drop an item ");
                    if (strength >= total + item.getWeight()) {
                        full = false;
                    }
                }
                if (strength >= total + item.getWeight()) {
                    inventory.add(item);
                }
            }
        } else if (item.getCategory().equals("weapon")) {
            if ((total + item.getWeight())/2 <= strength*2) {
                inventory.add(item);
                System.out.println(item.getName() + " picked");
            } else {

                if (2*strength >= (total + item.getWeight()) / 2) {
                } else {
                    System.out.println("Inventory is full.You must drop an item.");
                }
                if (2*strength >= (total + item.getWeight()) / 2) {
                    inventory.add(item);
                }
            }
        }
    }

    public Item drop() {
        System.out.println("Your inventory is");
        printInventory();

        System.out.println("Which item do you want to drop?");
        int dropping = sc.nextInt();
        dropping = dropping - 1;

        if (inventory.get(dropping).getCategory().equals("armor")) {
            System.out.println(inventory.get(dropping).getName() + "removed");
            int counter = 0;
            while (counter < inventory.size()) {
                if (inventory.get(counter) == inventory.get(dropping)) {
                    break;
                } else {
                    counter++;
                }
            }
            Item tempItem = inventory.get(dropping);
            inventory.remove(counter);
            return tempItem;
        } else if (inventory.get(dropping).getCategory().equals("weapon")) {
            System.out.println(inventory.get(dropping).getName() + "removed");
            int counter = 0;
            while (counter < inventory.size()) {
                if (inventory.get(counter) == inventory.get(dropping)) {
                    break;
                } else {
                    counter++;
                }
            }
            Item tempItem = inventory.get(dropping);
            inventory.remove(counter);
            return tempItem;
        }
        return null;
    }

    public void changeHandledItem() {
        System.out.println("Which item will you change \n Press 1 for Weapon \n Press 2 for Armor");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                inventory.add(handledWeapon);
                System.out.println(handledWeapon.getName() + "has been put to inventory.");
                handledWeapon = null;

                System.out.println("Which weapon will you handle?");
                System.out.println("This is inventory: ");
                for (int i = 0; i < inventory.size(); i++) {
                    System.out.println((i + 1) + inventory.get(i).getName());
                }
                System.out.println("Select weapon that you want to use");
                int select = sc.nextInt();
                select = select - 1;
                if (inventory.get(select) == null) {
                    System.out.println("Select valid integer.");
                } else {
                    handledWeapon = (Weapon) inventory.get(select);
                    inventory.remove(select);
                }
            case 2:
                inventory.add(wornArmor);
                System.out.println(wornArmor.getName() + "has been put to inventory.");
                wornArmor = null;

                System.out.println("Which armor will you wear?");
                System.out.println("This is inventory: ");
                for (int i = 0; i < inventory.size(); i++) {
                    System.out.println((i + 1) + inventory.get(i).getName());
                }
                System.out.println("Select armor that you want to wear");
                int select2 = sc.nextInt();
                select2 = select2 - 1;
                if (inventory.get(select2) == null) {
                    System.out.println("Select valid integer");
                } else {
                    wornArmor = (Armor) inventory.get(select2);
                    inventory.remove(select2);
                }
            default:
                System.out.println("Enter valid integer");
                break;
        }
    }

    public void renewHP(int choice, double action) {
        if (choice == 1) {
            this.HP = (int) (this.HP - action);
        } else if (choice == 2 && ((this.HP + action) > getMaxHP())) {
            this.HP = (int) getMaxHP();
        } else if (choice == 2) {
            this.HP = (int) (getHP() + action);
        } else {
            System.out.println("Oops!");
        }
    }

    public long getHP() {
        return HP;
    }

    public void setHP() {
        this.HP = (int) Math.round(0.7 * vitality + 0.2 * strength + 0.1 * intelligence);
    }

    public long getMaxHP() {
        return Math.round(0.7 * vitality + 0.2 * strength + 0.1 * intelligence);
    }

    public int getVitality() {
        return vitality;
    }

    public abstract void setVitality();

    public int getIntelligence() {
        return intelligence;
    }

    public abstract void setIntelligence();

    public int getStrength() {
        return strength;
    }

    public abstract void setStrength();

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public Weapon getHandledWeapon() {
        return handledWeapon;
    }

    public void setHandledWeapon(Weapon handledWeapon) {
        this.handledWeapon = handledWeapon;
    }

    public Armor getWornArmor() {
        return wornArmor;
    }

    public void setWornArmor(Armor wornArmor) {
        this.wornArmor = wornArmor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
