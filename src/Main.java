import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        // System objects
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        // Game variables
        String[] enemies = {"Skeleton,", "Zombie", "Warrior", "Assasin"};
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;

        // Player variables
        int health = 100;
        int attackDamage = 50;
        // potions
        int numHealthPotions = 3;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50; // percentage

        //game
        boolean running = true;

        System.out.println("Welcome to the Dungeon");

        

    }
}