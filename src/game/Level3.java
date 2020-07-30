package game;

import city.cs.engine.Shape;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 *  @author Will Dibble wodibble@gmail.com
 *  @version 1.13.2
 *  @since 1.9
 * This class is Level3.
 * This class has all the parameters, variables and objects needed to populate Level 3, the final level.
 * This level populates from GameLevel then randomly generates more platforms for the player to climb on.
 * Coins are then spawned on these random platforms which can be collected by the player for points.
 * However, Coins now have even less of a chance of spawning than level 2 requiring the player to, again, reach higher platforms to get points.
 * Gems are again spawned in on separate special platforms that give the player 3 points when collected.
 * It also loads more spikes than level 2 that can damage the player when collided with.
 * From the GameLevel class, more enemies are spawned in than the previous level.
 */

public class Level3 extends GameLevel {

    private static Enemy2 enemy2;

    public static Enemy2 getEnemy2() {
        return enemy2;
    }

    private static final int NUM_SCORE = 10;

    /**
     * Populate the world.
     */

    @Override
    public void populate(Game game) {
        super.populate(game);

        //stacks
        float y = 0;
        float y2 = 0;
        float y3 = 0;

        //Platform Coin Array to put coins in centre of platforms
        int[] a = new int[16];

        // Platforms 1
        Shape platform1Shape = new BoxShape(3.2f, 0.5f);
        for (int i = 0; i < 16; i = i + 1) {
            int x = ThreadLocalRandom.current().nextInt(-15, 15 + 1);
            Body platform1 = new StaticBody(this, platform1Shape);
            platform1.setPosition(new Vec2(x, -8f + y));
            platform1.setFillColor(Color.CYAN);
            platform1.addImage(new BodyImage("data/platform.png", 1.5f));

            a[i] = x;

            y = y + 6;
        }

        //Initialise Coins
        for (int i = 0; i < 16; i++) {
            int x = a[i];
            int r = ThreadLocalRandom.current().nextInt(1, 10);

            if (r > 3) {
                Body coin = new Coin(this);
                coin.setPosition(new Vec2(x, -7 + y2));
                coin.addCollisionListener(new Pickup(getPlayer()));

                //Give the coins a fixture for friction and density
                SolidFixture coinFixture = new SolidFixture(coin, Coin.shape);
                coinFixture.setDensity(100);
                coinFixture.setFriction(1);
                coinFixture.setRestitution(0.5f);

            }
            y2 = y2 + 6;
        }

        //Robot Boolean
        boolean robotBool = true;
        //Initialise Robots
        numOfEnemy2 = 0;
        for (int i = 0; i < 16; i++) {
            int x = a[i];

            if (x % 2 == 0) {
                enemy2 = new Enemy2(this);

                if (robotBool) {
                    enemy2.setPosition(new Vec2(x + 1.5f, -7 + y3));
                    enemy2ArrayX[numOfEnemy2] = x + 1.5f;
                    enemy2ArrayY[numOfEnemy2] = -7 + y3;
                    enemy2Position[i] = 1;
                    robotBool = false;
                } else {
                    enemy2.setPosition(new Vec2(x - 1.5f, -7 + y3));
                    enemy2ArrayX[numOfEnemy2] = x - 1.5f;
                    enemy2ArrayY[numOfEnemy2] = -7 + y3;
                    enemy2Position[i] = 2;
                    robotBool = true;
                }

                numOfEnemy2++;
                enemy2.addCollisionListener(new Death(getPlayer()));
            }

            y3 = y3 + 6;
        }

        //Extra Stacks
        //stacks
        float z = 0;
        float z2 = 0;

        //Platform Coin Array to put coins in centre of platforms
        int[] b = new int[4];

        // Extra Platforms
        Shape platform2Shape = new BoxShape(3.2f, 0.5f);
        for (int i = 0; i < 4; i = i + 1) {
            int w = ThreadLocalRandom.current().nextInt(0, 11);

            if (w % 2 == 1) {
                int r = ThreadLocalRandom.current().nextInt(-30, -20);
                Body platform2 = new StaticBody(this, platform2Shape);
                platform2.setPosition(new Vec2(r, -5f + z));
                platform2.setFillColor(Color.CYAN);
                platform2.addImage(new BodyImage("data/platform2.png", 1.5f));

                b[i] = r;
            } else {
                int r = ThreadLocalRandom.current().nextInt(20, 30);
                Body platform2 = new StaticBody(this, platform2Shape);
                platform2.setPosition(new Vec2(r, -5f + z));
                platform2.setFillColor(Color.CYAN);
                platform2.addImage(new BodyImage("data/platform2.png", 1.5f));

                b[i] = r;
            }

            z = z + 18;
        }

        //Initialise Gems
        for (int i = 0; i < 4; i++) {
            int r = b[i];
            Body gem = new Gem(this);
            gem.setPosition(new Vec2(r, -4 + z2));
            gem.addCollisionListener(new gemPickup(getPlayer()));

            //Give the coins a fixture for friction and density
            SolidFixture gemFixture = new SolidFixture(gem, Gem.shape);
            gemFixture.setDensity(100);
            gemFixture.setFriction(1);
            gemFixture.setRestitution(0.5f);

            z2 = z2 + 18;
        }

        //Initialise Spikes
        for (int i = 0; i < 12; i++) {
            Body spikes = new Spikes(this);
            spikes.setPosition(new Vec2(-38 + (i * 14), -11));
            spikes.addCollisionListener(new Death(getPlayer()));

            //Give the coins a fixture for friction and density
            SolidFixture gemFixture = new SolidFixture(spikes, Spikes.shape);
            gemFixture.setDensity(100);
            gemFixture.setFriction(1);
            gemFixture.setRestitution(0.5f);

        }

        /*
         * Collecting the Mushroom doubles your jump height for the level.
         * But halves your health.
         */

        //Initialise Mushroom
        Body mushroom = new Mushroom(this);
        mushroom.setPosition(new Vec2(-25, -10));
        mushroom.addCollisionListener(new mushroomPickup(getPlayer()));

    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0, -9);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(-5, 30);
    }

    @Override
    public boolean isCompleted() {
        return Player1.getCoinCount() >= NUM_SCORE;
    }

}
