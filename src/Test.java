import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Test {
    int a=0;
    private static Random random = new Random();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int level = 0;
        int enemyCounter = 1;
        int turn = 0;
        ArrayList<Enemy> enemyArrayList = new ArrayList<>();
        ArrayList<Item> itemArrayList = new ArrayList<>();
        ArrayList<Character> characterArrayList = new ArrayList<>();

        Weapon sword = new Sword("Short Sword",1,2,2);
        Armor fighterArmor = new LeatherArmor("Leather Armor",1 ,1);
        Character fighter = new Fighter("fighter",sword,fighterArmor);
        Weapon shield = new Shield("Short Shield",2,2,1);
        Armor tankArmor = new LeatherArmor("Leather Armor",1 ,1);
        Character tank = new Tank("tank",shield,tankArmor);
        Weapon wand = new Wand("Short Wand",1,2,0.5);
        Armor healerArmor = new LeatherArmor("Leather Armor",1 ,1);
        Character healer = new Healer("healer",wand,healerArmor);
        characterArrayList.add(fighter);
        characterArrayList.add(tank);
        characterArrayList.add(healer);


        System.out.println("   CANNON FODDER    ");
        System.out.println("********************");
        System.out.println("Your characters are:");
        System.out.println();
        System.out.println("--FIGHTER--");
        fighter.printInfo(fighter);
        System.out.println();
        System.out.println("---TANK---");
        tank.printInfo(tank);
        System.out.println();
        System.out.println("--HEALER--");
        healer.printInfo(healer);
        System.out.println();
        tank.add(shield);
        fighter.add(sword);
        healer.add(wand);



        boolean game = true;
        while (game) {
            if (characterArrayList.size() > 0) {
                System.out.println("Level" + level + " is generating..");
                System.out.println("Enemy number: " + enemyCounter);
                enemyGenerator(enemyCounter, enemyArrayList);
                for (int i = 0; i < enemyArrayList.size(); i++) {
                    System.out.println("Name: " + enemyArrayList.get(i).getName() + " with " + enemyArrayList.get(i).getHP() + " HP");
                }
                while (enemyArrayList.size() > 0) {
                    int i = 0;
                    for (int j = 0; j < enemyArrayList.size(); j++) {
                        if (enemyArrayList.get(j).isBlock()) {
                            enemyArrayList.get(j).setBlock(false);
                        }
                    }
                    while (i < 3) {
                        if (enemyArrayList.size() > 0) {
                            System.out.println("Which character do you want to attack enter it's first letter:");
                            String character = sc.next();
                            character = character.toLowerCase();
                            switch (character) {
                                case "h":

                                    System.out.println("You are playing with healer");
                                    int index = 0;
                                    for (int j = 0; j < characterArrayList.size(); j++) {
                                        if (characterArrayList.get(j).getName().equals("healer")) {
                                            index = j;
                                        }
                                    }
                                    System.out.println("What do you want to do?");
                                    System.out.println("Enter attack for attack");
                                    System.out.println("Enter heal for heal tank or fighter");
                                    System.out.println("Enter check for see if there any items on the grass");
                                    System.out.println("Enter inventory for see inventory");
                                    System.out.println("Enter wield for wield a weapon or armor");
                                    String choice = sc.next();
                                    choice = choice.toLowerCase();
                                    switch (choice) {
                                        case "attack":
                                            System.out.println("Choose your target!");
                                            for (int j = 0; j < enemyArrayList.size(); j++) {
                                                System.out.println("Name: " + enemyArrayList.get(j).getName() + " HP: " + enemyArrayList.get(j).getHP());
                                            }
                                            String enemyChoice = sc.next();
                                            enemyChoice = enemyChoice.toLowerCase();
                                            int index1 = 0;
                                            for (int j = 0; j < enemyArrayList.size(); j++) {
                                                if (enemyArrayList.get(j).getName().toLowerCase().equals(enemyChoice)) {
                                                    break;
                                                }
                                                index1++;
                                            }
                                            System.out.println("Healer attacked to " + enemyArrayList.get(index1).getName());
                                            double damage = healer.attack();
                                            enemyArrayList.get(index1).renewHP(1, damage);
                                            System.out.println("Healer made " + damage + "damage.");
                                            System.out.println(enemyArrayList.get(index1).getName() + " has " + enemyArrayList.get(index1).getHP() + " HP.");
                                            if (enemyArrayList.get(index1).getHP() < 0) {
                                                System.out.println(enemyArrayList.get(index1).getName() + " is dead.");
                                                Weapon newWeapon = throwWeapon();
                                                System.out.println(newWeapon.getName() + " dropped.");
                                                itemArrayList.add(newWeapon);
                                                enemyArrayList.remove(index1);
                                            }
                                            i++;
                                            break;
                                        case "heal":
                                            healer.getHandledWeapon().getType().equals("wand");
                                            System.out.println("Which one do you want to heal?");
                                            for (int j = 0; j < characterArrayList.size(); j++) {
                                                System.out.println(characterArrayList.get(j).getName() + " has " + characterArrayList.get(j).getHP());
                                            }
                                            String healFriend = sc.next();
                                            healFriend = healFriend.toLowerCase();
                                            System.out.println(healFriend);
                                            int index2 = 0;
                                            for (int j = 0; j < characterArrayList.size(); j++) {
                                                if (characterArrayList.get(j).getName().toLowerCase().equals(healFriend)) {
                                                    break;
                                                }
                                                index2++;
                                            }
                                            Wand wandOfHealer = (Wand) healer.getHandledWeapon();
                                            double heal = wandOfHealer.heal(healer);
                                            System.out.println(heal);
                                            characterArrayList.get(index2).renewHP(2, heal);
                                            System.out.println("New HP of " + characterArrayList.get(index2).getName() + " is " + characterArrayList.get(index2).getHP());
                                            i++;
                                            break;
                                        case "check":
                                            if (itemArrayList.isEmpty()) {
                                                System.out.println("There are no item on the grass.");
                                            } else {
                                                System.out.println("ITEMS: ");
                                                for (int j = 0; j < itemArrayList.size(); j++) {
                                                    itemArrayList.get(j).printInfo();
                                                }
                                                System.out.println("Will you pick?");
                                                int itemIndex = sc.nextInt();
                                                itemIndex = itemIndex - 1;
                                                String response = sc.next();
                                                healer.add(itemArrayList.get(itemIndex));
                                            }
                                            break;
                                        case "inventory":
                                            if (healer.getInventory().isEmpty()) {
                                                System.out.println("No item in inventory.");
                                            } else {
                                                healer.printInventory();
                                            }
                                            break;
                                        case "wield":
                                            healer.changeHandledItem();
                                        default:
                                            System.out.println("Enter a valid input.");
                                            break;
                                    }
                                    break;
                                case "t":
                                    System.out.println("You are playing with tank");
                                    int index3 = 0;
                                    for (int j = 0; j < characterArrayList.size(); j++) {
                                        if (characterArrayList.get(j).getName().equals("tank")) {
                                            index3 = j;
                                        }
                                    }
                                    System.out.println("What do you want to do?");
                                    System.out.println("Enter attack for attack");
                                    System.out.println("Enter check for see if there any items on the grass");
                                    System.out.println("Enter inventory for see inventory");
                                    System.out.println("Enter wield for wield a weapon or armor");
                                    String choice1 = sc.next();
                                    choice1 = choice1.toLowerCase();
                                    switch (choice1) {
                                        case "attack":
                                            for (int j = 0; j < enemyArrayList.size(); j++) {
                                                System.out.println("Name: " + enemyArrayList.get(j).getName() + " HP: " + enemyArrayList.get(j).getHP());
                                            }
                                            String enemyChoice = sc.next();
                                            enemyChoice = enemyChoice.toLowerCase();
                                            int index1 = 0;
                                            for (int j = 0; j < enemyArrayList.size(); j++) {
                                                if (enemyArrayList.get(j).getName().toLowerCase().equals(enemyChoice)) {
                                                    break;
                                                }
                                                index1++;
                                            }
                                            System.out.println("Tank attacked to " + enemyArrayList.get(index1).getName());
                                            double damage = tank.attack();
                                            enemyArrayList.get(index1).renewHP(1, damage);
                                            System.out.println("Tank made " + damage + "damage.");
                                            System.out.println(enemyArrayList.get(index1).getName() + " has " + enemyArrayList.get(index1).getHP() + " HP.");
                                            if (enemyArrayList.get(index1).getHP() < 0) {
                                                System.out.println(enemyArrayList.get(index1).getName() + " is dead.");
                                                Weapon newWeapon = throwWeapon();
                                                System.out.println(newWeapon.getName() + " dropped.");
                                                itemArrayList.add(newWeapon);
                                                enemyArrayList.remove(index1);
                                            }
                                            i++;
                                            break;
                                        case "check":
                                            if (itemArrayList.isEmpty()) {
                                                System.out.println("There are no item on the grass.");
                                            } else {
                                                System.out.println("ITEMS: ");
                                                for (int j = 0; j < itemArrayList.size(); j++) {
                                                    itemArrayList.get(j).printInfo();
                                                }
                                                System.out.println("Will you pick?");
                                                int itemIndex = sc.nextInt();
                                                itemIndex = itemIndex - 1;
                                                tank.add(itemArrayList.get(itemIndex));
                                            }
                                            break;
                                        case "inventory":
                                            if (tank.getInventory().isEmpty()) {
                                                System.out.println("No item in inventory.");
                                            } else {
                                                tank.printInventory();
                                            }
                                            break;
                                        case "wield":
                                            tank.changeHandledItem();
                                        default:
                                            System.out.println("Enter a valid input.");
                                            break;
                                    }
                                    break;
                                case "f":
                                    System.out.println("You are playing with fighter");
                                    int index4 = 0;
                                    for (int j = 0; j < characterArrayList.size(); j++) {
                                        if (characterArrayList.get(j).getName().equals("fighter")) {
                                            index4 = j;
                                        }
                                    }
                                    System.out.println("What do you want to do?");
                                    System.out.println("Enter attack for attack");
                                    System.out.println("Enter block for block an enemy one tour");
                                    System.out.println("Enter check for see if there any items on the grass");
                                    System.out.println("Enter inventory for see inventory");
                                    System.out.println("Enter wield for wield a weapon or armor");
                                    String choice2 = sc.next();
                                    choice2 = choice2.toLowerCase();
                                    switch (choice2) {
                                        case "attack":
                                            for (int j = 0; j < enemyArrayList.size(); j++) {
                                                System.out.println("Name: " + enemyArrayList.get(j).getName() + " HP: " + enemyArrayList.get(j).getHP());
                                            }
                                            System.out.println("Enter enemy name which you will attack?");
                                            String enemyChoice = sc.next();
                                            enemyChoice = enemyChoice.toLowerCase();
                                            int index1 = 0;

                                            for (int j = 0; j < enemyArrayList.size(); j++) {
                                                if (enemyArrayList.get(j).getName().toLowerCase().equals(enemyChoice)) {
                                                    break;
                                                }
                                                index1++;
                                            }
                                            System.out.println("Fighter attacked to " + enemyArrayList.get(index1).getName());
                                            double damage = fighter.attack();
                                            enemyArrayList.get(index1).renewHP(1, damage);
                                            System.out.println("Fighter made " + damage + "damage.");
                                            System.out.println(enemyArrayList.get(index1).getName() + " has " + enemyArrayList.get(index1).getHP() + " HP.");
                                            if (enemyArrayList.get(index1).getHP() < 0) {
                                                System.out.println(enemyArrayList.get(index1).getName() + " is dead.");
                                                Weapon newWeapon = throwWeapon();
                                                System.out.println(newWeapon.getName() + " dropped.");
                                                itemArrayList.add(newWeapon);
                                                enemyArrayList.remove(index1);
                                            }
                                            i++;
                                            break;
                                        case "block":
                                            int enemyAmount = enemyArrayList.size();
                                            enemyAmount = enemyAmount - 1;
                                            int randomEnemy = random.nextInt(0, enemyAmount);
                                            enemyArrayList.get(randomEnemy).setBlock(true);
                                            System.out.println(enemyArrayList.get(randomEnemy).getName() + "blocked.");
                                            i++;
                                            break;
                                        case "check":
                                            if (itemArrayList.isEmpty()) {
                                                System.out.println("There are no item on the grass.");
                                            } else {
                                                System.out.println("ITEMS: ");
                                                for (int j = 0; j < itemArrayList.size(); j++) {
                                                    itemArrayList.get(j).printInfo();
                                                }
                                                System.out.println("Will you pick?");
                                                int itemIndex = sc.nextInt();
                                                itemIndex = itemIndex - 1;
                                                tank.add(itemArrayList.get(itemIndex));
                                            }
                                            break;
                                        case "inventory":
                                            if (fighter.getInventory().isEmpty()) {
                                                System.out.println("No item in inventory.");
                                            } else {
                                                fighter.printInventory();
                                            }
                                            break;
                                        case "wield":
                                            fighter.changeHandledItem();
                                        default:
                                            System.out.println("Enter a valid input.");
                                            break;
                                    }
                                    break;
                                default:
                                    System.out.println("Enter true character name");
                                    break;
                            }
                        } else {
                            System.out.println("NEXT LEVEL");
                            i = 3;
                        }
                    }
                    if (enemyArrayList.size() > 0) {
                        for (int j = 0; j < enemyArrayList.size(); j++) {
                            int index5 = characterArrayList.size();
                            index5 = index5 - 1;
                            int xRandom = random.nextInt(0, index5);
                            if (!enemyArrayList.get(j).isBlock()) {
                                if (characterArrayList.get(xRandom).getHandledWeapon().getType().equals("shield")) {
                                    boolean isBlock = random.nextBoolean();
                                    if (!isBlock) {
                                        System.out.println(enemyArrayList.get(j).getName() + "attacked to " + characterArrayList.get(xRandom).getName() + " with damage " + enemyArrayList.get(j).attack());
                                        double damage = enemyArrayList.get(j).attack();
                                        characterArrayList.get(xRandom).renewHP(1, damage);
                                        System.out.println("New HP: " + characterArrayList.get(xRandom).getHP());
                                    } else {
                                        System.out.println(enemyArrayList.get(j));
                                    }
                                } else {
                                    System.out.println(enemyArrayList.get(j).getName() + "attacked to " + characterArrayList.get(xRandom).getName() + "with damage " + enemyArrayList.get(j).attack());
                                    double damage = enemyArrayList.get(j).attack();
                                    characterArrayList.get(xRandom).renewHP(1, damage);
                                    System.out.println("New HP: " + characterArrayList.get(xRandom).getHP());
                                }

                                if (characterArrayList.get(xRandom).getHP() <= 0) {
                                    System.out.println(characterArrayList.get(xRandom).getName() + "died.");
                                    characterArrayList.remove(xRandom);
                                }
                            } else {
                                System.out.println(enemyArrayList.get(j).getName() + " not available");
                            }
                        }
                    }else {
                        System.out.println("NEXT LEVEL");
                        i = 3;
                    }
                }
            } else {
                game = false;
            }
            level++;
            enemyCounter = (int) Math.pow(2, level);
        }
    }
    public static void enemyGenerator(int enemyCounter,ArrayList<Enemy> enemyArrayList){
        Weapon sword = new Sword("Broken sword",1,1,2);
        Weapon shield = new Shield("Broken Shield",2,1,1);
        Weapon wand = new Wand("Broken Wand",1,1,0.5);
        Armor leatherArmor = new LeatherArmor("Old Armor",1,1);

        for (int i = 0; i < enemyCounter; i++) {
            String name = "Enemy" + (i+1);
            int enemyWeapon = random.nextInt(1,10);
            if (enemyWeapon<8 && enemyWeapon>0){
                Enemy enemy = new Enemy(name,sword,leatherArmor);
                enemyArrayList.add(enemy);
            }
            else if (enemyWeapon>8 && enemyWeapon<10){
                Enemy enemy = new Enemy(name,wand,leatherArmor);
                enemyArrayList.add(enemy);
            }
            else if (enemyWeapon ==10){
                Enemy enemy = new Enemy(name,shield,leatherArmor);
                enemyArrayList.add(enemy);
            }
        }
    }
    public static Weapon throwWeapon(){
        Weapon[] throwen = new Weapon[10];
        throwen[0] = new Sword("MSword",2,2,3);
        throwen[2] = new Sword("BSword",3,1,2);
        throwen[3] = new Sword("GSword",1,5,4);
        throwen[4] = new Shield("GShield",1,3,2);
        throwen[5] = new Shield("BShield",2,1,1);
        throwen[6] =new Shield("MShield",3,1,2);
        throwen[7] = new Wand("MWand",1,2,2);
        throwen[8] = new Wand("BWand",2,2,1);
        throwen[9] = new Wand("GWand",3,3,3);

        int index = random.nextInt(0,9);
        return  throwen[index];
    }
}

