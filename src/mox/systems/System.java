/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.systems;

import mox.entities.EntityManager;
import mox.messages.MessageConsumer;
import mox.messages.MessageRouter;

/**
 *
 * @author Mihail
 */
public abstract class System implements MessageConsumer
{

    private final EntityManager manager;

    public System(EntityManager manager)
    {
        this.manager = manager;
    }

    public EntityManager getManager()
    {
        return manager;
    }
    
    public void subscribe(MessageRouter router)
    {
        // Children may implement as necessary.
    }

    public abstract void update(int delta);

}
