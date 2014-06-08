package com.cr.game.util;

import com.cr.game.core.Window;

public class Transform {
	
	private Vector3f translation, rotation, scaling;
	private Matrix4f ortho, perspective;
	private static Camera camera;
	
	public Transform(){
		translation = new Vector3f(0,0,0);
		rotation = new Vector3f(0,0,0);
		scaling = new Vector3f(1,1,1);
		ortho = new Matrix4f().initOrthographicProjection(0, Window.getWidth(), Window.getHeight(), 0, -1f, 1f);
		perspective = new Matrix4f().initPerspectiveProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000f);
	}
	
	public Matrix4f getTranslationMatrix(){
		Matrix4f translationMatrix = new Matrix4f().initTranslationMatrix(translation.x, translation.y, translation.z);
		return translationMatrix;
	}
	
	public Matrix4f getRotationMatrix(){
		Matrix4f rotMatrix = new Matrix4f().initRotationMatrix(rotation.x, rotation.y, rotation.z);
		return rotMatrix;
	}
	
	public Matrix4f getScalingMatrix(){
		Matrix4f scalingMatrix = new Matrix4f().initScalingMatrix(scaling.x, scaling.y, scaling.z);
		return scalingMatrix;
	}
	
	public Matrix4f getModelMatrix(){
		Matrix4f T = getTranslationMatrix();
		Matrix4f R = getRotationMatrix();
		Matrix4f S = getScalingMatrix();
		
		Matrix4f modelMatrix = T.mul(R.mul(S));
		
		return modelMatrix;
	}
	
	public Matrix4f getViewMatrix(){
		Matrix4f cameraTranslation = new Matrix4f().initTranslationMatrix(-Camera.getCamX(), -Camera.getCamY(), -Camera.getCamZ());
		return (cameraTranslation);
	}
	
	public Matrix4f getOrthoTransformation(){
		return ortho.mul(getViewMatrix().mul(getModelMatrix()));
	}
	
	public Matrix4f getPerspectiveTransformation(){
		return perspective.mul(getModelMatrix());
	}

	public void translate(float x, float y, float z) {
		this.translation.x = x;
		this.translation.y = y;
		this.translation.z = z;
	}
	
	public void rotate(float x, float y, float z) {
		this.rotation.x = x;
		this.rotation.y = y;
		this.rotation.z = z;
	}
	
	public void scale(float x, float y, float z) {
		this.scaling.x = x;
		this.scaling.y = y;
		this.scaling.z = z;
	}
	
	public Matrix4f getOrthoProjection(){
		return ortho;
	}
	
	public Matrix4f getPerspectiveProjection(){
		return perspective;
	}
	
	public void setOrthoProjection(float left, float right, float bottom, float top, float near, float far) {
		this.ortho = new Matrix4f().initOrthographicProjection(left, right, bottom, top, near, far);
	}

	public void setPerspectiveProjection(float fov, float width, float height, float zNear, float zFar) {
		this.perspective = new Matrix4f().initPerspectiveProjection(fov, width, height, zNear, zFar);
	}
	
	public Vector3f getTranslationVector() {
		return translation;
	}

	public void setTranslationVector(Vector3f translation) {
		this.translation = translation;
	}

	public Vector3f getRotationVector() {
		return rotation;
	}

	public Vector3f getScalingVector() {
		return scaling;
	}

	public void setRotationVector(Vector3f rotation) {
		this.rotation = rotation;
	}

	public void setScalingVector(Vector3f scaling) {
		this.scaling = scaling;
	}

	public static void setCamera(Camera camera) {
		Transform.camera = camera;
	}

}
