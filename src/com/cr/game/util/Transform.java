package com.cr.game.util;

import com.cr.game.core.Window;

public class Transform {
	
	private Vector3f translation;
	private Matrix4f ortho, pers;
	
	public Transform(){
		translation = new Vector3f(0,0,0);
		ortho = new Matrix4f().initProjectionOrtho(0, Window.getWidth(), Window.getHeight(), 0, -1f, 1f);
		//pers = new Matrix4f().initProjectionPerspective(70, Window.getWidth(),  Window.getHeight(), 0.01f, 1000f);
	}
	
	public Matrix4f getTranslationMatrix(){
		Matrix4f translationMatrix = new Matrix4f().initTranslationMatrix(translation.x, translation.y, translation.z);
		return translationMatrix;
	}
	
	public Matrix4f getOrthoProjection(){
		return ortho.mul(getTranslationMatrix());
	}
	
	public Matrix4f getPerspective(){
		return pers.mul(getTranslationMatrix());
	}

	public Vector3f getTranslationVector() {
		return translation;
	}

	public void setTranslation(Vector3f translation) {
		this.translation = translation;
	}
	
	public void translate(float x, float y, float z) {
		this.translation = new Vector3f(x,y,z);
	}

}
