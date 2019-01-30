package com.ineriam.games.vanegas.gamejam.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ineriam.games.vanegas.gamejam.GdxGame;
import com.ineriam.games.vanegas.gamejam.actors.Actor;
import com.ineriam.games.vanegas.gamejam.actors.Door;
import com.ineriam.games.vanegas.gamejam.actors.Health;
import com.ineriam.games.vanegas.gamejam.actors.Spark;
import com.ineriam.games.vanegas.gamejam.actors.Tile;
import com.ineriam.games.vanegas.gamejam.data.Const;
import com.ineriam.games.vanegas.gamejam.screens.GameOverScreen;
import com.ineriam.games.vanegas.gamejam.screens.GameScreen;
import com.ineriam.games.vanegas.gamejam.utils.Tools;

import java.util.ArrayList;
import java.util.HashMap;

public class World {
    final GdxGame game;
    private TextureAtlas atlasGraphics;

    public ArrayList<Actor> actorList = new ArrayList<Actor>();
    public HashMap<String, Actor> actorMap = new HashMap<String, Actor>();
    public Actor player;

    public Spark goldSpark;
    public Health health;

    public ArrayList<Door> doors = new ArrayList<Door>();

    public Tile[][] map;
    public String[][] worldMap;

    public Vector2 playerWorldMapPosition;

    private boolean isDoorUpOpen = false;
    private boolean isDoorDownOpen = false;
    private boolean isDoorLeftOpen = false;
    private boolean isDoorRightOpen = false;

    public int playerLevel = 0;
    public int playerHp = 0;
    public int playerCurrentExperience = 0;

    private boolean canMove = true;
    private boolean prepareGameOver = false;
    public boolean gameOver = false;
    private float stateTime = 0;

    public int livingEnemies;

    public World (GdxGame gdxGame) {
        this.game = gdxGame;
        atlasGraphics = game.getAssetManager().get(Const.ASSETS_GRAPHICS_ATLAS);

        initWorldMap();
    }

    private void initWorldMap () {
        try {
            worldMap = new String[Const.WORLD_MAP_COLS][Const.WORLD_MAP_ROWS];

            int worldX;
            int worldY;

            for (int y = 0; y < Const.WORLD_MAP_ROWS; y++) {
                for (int x = 0; x < Const.WORLD_MAP_COLS; x++) {
                    worldX = x;
                    worldY = Const.WORLD_MAP_ROWS - 1 - y;

                    worldMap[worldX][worldY] = Const.KEYS[worldX] + "_" + Const.KEYS[worldY];
                }
            }

            playerLevel = Tools.getPlayerLevel();
            playerHp = Tools.getPlayerCurrentHp(playerLevel);
            playerCurrentExperience = Tools.getPlayerExp();

            playerWorldMapPosition = new Vector2();
            playerWorldMapPosition = Tools.getPlayerWorldMapPosition();

            isDoorUpOpen = Tools.getLevelDoors(playerWorldMapPosition, Const.ID_DOOR_UP);
            isDoorDownOpen = Tools.getLevelDoors(playerWorldMapPosition, Const.ID_DOOR_DOWN);
            isDoorLeftOpen = Tools.getLevelDoors(playerWorldMapPosition, Const.ID_DOOR_LEFT);
            isDoorRightOpen = Tools.getLevelDoors(playerWorldMapPosition, Const.ID_DOOR_RIGHT);

            Vector2 playerPosition = Tools.getPlayerNewSpawnPosition();
            Actor actor = new Actor((int) playerPosition.x, (int) playerPosition.y, Const.TYPE_PLAYER, playerHp, playerLevel, 0, Const.ID_ACTOR);
            actorMap.put(actor.x + "_" + actor.y, actor);
            actorList.add(actor);

            player = actorList.get(0);



            loadMap((int) playerWorldMapPosition.x, (int) playerWorldMapPosition.y);
        } catch (Exception e) {

        }
    }

