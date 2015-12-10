/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spacecolony.game.util.math;

/**
 * Class that represents a 2-dimensional vector.
 */
public class Vec2 {

    /**
     * The x component of this vector
     */
    public float x;

    /**
     * The y component of this vector
     */
    public float y;

    /**
     * Creates a new vector with 0 as its components
     */
    public Vec2() {
        this(0);
    }

    /**
     * Creates a new Vector with xy as its compoments
     *
     * @param xy the value of the components of this vector
     */
    public Vec2(float xy) {
        this(xy, xy);
    }

    /**
     * Creates a new Vector with the x and y components
     *
     * @param x the x component of the new Vector
     * @param y the y component of the new Vector
     */
    public Vec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new Vector that is the copy on the provided one
     *
     * @param o the vector to copy
     */
    public Vec2(Vec2 o) {
        this(o.x, o.y);
    }

    /**
     * Copies this vector
     *
     * @return a copy of the vector this method was called on
     */
    public Vec2 copy() {
        return new Vec2(this);
    }

    /**
     * Sets the parameters of this Vector to be equal to the provided one
     *
     * @param o the vector to set the parameters with
     * @return this vector, that was just ser to be equal to o
     */
    public Vec2 set(Vec2 o) {
        x = o.x;
        y = o.y;
        return this;
    }

    /**
     * Sets the parameters of this Vector to be both equal to xy
     *
     * @param xy the value to set the parameters of this vector
     * @return this vector
     */
    public Vec2 set(float xy) {
        x = y = xy;
        return this;
    }

    /**
     * Sets the x and y parameters of this vector to be equal to x and y,
     * respectively
     *
     * @param x the new value of the x parameter
     * @param y the new value of the y parameter
     * @return this vector
     */
    public Vec2 set(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Adds the provided vector to this one, changing this vector's parameters
     *
     * @param o the vector to add
     * @return this vector
     */
    public Vec2 add(Vec2 o) {
        x += o.x;
        y += o.y;
        return this;
    }

    /**
     * Adds x and y to this vector as if (x,y) was a vector
     *
     * @param x the x to add to this vector
     * @param y the y to add to this vector
     * @return this vector
     */
    public Vec2 add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    /**
     * Substracts the vector o to this vector (roughly <code>this -= o</code>)
     *
     * @param o the o vector to substract
     * @return this vector
     */
    public Vec2 sub(Vec2 o) {
        x -= o.x;
        y -= o.y;
        return this;
    }

    /**
     * Substracts x and y to this vector as if (x,y) was a vector
     *
     * @param x the x to substracts to this vector
     * @param y the y to substracts to this vector
     * @return this vector
     */
    public Vec2 sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    /**
     * Multiplies the componens of the vector by the components of the provided
     * vector as in : this.x *= o.x this.y *= o.y
     *
     * Important note : this operation is NOT the dot product NOR the scalar
     * product.
     *
     * @param o the vector to multibly by
     * @return this vector
     */
    public Vec2 mult(Vec2 o) {
        x *= o.x;
        y *= o.y;
        return this;
    }

    /**
     * Multiplies this vector by the scalar v
     *
     * @param v the scalar to multiply by
     * @return this vector
     */
    public Vec2 mult(float v) {
        x *= v;
        y *= v;
        return this;
    }

    /**
     * Multiplies the componens of the vector by the components of the provided
     * vector as in : this.x *= o.x this.y *= o.y Where o = (vx,vy)
     *
     * Important note : this operation is NOT the dot product NOR the scalar
     * product.
     *
     * @param vx the x parameter to multibly by
     * @param vy the y parameter to multibly by
     * @return
     */
    public Vec2 mult(float vx, float vy) {
        x *= vx;
        y *= vy;
        return this;
    }

    /**
     * Divides each of the parameters of this vector by the correcponding
     * parameter of the provided vector
     *
     * @param o the vecor to divide by
     * @return this vector
     */
    public Vec2 div(Vec2 o) {
        x /= o.x;
        y /= o.y;
        return this;
    }

    /**
     * Divides this vector by v
     *
     * @param v the number to divide by
     * @return this vector
     */
    public Vec2 div(float v) {
        x /= v;
        y /= v;
        return this;
    }

    /**
     * Calculates the square of the lenght of this vector. This method is much
     * less exensive than {@link #lenght() lenght()}.
     *
     *
     * @return the square of the lenght of this vector
     */
    public float lengthSqr() {
        return x * x + y * y;
    }

    /**
     * Calculates the lenght of this vector. If you use this method a lot (as in
     * a for loop) consider trying {@link #lengthSqr() lengthSqr()} which is
     * less expensive.
     *
     * @return the lenght of this vector
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * Normalises the vector (it makes the vector have a lenght of 1)
     *
     * @return this vector
     */
    public Vec2 normalize() {
        return div(length());
    }

    /**
     * Calculates the dot product of this vector and o as in this*o
     *
     * @param o the vector to calculate the dot product with
     * @return the dot product between this and o
     */
    public float dot(Vec2 o) {
        return x * o.x + y * o.y;
    }

    /**
     * Clamps the x and y components af this vector between min and max (the x
     * and y will be between x and y)
     *
     * @param min the minimum of the clamp
     * @param max tha maximum of the clamp
     * @return this vector
     */
    public Vec2 clamp(float min, float max) {
        if (x < min) {
            x = min;
        } else if (x > max) {
            x = max;
        }
        if (y < min) {
            y = min;
        } else if (y > max) {
            y = max;
        }
        return this;
    }

    /**
     * Tests if this vector is the null vector (if x and y are 0)
     *
     * @return if this vector is the null vector
     */
    public boolean isNull() {
        return x == 0 && y == 0;
    }

    /**
     * Returns a string representation of this vector using the following format
     * : <code>{x,y}</code>
     *
     * @return a string representation of this vector
     */
    @Override
    public String toString() {
        return "{" + x + "," + y + "}";
    }
}
