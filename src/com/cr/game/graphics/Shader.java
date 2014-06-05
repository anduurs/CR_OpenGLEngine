package com.cr.game.graphics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.HashMap;

import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import com.cr.game.util.Matrix4f;

public class Shader {
	
	private int shaderProgram;
	private int vertexShader;
	private int fragmentShader;
	
	private HashMap<String, Integer> uniforms;
	
	public Shader(){
		uniforms = new HashMap<String, Integer>();
		shaderProgram = glCreateProgram();
	}
	
	public void addVertexShader(String fileName){
		vertexShader = glCreateShader(GL_VERTEX_SHADER);
		String vertexShaderSource = loadShader(fileName + ".vert");
		
		glShaderSource(vertexShader, vertexShaderSource);
		glCompileShader(vertexShader);
		
		if(glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL_FALSE)
			System.err.println("Couldn't compile vertexshader correctly");
		
		glAttachShader(shaderProgram, vertexShader);
		glDeleteShader(vertexShader);
	}
	
	public void addFragmentShader(String fileName){
		fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		String fragmentShaderSource = loadShader(fileName + ".frag");
		
		glShaderSource(fragmentShader, fragmentShaderSource);
		glCompileShader(fragmentShader);
		
		if(glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE)
			System.err.println("Couldn't compile fragmentShader correctly");
		
		glAttachShader(shaderProgram, fragmentShader);
		glDeleteShader(fragmentShader);
	}
	
	public void createShaderProgram(){
		glBindAttribLocation(shaderProgram, 0, "position");
		glBindAttribLocation(shaderProgram, 1, "texCoordIn");

		glLinkProgram(shaderProgram);
		glValidateProgram(shaderProgram);
	}
	
	public void addUniform(String uniform){
		int uniformLocation = glGetUniformLocation(shaderProgram, uniform);
		uniforms.put(uniform, uniformLocation);
	}
	
	public void updateUniformi(String uniformName, int value){
		glUniform1i(uniforms.get(uniformName), value);
	}
	
	public void updateUniformf(String uniformName, float value){
		glUniform1f(uniforms.get(uniformName), value);
	}
	
	public void updateUniform(String uniformName, Matrix4f value){
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
