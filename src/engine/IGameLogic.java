package engine;

public interface IGameLogic {
	void init(Window window) throws Exception;
    void update();
    void render(Window window);
	void cleanup();
}