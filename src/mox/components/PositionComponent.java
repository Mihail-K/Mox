/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.components;

import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Mihail
 */
public class PositionComponent implements Component
{
    
    private Vector2f position;
    private Vector2f velocity;
    
    public PositionComponent()
    {
        position = new Vector2f();
        velocity = new Vector2f();
    }
    
    public PositionComponent(Vector2f position, Vector2f velocity)
    {
        this.position = position;
        this.velocity = velocity;
    }

    public Vector2f getPosition()
    {
        return position;
    }

    public Vector2f getVelocity()
    {
        return velocity;
    }

    public void setPosition(Vector2f position)
    {
        this.position = position;
    }

    public void setVelocity(Vector2f velocity)
    {
        this.velocity = velocity;
    }
    
}
