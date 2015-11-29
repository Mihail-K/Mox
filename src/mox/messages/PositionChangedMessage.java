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
public class PositionChangedMessage extends Message
{
    
    private final long handle;
    private final Vector2f position;

    public PositionChangedMessage(long handle, Vector2f position)
    {
        super("position-changed");
        
        this.handle = handle;
        this.position = position;
    }

    public long getHandle()
    {
        return handle;
    }

    public Vector2f getPosition()
    {
        return position;
    }
    
}
