/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.systems;

import java.util.Set;

import mox.components.PositionComponent;
import mox.entities.Entity;
import mox.entities.EntityManager;
import mox.MessageRouter;
import mox.messages.PositionChangedMessage;
import mox.System;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Mihail
 */
public class PositionSystem extends System
{

    public PositionSystem(EntityManager manager, MessageRouter router)
    {
        super(manager, router);
    }

    public Set<Entity> getEntities()
    {
        return getEntityManager().getEntities(PositionComponent.class);
    }
    
    @Override
    public void init(GameContainer gc)
    {
        // Nothing.
    }

    @Override
    public void update(GameContainer gc, int delta)
    {
        getEntities().stream().forEach((entity) ->
        {
            PositionComponent pComponent = entity.getComponentAs(PositionComponent.class);
            
            Vector2f position = pComponent.getPosition();
            Vector2f velocity = pComponent.getVelocity().copy();
            
            if(velocity.lengthSquared() != 0)
            {
                position.add(velocity.scale((float) delta / 1000F));
                getMessageRouter().send(new PositionChangedMessage(
                        entity.getHandle(), position));
            }
        });
    }

}
