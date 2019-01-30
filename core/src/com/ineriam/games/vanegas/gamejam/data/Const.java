package com.ineriam.games.vanegas.gamejam.data;


import com.badlogic.gdx.math.Vector2;

public class Const {
    // STORAGE
    public static String GAME_PREFERENCES = "game-preferences";
    public static String STORAGE_NOW_PLAYING = "storage-now-playing";
    public static String STORAGE_PLAYER_WORLD_MAP_POSITION_X = "storage-player-world-map-position-x";
    public static String STORAGE_PLAYER_WORLD_MAP_POSITION_Y = "storage-player-world-map-position-y";
    public static String STORAGE_PLAYER_SPAWN_POSITION_X = "storage-player-spawn-position-x";
    public static String STORAGE_PLAYER_SPAWN_POSITION_Y = "storage-player-spawn-position-y";
    public static String STORAGE_PLAYER_CURRENT_LEVEL = "storage-player-current-level";
    public static String STORAGE_PLAYER_CURRENT_HP = "storage-player-current-hp";
    public static String STORAGE_PLAYER_CURRENT_EXP_HP = "storage-player-current-exp-hp";
    public static String STORAGE_LEVEL_DOORS_ = "storage-level-doors-";
    public static String ID_DOOR_UP = "_up";
    public static String ID_DOOR_DOWN = "_down";
    public static String ID_DOOR_LEFT = "_left";
    public static String ID_DOOR_RIGHT = "_right";

    public static String STORAGE_GUARDIAN_I_COMPLETE = "storage-guardian-i-complete";
    public static String STORAGE_GUARDIAN_II_COMPLETE = "storage-guardian-ii-complete";
    public static String STORAGE_GUARDIAN_III_COMPLETE = "storage-guardian-iii-complete";
    public static String STORAGE_GUARDIAN_IV_COMPLETE = "storage-guardian-iv-complete";
    public static String STORAGE_GUARDIAN_V_COMPLETE = "storage-guardian-v-complete";
    public static String STORAGE_GUARDIAN_VI_COMPLETE = "storage-guardian-vi-complete";

    // SCREENS
    public static float NOKIA_SCREEN_WIDTH = 84;
    public static float NOKIA_SCREEN_HEIGHT = 48;

    public static float NOKIA_SCREEN_WORLD_WIDTH = 48;
    public static float NOKIA_SCREEN_WORLD_HEIGHT = 48;

    public static float NOKIA_SCREEN_WORLD_INFO_WIDTH = 36;
    public static float NOKIA_SCREEN_WORLD_INFO_HEIGHT = 48;

    public static float NOKIA_SCREEN_CONTROLLER_MIN = 100;
    public static float NOKIA_SCREEN_CONTROLLER_MAX = 120;

    // COLOR LIGHT 199 240 216  #c7f0d8
    // COLOR DARK 67 82 61 #43523d

    // Assets
    public static String ASSETS_GRAPHICS_ATLAS = "vanegas_gamejam/graphics/graphics.atlas";
    public static String ASSETS_GRAPHICS_JSON = "vanegas_gamejam/graphics/graphics.json";
    public static String ASSETS_GRAPHICS_MAPS = "vanegas_gamejam/maps/";
    public static String ASSETS_GRAPHICS_MAPS_EXT = ".png";

    // Text
    public static String LOCALIZATION_PATH = "vanegas_gamejam/localization/strings";

    // Fonts
    public static final String DEFAULT_FONT = "vanegas_gamejam/fonts/nokia.ttf";
    public static final int DEFAULT_FONT_SIZE = 8;

    public static float PADDING = 2;

    // MAPS
    public static int WORLD_MAP_COLS = 10;
    public static int WORLD_MAP_ROWS = 5;
    public static String[] KEYS = {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"
    };

    public static int MAP_COLS = 15;
    public static int MAP_ROWS = 15;

    public static float HEALTH_RANDOM_DROP = 0.8f;

    // TILES
    public static int TILE_BLOCK = 0;
    public static int TILE_AIR = 1;

    public static int TILE_COLOR_BLOCK = 0xffffff;
    public static int TILE_COLOR_DOOR_UP = 0x00ff00;    // GREEN
    public static int TILE_COLOR_DOOR_LEFT = 0xff0000;  // RED
    public static int TILE_COLOR_DOOR_RIGHT = 0xffff00; // YELLOW
    public static int TILE_COLOR_DOOR_DOWN = 0x0000ff; // BLUE

    public static int TILE_SIZE = 1;

    // ACTORS
    public static int ACTOR_SIZE = 1;
    public static int TYPE_PLAYER = 1;
    public static int TYPE_MONSTER = 2;

    public static int ID_ACTOR = 1;
    public static int ID_LOKOBLIN = 2;
    public static int ID_COBLIN = 3;
    public static int ID_ZALFO = 4;
    public static int ID_BLIZZ = 5;
    public static int ID_CENTA = 6;
    public static int ID_GUARDIAN_I = 7;
    public static int ID_GUARDIAN_II = 8;
    public static int ID_GUARDIAN_III = 9;
    public static int ID_GUARDIAN_IV = 10;
    public static int ID_GUARDIAN_V = 11;
    public static int ID_GUARDIAN_VI = 12;

    public static int LOKOBLIN_COLOR = 0x7f7f7f; // GRIS OSCURO
    public static int LOKOBLIN_LEVEL = 1;
    public static int LOKOBLIN_VISION_DISTANCE = 3;

