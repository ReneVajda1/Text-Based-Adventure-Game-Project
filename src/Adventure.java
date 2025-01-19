import java.util.Random;
import java.util.Scanner;

public class Adventure {
    public static void main(String[] args) {


        // System objects
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        // Game variables
        String[] enemies = {"Skeleton,", "Zombie", "Warrior", "Assasin"};
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;

        // Player variables
        int heroHealth = 100;
        int heroAttackDamage = 50;
        // potions
        int numHealthPotions = 3;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50; // percentage

        //game
        boolean running = true;

        System.out.println("Welcome to the Dungeon");

        Game: //this is lable to while loop
        while(running) {
            System.out.println("----------------------------------------------");

            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println("\t# " + enemy + " appeared! #\n");
            //        # Skeleton has appeared! #


            while(enemyHealth > 0) {
                System.out.println("                   *********************");
                System.out.println("                  *|\t"+enemy + "'s HP: " + enemyHealth+ " |*");
                System.out.println("                   *********************");
                System.out.println("\tYour HP: " + heroHealth);
                System.out.println("\tWhat would you like to do? ");
                System.out.println("\t1. Attack: ");
                System.out.println("\t2. Drink health potion: ");
                System.out.println("\t3. Run: ");

                String input = in.nextLine(); //grabs next line from console and stores it in input

                if(input.equals("1")) {
                    int damageDealtByHero = rand.nextInt(heroAttackDamage);
                    int damageRecievedByEnemy = rand.nextInt(enemyAttackDamage);

                    enemyHealth -= damageDealtByHero;
                    heroHealth -= damageRecievedByEnemy;

                    System.out.println("\t> You strike the " + enemy + " for " + damageDealtByHero + " damage.");
                    System.out.println("\t> You recieved the " + damageRecievedByEnemy + " in retaliation!");

                    if(heroHealth <= 0 ){
                        System.out.println("\t.YOU HAVE DIED.");
                        running = false;
                    } else if (heroHealth <= 30 ) {
                        System.out.println("\t. You are too weak, your HP is: " + heroHealth);
                    } else {
                        System.out.println();
                    }

                } else if (input.equals("2")) {
                    if(numHealthPotions>0){
                        heroHealth += healthPotionHealAmount;
                        numHealthPotions --;
                        System.out.println("\t2. You drink a healt potion, healing yourself for: " + healthPotionHealAmount
                        + " . " + "\n\t> You now have: " + heroHealth + " HP "
                        + "\n\t> You have: " + numHealthPotions + " health potions left. \n");
                    } else {
                        System.out.println("\t. You have no health potions left!");
                    }

                } else if (input.equals("3")) {
                    System.out.println("\t. You run away from the enemy " + enemy);
                    continue Game;
                } else {
                    System.out.println("\t. Invalid command");
                }
            }

            System.out.println("----------------------------------------------");
            System.out.println(" # " + enemy + " was defeated! # ");
            System.out.println(" # You have " + heroHealth + " HP left. # ");
            if (rand.nextInt(100) > healthPotionDropChance) {
                numHealthPotions++;
                System.out.println(" # The " + enemy + " dropped a health potion! # ");
                System.out.println(" # You have " + numHealthPotions + " health potion(s) left. # ");
            }

            System.out.println("----------------------------------------------");
            System.out.println("What would you like to do now ? ");
            System.out.println("1. Continue fighting ");
            System.out.println("2. Exit the dungeon ");

            String input = in.nextLine();

            while(!input.equals("1") && !input.equals("2")) {
                System.out.println("Invalid command");
                input = in.next();
            }

            if (input.equals("1")) {
                System.out.println("You continue on your quest!");
            } else if (input.equals("2")) {
                System.out.println("You exit the dungeon, successfull from your quest");
                break;
            }
        }


    }
}