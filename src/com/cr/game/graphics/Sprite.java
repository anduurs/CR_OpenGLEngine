package com.cr.game.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Sprite {
	
	private int vboID, iboID, texBufferID, vaoID;
	private int width, height;
	private int texID;
	
	private FloatBuffer vertexBuffer, texCoordBuffer;
	private ByteBuffer indexBuffer;
	
	public Sprite(String textureName){
		texID = loadTexture(textureName);
		sendDataToOpenGL();
	}
	
	public void bind(){
		glBindVertexArray(vaoID);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
	}
	
	public void unbind(){
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		
		glBindVertexArray(0);
	}
	
	private void sendDataToOpenGL(){
		float[] vertices = {0, 0, 0,
							0, 0 + height, 0, 
							0 + width, 0 + height, 0, 
							0 + width, 0, 0};
		
		byte[] indices = {0,1,2, 
						   2,3,0};
		
		float[] texCoords = {0,0,
	             			 0,1,
	             			 1,1,
	             			 1,0};
		

		vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
		vertexBuffer.put(vertices);
		vertexBuffer.flip();
		
		indexBuffer = BufferUtils.createByteBuffer(indices.length);
		indexBuffer.put(indices);
		indexBuffer.flip();
		
		texCoordBuffer = BufferUtils.createFloatBuffer(texCoords.length);
		texCoordBuffer.put(texCoords);
		texCoordBuffer.flip();
		
		vboID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		texBufferID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, texBufferID);
		glBufferData(GL_ARRAY_BUFFER, texCoordBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		iboID = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, iboID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		

		vaoID = glGenVertexArrays();
		glBindVertexArray(vaoID);
		
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, texBufferID);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glBindVertexArray(0);
	}
	
	private int loadTexture(String name){
		try {
			BufferedImage img = ImageIO.read(new File("./res/textures/" + name + ".png"));
			int w = img.getWidth();
			int h = img.getHeight();
			
			width = w;
			height = h;
			int[] pixels = new int[w*h];
			
			img.getRGB(0, 0, w, h, pixels, 0, w);
			
			ByteBuffer buffer = BufferUtils.createByteBuffer(w*h*4);
			boolean hasAlpha = img.getColorModel().hasAlpha();
			
			for(int y = 0; y < h; y++)
				for(int x = 0; x < w; x++){
					int pixelData = pixels[x + (y*w)];
					buffer.put((byte) ((pixelData >> 16) & 0xFF));  //RED
					buffer.put((byte) ((pixelData >> 8) & 0xFF));   //GREEN
					buffer.put((byte) (pixelData & 0xFF));          //BLUE
					if(hasAlpha)
						buffer.put((byte) ((pixelData >> 24) & 0xFF)); //ALPHA
					else buffer.put((byte) 0xFF);
				}
			
			buffer.flip();
			
			int id = glGenTextures();
			glActiveTexture(GL_TEXTURE0);
			glBindTexture(GL_TEXTURE_2D, id);
			
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);

			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
			
			glBindTexture(GL_TEXTURE_2D, 0);
			
			return id;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return 0;
		
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getTexID() {
		return texID;
	}
	
}
