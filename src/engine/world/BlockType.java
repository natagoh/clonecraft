package engine.world;

public enum BlockType {
	GRASS {
        @Override
        public String getTextureFile() {
            return "resources/textures/grass.png";
        }
    },
	AIR {
        @Override
        public String getTextureFile() {
            return "resources/textures/grass.png";
        }
    };
	

	public abstract String getTextureFile();
}
