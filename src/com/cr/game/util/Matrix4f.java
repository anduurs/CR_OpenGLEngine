package com.cr.game.util;

/**
 * Class for representing a 4x4 matrix
 * @author Anders
 *
 */
public class Matrix4f {
	
	private float[][] matrix;
	
	/**
	 * Creates a new 4x4 matrix
	 */
	public Matrix4f(){
		matrix = new float[4][4];
	}
	
	/**
	 * Sets the current matrix to the identity matrix
	 * @return this matrix transformed to the identity matrix
	 */
	public Matrix4f loadIdentityMatrix(){
		matrix[0][0] = 1;	matrix[0][1] = 0;	matrix[0][2] = 0;	matrix[0][3] = 0;
		matrix[1][0] = 0;	matrix[1][1] = 1;	matrix[1][2] = 0;	matrix[1][3] = 0;
		matrix[2][0] = 0;	matrix[2][1] = 0;	matrix[2][2] = 1;	matrix[2][3] = 0;
		matrix[3][0] = 0;	matrix[3][1] = 0;	matrix[3][2] = 0;	matrix[3][3] = 1;
		
		return this;
	}
	
	/**
	 * Sets the current matrix to the translation matrix
	 * @param x amount of translation on the x-axis
	 * @param y amount of translation on the y-axis
	 * @param z amount of translation on the z-axis
	 * @return this matrix transformed to the translation matrix
	 */
	public Matrix4f initTranslationMatrix(float x, float y, float z){
		matrix[0][0] = 1;	matrix[0][1] = 0;	matrix[0][2] = 0;	matrix[0][3] = x;
		matrix[1][0] = 0;	matrix[1][1] = 1;	matrix[1][2] = 0;	matrix[1][3] = y;
		matrix[2][0] = 0;	matrix[2][1] = 0;	matrix[2][2] = 1;	matrix[2][3] = z;
		matrix[3][0] = 0;	matrix[3][1] = 0;	matrix[3][2] = 0;	matrix[3][3] = 1;
		
		return this;
	}
	
	/**
	 * Sets the current matrix to the scaling matrix
	 * @param x amount of scaling on the x-axis
	 * @param y amount of scaling on the y-axis
	 * @param z amount of scaling on the z-axis
	 * @return this matrix transformed to the scaling matrix
	 */
	public Matrix4f initScalingMatrix(float x, float y, float z){
		matrix[0][0] = x;	matrix[0][1] = 0;	matrix[0][2] = 0;	matrix[0][3] = 0;
		matrix[1][0] = 0;	matrix[1][1] = y;	matrix[1][2] = 0;	matrix[1][3] = 0;
		matrix[2][0] = 0;	matrix[2][1] = 0;	matrix[2][2] = z;	matrix[2][3] = 0;
		matrix[3][0] = 0;	matrix[3][1] = 0;	matrix[3][2] = 0;	matrix[3][3] = 1;
		
		return this;
	}
	
