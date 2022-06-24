package com.evoldev.searchengine.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>MapUtils</h1>
 * Utils class for {@link Map} implementations.
 */
public class MapUtils {

    /**
     * Orders a {@link Map} by value in descendant order.
     *
     * @param map Map to be ordered.
     * @return the ordered map as {@link LinkedHashMap}
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValueDescendant(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.<K, V>comparingByValue().reversed());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
