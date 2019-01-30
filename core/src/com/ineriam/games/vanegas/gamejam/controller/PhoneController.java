package com.ineriam.games.vanegas.gamejam.controller;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.ineriam.games.vanegas.gamejam.GdxGame;
import com.ineriam.games.vanegas.gamejam.data.Const;
import com.ineriam.games.vanegas.gamejam.utils.Tools;

public class PhoneController implements InputProcessor {
    final GdxGame game;
    private PhoneControllerInterface phoneControllerInterface;

    private ExtendViewport viewport;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    public Stage stage;
    private Skin skin;
    private Table table;
    private Table tableController;
    private TextButton btn1;
    private TextButton btn2;
    private TextButton btn3;
    private TextButton btn4;
    private TextButton btn5;
    private TextButton btn6;
    private TextButton btn7;
    private TextButton btn8;
    private TextButton btn9;
    private TextButton btnA;
    private TextButton btn0;
    private TextButton btnB;
    private TextButton btnMenu;
    private TextButton btnBack;
    private TextureAtlas atlasGraphics;

    private boolean enableDPad = true;
    private boolean enableMenu = true;
    private boolean enableBack = true;
    private boolean enableEnter = true;

    public PhoneController (GdxGame gdxGame, PhoneControllerInterface phoneControllerInterface,
                            boolean enableDPad, boolean enableMenu, boolean enableBack,
                            boolean enableEnter) {
        this.game = gdxGame;
        this.phoneControllerInterface = phoneControllerInterface;
        this.enableDPad = enableDPad;
        this.enableMenu = enableMenu;
        this.enableBack = enableBack;
        this.enableEnter = enableEnter;
    }

    public void show() {
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(
                Const.NOKIA_SCREEN_CONTROLLER_MIN,
                Const.NOKIA_SCREEN_CONTROLLER_MAX,
                camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        atlasGraphics = game.getAssetManager().get(Const.ASSETS_GRAPHICS_ATLAS);
        stage = new Stage(viewport);
        skin = Tools.getUISkin(game.getAssetManager());

        initializeUI();
    }

    private void initializeUI () {
        try {
            if (skin != null) {
                skin = null;
            }
            skin = Tools.getUISkin(game.getAssetManager());

            btn1 = new TextButton("1", skin, "phone");
            btn2 = new TextButton("2", skin, "phone"); // UP
            btn3 = new TextButton("3", skin, "phone");
            btn4 = new TextButton("4", skin, "phone"); // LEFT
            btn5 = new TextButton("5", skin, "phone"); // ENTER
            btn6 = new TextButton("6", skin, "phone"); // RIGHT
            btn7 = new TextButton("7", skin, "phone");
            btn8 = new TextButton("8", skin, "phone"); // DOWN
            btn9 = new TextButton("9", skin, "phone");
            btnA = new TextButton("*", skin, "phone");
            btn0 = new TextButton("0", skin, "phone");
            btnB = new TextButton("#", skin, "phone");
            btnMenu = new TextButton("MENU", skin, "phone"); // MENU
            btnBack = new TextButton("BACK", skin, "phone"); // BACK

            btn2.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    phoneControllerInterface.keyUp();
                }
            });

