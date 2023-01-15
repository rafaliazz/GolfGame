package com.project_1_2.group_16.models;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector2;

/**
 * Wrapper class for a wall object, used in maze courses.
 */
public class Wall {

    /**
     * Represents if the wall is the wall of a maze (has collision).
     */
    public static final int MAZE_WALL = 0;

    /**
     * Represents if the wall should behave like a body of water.
     */
    public static final int WATER = 1;

    /**
     * Standard height of the wall (above the ground).
     */
    public static final float HEIGHT = 0.25f;

    /**
     * Energy lost by hitting walls
     */
    public static final float frictionCoeficient = 0.8f;

    /**
     * If the ball has recently hit this wall. 
     */
    public boolean recentlyHit;

    private Vector2 topLeft;
    private Vector2 topRight;
    private Vector2 bottomLeft;
    private Vector2 bottomRight;

    private float width;
    private float length;

    private int type;

    private ModelInstance instance;
    
    public Wall(Vector2 topLeft, float width, float length, int type) {
        this.topLeft = topLeft;
        this.topRight = new Vector2(topLeft.x + width, topLeft.y);
        this.bottomLeft = new Vector2(topLeft.x, topLeft.y - length);
        this.bottomRight = new Vector2(topLeft.x + width, topLeft.y - length);

        this.width = width;
        this.length = length;

        this.type = type;
    }

    /**
     * Set the model of the wall
     * @param model
     */
    public void setModel(Model model) {
        this.instance = new ModelInstance(model);
        Vector2 pos = averageVector(this.topLeft, this.topRight, this.bottomLeft, this.bottomRight);
        this.instance.transform.translate(pos.x, 0.1f + HEIGHT / 2, pos.y);
    }

    /**
     * Get an instance of the wall.
     * @return a model instance
     */
    public ModelInstance getInstance() {        
        return this.instance;
    }

    public Vector2 getPosition() {
        return this.topLeft;
    }

    public float getWidth() {
        return this.width;
    }

    public float getLength() {
        return this.length;
    }

    public int getType() {
        return this.type;
    }

    /**
     * If the wall contains a pair of coordinates.
     * @param x x-coordinate
     * @param z z-coordinate
     * @return
     */
    public boolean contains(float x, float z) {
        if (x+Golfball.SIZE/2 < this.bottomLeft.x) return false;
        if (x-Golfball.SIZE/2 > this.topRight.x) return false;
        if (z+Golfball.SIZE/2 < this.bottomLeft.y) return false;
        if (z-Golfball.SIZE/2 > this.topRight.y) return false;
        return true;
    }

    /**
     * The wall closest to the coordinates.
     * @param x x-coordinate
     * @param y y-coordinate
     * @return Vector2.X if the wall is horizontal, otherwise Vector2.Y
     */
    public Vector2 closestWall(float x, float y) {
        float dstTop = this.width < this.length ? Float.MAX_VALUE : Math.abs(this.topLeft.y - y);
        float dstBottom = this.width < this.length ? Float.MAX_VALUE : Math.abs(this.bottomLeft.y - y);
        float dstLeft = this.length < this.width ? Float.MAX_VALUE : Math.abs(this.topLeft.x - x);
        float dstRight = this.length < this.width ? Float.MAX_VALUE : Math.abs(this.topRight.x - x);

        if (dstTop <= dstBottom && dstTop <= dstLeft && dstTop <= dstRight) {
            return Vector2.X; // horizontal
        }
        if (dstBottom <= dstTop && dstBottom <= dstLeft && dstBottom <= dstRight) {
            return Vector2.X;
        }
        if (dstLeft <= dstBottom && dstLeft <= dstTop && dstLeft <= dstRight) {
            return Vector2.Y; // vertical
        }
        if (dstRight <= dstBottom && dstRight <= dstLeft && dstRight <= dstTop) {
            return Vector2.Y;
        }
        throw new RuntimeException();
    }

    private Vector2 averageVector(Vector2... p) {
        float sumX = 0, sumY = 0;
        for (Vector2 v : p) {
            sumX += v.x;
            sumY += v.y;
        }
        return new Vector2(sumX, sumY).scl(1f / p.length);
    }
}
