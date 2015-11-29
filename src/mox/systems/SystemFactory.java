/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.systems;

import java.lang.reflect.InvocationTargetException;

import mox.entities.EntityManager;
import mox.messages.MessageRouter;

/**
 *
 * @author Mihail
 */
public final class SystemFactory
{
    
    private SystemFactory()
    {
        // Hidden Constructor.
    }

    public static System create(Class<?> type, EntityManager manager, MessageRouter router)
    {
        try
        {
            return (System) type.getConstructor(
                    EntityManager.class).newInstance(manager, router);
        }
        catch(NoSuchMethodException | SecurityException |
                InstantiationException | IllegalAccessException |
                IllegalArgumentException | InvocationTargetException e)
        {
            return null;
        }
    }
    
}
