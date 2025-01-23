import java.util.Random;
import java.util.Scanner;

public class Adventure {
    public static void main(String[] args) {
        // System objects
        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        // Game variables
        String[] enemies = {"Skeleton Warrior", "Rotting Goblin", "Zombie Warrior", "Frenzy Ghoul"};
        int maxEnemyHealth = 75;
        int enemyAttackDamage = 25;

        // Player variables
        int heroHealth = 100;
        int heroAttackDamage = 50;

        // Potions
        int numHealthPotions = 3;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 20; // percentage

        // Special items
        boolean hasMagicSword = false;
        boolean hasKey = false;
        boolean hasShield = false;
        int shieldBlockChance = 20; // 20% chance to block damage

        // Torch mechanics
        int torchCount = 0; // Player starts with 0 torches
        int torchBurnPercentage = 0; // No torch lit initially
        int woodenSticks = 5; // Start with 2 sticks
        int tinder = 5; // Start with 2 tinder

        // Goblin mechanics
        boolean goblinKilled = false;
        boolean goblinKicked = false;



        //

        // Navigation variables
        int forwardCount = 0;

        boolean running = true;
        System.out.println("----------------------------------------------");
        System.out.println("Welcome to the Dungeon...");
        System.out.println("----------------------------------------------");
        System.out.println("The air grows colder as you descend the narrow staircase.");
        System.out.println();
        System.out.println("The distant echoes of dripping water and unseen creatures send a chill down your spine.");
        System.out.println("You grip your weapon tightly as you step into the darkness...");
        System.out.println();
        System.out.println("Prepare yourself, hero. The dungeon awaits!\n");

        Game: while (running) {
            // Display torch and hero health bars
            System.out.println("----------------------------------------------");
            System.out.println("Torch: [" + getTorchBar(torchBurnPercentage) + "] " + torchBurnPercentage + "%");
            System.out.println("Hero: [" + getHealthBar(heroHealth, "♥") + "] " + heroHealth + " HP");

            if (torchBurnPercentage > 0) {
                torchBurnPercentage -= 33;
            }

            if (torchBurnPercentage <= 0) {
                if (torchCount > 0) {
                    System.out.println("Your torch burns out. You must craft or light another torch.");
                    System.out.println("Torches left: " + torchCount);
                    System.out.println("1. Light a new torch");
                    System.out.println("2. Continue in darkness");

                    String torchChoice = in.nextLine();
                    if (torchChoice.equals("1")) {
                        torchCount--;
                        torchBurnPercentage = 100;
                        System.out.println("You light a new torch. Torches left: " + torchCount);
                    } else {
                        System.out.println("You are consumed by the darkness...");
                        break Game;
                    }
                } else {
                    System.out.println("1. Craft a torch (requires 1 wooden stick and 1 tinder)");
                    System.out.println("2. Continue in to the darkness");

                    String torchChoice = in.nextLine();
                    if (torchChoice.equals("1")) {
                        if (woodenSticks > 0 && tinder > 0) {
                            torchCount++;
                            woodenSticks--;
                            tinder--;
                            torchBurnPercentage = 100;
                            System.out.println("You craft and light a new torch! Torches left: " + torchCount);
                        } else {
                            System.out.println("You don't have enough materials to craft a torch!");
                            System.out.println("You are consumed by the darkness...");
                            break Game;
                        }
                    } else {
                        System.out.println("You are consumed by the darkness...");
                        break Game;
                    }
                }
            }

            // Friendly Goblin logic
            if (rand.nextInt(100) < 5 && !goblinKilled) {
                System.out.println("----------------------------------------------");
                if (!goblinKicked) {
                    System.out.println("A 'Friendly Goblin' appears and asks you for a torch!");
                    System.out.println("1. Give him a torch");
                    System.out.println("2. Refuse to give him a torch");

                    String goblinChoice = in.nextLine();
                    if (goblinChoice.equals("1")) {
                        if (torchCount > 0) {
                            torchCount--;
                            System.out.println("You give the goblin a torch.");
                            System.out.println("He laughs maniacally: 'DIEEEE! Hahaha! There are no friendly goblins!'");
                            System.out.println("Without a torch, you are consumed by the darkness...");
                            break Game;
                        } else {
                            System.out.println("You don't have a torch to give! The goblin curses at you and vanishes.");
                        }
                    } else if (goblinChoice.equals("2")) {
                        System.out.println("You refuse to give the goblin a torch.");
                        System.out.println("The goblin mutters angrily and disappears into the shadows.");
                    }
                } else {
                    System.out.println("The 'Friendly Goblin' reappears!");
                    System.out.println("You kick him in the face!");
                    System.out.println("The goblin screams: 'Stop! I can lead you to treasure!'");
                    System.out.println("1. Follow him");
                    System.out.println("2. Kill him");

                    String followKillChoice = in.nextLine();
                    if (followKillChoice.equals("1")) {
                        System.out.println("You decide to follow the goblin...");
                        System.out.println("He leads you to a secret room!");
                        System.out.println("Inside, you find magical armor with a shield!");
                        System.out.println("The armor grants you a 20% chance to block all incoming damage!");
                        hasShield = true;
                        System.out.println("The goblin laughs and disappears into the shadows.");
                    } else if (followKillChoice.equals("2")) {
                        System.out.println("You kill the goblin. He drops 5 health potions!");
                        numHealthPotions += 5;
                        goblinKilled = true;
                    } else {
                        System.out.println("The goblin vanishes into the darkness.");
                    }
                }
                goblinKicked = true;
                continue;
            }




            // Mysterious woman lompa
            if (rand.nextInt(100) < 5) {
                System.out.println("----------------------------------------------");
                System.out.println("A mysterious door appears in the wall, and a beautiful woman beckons you inside.");
                System.out.println("She whispers, 'There is a shortcut this way. Just come closer...'");
                System.out.println("1. Approach her");
                System.out.println("2. Swing your torch");

                String womanChoice = in.nextLine();
                if (womanChoice.equals("1")) {
                    System.out.println("You step closer. Her forked tongue flicks as she licks her lips...");


                    if (rand.nextInt(100) < 100) {
                        System.out.println("----------------------------------------------");
                        System.out.println("1. Go closer");
                        System.out.println("2. Swing your torch");

                        String womanChoice2 = in.nextLine();
                        if (womanChoice2.equals("1")) {
                            System.out.println("...from now you can see her pale face better and she is smiling at you");


                            if (rand.nextInt(100) < 100) {
                                System.out.println("----------------------------------------------");
                                System.out.println("1. Go closer");
                                System.out.println("2. Swing your torch");

                                String womanChoice3 = in.nextLine();
                                if (womanChoice3.equals("1")) {
                                    System.out.println("You step closer. Her forked tongue flicks as she licks her lips...");
                                    System.out.println("She bites into your throat! You collapse...");
                                    game:
                                    break;
                                } else if (womanChoice3.equals("2")) {
                                    System.out.println("You swing your torch at her!");
                                    System.out.println("She hisses and screams, and the door vanishes.");
                                } else {
                                    System.out.println("The mysterious door vanishes into the darkness.");
                                }
                                continue;

                            } else if (womanChoice2.equals("2")) {
                                System.out.println("You swing your torch at her!");
                                System.out.println("She hisses and screams, and the door vanishes.");
                            } else {
                                System.out.println("The mysterious door vanishes into the darkness.");
                            }
                            continue;


                        } else if (womanChoice.equals("2")) {
                            System.out.println("You swing your torch at her!");
                            System.out.println("She hisses and screams, and the door vanishes.");
                        } else {
                            System.out.println("The mysterious door vanishes into the darkness.");
                        }
                        continue;
                    }
                }
            }

            // Wandering spirit
            if (rand.nextInt(100) < 5) {
                System.out.println("----------------------------------------------");
                System.out.println("A ghostly figure materializes before you, its translucent form flickering in the dim light.");
                System.out.println("It whispers, 'Mortal, heed my words... Choose wisely, for the veil is thin.'");
                System.out.println("1. Listen to the spirit's whispers");
                System.out.println("2. Attack the spirit");
                System.out.println("3. Walk away");

                String spiritChoice = in.nextLine();

                if (spiritChoice.equals("1")) {
                    System.out.println("The spirit whispers secrets of the dungeon...");
                    if (rand.nextInt(100) < 50) {
                        System.out.println("The spirit blesses you with knowledge of a hidden treasure!");
                        System.out.println("You gain a key and feel stronger (+20 HP).");
                        hasKey = true;
                        heroHealth += 20;
                    } else {
                        System.out.println("The spirit's whispers twist into screams, and you feel your strength draining...");
                        System.out.println("You lose 15 HP.");
                        heroHealth -= 15;
                    }
                } else if (spiritChoice.equals("2")) {
                    System.out.println("You swing your weapon at the spirit!");
                    if (rand.nextInt(100) < 50) {
                        System.out.println("Your weapon strikes true, and the spirit dissipates, leaving behind a glowing orb!");
                        System.out.println("You gain 1 health potion.");
                        numHealthPotions++;
                    } else {
                        System.out.println("Your weapon passes through the spirit, and its hollow eyes stare into your soul...");
                        System.out.println("You feel a chilling pain as it curses you (-10 HP).");
                        heroHealth -= 10;
                    }
                } else if (spiritChoice.equals("3")) {
                    System.out.println("You decide to leave the spirit undisturbed.");
                    System.out.println("The spirit watches silently as you walk away, fading back into the darkness.");
                } else {
                    System.out.println("Invalid choice. The spirit fades away, leaving you alone in the dungeon.");
                }

                continue;
            }



            // Magical cat mnauky
            if (rand.nextInt(100)<5) {
                System.out.println("----------------------------------------------");
                System.out.println("A Mysterious magical cat is had appeared.. its glowing.. curled up, and blocks your path");
                System.out.println("MEEEEEEEEEEEOW ");
                System.out.println("1. Pet the cat");
                System.out.println("2. Ignore the cat");
                System.out.println("3. Attack the cat");

                String catChoice = in.nextLine();

                if (catChoice.equals("1")) {
                    System.out.println("Cat's magical powers restored your strenght you have gained 50hp");
                    heroHealth += 50;

                } else if (catChoice.equals("2")) {
                    System.out.println("Cat is not to be ignored.. Cat scratches you causing 20 damage");
                    System.out.println("Glowing cat had disapeared.");
                    heroHealth -= 20;

                } else if (catChoice.equals("3")) {
                    System.out.println("Cat runs away ");
                }
                continue;
            }


            // Navigation logic
            System.out.println("----------------------------------------------");
            System.out.println("1. Go Left");
            System.out.println("2. Go Right");
            System.out.println("3. Go Forward");
            System.out.println("4. Craft a torch");
            System.out.println("5. Drink a health potion");

            String navigation = in.nextLine();

            if (navigation.equals("4")) {
                if (woodenSticks > 0 && tinder > 0) {
                    torchCount++;
                    woodenSticks--;
                    tinder--;
                    System.out.println("You craft a new torch! Torches: " + torchCount);
                } else {
                    System.out.println("You don't have enough materials to craft a torch. (Sticks: " + woodenSticks + ", Tinder: " + tinder + ")");
                }
                continue;
            } else if (navigation.equals("5")) {
                if (numHealthPotions > 0) {
                    heroHealth += healthPotionHealAmount;
                    numHealthPotions--;
                    System.out.println("You drink a health potion, healing yourself for " + healthPotionHealAmount);
                    System.out.println("Your HP: " + heroHealth);
                    System.out.println("Health potions left: " + numHealthPotions);
                } else {
                    System.out.println("You have no health potions left!");
                }
                continue;
            }

            if (navigation.equals("3")) {
                forwardCount++;
                if (forwardCount == 3) {
                    System.out.println("You have entered the Necromancer's room!");
                    System.out.println("Prepare for the ultimate fight!");

                    // Boss fight
                    int bossHealth = 200;
                    int bossAttackDamage = 35;

                    while (bossHealth > 0) {
                        System.out.println("Necromancer's HP: " + bossHealth);
                        System.out.println("Your HP: " + heroHealth);
                        System.out.println("1. Attack");
                        System.out.println("2. Drink health potion");

                        String bossInput = in.nextLine();

                        if (bossInput.equals("1")) {
                            if (!hasMagicSword) {
                                System.out.println("You don't have the Magic Sword. The boss kills you!");
                                break Game;
                            }

                            int damageDealt = rand.nextInt(heroAttackDamage) + 20; // Bonus damage for magic sword
                            int damageTaken = rand.nextInt(bossAttackDamage);

                            bossHealth -= damageDealt;
                            heroHealth -= damageTaken;

                            System.out.println("You strike the boss for " + damageDealt + " damage.");
                            System.out.println("The boss retaliates for " + damageTaken + " damage!");

                            if (heroHealth <= 0) {
                                System.out.println("A wave of suffocating darkness envelops you, stealing the last breath from your lungs.");
                                System.out.println("Your vision fades as the Necromancer whispers, 'Your soul is mine now, to torment for all eternity.'");
                                System.out.println("You collapse to the cold, unyielding floor, and the Necromancer's laughter echoes endlessly...");
                                System.out.println("GAME OVER.");
                                break Game;
                            }

                        } else if (bossInput.equals("2")) {
                            if (numHealthPotions > 0) {
                                heroHealth += healthPotionHealAmount;
                                numHealthPotions--;
                                System.out.println("You drink a health potion, healing yourself for " + healthPotionHealAmount);
                                System.out.println("Your HP: " + heroHealth);
                                System.out.println("Health potions left: " + numHealthPotions);
                            } else {
                                System.out.println("You have no health potions left!");
                            }
                        } else {
                            System.out.println("Invalid command.");
                        }
                    }

                    if (bossHealth <= 0) {
                        System.out.println("You have defeated the boss!");
                        if (!hasKey) {
                            System.out.println("You need a key to escape the dungeon!");
                        } else {
                            System.out.println("You use the key to escape the dungeon. Congratulations!");
                            break Game;
                        }
                    }
                }
            }


            else if (navigation.equals("1") || navigation.equals("2")) {
                System.out.println("You move cautiously...");
                int enemyHealth = rand.nextInt(maxEnemyHealth);
                String enemy = enemies[rand.nextInt(enemies.length)];
                System.out.println("A " + enemy + " appears!");

                while (enemyHealth > 0) {
                    System.out.println("----------------------------------------------");
                    System.out.println("Enemy: [" + getHealthBar(enemyHealth, "☠") + "] " + enemyHealth + " HP");
                    System.out.println("Hero: [" + getHealthBar(heroHealth, "♥") + "] " + heroHealth + " HP");
                    System.out.println("1. Attack");
                    System.out.println("2. Drink health potion");
                    System.out.println("3. Run");

                    String input = in.nextLine();

                    if (input.equals("1")) {
                        int damageDealt = rand.nextInt(heroAttackDamage);
                        int damageTaken = rand.nextInt(enemyAttackDamage);

                        // Shield logic
                        if (hasShield && rand.nextInt(100) < shieldBlockChance) {
                            damageTaken = 0;
                            System.out.println("Your shield blocks the attack!");
                        }

                        enemyHealth -= damageDealt;
                        heroHealth -= damageTaken;

                        System.out.println("You strike the " + enemy + " for " + damageDealt + " damage.");
                        if (damageTaken > 0) {
                            System.out.println("The " + enemy + " retaliates for " + damageTaken + " damage!");
                        }

                        if (heroHealth <= 0) {
                            System.out.println("You were slain by the " + enemy + "!");
                            break Game;
                        }

                    } else if (input.equals("2")) {
                        if (numHealthPotions > 0) {
                            heroHealth += healthPotionHealAmount;
                            numHealthPotions--;
                            System.out.println("You drink a health potion, healing yourself for " + healthPotionHealAmount);
                            System.out.println("Your HP: " + heroHealth);
                            System.out.println("Health potions left: " + numHealthPotions);
                        } else {
                            System.out.println("You have no health potions left!");
                        }

                    } else if (input.equals("3")) {
                        System.out.println("You run away from the " + enemy + "!");
                        continue Game;
                    } else {
                        System.out.println("Invalid command.");
                    }
                }

                if (enemyHealth <= 0) {
                    System.out.println("You have defeated the " + enemy + "!");

                    // Random drops
                    if (rand.nextInt(100) < 10) {
                        woodenSticks++;
                        System.out.println("The " + enemy + " dropped a Wooden Stick!");
                    }
                    if (rand.nextInt(100) < 10) {
                        tinder++;
                        System.out.println("The " + enemy + " dropped Tinder!");
                    }
                    if (rand.nextInt(100) < 5) {
                        hasMagicSword = true;
                        System.out.println("The " + enemy + " dropped a Magic Sword!");
                    }
                    if (rand.nextInt(100) < 5) {
                        hasKey = true;
                        System.out.println("The " + enemy + " dropped a Key!");
                    }
                    if (rand.nextInt(100) < healthPotionDropChance) {
                        numHealthPotions++;
                        System.out.println("The " + enemy + " dropped a health potion!");
                    }
                }
            } else {
                System.out.println("Invalid command.");
            }
        }
    }

    private static String getTorchBar(int percentage) {
        int bars = percentage / 10;
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < bars; i++) {
            bar.append("#");
        }
        for (int i = bars; i < 10; i++) {
            bar.append("-");
        }
        return bar.toString();
    }

    private static String getHealthBar(int health, String symbol) {
        int units = health / 10;
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < units; i++) {
            bar.append(symbol);
        }
        for (int i = units; i < 10; i++) {
            bar.append("-");
        }
        return bar.toString();
    }
}
