package com.ineriam.games.vanegas.gamejam.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.ineriam.games.vanegas.gamejam.GdxGame;
import com.ineriam.games.vanegas.gamejam.actors.Actor;
import com.ineriam.games.vanegas.gamejam.actors.Door;
import com.ineriam.games.vanegas.gamejam.actors.Health;
import com.ineriam.games.vanegas.gamejam.actors.Rain;
import com.ineriam.games.vanegas.gamejam.actors.Spark;
import com.ineriam.games.vanegas.gamejam.data.Const;

public class WorldRenderer {
    final GdxGame game;
    private World world;
    private TextureAtlas atlasGraphics;

    private ExtendViewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;

    private TextureRegion textureRegionBackground;
    private TextureRegion textureRegionTileBlock;
    private TextureRegion textureRegionPlayer;
    private TextureRegion textureRegionDoor;

    private TextureRegion textureRegionTileGrass;
    private TextureRegion textureRegionTileRock;
    private TextureRegion textureRegionTileFence;
    private TextureRegion textureRegionTileBones;
    private TextureRegion textureRegionTilePortal;

    private TextureRegion textureRegionLokoblin;
    private TextureRegion textureRegionCoblin;
    private TextureRegion textureRegionZalfo;
    private TextureRegion textureRegionBlizz;
    private TextureRegion textureRegionCenta;
    private TextureRegion textureRegionGuardian;

    private TextureRegion textureRegionHeart;
    private TextureRegion textureRegionSword;

    private TextureRegion textureRegionLvl1;
    private TextureRegion textureRegionLvl2;
    private TextureRegion textureRegionLvl3;
    private TextureRegion textureRegionLvl4;
    private TextureRegion textureRegionLvl5;
    private TextureRegion textureRegionLvl6;
    private TextureRegion textureRegionLvl7;
    private TextureRegion textureRegionLvl8;
    private TextureRegion textureRegionLvl9;

    private TextureRegion textureRegionHealth;

    private TextureRegion textureRegionRain;

    private TextureRegion regionGoldSpark;

    private Animation<TextureRegion> goldSparkAnimation;

    private Vector3 lerpCamera = new Vector3();

    public WorldRenderer (GdxGame gdxGame, World world) {
        this.game = gdxGame;
        this.world = world;

        atlasGraphics = game.getAssetManager().get(Const.ASSETS_GRAPHICS_ATLAS);

        float minCam = 6;  // 10
        float maxCam = minCam * 2.5f;

        camera = new OrthographicCamera();

        viewport = new ExtendViewport(minCam, minCam,
                maxCam, maxCam, camera);

        viewport.apply();

        camera.position.set(world.player.x,world.player.y,0);
        camera.update();

        spriteBatch = new SpriteBatch();

        loadResources();
    }

