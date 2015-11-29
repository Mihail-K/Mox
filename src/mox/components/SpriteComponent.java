/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.components;

import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Mihail
 */
public class SpriteComponent implements Component
{
    
    private Shape shape;
    private boolean visible;
    
    public SpriteComponent(Shape shape)
    {
        this(shape, true);
    }
    
    public SpriteComponent(Shape shape, boolean visible)
    {
        this.shape = shape;
        this.visible = visible;
    }
    
    public Shape getShape()
    {
        return shape;
    }
    
    public boolean isVisible()
    {
        return visible;
    }

    public void setShape(Shape shape)
    {
        this.shape = shape;
    }
    
    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }
    
}
