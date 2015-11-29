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
    private final MessageRouter router;

    public System(EntityManager manager, MessageRouter router)
    {
        this.manager = manager;
        this.router = router;
    }

    public EntityManager getManager()
    {
        return manager;
    }

    public MessageRouter getRouter()
    {
        return router;
    }
    
    public abstract void init();

    public abstract void update(int delta);

}
