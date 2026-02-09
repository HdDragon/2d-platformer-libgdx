package com.mygame;

import com.badlogic.gdx.Game;
import com.mygame.screens.GameScreen;




public class MainGame extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen(this));
    }




}