	/**
	 * Sets the current matrix to the rotation matrix
	 * @param x the angle in degrees which will be rotated around the x-axis
	 * @param y the angle in degrees which will be rotated around the y-axis
	 * @param z the angle in degrees which will be rotated around the z-axis
	 * @return this matrix transformed to the rotation matrix
	 */
	public Matrix4f initRotationMatrix(float x, float y, float z){
		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();

		x = (float)Math.toRadians(x);
		y = (float)Math.toRadians(y);
		z = (float)Math.toRadians(z);

		rz.matrix[0][0] = (float)Math.cos(z);		rz.matrix[0][1] = -(float)Math.sin(z);		rz.matrix[0][2] = 0;				    	rz.matrix[0][3] = 0;
		rz.matrix[1][0] = (float)Math.sin(z);		rz.matrix[1][1] = (float)Math.cos(z);		rz.matrix[1][2] = 0;						rz.matrix[1][3] = 0;
		rz.matrix[2][0] = 0;						rz.matrix[2][1] = 0;						rz.matrix[2][2] = 1;						rz.matrix[2][3] = 0;
		rz.matrix[3][0] = 0;						rz.matrix[3][1] = 0;						rz.matrix[3][2] = 0;						rz.matrix[3][3] = 1;

		rx.matrix[0][0] = 1;						rx.matrix[0][1] = 0;						rx.matrix[0][2] = 0;						rx.matrix[0][3] = 0;
		rx.matrix[1][0] = 0;						rx.matrix[1][1] = (float)Math.cos(x);		rx.matrix[1][2] = -(float)Math.sin(x);		rx.matrix[1][3] = 0;
		rx.matrix[2][0] = 0;						rx.matrix[2][1] = (float)Math.sin(x);		rx.matrix[2][2] = (float)Math.cos(x);		rx.matrix[2][3] = 0;
		rx.matrix[3][0] = 0;						rx.matrix[3][1] = 0;						rx.matrix[3][2] = 0;						rx.matrix[3][3] = 1;

		ry.matrix[0][0] = (float)Math.cos(y);		ry.matrix[0][1] = 0;						ry.matrix[0][2] = -(float)Math.sin(y);		ry.matrix[0][3] = 0;
		ry.matrix[1][0] = 0;						ry.matrix[1][1] = 1;						ry.matrix[1][2] = 0;						ry.matrix[1][3] = 0;
		ry.matrix[2][0] = (float)Math.sin(y);		ry.matrix[2][1] = 0;						ry.matrix[2][2] = (float)Math.cos(y);		ry.matrix[2][3] = 0;
		ry.matrix[3][0] = 0;						ry.matrix[3][1] = 0;						ry.matrix[3][2] = 0;						ry.matrix[3][3] = 1;

		matrix = rz.mul(ry.mul(rx)).getMatrix();

		return this;
	}
	
	/**
	 * Sets this matrix to the orthographic projection matrix
	 * @param left
	 * @param right
	 * @param bottom
	 * @param top
	 * @param near
	 * @param far
	 * @return this matrix transformed to the orthographic projection matrix
	 */
	public Matrix4f initOrthographicProjection(float left, float right, float bottom, float top, float near, float far){
		matrix[0][0] = 2/(right - left);	matrix[0][1] = 0;					matrix[0][2] = 0;				matrix[0][3] = -(right + left)/(right - left);
		matrix[1][0] = 0;					matrix[1][1] = 2/(top - bottom);	matrix[1][2] = 0;				matrix[1][3] = -(top + bottom)/(top - bottom);
		matrix[2][0] = 0;					matrix[2][1] = 0;					matrix[2][2] = 2/(far - near);	matrix[2][3] = -(far + near)/(far - near);
		matrix[3][0] = 0;					matrix[3][1] = 0;					matrix[3][2] = 0;				matrix[3][3] = 1;

		return this;
	}
	
	public Matrix4f initPerspectiveProjection(float fov, float width, float height, float zNear, float zFar){
		float aspectRatio = width/height;
		float tanHalfFOV = (float)Math.tan(fov / 2);
		float zRange = zNear - zFar;

		matrix[0][0] = 1.0f / (tanHalfFOV * aspectRatio);	matrix[0][1] = 0;					matrix[0][2] = 0;						matrix[0][3] = 0;
		matrix[1][0] = 0;									matrix[1][1] = 1.0f / tanHalfFOV;	matrix[1][2] = 0;						matrix[1][3] = 0;
		matrix[2][0] = 0;									matrix[2][1] = 0;					matrix[2][2] = (-zNear -zFar)/zRange;	matrix[2][3] = 2 * zFar * zNear / zRange;
		matrix[3][0] = 0;									matrix[3][1] = 0;					matrix[3][2] = 1;						matrix[3][3] = 0;

		return this;
	}
	
	/**
	 * Performs matrix multiplication between this matrix and the given
	 * @param r the matrix which will be multiplied with this matrix
	 * @return the matrix which is the result of the matrix multiplication
	 */
	public Matrix4f mul(Matrix4f r){
		Matrix4f res = new Matrix4f();

		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				res.set(i, j, matrix[i][0] * r.get(0, j) +
							  matrix[i][1] * r.get(1, j) +
							  matrix[i][2] * r.get(2, j) +
							  matrix[i][3] * r.get(3, j));
			}
		}

		return res;
	}
	
	public float[][] getMatrix(){
		float[][] res = new float[4][4];

		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				res[i][j] = matrix[i][j];

		return res;
	}

	public float get(int x, int y){
		return matrix[x][y];
	}
	
	public void setMatrix(float[][] m){
		this.matrix = m;
	}

	public void set(int x, int y, float value){
		matrix[x][y] = value;
	}

}
