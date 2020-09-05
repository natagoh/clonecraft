package game;

import engine.GameItem;
import engine.IGameLogic;
import engine.Input;
import engine.Window;
import engine.primitives.Mesh;
import engine.primitives.Texture;
import engine.view.FirstPersonCamera;
import engine.world.Block;
import engine.world.BlockType;
import engine.world.Chunk;

public class Clonecraft implements IGameLogic {

	private final Renderer renderer;
    private GameItem[] gameItems;
    private FirstPersonCamera fpvCamera;
    
    public Clonecraft() {
        renderer = new Renderer();
        fpvCamera = new FirstPersonCamera();
        Input input = new Input();
    }
    
    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);
        Chunk chunk = new Chunk();
        gameItems = chunk.getBlocks();
    }
    
    public void update(float deltaTime) {
    	fpvCamera.update(deltaTime);
    }
    
    @Override
    public void render(Window window) {
    	renderer.render(window, fpvCamera, gameItems);
    }
    
    @Override
    public void cleanup() {
        renderer.cleanup();
        for (GameItem gameItem : gameItems) {
            gameItem.getMesh().cleanUp();
        }
    }
}