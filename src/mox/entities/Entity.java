/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import mox.Component;

/**
 *
 * @author Mihail
 */
public class Entity
{
    
    private long handle;
    private final Map<Class<?>, Component> components;
    
    protected Entity()
    {
        this.components = Collections.emptyMap();
    }
    
    protected Entity(Map<Class<?>, Component> components)
    {
        this.components = new HashMap<>(components);
    }
    
    protected boolean addComponent(Component component)
    {
        if(hasComponent(component.getClass()))
        {
            return false;
        }
        else
        {
            components.put(component.getClass(), component);
            return true;
        }
    }
    
    public Component getComponent(Class<?> type)
    {
        return components.get(type);
    }
    
    public<Type> Type getComponentAs(Class<Type> type)
    {
        return (Type) components.get(type);
    }
    
    public Collection<Component> getComponents()
    {
        return Collections.unmodifiableCollection(components.values());
    }
    
    public long getHandle()
    {
        return handle;
    }
    
    public boolean hasComponent(Class<?> type)
    {
        return components.containsKey(type);
    }
    
    protected boolean removeComponent(Class<?> type)
    {
        return components.remove(type) != null;
    }
    
    protected void setHandle(long handle)
    {
        this.handle = handle;
    }
    
}