    private void loadResources () {
        textureRegionBackground = new TextureRegion(atlasGraphics.findRegion("background"));
        textureRegionTileBlock = new TextureRegion(atlasGraphics.findRegion("tile_block"));
        textureRegionPlayer = new TextureRegion(atlasGraphics.findRegion("player"));
        textureRegionDoor = new TextureRegion(atlasGraphics.findRegion("door"));

        textureRegionTileGrass = new TextureRegion(atlasGraphics.findRegion("grass"));
        textureRegionTileRock = new TextureRegion(atlasGraphics.findRegion("rock"));
        textureRegionTileFence = new TextureRegion(atlasGraphics.findRegion("fence"));
        textureRegionTileBones = new TextureRegion(atlasGraphics.findRegion("navy"));
        textureRegionTilePortal = new TextureRegion(atlasGraphics.findRegion("portal"));

        textureRegionLokoblin = new TextureRegion(atlasGraphics.findRegion("lokoblin"));
        textureRegionCoblin = new TextureRegion(atlasGraphics.findRegion("coblin"));
        textureRegionZalfo = new TextureRegion(atlasGraphics.findRegion("zalfo"));
        textureRegionBlizz = new TextureRegion(atlasGraphics.findRegion("blizz"));
        textureRegionCenta = new TextureRegion(atlasGraphics.findRegion("centa"));
        textureRegionGuardian = new TextureRegion(atlasGraphics.findRegion("guardian"));

        textureRegionHealth = new TextureRegion(atlasGraphics.findRegion("health"));

        textureRegionHeart = new TextureRegion(atlasGraphics.findRegion("heart"));
        textureRegionSword = new TextureRegion(atlasGraphics.findRegion("sword"));

        textureRegionLvl1 = new TextureRegion(atlasGraphics.findRegion("lvl_a"));
        textureRegionLvl2 = new TextureRegion(atlasGraphics.findRegion("lvl_b"));
        textureRegionLvl3 = new TextureRegion(atlasGraphics.findRegion("lvl_c"));
        textureRegionLvl4 = new TextureRegion(atlasGraphics.findRegion("lvl_d"));
        textureRegionLvl5 = new TextureRegion(atlasGraphics.findRegion("lvl_e"));
        textureRegionLvl6 = new TextureRegion(atlasGraphics.findRegion("lvl_f"));
        textureRegionLvl7 = new TextureRegion(atlasGraphics.findRegion("lvl_g"));
        textureRegionLvl8 = new TextureRegion(atlasGraphics.findRegion("lvl_h"));
        textureRegionLvl9 = new TextureRegion(atlasGraphics.findRegion("lvl_i"));

        textureRegionRain = new TextureRegion(atlasGraphics.findRegion("bar"));

        goldSparkAnimation();

    }

    private void goldSparkAnimation () {
        try {
            regionGoldSpark = new TextureRegion(atlasGraphics.findRegion("sparks"));
            TextureRegion[] expl = new TextureRegion(regionGoldSpark).split(8, 8)[0];
            goldSparkAnimation = new Animation(0.05f, expl[0], expl[1], expl[2], expl[1]);
        } catch (Exception e) {

        }
    }

    public void update (float delta) {
        camera.position.lerp(lerpCamera.set(
                world.player.x - (Const.ACTOR_SIZE / 2),
                world.player.y - (Const.ACTOR_SIZE / 2),
                0),
                Const.LERP_FACTOR * delta);



        camera.position.x = MathUtils.clamp(camera.position.x, camera.viewportWidth / 2, Const.MAP_COLS - (camera.viewportWidth / 2));
        camera.position.y = MathUtils.clamp(camera.position.y, camera.viewportHeight / 2, Const.MAP_ROWS - (camera.viewportHeight / 2));
        camera.update();

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        renderBackground();
        renderMap();
        renderDoors();
        renderHealth();
        renderActors();
        renderGoldSpark();
        renderRain();
        spriteBatch.end();
    }

    private void renderBackground () {
        spriteBatch.draw(textureRegionBackground, 0, 0, Const.TILE_SIZE * Const.MAP_COLS, Const.TILE_SIZE * Const.MAP_ROWS);
    }

