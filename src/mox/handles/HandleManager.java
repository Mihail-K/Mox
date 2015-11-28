/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mox.handles;

/**
 *
 * @author Mihail
 * @param <T>
 */
public class HandleManager<T>
{
    
    private static class HandleEntry
    {
        
        boolean active;
        int version;
        int nextIndex;
        
        Object object;
        
        HandleEntry(int nextIndex)
        {
            this.nextIndex = nextIndex;
        }
        
        void increment()
        {
            version = version == Integer.MAX_VALUE ? 1 : version + 1;
        }
        
        boolean matches(long handle)
        {
            return active && version == getHandleVersion(handle);
        }
        
    }
    
    private final HandleEntry[] handles;
    
    private int length;
    private int nextIndex;
    
    public HandleManager()
    {
        this(4096);
    }
    
    public HandleManager(int capacity)
    {
        handles = new HandleEntry[capacity];
        this.reset();
    }
    
    public long add(T object)
    {
        int index = nextIndex;
        if(length >= capacity() || index >= capacity())
        {
            throw new RuntimeException("No more handles.");
        }
        
        if(handles[index].active)
        {
            throw new RuntimeException("Handle already is use.");
        }
        
        nextIndex = handles[index].nextIndex;
        handles[index].nextIndex = 0;
        
        handles[index].active = true;
        handles[index].object = object;
        handles[index].increment();
        length++;
        
        return createHandle(index, handles[index].version);
    }
    
    public int capacity()
    {
        return handles.length;
    }
    
    public boolean empty()
    {
        return length == 0;
    }
    
    public T get(long handle)
    {
        int index = getValidIndex(handle);
        if(handles[index].matches(handle))
        {
            return (T) handles[index].object;
        }
        
        return null;
    }
    
    public int length()
    {
        return length;
    }
    
    public boolean remove(long handle)
    {
        int index = getValidIndex(handle);
        if(handles[index].matches(handle))
        {
            handles[index].nextIndex = nextIndex;
            handles[index].active = false;
            nextIndex = index;
            length--;
            
            return true;
        }
        
        return false;
    }
    
    public final void reset()
    {
        nextIndex = length = 0;
        for(int index = 0; index < capacity(); index++)
        {
            int next = index + 1;
            next = (next == capacity() - 1) ? 0 : next;
            
            if(handles[index] == null)
            {
                handles[index] = new HandleEntry(next);
            }
            else
            {
                handles[index].nextIndex = next;
                handles[index].active = false;
                handles[index].object = null;
                handles[index].version = 1;
            }
        }
    }
    
    public boolean update(long handle, T object)
    {
        int index = getValidIndex(handle);
        if(handles[index].matches(handle))
        {
            handles[index].object = object;
            return true;
        }
        
        return false;
    }
    
    private int getValidIndex(long handle)
    {
        int index = getHandleIndex(handle);
        if(index < 0 || index >= handles.length)
        {
            throw new IndexOutOfBoundsException(index + " is out of bounds");
        }
        
        return index;
    }
    
    private static long createHandle(int index, int version)
    {
        return ((long) version << 32) | (long) index;
    }
    
    private static int getHandleIndex(long handle)
    {
        return (int) (handle & Integer.MAX_VALUE);
    }
    
    private static int getHandleVersion(long handle)
    {
        return (int) ((handle >> 32) & Integer.MAX_VALUE);
    }
    
}
