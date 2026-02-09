package com.mygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygame.MainGame;
import com.mygame.entities.Coin;
import com.mygame.entities.Player;
import com.mygame.utils.InputHandler;
import com.mygame.world.World;



public class GameScreen extends ScreenAdapter {
    private final MainGame game;
    private OrthographicCamera camera;
    private World world;
    private SpriteBatch batch;
    private Player player;
    private ExtendViewport viewport;
    private Texture platformTexture;
    private BitmapFont font;
    private Texture backgroudTexture;

    Array<Coin> coins;
    int coinCounter;
    float coinTimer;
    float gameTime;

    public GameScreen(MainGame game){
        this.game = game;
        this.batch = new SpriteBatch();
        this.player = new Player();
        this.world = new World();
        this.camera = new OrthographicCamera();
        this.viewport = new ExtendViewport(20, 10, camera);
        this.coins = new Array<>();
        this.coinTimer = 0;
        this.gameTime = 0;
        this.coinCounter = 0;
    }


    @Override
    public void show(){
        platformTexture = new Texture(Gdx.files.internal("platform.jpg"));
        backgroudTexture = new Texture(Gdx.files.internal("background.jpg"));
        font = new BitmapFont();
        font.getData().setScale(2);
        font.setColor(Color.BLACK);

        Gdx.input.setInputProcessor(new InputHandler(world.getPlayer()));
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height, true);
    }

    @Override
    public void render(float delta){
        world.update(delta);

        // Update cam (only x)
        camera.position.x = world.getPlayer().position.x + Player.WIDTH / 2;
        camera.position.y = viewport.getWorldHeight() / 2;
        camera.update();

        float delta1 = Gdx.graphics.getDeltaTime();
        gameTime += delta1;

        // Drawing
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(backgroudTexture, camera.position.x - viewport.getWorldWidth() / 2,
            0,
            viewport.getWorldWidth(), viewport.getWorldHeight());

        for (Rectangle platform: world.getPlatforms()){
            batch.draw(platformTexture, platform.x, platform.y, platform.width, platform.height);
        }

        batch.draw(player.playerTexture, world.getPlayer().position.x, world.getPlayer().position.y, Player.WIDTH, Player.HEIGHT);

        /**
        coinTimer += delta;
        for (Coin coin : coins){
            if (!coin.isCollected){
                batch.draw(coin.coinTexture, coin.x, coin.y, 1, 1);
            }
        }
        if (coinTimer >= 7){
            coins.add(new Coin(MathUtils.random(0, 1400), MathUtils.random(200, 500)));
            coinTimer = 0;
        }
        for (Coin coin : coins){
            if (!coin.isCollected && Intersector.overlaps(
                new Circle(player.position.x + 16, player.position.y + 16, 16),
                new Rectangle(coin.x, coin.y, 32, 32)
            )){
                coin.isCollected = true;
                coinCounter++;
            }
        }

        font.draw(batch, "Coins: " + coinCounter, 20, Gdx.graphics.getHeight() - 20);

        int minutes = (int)(gameTime / 60);
        int seconds = (int)(gameTime % 60);
        font.draw(batch, String.format("Time: %02d:%02d", minutes, seconds), 20, Gdx.graphics.getHeight() - 50);
        */
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroudTexture.dispose();
        platformTexture.dispose();
        font.dispose();
    }
}
