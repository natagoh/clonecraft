package engine.view;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SHIFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.glfwGetTime;

import engine.Input;

public class FirstPersonCamera extends Camera {
	
	private static final float MOVE_SPEED = 4.0f;
	private static final float MOUSE_SENSITIVITY = 0.15f;
	
	private double oldMouseX = 0.0f;
	private double oldMouseY = 0.0f;
	private double newMouseX;
	private double newMouseY;
	
    public void update(float deltaTime) {
		newMouseX = Input.getMouseX();
		newMouseY = Input.getMouseY();
		
		float cameraSpeed = MOVE_SPEED * deltaTime;
		
		// reversed since (x,y)-coordinates range from bottom to top
		float dx = (float) (oldMouseX - newMouseX);
		float dy = (float) (oldMouseY - newMouseY); 

        if (Input.isKeyDown(GLFW_KEY_W)) super.movePosition(0, 0, -cameraSpeed);
		if (Input.isKeyDown(GLFW_KEY_A)) super.movePosition(-cameraSpeed, 0, 0);
		if (Input.isKeyDown(GLFW_KEY_S)) super.movePosition(0, 0, cameraSpeed);
		if (Input.isKeyDown(GLFW_KEY_D)) super.movePosition(cameraSpeed, 0, 0);
		
		if (Input.isKeyDown(GLFW_KEY_SPACE)) super.movePosition(0, cameraSpeed, 0);
		if (Input.isKeyDown(GLFW_KEY_LEFT_SHIFT)) super.movePosition(0, -cameraSpeed, 0);
		
//		if (Input.isButtonDown(GLFW_MOUSE_BUTTON_LEFT)) {
			super.moveRotation(-dy * MOUSE_SENSITIVITY, -dx * MOUSE_SENSITIVITY, 0);
//		}
		
		oldMouseX = newMouseX;
		oldMouseY = newMouseY;
	}
}