    private void renderMap () {
        try {
            for (int y = 0; y < Const.MAP_ROWS; y++) {
                for (int x = 0; x < Const.MAP_COLS; x++) {
                    if (world.map[x][y] != null) {
                        if (world.map[x][y].type == Const.TILE_BLOCK) {

                            if (world.playerWorldMapPosition.x == 0 || world.playerWorldMapPosition.x == 1) {
                                // Grass world
                                spriteBatch.draw(textureRegionTileGrass, x, y, Const.TILE_SIZE, Const.TILE_SIZE);
                            } else if (world.playerWorldMapPosition.x == 2 || world.playerWorldMapPosition.x == 3) {
                                // Rock world
                                spriteBatch.draw(textureRegionTileRock, x, y, Const.TILE_SIZE, Const.TILE_SIZE);
                            } else if (world.playerWorldMapPosition.x == 4 || world.playerWorldMapPosition.x == 5) {
                                // Metal world
                                spriteBatch.draw(textureRegionTileFence, x, y, Const.TILE_SIZE, Const.TILE_SIZE);
                            } else if (world.playerWorldMapPosition.x == 6 || world.playerWorldMapPosition.x == 7) {
                                // Bones world
                                spriteBatch.draw(textureRegionTileBones, x, y, Const.TILE_SIZE, Const.TILE_SIZE);
                            } else if (world.playerWorldMapPosition.x == 8 || world.playerWorldMapPosition.x == 9) {
                                // Portal world
                                spriteBatch.draw(textureRegionTilePortal, x, y, Const.TILE_SIZE, Const.TILE_SIZE);
                            } else {
                                spriteBatch.draw(textureRegionTileBlock, x, y, Const.TILE_SIZE, Const.TILE_SIZE);
                            }


                        }
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    private void renderActors () {
        try {
            for (Actor actor : world.actorList) {
                if (actor.getType() == Const.TYPE_PLAYER) {
                    spriteBatch.draw(textureRegionPlayer, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                } else if (actor.getType() == Const.TYPE_MONSTER) {
                    switch (actor.state) {
                        case MONSTER:
                        case MONSTER_TWO:
                        case MONSTER_END:

                            switch (actor.level) {
                                case 1:
                                    spriteBatch.draw(textureRegionLokoblin, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                                    break;
                                case 2:
                                    spriteBatch.draw(textureRegionCoblin, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                                    break;
                                case 3:
                                    spriteBatch.draw(textureRegionZalfo, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                                    break;
                                case 4:
                                    spriteBatch.draw(textureRegionBlizz, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                                    break;
                                case 5:
                                    spriteBatch.draw(textureRegionCenta, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                                    break;
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                    spriteBatch.draw(textureRegionGuardian, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                                    break;
                            }



                            break;
                        case HEART:
                            spriteBatch.draw(textureRegionHeart, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                            break;
                        case HEART_VALUE:
                        case ATTACK_VALUE:
                            switch (actor.hp) {
                                case 1:
                                    spriteBatch.draw(textureRegionLvl1, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                                    break;
                                case 2:
                                    spriteBatch.draw(textureRegionLvl2, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                                    break;
                                case 3:
                                    spriteBatch.draw(textureRegionLvl3, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                                    break;
                                case 4:
                                    spriteBatch.draw(textureRegionLvl4, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                                    break;
                                case 5:
                                    spriteBatch.draw(textureRegionLvl5, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                                    break;
                                case 6:
                                    spriteBatch.draw(textureRegionLvl6, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                                    break;
                                case 7:
                                    spriteBatch.draw(textureRegionLvl7, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                                    break;
                                case 8:
                                    spriteBatch.draw(textureRegionLvl8, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                                    break;
                                case 9:
                                    spriteBatch.draw(textureRegionLvl9, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                                    break;
                            }
                            break;
                        case ATTACK:
                            spriteBatch.draw(textureRegionSword, actor.x, actor.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                            break;
                    }

                }
            }
        } catch (Exception e) {

        }
    }

    private void renderDoors () {
        try {
            for (Door door : world.doors) {
                if (!door.isOpen) {
                    spriteBatch.draw(textureRegionDoor, door.x, door.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
                }
            }
        } catch (Exception e) {

        }
    }

    private void renderHealth () {
        try {
            if (world.health.state == Health.VISIBLE) {
                spriteBatch.draw(textureRegionHealth, world.health.bounds.x, world.health.bounds.y, Const.ACTOR_SIZE, Const.ACTOR_SIZE);
            }
        } catch (Exception e) {

        }
    }

    private void renderGoldSpark () {
        if (world.goldSpark.state == Spark.VISIBLE) {
            TextureRegion frame = this.goldSparkAnimation.getKeyFrame(world.goldSpark.stateTime, false);
            spriteBatch.draw(frame, world.goldSpark.bounds.x, world.goldSpark.bounds.y, world.goldSpark.bounds.width, world.goldSpark.bounds.height);
        }
    }

    private void renderRain () {
        try {

            for (Rain rain : game.rainSystem.rainArrayList) {
                spriteBatch.draw(textureRegionRain, rain.x, rain.y, Const.RAIN_SIZE_WIDTH, Const.RAIN_SIZE_HEIGHT);
            }

        } catch (Exception e) {

        }
    }

    public void resize (int width, int height) {
        viewport.update(width, height);
        //camera.position.set(camera.viewportWidth / 2,camera.viewportHeight / 2,0);
        //camera.update();
    }
}
