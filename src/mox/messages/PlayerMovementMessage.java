/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.messages;

import mox.Message;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Mihail
 */
public class PlayerMovementMessage extends Message
{
    
    public static enum Direction
    {
        UP(270),
        DOWN(90),
        LEFT(180),
        RIGHT(0);
        
        public final float direction;
        
        Direction(float direction)
        {
            this.direction = direction;
        }
    }
    
    private final Direction direction;
    private final boolean released;
    
    public PlayerMovementMessage(Direction direction, boolean released)
    {
        super("player-input");
        
        this.direction = direction;
        this.released = released;
    }

    public Direction getDirection()
    {
        return direction;
    }

    public boolean isReleased()
    {
        return released;
    }
    
}
