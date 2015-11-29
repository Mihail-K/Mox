/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.systems;

import mox.MessageRouter;
import mox.System;
import mox.entities.EntityManager;
import mox.messages.PlayerInputMessage;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;

/**
 *
 * @author Mihail
 */
public class PlayerSystem extends System
    implements InputProviderListener
{
    
    private static final Command UP = new BasicCommand("arrow-up");
    private static final Command DOWN = new BasicCommand("arrow-down");
    private static final Command LEFT = new BasicCommand("arrow-left");
    private static final Command RIGHT = new BasicCommand("arrow-right");

    public PlayerSystem(EntityManager manager, MessageRouter router)
    {
        super(manager, router);
    }

    @Override
    public void controlPressed(Command cmd)
    {
        getMessageRouter().send(new PlayerInputMessage(
                cmd.toString(), PlayerInputMessage.InputType.PRESSED));
    }

    @Override
    public void controlReleased(Command cmd)
    {
        getMessageRouter().send(new PlayerInputMessage(
                cmd.toString(), PlayerInputMessage.InputType.RELEASED));
    }

    @Override
    public void init(GameContainer gc)
    {
        InputProvider provider = new InputProvider(gc.getInput());
        
        provider.addListener(this);
        provider.bindCommand(new KeyControl(Input.KEY_UP), UP);
        provider.bindCommand(new KeyControl(Input.KEY_DOWN), DOWN);
        provider.bindCommand(new KeyControl(Input.KEY_LEFT), LEFT);
        provider.bindCommand(new KeyControl(Input.KEY_RIGHT), RIGHT);
    }

    @Override
    public void update(GameContainer gc, int delta)
    {
        // Nothing to do.
    }
    
}
