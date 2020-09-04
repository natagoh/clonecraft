package engine.view;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_LEFT_SHIFT;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;

import org.joml.Vector3f;

import engine.Input;

public class FirstPersonCamera extends Camera {
	
	private final float moveSpeed = 0.05f;
	private final float mouseSensitivity = 0.15f;
	private double oldMouseX = 0;
	private double oldMouseY = 0;
	private double newMouseX;
	private double newMouseY;
	
    public void update() {
		newMouseX = Input.getMouseX();
		newMouseY = Input.getMouseY();
		
		// reversed since (x,y)-coordinates range from bottom to top
		float dx = (float) (oldMouseX - newMouseX);
		float dy = (float) (oldMouseY - newMouseY); 

        if (Input.isKeyDown(GLFW_KEY_W)) super.movePosition(0, 0, -moveSpeed);
		if (Input.isKeyDown(GLFW_KEY_A)) super.movePosition(-moveSpeed, 0, 0);
		if (Input.isKeyDown(GLFW_KEY_S)) super.movePosition(0, 0, moveSpeed);
		if (Input.isKeyDown(GLFW_KEY_D)) super.movePosition(moveSpeed, 0, 0);
		
		if (Input.isKeyDown(GLFW_KEY_SPACE)) super.movePosition(0, moveSpeed, 0);
		if (Input.isKeyDown(GLFW_KEY_LEFT_SHIFT)) super.movePosition(0, -moveSpeed, 0);
		
//		if (Input.isButtonDown(GLFW_MOUSE_BUTTON_LEFT)) {
			super.moveRotation(-dy * mouseSensitivity, -dx * mouseSensitivity, 0);
//		}
		
		oldMouseX = newMouseX;
		oldMouseY = newMouseY;
	}
}
