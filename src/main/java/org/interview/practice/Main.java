package org.interview.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Main {
//    public static void main(String[] args) {
//        int[] arr = {3, 5, 1, 2, 7, 8, 2, 9, 5, 3};
////        {2,2,3,3,1,5,7,8,9}
////        {3,3,5,5,2,2,5,1,7,8,9}
//
////        O(N)
//        Map<Integer, int[]> map = new HashMap<>();
//
////        O(N)
//        for (int i = 0; i < arr.length; i++) {
//            if (map.containsKey(arr[i])) {
//                int[] a = map.get(arr[i]);
//                a[0]++;
//                map.put(arr[i], a);
//            } else {
//                int[] a = new int[2];
//                a[0]++;
//                a[1] = i;
//                map.put(arr[i], a);
//            }

    /// /            System.out.println(arr[i] + " " + Arrays.toString(a));
//        }
//
//        Integer[] list = Arrays.stream(arr).boxed().toArray(Integer[]::new);
//
//        Arrays.sort(list, (a, b) -> {
//            if (map.get(a)[0] == map.get(b)[0]) return Integer.compare(map.get(a)[1], map.get(b)[1]);
//            return Integer.compare(map.get(b)[0], map.get(a)[0]);
//        });
//
//        System.out.println(Arrays.toString(list));
//    }
    public static void main(String[] args) {
        Map<Key, String> map = new HashMap<>();
        Key key1 = new Key(1, "A");
        Key key2 = new Key(1, "A");
        Key key3 = new Key(2, "B");
        map.put(key1, "Value1");
        map.put(key2, "Value2");
        map.put(key3, "Value3");
        map.put(key3, "Value3.1");

        System.out.println("Map size: " + map.size()); // 2
        System.out.println("Get key1: " + map.get(key1)); // Value2
        System.out.println("Get key2: " + map.get(key2)); // Value2
        System.out.println("Get key3: " + map.get(key3)); // Value3

    }
}

class Key {
    int id;
    String name;

    public Key(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Key)) return false;
        Key other = (Key) obj;
        return id == other.id && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

