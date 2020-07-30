package game;

import city.cs.engine.*;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.0
 * This class is Player1.
 * This class has all the variables needed to initialise the players, shape, image, score and health.
 * Depending on which way the player is facing, their image changes to give the illusion of movement.
 * This class holds all the methods needed to increment the players score and health.
 */

public class Player1 extends Walker {

    private static final Shape shape = new PolygonShape(0.149f, 0.975f, 0.775f, 0.193f, 0.772f, -0.099f, 0.401f, -0.928f, -0.36f, -0.922f, -0.719f, -0.025f, -0.725f, 0.163f, -0.14f, 0.972f);

    //Images for Left and Right animation
    public static final BodyImage imageRight =
            new BodyImage("data/player/walkRight.gif", 2.25f);

    public static final BodyImage imageLeft =
            new BodyImage("data/player/walkLeft.gif", 2.25f);

    public static final BodyImage imageIdleRight =
            new BodyImage("data/player/idleRight.png", 2.25f);

    public static final BodyImage imageIdleLeft =
            new BodyImage("data/player/idleLeft.png", 2.25f);

    //Jump png
    public static final BodyImage playerJump =
            new BodyImage("data/player/jump.png", 2.25f);

    private static int coinCount;
    public static int healthCount;

    /**
     * Create the player and set player facing right with a coin count of 0.
     *
     * @param world in the world.
     */
    public Player1(World world) {
        super(world, shape);
        addImage(imageIdleRight);
        coinCount = 0;
        healthCount = 100;
    }

    /** Retrieve the Players Coin Count/Score.
     *
     * @return Coin Count/Score.
     */
    public static int getCoinCount() {
        return coinCount;
    }

    /**
     * Every time the player collects a coin, add 1 to their score.
     */
    public void incrementCoinCount() {
        coinCount++;
        System.out.println("Score = " + coinCount);
    }

    /**
     * Every time the player collects a gem, add 3 to their score.
     */
    public void incrementGemCount() {
        coinCount = coinCount + 3;
        System.out.println("Score = " + coinCount);
    }

    /**
     * Retrieve the Players Health.
     *
     * @return Health Count.
     */
    public static int getHealthCount() {
        return healthCount;
    }

    /**
     * Every time the player hits an enemy, reduce health by 25.
     */
    public void incrementHealthCount() {
        healthCount = healthCount - 25;
        System.out.println("Health = " + healthCount);
    }

    /**
     * Set the players health to newCount.
     *
     * @param newCount players new health.
     */
    public static void setHealthCount(int newCount) {
        healthCount = newCount;
    }

    /**
     * Set the players Score to newCount.
     *
     * @param newCount players new health.
     */
    public static void setCoinCount(int newCount) {
        coinCount = newCount;
    }
}
