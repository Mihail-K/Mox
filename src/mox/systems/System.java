/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.systems;

import mox.entities.EntityManager;

/**
 *
 * @author Mihail
 */
public abstract class System
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
    
    public abstract void update(double delta);
    
}
