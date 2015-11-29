/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.messages;

import mox.Message;

/**
 *
 * @author Mihail
 */
public class PlayerInputMessage extends Message
{
    
    public static enum InputType
    {
        PRESSED,
        RELEASED
    }
    
    private final String key;
    private final InputType type;
    
    public PlayerInputMessage(String key, InputType type)
    {
        super("player-input");
        
        this.key = key;
        this.type = type;
    }

    public String getKey()
    {
        return key;
    }

    public InputType getType()
    {
        return type;
    }
    
}
