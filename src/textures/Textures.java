package textures;

import renderEngine.Loader;

public class Textures {
	
	private static final Loader LOADER = new Loader();
	
	private static final String MINECRAFT_DIAMOND_ORE_FILEPATH = "minecraft/textures/blocks/diamond_ore";
	private static final int MINECRAFT_DIAMOND_ORE_TEXTURE_ID =  Textures.LOADER.loadTexture(MINECRAFT_DIAMOND_ORE_FILEPATH);
	public static final ModelTexture MINECRAFT_DIAMOND_ORE = new ModelTexture(Textures.MINECRAFT_DIAMOND_ORE_TEXTURE_ID);
	
	private static final String MINECRAFT_IRON_BLOCK_FILEPATH = "minecraft/textures/blocks/iron_block";
	private static final int MINECRAFT_IRON_BLOCK_TEXTURE_ID =  Textures.LOADER.loadTexture(MINECRAFT_IRON_BLOCK_FILEPATH);
	public static final ModelTexture MINECRAFT_IRON_BLOCK = new ModelTexture(Textures.MINECRAFT_IRON_BLOCK_TEXTURE_ID);
	
	private static final String MINECRAFT_GOLD_BLOCK_FILEPATH = "minecraft/textures/blocks/gold_block";
	private static final int MINECRAFT_GOLD_BLOCK_TEXTURE_ID =  Textures.LOADER.loadTexture(MINECRAFT_GOLD_BLOCK_FILEPATH);
	public static final ModelTexture MINECRAFT_GOLD_BLOCK = new ModelTexture(Textures.MINECRAFT_GOLD_BLOCK_TEXTURE_ID);

}