            btn4.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    phoneControllerInterface.keyLeft();
                }
            });

            btn5.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    phoneControllerInterface.keyEnter();
                }
            });

            btn6.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    phoneControllerInterface.keyRight();
                }
            });

            btn8.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    phoneControllerInterface.keyDown();
                }
            });

            btnMenu.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    phoneControllerInterface.keyMenu();
                }
            });

            btnBack.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    phoneControllerInterface.keyBack();
                }
            });

            if (!enableDPad) {
                btn2.setDisabled(true);
                btn4.setDisabled(true);
                btn6.setDisabled(true);
                btn8.setDisabled(true);

                btn2.setTouchable(Touchable.disabled);
                btn4.setTouchable(Touchable.disabled);
                btn6.setTouchable(Touchable.disabled);
                btn8.setTouchable(Touchable.disabled);
            }

            if (!enableEnter) {
                btn5.setDisabled(true);
                btn5.setTouchable(Touchable.disabled);
            }

            if (!enableMenu) {
                btnMenu.setDisabled(true);
                btnMenu.setTouchable(Touchable.disabled);
            }

            if (!enableBack) {
                btnBack.setDisabled(true);
                btnBack.setTouchable(Touchable.disabled);
            }

            btn1.setDisabled(true);
            btn1.setTouchable(Touchable.disabled);
            btn3.setDisabled(true);
            btn3.setTouchable(Touchable.disabled);
            btn7.setDisabled(true);
            btn7.setTouchable(Touchable.disabled);
            btn9.setDisabled(true);
            btn9.setTouchable(Touchable.disabled);
            btnA.setDisabled(true);
            btnA.setTouchable(Touchable.disabled);
            btn0.setDisabled(true);
            btn0.setTouchable(Touchable.disabled);
            btnB.setDisabled(true);
            btnB.setTouchable(Touchable.disabled);

            table = new Table();
            table.add(btnMenu).width(Value.percentWidth(0.33f, table)).height(Value.percentWidth(0.18f, table)).padBottom(Const.PADDING);
            table.add().width(Value.percentWidth(0.33f, table)).height(Value.percentWidth(0.18f, table)).padBottom(Const.PADDING);
            table.add(btnBack).width(Value.percentWidth(0.33f, table)).height(Value.percentWidth(0.18f, table)).padBottom(Const.PADDING);
            table.row();
            table.add(btn1).width(Value.percentWidth(0.33f, table)).height(Value.percentWidth(0.18f, table));
            table.add(btn2).width(Value.percentWidth(0.33f, table)).height(Value.percentWidth(0.18f, table));
            table.add(btn3).width(Value.percentWidth(0.33f, table)).height(Value.percentWidth(0.18f, table));
            table.row();
            table.add(btn4).width(Value.percentWidth(0.33f, table)).height(Value.percentWidth(0.18f, table));
            table.add(btn5).width(Value.percentWidth(0.33f, table)).height(Value.percentWidth(0.18f, table));
            table.add(btn6).width(Value.percentWidth(0.33f, table)).height(Value.percentWidth(0.18f, table));
            table.row();
            table.add(btn7).width(Value.percentWidth(0.33f, table)).height(Value.percentWidth(0.18f, table));
            table.add(btn8).width(Value.percentWidth(0.33f, table)).height(Value.percentWidth(0.18f, table));
            table.add(btn9).width(Value.percentWidth(0.33f, table)).height(Value.percentWidth(0.18f, table));
            table.row();
            table.add(btnA).width(Value.percentWidth(0.33f, table)).height(Value.percentWidth(0.18f, table));
            table.add(btn0).width(Value.percentWidth(0.33f, table)).height(Value.percentWidth(0.18f, table));
            table.add(btnB).width(Value.percentWidth(0.33f, table)).height(Value.percentWidth(0.18f, table));
            //table.row().pad(Const.PADDING);

            tableController =new Table();
            tableController.setFillParent(true);
            tableController.setDebug(false);
            tableController.add(table).expand().fill();
            tableController.row();
            tableController.row().pad(Const.PADDING);

            stage.addActor(tableController);
        } catch (Exception e) {

        }
    }

    public void render(float delta) {
        camera.update();



        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.DPAD_UP) || Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_8)) {
            phoneControllerInterface.keyUp();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN) || Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_2)) {
            phoneControllerInterface.keyDown();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT) || Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_4)) {
            phoneControllerInterface.keyLeft();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT) || Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_6)) {
            phoneControllerInterface.keyRight();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_5)) {
            phoneControllerInterface.keyEnter();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.B) || Gdx.input.isKeyPressed(Input.Keys.BACK) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_9)) {
            phoneControllerInterface.keyBack();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.M) || Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isKeyPressed(Input.Keys.NUMPAD_7)) {
            phoneControllerInterface.keyMenu();
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
