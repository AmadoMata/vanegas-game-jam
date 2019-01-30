package com.ineriam.games.vanegas.gamejam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.ineriam.games.vanegas.gamejam.GdxGame;
import com.ineriam.games.vanegas.gamejam.controller.PhoneController;
import com.ineriam.games.vanegas.gamejam.controller.PhoneControllerInterface;
import com.ineriam.games.vanegas.gamejam.data.Const;
import com.ineriam.games.vanegas.gamejam.utils.Tools;
import com.ineriam.games.vanegas.gamejam.world.World;
import com.ineriam.games.vanegas.gamejam.world.WorldInfo;
import com.ineriam.games.vanegas.gamejam.world.WorldRenderer;

public class GameScreen implements Screen, PhoneControllerInterface {
    final GdxGame game;
    private World world;
    private WorldRenderer worldRenderer;
    private WorldInfo worldInfo;
    private PhoneController phoneController;

    private int worldScreenWidth;
    private int worldScreenHeight;
    private int worldScreenX;
    private int worldScreenY;

    private int worldInfoScreenWidth;
    private int worldInfoScreenHeight;
    private int worldInfoScreenX;
    private int worldInfoScreenY;

    private int phoneControllerWidth;
    private int phoneControllerHeight;
    private int phoneControllerX;
    private int phoneControllerY;

    private InputEvent inputEvent;

    public GameScreen (GdxGame gdxGame) {
        this.game = gdxGame;
    }

    @Override
    public void show() {
        phoneController = new PhoneController(game, this, true, true, false, false);
        phoneController.show();

        world = new World(game);
        worldRenderer = new WorldRenderer(game, world);

        worldInfo = new WorldInfo(game, world);
        worldInfo.show();

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(phoneController.stage);
        inputMultiplexer.addProcessor(phoneController);
        Gdx.input.setInputProcessor(inputMultiplexer);

        inputEvent = new InputEvent();
        inputEvent.setType(InputEvent.Type.touchDown);

        boolean guardianI = Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_I_COMPLETE);
        boolean guardianII = Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_II_COMPLETE);
        boolean guardianIII = Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_III_COMPLETE);
        boolean guardianIV = Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_IV_COMPLETE);
        boolean guardianV= Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_V_COMPLETE);
        boolean guardianVI = Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_VI_COMPLETE);

        if (guardianI && guardianII && guardianIII && guardianIV && guardianV && guardianVI) {
            game.setScreen(new GameCompleteScreen(game));
        }


    }

    @Override
    public void render(float delta) {
        if (delta > 0) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            // World
            world.update(delta);
            Gdx.gl.glViewport(worldScreenX, worldScreenY, worldScreenWidth, worldScreenHeight);
            worldRenderer.update(delta);

            // WorldInfo
            Gdx.gl.glViewport(worldInfoScreenX, worldInfoScreenY, worldInfoScreenWidth, worldInfoScreenHeight);
            worldInfo.update(delta);

            Gdx.gl.glViewport(phoneControllerX, phoneControllerY, phoneControllerWidth, phoneControllerHeight);
            phoneController.render(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
        if (game.hideController) {
            worldScreenWidth = (int) ((Const.NOKIA_SCREEN_WORLD_WIDTH * width) / Const.NOKIA_SCREEN_WIDTH);
            worldScreenHeight = (int) ((Const.NOKIA_SCREEN_WORLD_HEIGHT * width) / Const.NOKIA_SCREEN_WIDTH);
            worldScreenX = 0;
            worldScreenY = 0;

            worldInfoScreenWidth = width - worldScreenWidth;
            worldInfoScreenHeight = worldScreenHeight;
            worldInfoScreenX = worldScreenWidth;
            worldInfoScreenY = worldScreenY;

            phoneControllerWidth = 0;
            phoneControllerHeight = 0;
            phoneControllerX = 0;
            phoneControllerY = 0;
        } else {
            worldScreenWidth = (int) ((Const.NOKIA_SCREEN_WORLD_WIDTH * width) / Const.NOKIA_SCREEN_WIDTH);
            worldScreenHeight = (int) ((Const.NOKIA_SCREEN_WORLD_HEIGHT * width) / Const.NOKIA_SCREEN_WIDTH);
            worldScreenX = 0;
            worldScreenY = height - worldScreenHeight;

            worldInfoScreenWidth = width - worldScreenWidth;
            worldInfoScreenHeight = worldScreenHeight;
            worldInfoScreenX = worldScreenWidth;
            worldInfoScreenY = worldScreenY;

            phoneControllerWidth = width;
            phoneControllerHeight = height - worldScreenHeight;
            phoneControllerX = 0;
            phoneControllerY = 0;
        }


        worldRenderer.resize(worldScreenWidth, worldScreenHeight);
        worldInfo.resize(worldInfoScreenWidth, worldInfoScreenHeight);
        phoneController.resize(phoneControllerWidth, phoneControllerHeight);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void keyUp() {
        world.onKeyUp(Const.DIRECTION_UP);
    }

    @Override
    public void keyDown() {
        world.onKeyUp(Const.DIRECTION_DOWN);
    }

    @Override
    public void keyLeft() {
        world.onKeyUp(Const.DIRECTION_LEFT);
    }

    @Override
    public void keyRight() {
        world.onKeyUp(Const.DIRECTION_RIGHT);
    }

    @Override
    public void keyMenu() {
        game.setScreen(new MenuScreen(game));
    }

    @Override
    public void keyEnter() {

    }

    @Override
    public void keyBack() {

    }

}