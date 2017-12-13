package com.mygdx.game.MyBaseClasses.Scene2D;

import com.badlogic.gdx.math.Vector2;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by tanulo on 2017. 12. 13..
 */

public class MyRectangle {
    public float centerX = 0, centerY = 0, width = 0, height = 0, rotation = 0;
    public float originX = 0, originY = 0;

    public MyRectangle(float centerX, float centerY, float width, float height, float rotation) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
    }

    public MyRectangle() {
    }

    public MyRectangle(float x, float y, float width, float height) {
        throw new NotImplementedException();
    }

    public Vector2[] getCorners() {
        Vector2[] vector2 = new Vector2[4];
        float w2 = width/2;
        float h2 = height/2;
        float radius = (float) Math.sqrt(h2*h2 + w2*w2);
        float radrot = (float) Math.toRadians(rotation);
        float angle = (float) Math.asin(h2 / radius);
        vector2[0] = new Vector2(w2 + centerX + radius * (float) Math.cos(radrot - angle), h2 + centerY + radius * (float) Math.sin(radrot - angle));
        vector2[1] = new Vector2(w2 + centerX + radius * (float) Math.cos(radrot + angle), h2 + centerY + radius * (float) Math.sin(radrot + angle));
        vector2[2] = new Vector2(w2 + centerX + radius * (float) Math.cos(radrot + PI - angle), h2 + centerY + radius * (float) Math.sin(radrot + PI - angle));
        vector2[3] = new Vector2(w2 + centerX + radius * (float) Math.cos(radrot + PI + angle), h2 + centerY + radius * (float) Math.sin(radrot + PI + angle));
        return vector2;
    }


    public void rotate(float originX, float originY, float degree) {
        throw new NotImplementedException();
    }

    public void setRotation(float originX, float originY, float degree) {
        throw new NotImplementedException();
    }

    static float PI = (float) Math.PI;

    //https://forums.coronalabs.com/topic/39094-code-for-rotated-rectangle-collision-detection/
    static boolean overlaps(MyRectangle objA, MyRectangle objB) {
        //x10, y10 is centre point of rect1. x20, y20 is centre point of rect2
        //height1, width1 are half heights/widths of rect1, radrot is rotation of rect in radians

        float height1 = objA.height / 2;
        float height2 = objB.height / 2;

        float width1 = objA.width / 2;
        float width2 = objB.width / 2;

        float radrot1 = (float) Math.toRadians(objA.rotation);
        float radrot2 = (float) Math.toRadians(objB.rotation);

        float radius1 = (float) Math.sqrt(height1 * height1 + width1 * width1);
        float radius2 = (float) Math.sqrt(height2 * height2 + width2 * width2);

        float angle1 = (float) Math.asin(height1 / radius1);
        float angle2 = (float) Math.asin(height2 / radius2);

        float x1[] = new float[5];
        float y1[] = new float[5];
        float x2[] = new float[5];
        float y2[] = new float[5];

        x1[1] = objA.centerX + radius1 * (float) Math.cos(radrot1 - angle1);
        y1[1] = objA.centerY + radius1 * (float) Math.sin(radrot1 - angle1);
        x1[2] = objA.centerX + radius1 * (float) Math.cos(radrot1 + angle1);
        y1[2] = objA.centerY + radius1 * (float) Math.sin(radrot1 + angle1);
        x1[3] = objA.centerX + radius1 * (float) Math.cos(radrot1 + PI - angle1);
        y1[3] = objA.centerY + radius1 * (float) Math.sin(radrot1 + PI - angle1);
        x1[4] = objA.centerX + radius1 * (float) Math.cos(radrot1 + PI + angle1);
        y1[4] = objA.centerY + radius1 * (float) Math.sin(radrot1 + PI + angle1);

        x2[1] = objB.centerX + radius2 * (float) Math.cos(radrot2 - angle2);
        y2[1] = objB.centerY + radius2 * (float) Math.sin(radrot2 - angle2);
        x2[2] = objB.centerX + radius2 * (float) Math.cos(radrot2 + angle2);
        y2[2] = objB.centerY + radius2 * (float) Math.sin(radrot2 + angle2);
        x2[3] = objB.centerX + radius2 * (float) Math.cos(radrot2 + PI - angle2);
        y2[3] = objB.centerY + radius2 * (float) Math.sin(radrot2 + PI - angle2);
        x2[4] = objB.centerX + radius2 * (float) Math.cos(radrot2 + PI + angle2);
        y2[4] = objB.centerY + radius2 * (float) Math.sin(radrot2 + PI + angle2);

        float[] axisx = new float[5];
        float[] axisy = new float[5];

        axisx[1] = x1[1] - x1[2];
        axisy[1] = y1[1] - y1[2];
        axisx[2] = x1[3] - x1[2];
        axisy[2] = y1[3] - y1[2];

        axisx[3] = x2[1] - x2[2];
        axisy[3] = y2[1] - y2[2];
        axisx[4] = x2[3] - x2[2];
        axisy[4] = y2[3] - y2[2];

        for (int k = 1; k <= 4; k++) {

            float proj = x1[1] * axisx[k] + y1[1] * axisy[k];

            float minProj1 = proj;
            float maxProj1 = proj;

            for (int i = 2; i <= 4; i++) {
                proj = x1[i] * axisx[k] + y1[i] * axisy[k];

                if (proj < minProj1) {
                    minProj1 = proj;
                } else if (proj > maxProj1) {
                    maxProj1 = proj;
                }
            }

            proj = x2[1] * axisx[k] + y2[1] * axisy[k];

            float minProj2 = proj;
            float maxProj2 = proj;

            for (int j = 2; j <= 4; j++) {
                proj = x2[j] * axisx[k] + y2[j] * axisy[k];
                if (proj < minProj2) {
                    minProj2 = proj;
                } else if (proj > maxProj2) {
                    maxProj2 = proj;
                }
            }
            if (maxProj2 < minProj1 || maxProj1 < minProj2) {
                return false;
            }
        }
        return true;
    }

    public boolean overlaps(MyRectangle other) {
        return overlaps(this, other);
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void setPosition(float centerX, float centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }


    public static void main(String[] args) {
        MyRectangle r1 = new MyRectangle(1, 1, 2, 2, 0);
        MyRectangle r2 = new MyRectangle(3.2f, 1, 2, 2, 0);
        for (float f = 0; f < 360; f += 10) {
            r2.rotation = f;
            System.out.println(f + " - " + MyRectangle.overlaps(r1, r2));
        }
    }
}

