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

        GameItem gameItem1 = new Block(BlockType.GRASS).getGameItem();
        gameItem1.setPosition(0, 0, -3);

        GameItem gameItem2 = new Block(BlockType.GRASS).getGameItem();
        gameItem2.setPosition(1f, 0, -3);

        GameItem gameItem3 = new Block(BlockType.GRASS).getGameItem();
        gameItem3.setPosition(0, 0, -4f);

        GameItem gameItem4 = new Block(BlockType.GRASS).getGameItem();
        gameItem4.setPosition(1f, 0, -4f);
        
        GameItem gameItem5 = new Block(BlockType.GRASS).getGameItem();
        gameItem5.setPosition(1f, 1f, -4f);
        gameItems = new GameItem[]{gameItem1, gameItem2, gameItem3, gameItem4, gameItem5};
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