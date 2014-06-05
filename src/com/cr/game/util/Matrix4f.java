package com.cr.game.util;

public class Matrix4f {
	
	private float[][] m;
	
	public Matrix4f(){
		m = new float[4][4];
	}
	
	public Matrix4f loadIdentity(){
		m[0][0] = 1;	m[0][1] = 0;	m[0][2] = 0;	m[0][3] = 0;
		m[1][0] = 0;	m[1][1] = 1;	m[1][2] = 0;	m[1][3] = 0;
		m[2][0] = 0;	m[2][1] = 0;	m[2][2] = 1;	m[2][3] = 0;
		m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4f initTranslationMatrix(float x, float y, float z){
		m[0][0] = 1;	m[0][1] = 0;	m[0][2] = 0;	m[0][3] = x;
		m[1][0] = 0;	m[1][1] = 1;	m[1][2] = 0;	m[1][3] = y;
		m[2][0] = 0;	m[2][1] = 0;	m[2][2] = 1;	m[2][3] = z;
		m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4f initScalingMatrix(float x, float y, float z){
		m[0][0] = x;	m[0][1] = 0;	m[0][2] = 0;	m[0][3] = 0;
		m[1][0] = 0;	m[1][1] = y;	m[1][2] = 0;	m[1][3] = 0;
		m[2][0] = 0;	m[2][1] = 0;	m[2][2] = z;	m[2][3] = 0;
		m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4f initRotationMatrix(float x, float y, float z){
		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();

		x = (float)Math.toRadians(x);
		y = (float)Math.toRadians(y);
		z = (float)Math.toRadians(z);

		rz.m[0][0] = (float)Math.cos(z);	rz.m[0][1] = -(float)Math.sin(z);	rz.m[0][2] = 0;				    	rz.m[0][3] = 0;
		rz.m[1][0] = (float)Math.sin(z);	rz.m[1][1] = (float)Math.cos(z);	rz.m[1][2] = 0;						rz.m[1][3] = 0;
		rz.m[2][0] = 0;						rz.m[2][1] = 0;						rz.m[2][2] = 1;						rz.m[2][3] = 0;
		rz.m[3][0] = 0;						rz.m[3][1] = 0;						rz.m[3][2] = 0;						rz.m[3][3] = 1;

		rx.m[0][0] = 1;						rx.m[0][1] = 0;						rx.m[0][2] = 0;						rx.m[0][3] = 0;
		rx.m[1][0] = 0;						rx.m[1][1] = (float)Math.cos(x);	rx.m[1][2] = -(float)Math.sin(x);	rx.m[1][3] = 0;
		rx.m[2][0] = 0;						rx.m[2][1] = (float)Math.sin(x);	rx.m[2][2] = (float)Math.cos(x);	rx.m[2][3] = 0;
		rx.m[3][0] = 0;						rx.m[3][1] = 0;						rx.m[3][2] = 0;						rx.m[3][3] = 1;

		ry.m[0][0] = (float)Math.cos(y);	ry.m[0][1] = 0;						ry.m[0][2] = -(float)Math.sin(y);	ry.m[0][3] = 0;
		ry.m[1][0] = 0;						ry.m[1][1] = 1;						ry.m[1][2] = 0;						ry.m[1][3] = 0;
		ry.m[2][0] = (float)Math.sin(y);	ry.m[2][1] = 0;						ry.m[2][2] = (float)Math.cos(y);	ry.m[2][3] = 0;
		ry.m[3][0] = 0;						ry.m[3][1] = 0;						ry.m[3][2] = 0;						ry.m[3][3] = 1;

		m = rz.mul(ry.mul(rx)).getM();

		return this;
	}
	
	public Matrix4f initProjectionOrtho(float left, float right, float bottom, float top, float near, float far){
		float width = right - left;
		float height = top - bottom;
		float depth = far - near;

		m[0][0] = 2/width;	m[0][1] = 0;		m[0][2] = 0;		m[0][3] = -(right + left)/width;
		m[1][0] = 0;		m[1][1] = 2/height;	m[1][2] = 0;		m[1][3] = -(top + bottom)/height;
		m[2][0] = 0;		m[2][1] = 0;		m[2][2] = -2/depth;	m[2][3] = -(far + near)/depth;
		m[3][0] = 0;		m[3][1] = 0;		m[3][2] = 0;		m[3][3] = 1;

		return this;
	}
	
	public Matrix4f initProjectionPerspective(float fov, float width, float height, float zNear, float zFar)
	{
		float ar = width/height;
		float tanHalfFOV = (float)Math.tan(Math.toRadians(fov / 2));
		float zRange = zNear - zFar;

		m[0][0] = 1.0f / (tanHalfFOV * ar);	m[0][1] = 0;					m[0][2] = 0;	m[0][3] = 0;
		m[1][0] = 0;						m[1][1] = 1.0f / tanHalfFOV;	m[1][2] = 0;	m[1][3] = 0;
		m[2][0] = 0;						m[2][1] = 0;					m[2][2] = (-zNear -zFar)/zRange;	m[2][3] = 2 * zFar * zNear / zRange;
		m[3][0] = 0;						m[3][1] = 0;					m[3][2] = 1;	m[3][3] = 0;


		return this;
	}
	
	public Matrix4f mul(Matrix4f r){
		Matrix4f res = new Matrix4f();

		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				res.set(i, j, m[i][0] * r.get(0, j) +
							  m[i][1] * r.get(1, j) +
							  m[i][2] * r.get(2, j) +
							  m[i][3] * r.get(3, j));
			}
		}

		return res;
	}
	
	public float[][] getM(){
		float[][] res = new float[4][4];

		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				res[i][j] = m[i][j];

		return res;
	}

	public float get(int x, int y){
		return m[x][y];
	}
	
	public void setM(float[][] m){
		this.m = m;
	}

	public void set(int x, int y, float value){
		m[x][y] = value;
	}

}
