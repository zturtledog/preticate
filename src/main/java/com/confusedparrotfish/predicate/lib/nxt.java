package com.confusedparrotfish.predicate.lib;

import java.util.HashMap;

public class nxt<T> {
    HashMap<T,Object> intr = new HashMap<T,Object>();

    public nxt<T> set(T key, Object val) {
        intr.put(key, val);
        return this;
    }
    
    public Object get(T str) {
        return intr.get(str);
    }
}
