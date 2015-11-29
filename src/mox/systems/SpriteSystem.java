/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.systems;

import java.util.LinkedList;
import java.util.Queue;

import mox.components.SpriteComponent;
import mox.entities.Entity;
import mox.entities.EntityManager;
import mox.messages.Message;
import mox.messages.MessageRouter;
import mox.messages.PositionChangedMessage;

/**
 *
 * @author Mihail
 */
public class SpriteSystem extends System
{
    
    private final Queue<Message> queue = new LinkedList<>();

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
    public void init()
    {
        getMessageRouter().subscribe("position-changed", this);
    }

    @Override
    public void update(int delta)
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
