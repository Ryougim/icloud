package test;

import algorithm.bus.BusStation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
//        Map<String, String> map1 = new HashMap();
//        map1.put("id", "1");
//        map1.put("name", "张三");
//
//        Map<String, String> map2 = new HashMap();
//        map2.put("id", "2");
//        map2.put("name", "李四");
//
//        List<Map<String, String>> list1 = new ArrayList<>();
//        list1.add(map1);
//        list1.add(map2);
//
//        Map<String, String> map3 = new HashMap();
//        map3.put("id", "3");
//        map3.put("name", "王五");
//
//        Map<String, String> map4 = new HashMap();
//        map4.put("id", "4");
//        map4.put("name", "赵六");
//
//        List<Map<String, String>> list2 = new ArrayList<>();
//        list2.add(map3);
//        list2.add(map4);
//
//        Map<String, List<Map<String, String>>> map11 = new HashMap();
//        map11.put("list", list1);
//
//        Map<String, List<Map<String, String>>> map22 = new HashMap();
//        map22.put("list", list2);
//
//        List<Map<String, List<Map<String, String>>>> list = new ArrayList<>();
//        list.add(map11);
//        list.add(map22);
//
//        String s = list.stream().flatMap(f -> f.get("list").stream()).map(m -> m.get("name"))
//                .collect(Collectors.joining("','", "'", "'"));
//
//        System.out.println("s = " + s);
//
//        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap(10);
//        concurrentHashMap.put("a", "1");
//        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList();
//        copyOnWriteArrayList.add("1");
//        BusStation s = new BusStation("1");
//        System.out.println(s.getClass().getClassLoader());

//        System.out.println(3/10);

//        ArrayList<Integer> a = new ArrayList<>();
//        ArrayList b = a;
//        b.add("ss");
//        System.out.println(a.get(0));
//
//        Map map = new HashMap();
//        map.values();

        String a = "a";
        int c = (int) a.toCharArray()[0];
        System.out.println(c);
    }
}