    private void loadMap (int px, int py) {
        try {

            String mapName = Const.ASSETS_GRAPHICS_MAPS + worldMap[px][py] + Const.ASSETS_GRAPHICS_MAPS_EXT;

            Pixmap pixmap = new Pixmap(Gdx.files.internal(mapName));

            map = new Tile[Const.MAP_COLS][Const.MAP_ROWS];

            int pixel;
            int tileX;
            int tileY;

            for (int y = 0; y < Const.MAP_ROWS; y++) {
                for (int x = 0; x < Const.MAP_COLS; x++) {
                    pixel = (pixmap.getPixel(x, y) >>> 8) & 0xffffff;

                    tileX = x;
                    tileY = Const.MAP_ROWS - 1 - y;

                    // Actors
                    if (Tools.match(pixel, Const.LOKOBLIN_COLOR)) {
                        Actor lokoblin = new Actor(tileX, tileY, Const.TYPE_MONSTER, Const.LOKOBLIN_LEVEL, Const.LOKOBLIN_LEVEL, Const.LOKOBLIN_VISION_DISTANCE, Const.ID_LOKOBLIN);
                        actorMap.put(lokoblin.x + "_" + lokoblin.y, lokoblin);
                        actorList.add(lokoblin);
                        livingEnemies++;
                    }

                    if (Tools.match(pixel, Const.COBLIN_COLOR)) {
                        Actor coblin = new Actor(tileX, tileY, Const.TYPE_MONSTER, Const.COBLIN_LEVEL, Const.COBLIN_LEVEL, Const.COBLIN_VISION_DISTANCE, Const.ID_COBLIN);
                        actorMap.put(coblin.x + "_" + coblin.y, coblin);
                        actorList.add(coblin);
                        livingEnemies++;
                    }

                    if (Tools.match(pixel, Const.ZALFO_COLOR)) {
                        Actor zalfo = new Actor(tileX, tileY, Const.TYPE_MONSTER, Const.ZALFO_LEVEL, Const.ZALFO_LEVEL, Const.ZALFO_VISION_DISTANCE, Const.ID_ZALFO);
                        actorMap.put(zalfo.x + "_" + zalfo.y, zalfo);
                        actorList.add(zalfo);
                        livingEnemies++;
                    }

                    if (Tools.match(pixel, Const.BLIZZ_COLOR)) {
                        Actor blizz = new Actor(tileX, tileY, Const.TYPE_MONSTER, Const.BLIZZ_LEVEL, Const.BLIZZ_LEVEL, Const.BLIZZ_VISION_DISTANCE, Const.ID_BLIZZ);
                        actorMap.put(blizz.x + "_" + blizz.y, blizz);
                        actorList.add(blizz);
                        livingEnemies++;
                    }

                    if (Tools.match(pixel, Const.CENTA_COLOR)) {
                        Actor centa = new Actor(tileX, tileY, Const.TYPE_MONSTER, Const.CENTA_LEVEL, Const.CENTA_LEVEL, Const.CENTA_VISION_DISTANCE, Const.ID_CENTA);
                        actorMap.put(centa.x + "_" + centa.y, centa);
                        actorList.add(centa);
                        livingEnemies++;
                    }

                    if (Tools.match(pixel, Const.GUARDIAN_I_COLOR)) {
                        if (!Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_I_COMPLETE)) {
                            Actor guardianI = new Actor(tileX, tileY, Const.TYPE_MONSTER, Const.GUARDIAN_I_LEVEL, Const.GUARDIAN_I_LEVEL, Const.GUARDIAN_VISION_DISTANCE, Const.ID_GUARDIAN_I);
                            actorMap.put(guardianI.x + "_" + guardianI.y, guardianI);
                            actorList.add(guardianI);
                            livingEnemies++;
                        }
                    }

                    if (Tools.match(pixel, Const.GUARDIAN_II_COLOR)) {
                        if (!Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_II_COMPLETE)) {
                            Actor guardianII = new Actor(tileX, tileY, Const.TYPE_MONSTER, Const.GUARDIAN_II_LEVEL, Const.GUARDIAN_II_LEVEL, Const.GUARDIAN_VISION_DISTANCE, Const.ID_GUARDIAN_II);
                            actorMap.put(guardianII.x + "_" + guardianII.y, guardianII);
                            actorList.add(guardianII);
                            livingEnemies++;
                        }
                    }

                    if (Tools.match(pixel, Const.GUARDIAN_III_COLOR)) {
                        if (!Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_III_COMPLETE)) {
                            Actor guardianIII = new Actor(tileX, tileY, Const.TYPE_MONSTER, Const.GUARDIAN_III_LEVEL, Const.GUARDIAN_III_LEVEL, Const.GUARDIAN_VISION_DISTANCE, Const.ID_GUARDIAN_III);
                            actorMap.put(guardianIII.x + "_" + guardianIII.y, guardianIII);
                            actorList.add(guardianIII);
                            livingEnemies++;
                        }
                    }

                    if (Tools.match(pixel, Const.GUARDIAN_IV_COLOR)) {
                        if (!Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_IV_COMPLETE)) {
                            Actor guardianIV = new Actor(tileX, tileY, Const.TYPE_MONSTER, Const.GUARDIAN_IV_LEVEL, Const.GUARDIAN_IV_LEVEL, Const.GUARDIAN_VISION_DISTANCE, Const.ID_GUARDIAN_IV);
                            actorMap.put(guardianIV.x + "_" + guardianIV.y, guardianIV);
                            actorList.add(guardianIV);
                            livingEnemies++;
                        }
                    }