    public static int COBLIN_COLOR = 0x880015; // GUINDO
    public static int COBLIN_LEVEL = 2;
    public static int COBLIN_VISION_DISTANCE = 3;

    public static int ZALFO_COLOR = 0xff7f27; // ORANGE
    public static int ZALFO_LEVEL = 3;
    public static int ZALFO_VISION_DISTANCE = 4;

    public static int BLIZZ_COLOR = 0x22b14c; // GREEN
    public static int BLIZZ_LEVEL = 4;
    public static int BLIZZ_VISION_DISTANCE = 5;

    public static int CENTA_COLOR = 0x00a2e8; // TURQUESA
    public static int CENTA_LEVEL = 5;
    public static int CENTA_VISION_DISTANCE = 6;

    public static int GUARDIAN_VISION_DISTANCE = 15;
    public static int GUARDIAN_I_COLOR = 0xb97a57; // MARRON
    public static int GUARDIAN_I_LEVEL = 6;

    public static int GUARDIAN_II_COLOR = 0xffaec9; // PINK
    public static int GUARDIAN_II_LEVEL = 7;

    public static int GUARDIAN_III_COLOR = 0x99d9ea; // TURQUESA CLARO
    public static int GUARDIAN_III_LEVEL = 8;

    public static int GUARDIAN_IV_COLOR = 0xffc90e; // DORADO
    public static int GUARDIAN_IV_LEVEL = 8;

    public static int GUARDIAN_V_COLOR = 0xb5e61d; // LIMA
    public static int GUARDIAN_V_LEVEL = 9;

    public static int GUARDIAN_VI_COLOR = 0x7092be; // GRIS AZULADO
    public static int GUARDIAN_VI_LEVEL = 9;

    public static float LERP_FACTOR = 4.5f;

    public static float STATE_MONSTER_TIME = 1.8f;
    public static float STATE_MONSTER_TIME_HEART = 0.8f;
    public static float STATE_MONSTER_TIME_HEART_VALUE = 0.8f;
    public static float STATE_MONSTER_TIME_TWO = 0.8f;
    public static float STATE_MONSTER_TIME_ATTACK = 0.8f;
    public static float STATE_MONSTER_TIME_ATTACK_VALUE = 0.8f;
    public static float STATE_MONSTER_TIME_END = 8f;

    // DIRECTIONS
    public static Vector2 DIRECTION_LEFT = new Vector2(-1, 0);
    public static Vector2 DIRECTION_RIGHT = new Vector2(1, 0);
    public static Vector2 DIRECTION_UP = new Vector2(0, 1);
    public static Vector2 DIRECTION_DOWN = new Vector2(0, -1);

    // DOORS
    public final static int DOOR_DIR_UP = 0;
    public final static int DOOR_DIR_LEFT = 1;
    public final static int DOOR_DIR_RIGHT = 2;
    public final static int DOOR_DIR_DOWN = 3;

    public static Vector2 PLAYER_SPAWN_UP = new Vector2(7, 13);
    public static Vector2 PLAYER_SPAWN_DOWN = new Vector2(7, 1);
    public static Vector2 PLAYER_SPAWN_LEFT = new Vector2(1, 7);
    public static Vector2 PLAYER_SPAWN_RIGHT = new Vector2(13, 7);

    // GUARDIANS POSITION
    public static Vector2 POSITION_GUARDIAN_I = new Vector2(1, 3);
    public static Vector2 POSITION_GUARDIAN_II = new Vector2(3, 0);
    public static Vector2 POSITION_GUARDIAN_III = new Vector2(5, 4);
    public static Vector2 POSITION_GUARDIAN_IV = new Vector2(6, 1);
    public static Vector2 POSITION_GUARDIAN_V = new Vector2(8, 2);
    public static Vector2 POSITION_GUARDIAN_VI = new Vector2(9, 4);



    // LEVELS
    public static Level[] PLAYER_LEVELS = {
            new Level(0, 0, 0),
            new Level(1, 0, 1),
            new Level(2, 25, 2),
            new Level(3, 50, 3),
            new Level(4, 76, 4),
            new Level(5, 120, 5),   // 102 1.9
            new Level(6, 190, 6),   // 128
            new Level(7, 240, 7),   // 155
            new Level(8, 290, 8),   // 207
            new Level(9, 350, 9),   // 259
    };

    public static Level[] MONSTER_LEVELS = {
            new Level(0, 0, 0),
            new Level(1, 2, 1),
            new Level(2, 4, 2),
            new Level(3, 8, 3),
            new Level(4, 16, 4),
            new Level(5, 32, 5),
            new Level(6, 64, 6),
            new Level(7, 80, 7),
            new Level(8, 80, 8),
            new Level(9, 80, 9),
    };

    public static float TIME_GAME_OVER = 2;
    public static float RAIN_SIZE_WIDTH = 0.1f;
    public static float RAIN_SIZE_HEIGHT = 1f;
    public static float RAIN_NORMAL_TIME_MIN = 100;
    public static float RAIN_NORMAL_TIME_MAX = 300;

    public static float RAIN_DROPS_MIN = 300;
    public static float RAIN_DROPS_MAX = 800;

    public static float RAIN_TIME_MIN = 50;
    public static float RAIN_TIME_MAX = 120;

    public static float RAIN_SPEED = 0.25f;
}

