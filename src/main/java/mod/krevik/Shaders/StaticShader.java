package mod.krevik.Shaders;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram{

	
	private static final String vertexRes = "/assets/mystic/shaders/vertexshader.vs";
	private static final String fragmentRes = "/assets/mystic/shaders/fragmentshader.fs";
	
	private int location_transformationMatrix;

	public StaticShader() {
		super(vertexRes,fragmentRes);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoordinates");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}

}
