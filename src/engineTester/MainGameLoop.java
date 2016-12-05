package engineTester;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;
import models.RawModel;
import models.TexturedModel;
import objConverter.ModelData;
import objConverter.OBJFileLoader;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import textures.Textures;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		List<Entity> entities = new ArrayList<Entity>();
		List<Terrain> terrains = new ArrayList<Terrain>();
		
		TerrainTexture backgroundTexture = new TerrainTexture(Textures.TM_GRASS.getID());
		TerrainTexture rTexture = new TerrainTexture(Textures.TM_MUD.getID());
		TerrainTexture gTexture = new TerrainTexture(Textures.TM_GRASS_RED_FLOWERS.getID());
		TerrainTexture bTexture = new TerrainTexture(Textures.TM_PATH.getID());
		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
		TerrainTexture blendMap = new TerrainTexture(Textures.TM_BLEND_MAP.getID());
		
		ModelData data = OBJFileLoader.loadOBJ("models/dragon/model");
		RawModel model = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
		ModelTexture texture = new ModelTexture(loader.loadTexture("models/dragon/texture"));
		texture.setShineDamper(10);
		texture.setReflectivity(10);

		TexturedModel texturedModel = new TexturedModel(model, texture);
		Entity entity = new Entity(texturedModel, new Vector3f(0, 0, 0), 0, 0, 0, 1);
		entities.add(entity);
		
		ModelData playerData = OBJFileLoader.loadOBJ("tm/player");
		RawModel playerModel = loader.loadToVAO(playerData.getVertices(), playerData.getTextureCoords(), playerData.getNormals(), playerData.getIndices());
		ModelTexture playerTexture = new ModelTexture(loader.loadTexture("tm/playerTexture"));
		playerTexture.setShineDamper(1.0f);
		TexturedModel playerTexturedModel = new TexturedModel(playerModel, playerTexture);
		Player player = new Player(playerTexturedModel, new Vector3f(0, 0, -25), 0, 0, 0, 0.25f);
		entities.add(player);
		
		Terrain terrain = new Terrain(-0.5f, -0.5f, loader, texturePack, blendMap, "tm/heightMap");
		terrains.add(terrain);

		Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));
		Camera camera = new Camera(player);
		
		MasterRenderer renderer = new MasterRenderer();

		while (!Display.isCloseRequested()) {
			camera.move();
			player.move();
			
			for (Terrain t : terrains) {
				renderer.processTerrain(t);
			}
			
			for (Entity e : entities) {
				renderer.processEntity(e);
			}
			
			renderer.render(light, camera);

			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}