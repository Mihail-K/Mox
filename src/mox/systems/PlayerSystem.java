/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.systems;

import mox.MessageRouter;
import mox.System;
import mox.entities.EntityManager;
import mox.messages.PlayerMovementMessage;
import mox.messages.PlayerMovementMessage.Direction;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Mihail
 */
public class PlayerSystem extends System
    implements InputProviderListener
{
    
    private static final Command UP = new BasicCommand("UP");
    private static final Command DOWN = new BasicCommand("DOWN");
    private static final Command LEFT = new BasicCommand("LEFT");
    private static final Command RIGHT = new BasicCommand("RIGHT");

    public PlayerSystem(EntityManager manager, MessageRouter router)
    {
        super(manager, router);
    }

    @Override
    public void controlPressed(Command cmd)
    {
        Direction direction = Direction.valueOf(((BasicCommand) cmd).getName());
        getMessageRouter().send(new PlayerMovementMessage(direction, false));
    }

    @Override
    public void controlReleased(Command cmd)
    {
        Direction direction = Direction.valueOf(((BasicCommand) cmd).getName());
        getMessageRouter().send(new PlayerMovementMessage(direction, true));
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
