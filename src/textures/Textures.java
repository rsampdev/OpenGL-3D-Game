package textures;

import renderEngine.Loader;

public class Textures {
	
	private static final Loader LOADER = new Loader();
	
	private static final String MINECRAFT_DIAMOND_ORE_FILEPATH = "minecraft/textures/blocks/diamond_ore";
	private static final int MINECRAFT_DIAMOND_ORE_TEXTURE_ID =  Textures.LOADER.loadTexture(MINECRAFT_DIAMOND_ORE_FILEPATH);
	public static final ModelTexture MINECRAFT_DIAMOND_ORE = new ModelTexture(Textures.MINECRAFT_DIAMOND_ORE_TEXTURE_ID);
	
	private static final String MINECRAFT_GLASS_FILEPATH = "minecraft/textures/blocks/glass";
	private static final int MINECRAFT_GLASS_TEXTURE_ID =  Textures.LOADER.loadTexture(MINECRAFT_GLASS_FILEPATH);
	public static final ModelTexture MINECRAFT_GLASS_BLOCK = new ModelTexture(Textures.MINECRAFT_GLASS_TEXTURE_ID);
	
	private static final String MINECRAFT_GOLD_BLOCK_FILEPATH = "minecraft/textures/blocks/gold_block";
	private static final int MINECRAFT_GOLD_BLOCK_TEXTURE_ID =  Textures.LOADER.loadTexture(MINECRAFT_GOLD_BLOCK_FILEPATH);
	public static final ModelTexture MINECRAFT_GOLD_BLOCK = new ModelTexture(Textures.MINECRAFT_GOLD_BLOCK_TEXTURE_ID);
	
	private static final String MINECRAFT_IRON_BLOCK_FILEPATH = "minecraft/textures/blocks/iron_block";
	private static final int MINECRAFT_IRON_BLOCK_TEXTURE_ID =  Textures.LOADER.loadTexture(MINECRAFT_IRON_BLOCK_FILEPATH);
	public static final ModelTexture MINECRAFT_IRON_BLOCK = new ModelTexture(Textures.MINECRAFT_IRON_BLOCK_TEXTURE_ID);
	
	private static final String TM_GRASS_FILEPATH = "tm/grassy";
	private static final int TM_GRASS_ID =  Textures.LOADER.loadTexture(TM_GRASS_FILEPATH);
	public static final ModelTexture TM_GRASS = new ModelTexture(Textures.TM_GRASS_ID);
	
	private static final String TM_GRASS_RED_FLOWERS_FILEPATH = "tm/grassy";
	private static final int TM_GRASS_RED_FLOWERS_ID =  Textures.LOADER.loadTexture(TM_GRASS_RED_FLOWERS_FILEPATH);
	public static final ModelTexture TM_GRASS_RED_FLOWERS = new ModelTexture(Textures.TM_GRASS_RED_FLOWERS_ID);
	
	private static final String TM_MUD_FILEPATH = "tm/mud";
	private static final int TM_MUD_ID =  Textures.LOADER.loadTexture(TM_MUD_FILEPATH);
	public static final ModelTexture TM_MUD = new ModelTexture(Textures.TM_MUD_ID);
	
	private static final String TM_PATH_FILEPATH = "tm/path";
	private static final int TM_PATH_ID =  Textures.LOADER.loadTexture(TM_PATH_FILEPATH);
	public static final ModelTexture TM_PATH = new ModelTexture(Textures.TM_PATH_ID);
	
	private static final String TM_BLEND_MAP_FILEPATH = "tm/blendMap";
	private static final int TM_BLEND_MAP_ID =  Textures.LOADER.loadTexture(TM_BLEND_MAP_FILEPATH);
	public static final ModelTexture TM_BLEND_MAP = new ModelTexture(Textures.TM_BLEND_MAP_ID);

}
