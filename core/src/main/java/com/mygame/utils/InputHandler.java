package com.mygame.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygame.entities.Player;


public class InputHandler implements InputProcessor {
    private final Player player;

    public InputHandler(Player player){
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode){
        switch(keycode){
            case Input.Keys.SPACE:
                player.jump();
                return true;
            case Input.Keys.A:
                player.velocity.x = -Player.SPEED;
                return true;
            case Input.Keys.D:
                player.velocity.x = Player.SPEED;
                return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode){
        switch(keycode) {
            case Input.Keys.A:
            case Input.Keys.D:
                player.velocity.x = 0;
                return true;
        }
        return false;
    }


    @Override public boolean keyTyped(char character) { return false; }
    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    @Override public boolean touchCancelled(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override public boolean scrolled(float amountX, float amountY) { return false; }

}
