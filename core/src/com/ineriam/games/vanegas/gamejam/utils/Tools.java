package com.ineriam.games.vanegas.gamejam.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.ineriam.games.vanegas.gamejam.data.Const;

public class Tools {
    public static void renderRectangle (ShapeRenderer shapeRenderer, Rectangle rectangle) {
        shapeRenderer.rect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(),
                rectangle.getHeight());
    }

    public static Color toRGB(int r, int g, int b) {
        float RED = r / 255.0f;
        float GREEN = g / 255.0f;
        float BLUE = b / 255.0f;
        return new Color(RED, GREEN, BLUE, 1);
    }

    public static Skin getUISkin (AssetManager assetManager) {
        try {
            TextureAtlas textureAtlas = assetManager.get(Const.ASSETS_GRAPHICS_ATLAS);
            Skin skin = new Skin();
            skin.addRegions(textureAtlas);
            skin.add("default-font", assetManager.get(Const.DEFAULT_FONT), BitmapFont.class);
            skin.load(Gdx.files.internal(Const.ASSETS_GRAPHICS_JSON));
            return skin;
        } catch (Exception e) {
            return null;
        }
    }

    public static void savePlayerWorldMapPosition (Vector2 position) {
        try {
            Preferences preferences = Gdx.app.getPreferences(Const.GAME_PREFERENCES);
            preferences.putInteger(Const.STORAGE_PLAYER_WORLD_MAP_POSITION_X, (int) position.x);
            preferences.putInteger(Const.STORAGE_PLAYER_WORLD_MAP_POSITION_Y, (int) position.y);
            preferences.flush();
        } catch (Exception e) {

        }
    }

    public static Vector2 getPlayerWorldMapPosition () {
        try {
            Preferences preferences = Gdx.app.getPreferences(Const.GAME_PREFERENCES);
            int x = preferences.getInteger(Const.STORAGE_PLAYER_WORLD_MAP_POSITION_X, 0);
            int y = preferences.getInteger(Const.STORAGE_PLAYER_WORLD_MAP_POSITION_Y, 0);

            return new Vector2(x, y);
        } catch (Exception e) {
            return new Vector2(0, 0);
        }
    }

    public static void savePlayerNewSpawnPosition (Vector2 position) {
        try {
            Preferences preferences = Gdx.app.getPreferences(Const.GAME_PREFERENCES);
            preferences.putInteger(Const.STORAGE_PLAYER_SPAWN_POSITION_X, (int) position.x);
            preferences.putInteger(Const.STORAGE_PLAYER_SPAWN_POSITION_Y, (int) position.y);
            preferences.flush();
        } catch (Exception e) {

        }
    }

    public static Vector2 getPlayerNewSpawnPosition () {
        try {
            Preferences preferences = Gdx.app.getPreferences(Const.GAME_PREFERENCES);
            int x = preferences.getInteger(Const.STORAGE_PLAYER_SPAWN_POSITION_X, 7);
            int y = preferences.getInteger(Const.STORAGE_PLAYER_SPAWN_POSITION_Y, 1);

            return new Vector2(x, y);
        } catch (Exception e) {
            return new Vector2(7, 1);
        }
    }

    public static void saveNewPlayerLevel (int newLevel) {
        try {
            Preferences preferences = Gdx.app.getPreferences(Const.GAME_PREFERENCES);
            preferences.putInteger(Const.STORAGE_PLAYER_CURRENT_LEVEL, newLevel);
            preferences.flush();
        } catch (Exception e) {

        }
    }

    public static int getPlayerLevel () {
        try {
            Preferences preferences = Gdx.app.getPreferences(Const.GAME_PREFERENCES);
            return preferences.getInteger(Const.STORAGE_PLAYER_CURRENT_LEVEL, 2);
        } catch (Exception e) {
            return 2;
        }
    }


    public static void savePlayerCurrentHp (int newLevel) {
        try {
            Preferences preferences = Gdx.app.getPreferences(Const.GAME_PREFERENCES);
            preferences.putInteger(Const.STORAGE_PLAYER_CURRENT_HP, newLevel);
            preferences.flush();
        } catch (Exception e) {

        }
    }

    public static int getPlayerCurrentHp (int currentLevel) {
        try {
            Preferences preferences = Gdx.app.getPreferences(Const.GAME_PREFERENCES);
            return preferences.getInteger(Const.STORAGE_PLAYER_CURRENT_HP, currentLevel);
        } catch (Exception e) {
            return currentLevel;
        }
    }

    public static void savePlayerExp (int newLevel) {
        try {
            Preferences preferences = Gdx.app.getPreferences(Const.GAME_PREFERENCES);
            preferences.putInteger(Const.STORAGE_PLAYER_CURRENT_EXP_HP, newLevel);
            preferences.flush();
        } catch (Exception e) {

        }
    }

    public static int getPlayerExp () {
        try {
            Preferences preferences = Gdx.app.getPreferences(Const.GAME_PREFERENCES);
            return preferences.getInteger(Const.STORAGE_PLAYER_CURRENT_EXP_HP, 0);
        } catch (Exception e) {
            return 0;
        }
    }

    public static void saveNowPlaying (boolean flag) {
        try {
            Preferences preferences = Gdx.app.getPreferences(Const.GAME_PREFERENCES);
            preferences.putBoolean(Const.STORAGE_NOW_PLAYING, flag);
            preferences.flush();
        } catch (Exception e) {

        }
    }

    public static boolean getNowPlaying () {
        try {
            Preferences preferences = Gdx.app.getPreferences(Const.GAME_PREFERENCES);
            return preferences.getBoolean(Const.STORAGE_NOW_PLAYING, false);
        } catch (Exception e) {
            return false;
        }
    }

    public static void saveGuardianComplete (String key, boolean flag) {
        try {
            Preferences preferences = Gdx.app.getPreferences(Const.GAME_PREFERENCES);
            preferences.putBoolean(key, flag);
            preferences.flush();
        } catch (Exception e) {

        }
    }

    public static boolean getGuardianComplete (String key) {
        try {
            Preferences preferences = Gdx.app.getPreferences(Const.GAME_PREFERENCES);
            return preferences.getBoolean(key, false);
        } catch (Exception e) {
            return false;
        }
    }

    public static void saveLevelDoors (Vector2 level, String door, boolean flag) {
        try {
            String key = Const.STORAGE_LEVEL_DOORS_ + (int) level.x + "_" + (int) level.y + door;
            Preferences preferences = Gdx.app.getPreferences(Const.GAME_PREFERENCES);
            preferences.putBoolean(key, flag);
            preferences.flush();
        } catch (Exception e) {

        }
    }

    public static boolean getLevelDoors (Vector2 level, String door) {
        try {
            Preferences preferences = Gdx.app.getPreferences(Const.GAME_PREFERENCES);
            String key = Const.STORAGE_LEVEL_DOORS_ + (int) level.x + "_" + (int) level.y + door;
            return preferences.getBoolean(key, false);
        } catch (Exception e) {
            return false;
        }
    }

    public static void resetGame () {
        try {
            saveNowPlaying(false);
            savePlayerWorldMapPosition(new Vector2(0, 0));
            savePlayerNewSpawnPosition(new Vector2(7, 1));
            saveNewPlayerLevel(2);
            savePlayerExp(0);
            savePlayerCurrentHp(2);

            saveGuardianComplete(Const.STORAGE_GUARDIAN_I_COMPLETE, false);
            saveGuardianComplete(Const.STORAGE_GUARDIAN_II_COMPLETE, false);
            saveGuardianComplete(Const.STORAGE_GUARDIAN_III_COMPLETE, false);
            saveGuardianComplete(Const.STORAGE_GUARDIAN_IV_COMPLETE, false);
            saveGuardianComplete(Const.STORAGE_GUARDIAN_V_COMPLETE, false);
            saveGuardianComplete(Const.STORAGE_GUARDIAN_VI_COMPLETE, false);

            int worldX;
            int worldY;

            for (int y = 0; y < Const.WORLD_MAP_ROWS; y++) {
                for (int x = 0; x < Const.WORLD_MAP_COLS; x++) {
                    worldX = x;
                    worldY = Const.WORLD_MAP_ROWS - 1 - y;

                    saveLevelDoors(new Vector2(worldX, worldY), Const.ID_DOOR_UP, false);
                    saveLevelDoors(new Vector2(worldX, worldY), Const.ID_DOOR_DOWN, false);
                    saveLevelDoors(new Vector2(worldX, worldY), Const.ID_DOOR_LEFT, false);
                    saveLevelDoors(new Vector2(worldX, worldY), Const.ID_DOOR_RIGHT, false);

                }
            }

        } catch (Exception e) {

        }
    }

    public static boolean match (int src, int dst) {
        return src == dst;
    }
}
