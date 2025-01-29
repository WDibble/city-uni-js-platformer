package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.5
 * This class is GameLevel
 * This class all the parameters, variables and objects needed to populate each level.
 * When a new level is initialised, it first populates the ground, walls, player and enemies
 * before then loading the level.
 */

public abstract class GameLevel extends World {

    public static Player1 p1;

    public static Enemy1 enemy1;

    public static Enemy2 enemy2;

    public static int numOfEnemy2 = 0;

    public static float[] enemy2ArrayX = new float[16];
    public static float[] enemy2ArrayY = new float[16];

    public static int[] enemy2Position = new int[16];

    public Player1 getPlayer() {
        return p1;
    }

    public Enemy1 getEnemy() {
        return enemy1;
    }

    /**
     * Populate the game world.
     *
     * @param game the game.
     */
    public void populate(Game game) {

        //super(120);

        // make the ground
        int xPosGround = -32;
        for (int i = 0; i < 3; i++) {
            Shape groundShape = new BoxShape(16, 0.5f);
            Body ground = new StaticBody(this, groundShape);
            new AttachedImage(ground, (new BodyImage("data/grass3.png", 1)), 1, 0, new Vec2(0, 0));
            ground.setPosition(new Vec2(xPosGround, -11.5f));
            xPosGround = xPosGround + 32;
        }

        //Invisible walls
        Shape leftWallShape = new BoxShape(0.5f, 30);
        Body leftWall = new StaticBody(this, leftWallShape);
        leftWall.setPosition(new Vec2(-49, 15));
        new AttachedImage(leftWall, (new BodyImage("data/grass3.png", 1)), 0, 0, new Vec2(0, 0));
        leftWall.addCollisionListener(new wallCollision(getEnemy()));

        Shape rightWallShape = new BoxShape(0.5f, 30);
        Body rightWall = new StaticBody(this, rightWallShape);
        rightWall.setPosition(new Vec2(49f, 15));
        new AttachedImage(rightWall, (new BodyImage("data/grass3.png", 1)), 0, 0, new Vec2(0, 0));
        rightWall.addCollisionListener(new wallCollision(getEnemy()));

        //Initialise Player 1
        p1 = new Player1(this);
        p1.setPosition(startPosition());
        p1.setGravityScale(5); 

        //Initialise Enemy
        if (Game.level == 1) {
            enemy1 = new Enemy1(this);
            enemy1.setPosition(new Vec2(-10, -9));
            enemy1.startWalking(2);
            enemy1.addCollisionListener(new Death(getPlayer()));
        }

        if (Game.level == 2) {
            for (int i = 0; i < 3; i++) {
                enemy1 = new Enemy1(this);
                enemy1.setPosition(new Vec2(-10 + (i * 7), -9));
                enemy1.startWalking(2);
                enemy1.addCollisionListener(new Death(getPlayer()));

                Vec2 v = enemy1.getLinearVelocity();
                if (Math.abs(v.y) < 0.01f) {
                    enemy1.jump(5);
                }
            }
        }

        if (Game.level == 3) {
            for (int i = 0; i < 6; i++) {
                enemy1 = new Enemy1(this);
                enemy1.setPosition(new Vec2(-40 + (i * 9), -9));
                enemy1.startWalking(2);
                enemy1.addCollisionListener(new Death(getPlayer()));

                Vec2 v = enemy1.getLinearVelocity();
                if (Math.abs(v.y) < 0.01f) {
                    enemy1.jump(5);
                }
            }
        }

        Door door = new Door(this);
        door.setPosition(doorPosition());
        door.addCollisionListener(new DoorListener(game));
    }

    /**
     * The initial position of the player.
     */
    public abstract Vec2 startPosition();

    /**
     * The position of the exit door.
     */
    public abstract Vec2 doorPosition();

    /**
     * Is this level complete?
     */
    public abstract boolean isCompleted();

}
