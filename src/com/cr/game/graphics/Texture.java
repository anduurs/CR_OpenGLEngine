package com.cr.game.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL13.*;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.EXTTextureFilterAnisotropic;
import org.lwjgl.opengl.GL11;

import com.cr.game.util.ImageLoader;

public class Texture {
	
	private BufferedImage img;
	
	private int id;
	private int width, height;
	
	public Texture(String name){
		img = ImageLoader.getImage(name);
		width = img.getWidth();
		height = img.getHeight();
		id = loadTexture(name);
	}
	
	public void bind(){
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public void unbind(){
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	private int loadTexture(String name){
		int[] pixels = new int[width*height];
			
		img.getRGB(0, 0, width, height, pixels, 0, width);
			
		ByteBuffer buffer = BufferUtils.createByteBuffer(width*height*4);
		boolean hasAlpha = img.getColorModel().hasAlpha();
			
		//loop through all the pixels and extract the red, green, blue and alpha components
		//and put them in the buffer which will then be sent to the gpu
		for(int y = 0; y < height; y++)
			for(int x = 0; x < width; x++){
				int pixelData = pixels[x + (y*width)];
				buffer.put((byte) ((pixelData >> 16) & 0xFF));  //RED Component
				buffer.put((byte) ((pixelData >> 8) & 0xFF));   //GREEN Component
				buffer.put((byte) (pixelData & 0xFF));          //BLUE Component
				if(hasAlpha)
					buffer.put((byte) ((pixelData >> 24) & 0xFF)); //ALPHA Component
				else buffer.put((byte) 0xFF);
			}
			
		buffer.flip();
			
		int id = glGenTextures();
		
		glActiveTexture(GL_TEXTURE0);
		glBindTexture(GL_TEXTURE_2D, id);
			
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, 16);

		//send the texture data to the gpu
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		
		
			
		glBindTexture(GL_TEXTURE_2D, 0);
			
		return id;
	}

	public int getID() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
