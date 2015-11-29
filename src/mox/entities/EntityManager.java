/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.entities;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import mox.components.Component;
import mox.handles.HandleManager;

/**
 *
 * @author Mihail
 */
public class EntityManager
{
    
    private final HandleManager<Entity> entities;
    private final Map<Class<?>, Set<Long>> lookups;
    
    /* this() */
    {
        lookups = new HashMap<>();
    }
    
    public EntityManager()
    {
        entities = new HandleManager<>();
    }
    
    public EntityManager(int capacity)
    {
        entities = new HandleManager<>(capacity);
    }
    
    public boolean addComponent(long handle, Component component)
    {
        Entity entity = entities.get(handle);
        if(entity == null) return false;
        
        if(!entity.hasComponent(component.getClass()))
        {
            entity.addComponent(component);
            registerComponent(handle, component);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public boolean addComponent(long handle, Class<?> type)
            throws InstantiationException, IllegalAccessException
    {
        Component component = (Component) type.newInstance();
        return addComponent(handle, component);
    }
    
    public long addEntity()
    {
        Entity entity = EntityFactory.createEntity();
        long handle = entities.add(entity);
        entity.setHandle(handle);
        return handle;
    }
    
    public long addEntity(Component... components)
    {
        Entity entity = EntityFactory.createEntity(components);
        long handle = entities.add(entity);
        entity.setHandle(handle);
        registerComponents(entity);
        return handle;
    }
    
    public long addEntity(Class<?>... types)
            throws InstantiationException, IllegalAccessException
    {
        Entity entity = EntityFactory.createEntity(types);
        long handle = entities.add(entity);
        entity.setHandle(handle);
        registerComponents(entity);
        return handle;
    }
    
    public boolean hasEntity(long handle)
    {
        return entities.get(handle) != null;
    }
    
    public Entity getEntity(long handle)
    {
        return entities.get(handle);
    }
    
    public Set<Component> getComponents(Class<?> type)
    {
        return Collections.unmodifiableSet(getHandles(type).stream()
                .map(this::getEntity).map(e -> e.getComponent(type))
                .collect(Collectors.toSet()));
    }
    
    public Set<Entity> getEntities(Class<?> type)
    {
        return Collections.unmodifiableSet(getHandles(type).stream()
                .map(this::getEntity).collect(Collectors.toSet()));
    }
    
    public Set<Long> getHandles(Class<?> type)
    {
        Set<Long> handles = lookups.get(type);
        
        if(handles != null)
        {
            return Collections.unmodifiableSet(handles.stream()
                    .collect(Collectors.toSet()));
        }
        else
        {
            return Collections.emptySet();
        }
    }
    
    public boolean removeComponent(long handle, Class<?> type)
    {
        Entity entity = entities.get(handle);
        
        if(entity != null)
        {
            return entity.removeComponent(type);
        }
        else
        {
            return false;
        }
    }
    
    public boolean removeEntity(long handle)
    {
        Entity entity = entities.get(handle);
        if(entity == null) return false;
        
        unregisterComponents(entity);
        entities.remove(handle);
        return true;
    }
    
    private void registerComponents(Entity entity)
    {
        entity.getComponents().stream().forEach((component) ->
        {
            registerComponent(entity.getHandle(), component);
        });
    }
    
    private void registerComponent(long handle, Component component)
    {
        Set<Long> handles = lookups.get(component.getClass());
        if(handles == null)
        {
            handles = new HashSet<>();
            lookups.put(component.getClass(), handles);
        }
        handles.add(handle);
    }
    
    private void unregisterComponents(Entity entity)
    {
        entity.getComponents().stream().forEach((component) ->
        {
            unregisterComponent(entity.getHandle(), component);
        });
    }
    
    private void unregisterComponent(long handle, Component component)
    {
        Set<Long> handles = lookups.get(component.getClass());
        if(handles != null)
        {
            handles.remove(handle);

            if(handles.isEmpty())
            {
                lookups.remove(component.getClass());
            }
        }
    }
    
}
