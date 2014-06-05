package com.cr.game.graphics;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_REPEAT;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_RGBA8;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameterf;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

public class Texture {
	
	private int id;
	private int width, height;
	
	public Texture(String name){
		id = loadTexture(name);
	}
	
	public void bind(){
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public void unbind(){
		glBindTexture(GL_TEXTURE_2D, 0);
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
		
			glBindTexture(GL_TEXTURE_2D, id);
			
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
			
			glBindTexture(GL_TEXTURE_2D, 0);
			
			return id;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		return 0;
		
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
