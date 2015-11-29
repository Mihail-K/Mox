/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.systems;

import java.util.ArrayDeque;
import java.util.Queue;

import mox.components.SpriteComponent;
import mox.entities.Entity;
import mox.entities.EntityManager;
import mox.Message;
import mox.MessageRouter;
import mox.messages.PositionChangedMessage;
import mox.System;
import org.newdawn.slick.GameContainer;

/**
 *
 * @author Mihail
 */
public class SpriteSystem extends System
{
    
    private final Queue<Message> queue = new ArrayDeque<>();

    public SpriteSystem(EntityManager manager, MessageRouter router)
    {
        super(manager, router);
    }
    
    @Override
    public void consume(Message message)
    {
        queue.add(message);
    }
    
    @Override
    public void init(GameContainer gc)
    {
        getMessageRouter().subscribe("position-changed", this);
    }

    @Override
    public void update(GameContainer gc, int delta)
    {
        Message message;
        while((message = queue.poll()) != null)
        {
            switch(message.getName())
            {
                case "position-changed":
                    positionChanged((PositionChangedMessage) message);
                    break;
            }
        }
    }
    
    private void positionChanged(PositionChangedMessage message)
    {
        Entity entity = getEntityManager().getEntity(message.getHandle());
        if(entity == null) return;
        
        SpriteComponent sComponent = entity.getComponentAs(SpriteComponent.class);
        if(sComponent == null) return;
        
        sComponent.getShape().setLocation(message.getPosition());
    }
    
}
