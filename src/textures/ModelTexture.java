package textures;

import renderEngine.Loader;

public class ModelTexture {
	
	private static final Loader LOADER = new Loader();
	
	private static final String MINECRAFT_DIAMOND_ORE_FILEPATH = "minecraft/textures/blocks/diamond_ore";
	private static final int MINECRAFT_DIAMOND_ORE_TEXTURE_ID =  ModelTexture.LOADER.loadTexture(MINECRAFT_DIAMOND_ORE_FILEPATH);
	
	public static final ModelTexture MINECRAFT_DIAMOND_ORE = new ModelTexture(ModelTexture.MINECRAFT_DIAMOND_ORE_TEXTURE_ID);
	
	private int textureID;
	
	public ModelTexture(int id) {
		this.textureID = id;
	}
	
	public int getID() {
		return this.textureID;
	}
	
	public static void cleanUp() {
		ModelTexture.LOADER.cleanUp();
	}

}