                    if (Tools.match(pixel, Const.GUARDIAN_V_COLOR)) {
                        if (!Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_V_COMPLETE)) {
                            Actor guardianV = new Actor(tileX, tileY, Const.TYPE_MONSTER, Const.GUARDIAN_V_LEVEL, Const.GUARDIAN_V_LEVEL, Const.GUARDIAN_VISION_DISTANCE, Const.ID_GUARDIAN_V);
                            actorMap.put(guardianV.x + "_" + guardianV.y, guardianV);
                            actorList.add(guardianV);
                            livingEnemies++;
                        }
                    }

                    if (Tools.match(pixel, Const.GUARDIAN_VI_COLOR)) {
                        if (!Tools.getGuardianComplete(Const.STORAGE_GUARDIAN_VI_COMPLETE)) {
                            Actor guardianVI = new Actor(tileX, tileY, Const.TYPE_MONSTER, Const.GUARDIAN_VI_LEVEL, Const.GUARDIAN_VI_LEVEL, Const.GUARDIAN_VISION_DISTANCE, Const.ID_GUARDIAN_VI);
                            actorMap.put(guardianVI.x + "_" + guardianVI.y, guardianVI);
                            actorList.add(guardianVI);
                            livingEnemies++;
                        }
                    }

                    // Doors
                    if (Tools.match(pixel, Const.TILE_COLOR_DOOR_UP)) {
                        doors.add(new Door(tileX, tileY, Const.DOOR_DIR_UP, isDoorUpOpen));
                    } else if (Tools.match(pixel, Const.TILE_COLOR_DOOR_DOWN)) {
                        doors.add(new Door(tileX, tileY, Const.DOOR_DIR_DOWN, isDoorDownOpen));
                    } else if (Tools.match(pixel, Const.TILE_COLOR_DOOR_LEFT)) {
                        doors.add(new Door(tileX, tileY, Const.DOOR_DIR_LEFT, isDoorLeftOpen));
                    } else if (Tools.match(pixel, Const.TILE_COLOR_DOOR_RIGHT)) {
                        doors.add(new Door(tileX, tileY, Const.DOOR_DIR_RIGHT, isDoorRightOpen));
                    }

                    if (Tools.match(pixel, Const.TILE_COLOR_BLOCK)) {
                        map[tileX][tileY] = new Tile(x, y, Const.TILE_BLOCK, true);
                    } else {
                        map[tileX][tileY] = new Tile(x, y, Const.TILE_AIR, false);
                    }
                }
            }

            pixmap.dispose();


            goldSpark = new Spark();
            health = new Health();

        } catch (Exception e) {

        }
    }

    public void update (float delta) {
        if (prepareGameOver) {
            stateTime += delta;
            if (stateTime > Const.TIME_GAME_OVER) {
                if (!gameOver) {
                    gameOver = true;
                    game.setScreen(new GameOverScreen(game));
                }
            }

        } else {
            for (Actor actor : actorList) {
                if (actor.getType() == Const.TYPE_MONSTER) {
                    actor.updateMonster(delta);
                }
            }
        }

        goldSpark.update(delta);
        health.update(delta);

        if (health.state == Health.VISIBLE) {
            if (player.x == health.bounds.x && player.y == health.bounds.y) {
                health.state = Health.HIDDEN;
                player.hp++;
                if (player.hp > player.level) {
                    player.hp = player.level;
                }
            }
        }

    }

    private boolean canGo (Actor actor, Vector2 dir) {
        try {
            return map[(int)(actor.x + dir.x)][(int)(actor.y + dir.y)].solid == false &&
                    collideWithDoor(actor, dir);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean collideWithDoor (Actor actor, Vector2 dir) {
        try {

            int x = (int)(actor.x + dir.x);
            int y = (int)(actor.y + dir.y);

            for (int i = 0; i < doors.size(); i++) {
                if (doors.get(i).x == x && doors.get(i).y == y) {
                    if (doors.get(i).isOpen) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean moveTo (Actor actor, Vector2 dir) {
        try {
            if (!canGo(actor, dir)) {
                return false;
            }

            String newKey = (int)(actor.x + dir.x) + "_" + (int)(actor.y + dir.y);

            if (actorMap.get(newKey) != null) {
                Actor victim = actorMap.get(newKey);

                if (actor.type == Const.TYPE_PLAYER && victim.type == Const.TYPE_MONSTER) {
                    // Player hit monster

                    goldSpark.hit(victim.x, victim.y);


                    if (playerLevel < Const.PLAYER_LEVELS[Const.PLAYER_LEVELS.length - 1].levelId) {
                        // Add exp
                        playerCurrentExperience += Const.MONSTER_LEVELS[victim.level].hp;

                        if (playerCurrentExperience >= Const.PLAYER_LEVELS[playerLevel + 1].hp) {
                            playerLevel++;

                            Tools.saveNewPlayerLevel(playerLevel);
                            Tools.savePlayerCurrentHp(playerLevel);
                            player.hp = playerLevel;

                            playerCurrentExperience = 0;
                            Tools.savePlayerExp(playerCurrentExperience);
                        }
                    } else if (playerLevel > Const.PLAYER_LEVELS[Const.PLAYER_LEVELS.length - 1].levelId) {
                        playerLevel = Const.PLAYER_LEVELS[Const.PLAYER_LEVELS.length - 1].levelId;
                        playerCurrentExperience = Const.PLAYER_LEVELS[Const.PLAYER_LEVELS.length - 1].hp;
                        player.hp = playerLevel;
                        Tools.saveNewPlayerLevel(playerLevel);
                        Tools.savePlayerCurrentHp(playerLevel);
                        Tools.savePlayerExp(playerCurrentExperience);
                    }

                    /*
                    if (playerCurrentExperience >= Const.PLAYER_LEVELS[playerLevel + 1].hp) {
                        playerLevel++;
                        if (playerLevel > Const.PLAYER_LEVELS[Const.PLAYER_LEVELS.length - 1].levelId) {
                            playerLevel = Const.PLAYER_LEVELS[Const.PLAYER_LEVELS.length - 1].levelId;
                            playerCurrentExperience = 0;
                            Tools.savePlayerExp(playerCurrentExperience);
                        } else {
                            Tools.savePlayerExp(playerCurrentExperience);
                        }

                        Tools.saveNewPlayerLevel(playerLevel);
                        Tools.savePlayerCurrentHp(playerLevel);
                        player.hp = playerLevel;

                        playerCurrentExperience = 0;
                        Tools.savePlayerExp(playerCurrentExperience);
                    }
                    */

                    // Hit monster
                    victim.hp = victim.hp - Const.PLAYER_LEVELS[playerLevel].attack;
                    if (victim.hp <= 0) {
                        victim.hp = 0;

                        if (MathUtils.random() > Const.HEALTH_RANDOM_DROP) {
                            health.hit(victim.x, victim.y);
                        }


                        if (victim.actorId == Const.ID_GUARDIAN_I) {
                            Tools.saveGuardianComplete(Const.STORAGE_GUARDIAN_I_COMPLETE, true);
                        }

                        if (victim.actorId == Const.ID_GUARDIAN_II) {
                            Tools.saveGuardianComplete(Const.STORAGE_GUARDIAN_II_COMPLETE, true);
                        }

                        if (victim.actorId == Const.ID_GUARDIAN_III) {
                            Tools.saveGuardianComplete(Const.STORAGE_GUARDIAN_III_COMPLETE, true);
                        }

                        if (victim.actorId == Const.ID_GUARDIAN_IV) {
                            Tools.saveGuardianComplete(Const.STORAGE_GUARDIAN_IV_COMPLETE, true);
                        }

                        if (victim.actorId == Const.ID_GUARDIAN_V) {
                            Tools.saveGuardianComplete(Const.STORAGE_GUARDIAN_V_COMPLETE, true);
                        }

                        if (victim.actorId == Const.ID_GUARDIAN_VI) {
                            Tools.saveGuardianComplete(Const.STORAGE_GUARDIAN_VI_COMPLETE, true);
                        }

                        actorMap.remove(newKey);
                        actorList.remove(actorList.indexOf(victim));

                        livingEnemies--;
                        if (livingEnemies <= 0) {
                            if (!isDoorUpOpen) {
                                Tools.saveLevelDoors(playerWorldMapPosition, Const.ID_DOOR_UP,true);
                            }

                            if (!isDoorDownOpen) {
                                Tools.saveLevelDoors(playerWorldMapPosition, Const.ID_DOOR_DOWN,true);
                            }

                            if (!isDoorLeftOpen) {
                                Tools.saveLevelDoors(playerWorldMapPosition, Const.ID_DOOR_LEFT,true);
                            }

                            if (!isDoorRightOpen) {
                                Tools.saveLevelDoors(playerWorldMapPosition, Const.ID_DOOR_RIGHT,true);
                            }

                            openDoors();
                        }
                    }
                }

                if (actor.type == Const.TYPE_MONSTER && victim.type == Const.TYPE_PLAYER) {
                    // Monster hit player, rest player hp
                    goldSpark.hit(player.x, player.y);

                    player.hp = player.hp - Const.MONSTER_LEVELS[actor.level].attack;

                    if (player.hp <= 0) {
                        player.hp = 0;
                        actorMap.remove(player);
                        actorList.remove(actorList.indexOf(player));
                        Tools.savePlayerCurrentHp(playerLevel);
                        canMove = false;
                        prepareGameOver = true;
                    } else {
                        Tools.savePlayerCurrentHp(player.hp);
                    }
                }

            } else {
                actorMap.remove(actor.x + "_" + actor.y);

                actor.x += dir.x;
                actor.y += dir.y;

                actorMap.put(actor.x + "_" + actor.y, actor);

                if (actor.getType() == Const.TYPE_PLAYER) {
                    for (Door door : doors) {
                        if (door.x == actor.x && door.y == actor.y) {
                            nextMap(door);
                            break;

                        }
                    }
                }

            }

            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public void onKeyUp (Vector2 direction) {
        try {

            if (!canMove) {
                return;
            }

            /*
            if (gameOver) {
                return;
            }
            */
            boolean acted = moveTo(player, direction);

            if (acted) {
                for (Actor enemy : actorList) {
                    if (enemy != null) {
                        if (enemy.type == Const.TYPE_MONSTER) {
                            aiAct(enemy);
                        }
                    }
                }
            }

        } catch (Exception e) {
        }
    }

    private void nextMap (Door door) {
        try {
            int nextX = (int) playerWorldMapPosition.x;
            int nextY = (int) playerWorldMapPosition.y;

            Vector2 newSpawn = new Vector2();
            String doorKey = "";

            switch (door.direction) {
                case Const.DOOR_DIR_UP:
                    nextY++;
                    newSpawn = Const.PLAYER_SPAWN_DOWN;
                    doorKey = Const.ID_DOOR_DOWN;

                    break;
                case Const.DOOR_DIR_DOWN:
                    nextY--;
                    newSpawn = Const.PLAYER_SPAWN_UP;
                    doorKey = Const.ID_DOOR_UP;

                    break;
                case Const.DOOR_DIR_LEFT:
                    nextX--;
                    newSpawn = Const.PLAYER_SPAWN_RIGHT;
                    doorKey = Const.ID_DOOR_RIGHT;

                    break;
                case Const.DOOR_DIR_RIGHT:
                    nextX++;
                    newSpawn = Const.PLAYER_SPAWN_LEFT;
                    doorKey = Const.ID_DOOR_LEFT;

                    break;
            }

            nextX = MathUtils.clamp(nextX, 0, (Const.WORLD_MAP_COLS - 1));
            nextY = MathUtils.clamp(nextY, 0, (Const.WORLD_MAP_ROWS - 1));


            Tools.savePlayerWorldMapPosition(new Vector2(nextX, nextY));
            Tools.savePlayerNewSpawnPosition(newSpawn);
            Tools.saveLevelDoors(new Vector2(nextX, nextY), doorKey, true);

            game.setScreen(new GameScreen(game));
        } catch (Exception e) {

        }
    }

    private void aiAct (Actor actor) {
        try {
            Vector2[] directions =  {
                    new Vector2(-1, 0),
                    new Vector2(1, 0),
                    new Vector2(0, 1),
                    new Vector2(0, -1)
            };

            int dx;
            int dy;

            dx = player.x - actor.x;
            dy = player.y - actor.y;

            if (Math.abs(dx) + Math.abs(dy) > actor.visionDistance)
                while (!moveTo(actor, directions[MathUtils.random(0, 3)]));

            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx < 0) {
                    // left
                    moveTo(actor, directions[0]);
                } else {
                    // right
                    moveTo(actor, directions[1]);
                }
            } else {
                if (dy > 0) {
                    // up
                    moveTo(actor, directions[2]);
                } else {
                    // down
                    moveTo(actor, directions[3]);
                }
            }

            if (player.hp < 1) {
                // gameOver = true;
            }
        } catch (Exception e) {

        }
    }

    private void openDoors () {
        try {
            for (int i = 0; i < doors.size(); i++) {
                doors.get(i).setOpen(true);
            }
        } catch (Exception e) {

        }
    }
}

