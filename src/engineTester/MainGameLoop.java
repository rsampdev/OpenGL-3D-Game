package engineTester;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
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
		Entity entity = new Entity(texturedModel, new Vector3f(0, 0, -25), 0, 0, 0, 1);
		entities.add(entity);
		
		Terrain terrain1 = new Terrain(-0.5f, -0.5f, loader, texturePack, blendMap);
		terrains.add(terrain1);

		Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));
		Camera camera = new Camera();
		
		MasterRenderer renderer = new MasterRenderer();

		while (!Display.isCloseRequested()) {
			camera.move();

			for (Entity e : entities) {
				renderer.processEntity(e);
			}
			
			for (Terrain t : terrains) {
				renderer.processTerrain(t);
			}

			renderer.render(light, camera);

			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}