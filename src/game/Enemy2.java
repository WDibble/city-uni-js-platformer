package game;

import city.cs.engine.*;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.8
 * This class is Enemy2.
 * This class sets the elements and variables for the robots to guard the platforms.
 */

public class Enemy2 extends Walker {

    public int healthCount;

    private static final Shape shape = new PolygonShape(0.149f, 0.975f, 0.775f, 0.193f, 0.772f, -0.099f, 0.401f, -0.928f, -0.36f, -0.922f, -0.719f, -0.025f, -0.725f, 0.163f, -0.14f, 0.972f);

    private static final BodyImage image = new BodyImage("data/enemy2/character_robot_idle.png", 2.25f);

    public int enemyNum = 0;

    /**
     * Generate Enemy
     *
     * @param world the game world.
     */
    public Enemy2(World world) {
        super(world, shape);
        //Add the enemy's images to the body.
        addImage(image);
        //Set the enemy's gravity.
        setGravityScale(5);
        //Set the enemy's health.
        healthCount = 30;
        enemyNum++;
    }
    /**
     * When called, get the health count of enemy 2.
     *
     * @return the enemy's health count.
     */
    public int getHealthCount() {
        return healthCount;
    }

}



