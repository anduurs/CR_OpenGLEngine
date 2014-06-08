package com.cr.game.graphics.shader;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glBindAttribLocation;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniformMatrix4;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;

import com.cr.game.core.Matrix4f;

public class Shader {
	
	private int shaderProgram;
	private int vertexShader;
	private int fragmentShader;
	
	private HashMap<String, Integer> uniforms;
	
	public Shader(String vertShader, String fragShader){
		uniforms = new HashMap<String, Integer>();
		shaderProgram = glCreateProgram();
		
		addVertexShader(vertShader);
		addFragmentShader(fragShader);
		createShaderProgram();
	}
	
	private void addVertexShader(String fileName){
		vertexShader = glCreateShader(GL_VERTEX_SHADER);
		String vertexShaderSource = loadShader(fileName + ".vert");
		
		glShaderSource(vertexShader, vertexShaderSource);
		glCompileShader(vertexShader);
		
		if(glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_FALSE)
			System.err.println("Couldn't compile vertexshader correctly");
		
		glAttachShader(shaderProgram, vertexShader);
		glDeleteShader(vertexShader);
	}
	
	private void addFragmentShader(String fileName){
		fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		String fragmentShaderSource = loadShader(fileName + ".frag");
		
		glShaderSource(fragmentShader, fragmentShaderSource);
		glCompileShader(fragmentShader);
		
		if(glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE)
			System.err.println("Couldn't compile fragmentShader correctly");
		
		glAttachShader(shaderProgram, fragmentShader);
		glDeleteShader(fragmentShader);
	}
	
	private void createShaderProgram(){
		glBindAttribLocation(shaderProgram, 0, "position");
		glBindAttribLocation(shaderProgram, 1, "texCoordIn");

		glLinkProgram(shaderProgram);
		glValidateProgram(shaderProgram);
	}
	
	public void addUniform(String uniform){
		int uniformLocation = glGetUniformLocation(shaderProgram, uniform);
		uniforms.put(uniform, uniformLocation);
	}
	
	public void setUniformi(String uniformName, int value){
		glUniform1i(uniforms.get(uniformName), value);
	}
	
	public void setUniformf(String uniformName, float value){
		glUniform1f(uniforms.get(uniformName), value);
	}
	
	public void setUniform(String uniformName, Matrix4f value){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(4 * 4);
		
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				buffer.put(value.get(i, j));
		buffer.flip();
		
		glUniformMatrix4(uniforms.get(uniformName), true, buffer);
	}
	
	public void bind(){
		glUseProgram(shaderProgram);
	}
	
	public void unbind(){
		glUseProgram(0);
	}
	
	public void deleteShader(){
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		glDeleteProgram(shaderProgram);
	}
	
	private static String loadShader(String fileName){
		StringBuilder shaderSource = new StringBuilder();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/shaders/" + fileName));
			String line;
			while((line = reader.readLine()) != null)
				shaderSource.append(line).append('\n');
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return shaderSource.toString();
	}

}
