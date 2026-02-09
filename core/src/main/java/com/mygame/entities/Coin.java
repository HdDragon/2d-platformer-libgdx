package com.mygame.entities;

import com.badlogic.gdx.graphics.Texture;

public class Coin {
    public float x, y;
    public boolean isCollected;
    public Texture coinTexture;

    public Coin(float x, float y){
        this.x = x;
        this.y = y;
        this.coinTexture = new Texture("coin.png");
    }

    public void dispose() {
        if (coinTexture != null){
            coinTexture.dispose();
        }
    }
}
