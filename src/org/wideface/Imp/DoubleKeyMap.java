package org.wideface.Imp;

import java.util.HashMap;

public class DoubleKeyMap<K1,K2,V> {

    HashMap<K1,HashMap<K2,V>> map = new HashMap<K1,HashMap<K2,V>>();

    public boolean contains(K1 k1,K2 k2)
    {
        if(! map.containsKey(k1)) return false;
        HashMap<K2,V> map2 = map.get(k1);
        return map2.containsKey(k2);
    }

    public boolean put(K1 k1, K2 k2, V v)
    {
        if(map.containsKey(k1))
        {
            HashMap<K2,V> map2 = map.get(k1);
            return put(map2,k2,v);
        }
        else
        {
            HashMap<K2,V> map2 =new HashMap<K2,V>();
            map.put(k1,map2);
            return put(map2,k2,v);
        }
    }

    private boolean put(HashMap<K2,V> map2, K2 k2, V v )
    {
        if(map2.containsKey(k2)) return false;
        map2.put(k2,v);
        return true;
    }

    public V get(K1 k1,K2 k2)
    {
        if(contains(k1,k2))
        {
            HashMap<K2,V> map2 = map.get(k1);
            return map2.get(k2);
        }
        else
        {
            return null;
        }
    }
}
