package com.zrf.reader.utils;

import java.util.Map;

/**
 * User: zrf (625001446aq@gmail.com)
 * DateTime: 2016-03-18 22:02
 */
public class MapUtils {

    public static final boolean isEmpty(Map map){
        if(map!=null && map.size()>0){
            return false;
        }
        return true;
    }
}
