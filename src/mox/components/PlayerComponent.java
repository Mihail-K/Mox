/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.components;

import mox.Component;

/**
 *
 * @author Mihail
 */
public class PlayerComponent implements Component
{
    
    private boolean controlled;
    private float speed;

    public PlayerComponent(boolean controlled)
    {
        this(controlled, 50F);
    }

    public PlayerComponent(boolean controlled, float speed)
    {
        this.controlled = controlled;
        this.speed = speed;
    }

    public float getSpeed()
    {
        return speed;
    }

    public boolean isControlled()
    {
        return controlled;
    }

    public void setControlled(boolean controlled)
    {
        this.controlled = controlled;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }
    
}
