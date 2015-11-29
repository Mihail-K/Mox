/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox;

import mox.entities.EntityManager;

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

    public EntityManager getEntityManager()
    {
        return manager;
    }

    public MessageRouter getMessageRouter()
    {
        return router;
    }
    
    public abstract void init();

    public abstract void update(int delta);

}
