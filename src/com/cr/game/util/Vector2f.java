package com.cr.game.util;

/**
 * Class for representing a vector in a 2d coordinate space
 * 
 * @author Anders
 */
public class Vector2f {

	public float x;
	public float y;
	
	public static boolean normalizing = false;

	/**
	 * Creates a vector in 2d coordinate space with components x and y
	 * 
	 * @param x
	 *            the x component in the vector
	 * @param y
	 *            the y component in the vector
	 */
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Calculates the length of the vector
	 * 
	 * @return returns the length of the vector
	 */
	public float length() {
		if(normalizing){
			float len = (float) Math.sqrt(x * x + y * y);
			return Math.round(len);
		}else{
			return (float) Math.sqrt(x * x + y * y);
		}
	}

	/**
	 * Calculates the dot product of two vectors
	 * 
	 * @param v
	 *            the vector to be "dotted" with
	 * @return returns the value of the dot product
	 */
	public float dot(Vector2f v) {
		return x * v.x + y * v.y;
	}

	/**
	 * Normalizes the vector (turning it into the unit vector) a unit vector is
	 * a vector which has a magnitude of one Useful do determine the direction
	 * of entities, often used as the direction vector
	 * 
	 * @return returns the new normalized vector
	 */
	public Vector2f normalize() {
		normalizing = true;
		float length = length();
		return new Vector2f(x / length, y / length);
	}

	/**
	 * Rotates the vector around origo by the given angle
	 * @param angle the angle in degrees which the vector will be rotated by around origo
	 * @return returns a new rotated vector
	 */
	public Vector2f rotate(float angle) {
		float rad = (float) Math.toRadians(angle);
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);

		return new Vector2f(x * cos - y * sin, x * sin + y * cos);
	}

	/**
	 * Rotates the vector around the given vector by the given angle
	 * @param v the vector to rotate around
	 * @param angle the angle in degrees which the vector will be rotated by
	 * @return returns a new rotated vector
	 */
	public Vector2f rotate(Vector2f v, float angle){
		float rad = (float)Math.toRadians(angle);
		float cos = (float)Math.cos(rad);
		float sin = (float)Math.sin(rad);
		
		float newX = cos * (x - v.x) - sin * (y - v.y) + v.x;
		float newY = sin * (x - v.x) + cos * (y - v.y) + v.y;
		
		return new Vector2f(newX, newY);
	}

	/**
	 * Addition of two vectors
	 * 
	 * @param v
	 *            the vector to add
	 * @return returns the resulting vector of the addition
	 */
	public Vector2f add(Vector2f v) {
		return new Vector2f(x + v.x, y + v.y);
	}

	/**
	 * Adds the x and y components with a given scalar
	 * 
	 * @param scalar
	 *            the value which the components will be added with
	 * @return the new scaled vector
	 */
	public Vector2f add(float scalar) {
		return new Vector2f(x + scalar, y + scalar);
	}

	/**
	 * Subtraction of two vectors
	 * 
	 * @param v
	 *            the vector to subtract
	 * @return returns the resulting vector of the subtraction
	 */
	public Vector2f sub(Vector2f v) {
		return new Vector2f(x - v.x, y - v.y);
	}

	/**
	 * Subtracts the x and y components with a given scalar
	 * 
	 * @param scalar
	 *            the value which the components will be subtracted with
	 * @return the new scaled vector
	 */
	public Vector2f sub(float scalar) {
		return new Vector2f(x - scalar, y - scalar);
	}

	/**
	 * Multiplication of two vectors
	 * 
	 * @param v
	 *            the vector to multiply
	 * @return returns the resulting vector of the multiplication
	 */
	public Vector2f mul(Vector2f v) {
		return new Vector2f(x * v.x, y * v.y);
	}

	/**
	 * Scales a vector by multiplying the x and y components with a scalar
	 * 
	 * @param scalar
	 *            the value which the components will be multiplied with
	 * @return returns a new scaled vector
	 */
	public Vector2f mul(float scalar) {
		return new Vector2f(x * scalar, y * scalar);
	}

	/**
	 * Division of two vectors
	 * 
	 * @param v
	 *            the vector to divide
	 * @return returns the resulting vector of the division
	 */
	public Vector2f div(Vector2f v) {
		return new Vector2f(x / v.x, y / v.y);
	}

	/**
	 * Scales a vector by dividing the x and y components with a scalar
	 * 
	 * @param scalar
	 *            the value which the components will be divided with
	 * @return returns a new scaled vector
	 */
	public Vector2f div(float scalar) {
		return new Vector2f(x / scalar, y / scalar);
	}

	/**
	 * Returns a clone of the vector
	 */
	public Vector2f clone() {
		return new Vector2f(x, y);
	}

	/**
	 * Returns a string representation of the vector
	 */
	@Override
	public String toString() {
		return "(" + x + " " + y + ")";
	}
	
	public boolean equals(Vector2f v){
		return x == v.x && y == v.y;
	}

	/**
	 * Sets a new position to this vector
	 * @param x the new x position
	 * @param y the new y position
	 */
	public void set(float x, float y){
		this.x = x;
		this.y = y;
	}
}
