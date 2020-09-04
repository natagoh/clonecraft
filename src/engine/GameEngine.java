package engine;

import org.lwjgl.glfw.GLFW;

public class GameEngine implements Runnable {
    
    private final Window window;    
    private final IGameLogic gameLogic;

    public GameEngine(String windowTitle, int width, int height, boolean vSync, IGameLogic gameLogic) throws Exception {
        window = new Window(windowTitle, width, height, vSync);
        this.gameLogic = gameLogic;
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch (Exception excp) {
            excp.printStackTrace();
        }
    }

    protected void init() throws Exception {
        window.init();
        
        // lock cursor
        window.disableCursor(true);
        gameLogic.init(window);
    }

    protected void gameLoop() {
        boolean running = true;
        while (running && !window.windowShouldClose()) {
            update();
            render();
            if (Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
            	return;
            }
        }
    }
    
    protected void update() {
        gameLogic.update();
        window.update();
    }

    protected void render() {
        gameLogic.render(window);
        window.swapBuffer();
    }
}