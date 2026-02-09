package com.mygame.world;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.mygame.entities.Player;
import com.badlogic.gdx.math.Rectangle;


public class World {
    private Array<Rectangle> platforms = new Array<>();
    private Player player;


    public World() {
        player = new Player();
        player.position.set(5, 1); // Place the player on the platform
        platforms.add(new Rectangle(-3, 2, 100, 0.5f)); // Main platform
        platforms.add(new Rectangle(12, 5, 3, 0.5f)); // Additional platform
        platforms.add(new Rectangle(21, 7, 3, 0.5f)); // Additional too
    }

    public void update(float delta) {
        checkCollisions();
        player.update(delta);
    }

    private void checkCollisions() {
        player.grounded = false;

        // Calculate the new pos to check ground (only y)
        float testY = player.position.y + player.velocity.y * Gdx.graphics.getDeltaTime();
        Rectangle testBounds = new Rectangle(player.position.x, testY, Player.WIDTH, Player.HEIGHT);

        // Check grounding (collision from bellow)
        for (Rectangle platform : platforms) {
            if (testBounds.overlaps(platform)) {
                // Check player on top
                if (player.bounds.y >= platform.y + platform.height - 0.15f &&
                    player.velocity.y <= 0) {
                    player.position.y = platform.y + platform.height;
                    player.velocity.y = 0;
                    player.grounded = true;
                    break;
                }
            }
        }

        // Movement along X
        float newX = player.position.x + player.velocity.x * Gdx.graphics.getDeltaTime();
        Rectangle xBounds = new Rectangle(newX, player.position.y, Player.WIDTH, Player.HEIGHT);

        for (Rectangle platform : platforms) {
            if (xBounds.overlaps(platform)) {
                if (player.velocity.x > 0) { // Move right
                    newX = platform.x - Player.WIDTH;
                } else if (player.velocity.x < 0) { // Move left
                    newX = platform.x + platform.width;
                }
                player.velocity.x = 0;
                break;
            }
        }

        // Movement along y (if not grounded)
        float newY = player.position.y;
        if (!player.grounded) {
            newY = testY;
            Rectangle yBounds = new Rectangle(player.position.x, newY, Player.WIDTH, Player.HEIGHT);

            for (Rectangle platform : platforms) {
                if (yBounds.overlaps(platform)) {
                    if (player.velocity.y > 0) { // Header
                        newY = platform.y - Player.HEIGHT;
                        player.velocity.y = 0;
                    } else if (player.velocity.y < 0) { // Falling onto the plat
                        newY = platform.y + platform.height;
                        player.velocity.y = 0;
                        player.grounded = true;
                    }
                    break;
                }
            }
        }

        // Applying new coords
        player.position.set(newX, newY);
        player.bounds.set(player.position.x, player.position.y, Player.WIDTH, Player.HEIGHT);

        // Protect from fall
        if (player.position.y < 0) {
            player.position.y = 0;
            player.velocity.y = 0;
            player.grounded = true;
        }
    }


    public Player getPlayer() { return player; }
    public Array<Rectangle> getPlatforms() { return platforms; }
}
