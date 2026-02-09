package com.mygame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Player {
    public static final float WIDTH = 1f;
    public static final float HEIGHT = 2f;
    public static final float SPEED = 4f;
    public static final float JUMP_FORCE = 8f;

    public Vector2 position = new Vector2();
    public Vector2 velocity = new Vector2();
    public Rectangle bounds = new Rectangle();
    public boolean grounded = false;

    public Texture playerTexture;


    public Player(){
        this.playerTexture = new Texture("player.png");
    }

    public void update(float delta){
        velocity.y -= 15f * delta; // Gravity
        velocity.y = Math.max(velocity.y, -JUMP_FORCE * 2); // Fall limitation
        position.add(velocity.x * delta, velocity.y * delta); // Pos update
        bounds.set(position.x, position.y, WIDTH, HEIGHT); // Hitbox update

    }

    public void jump() {
        if (grounded){
            velocity.y = JUMP_FORCE;
            grounded = false;
        }
    }

    public void dispose() {
        if (playerTexture != null){
            playerTexture.dispose();
        }
    }
}
