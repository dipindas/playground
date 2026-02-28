package com.demo.playground.test3;
import java.util.ArrayList;
import java.util.LinkedList;

public class HashMapImplementation {
    public static void main(String[] args) {

    }
}

class HashMapImpl {
    static class Node{
        private String key;
        private Integer value;
        public Node(){}

        public Node(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

    }

    private ArrayList<LinkedList<Node>> customHashMap;
    private static int INITIAL_SIZE = 16;

    public HashMapImpl() {
        customHashMap = new ArrayList<>();
        for(int i=0; i<INITIAL_SIZE; i++) {
            customHashMap.add(new LinkedList<>());
        }
    }

    private void put(String key, Integer value) {
        Integer hashCode = (key == null) ? 0 : Math.abs(key.hashCode()) % INITIAL_SIZE;
        LinkedList<Node> ll = customHashMap.get(hashCode);
        for(Node node : ll) {
            if(key == null && node.getKey() == null) {
                node.setValue(value);
                return;
            }
            else if (key != null && key.equals(node.getKey())) {
                node.setValue(value);
                return;
            }
        }
        Node node = new Node(key, value);
        ll.add(node);
    }

    private Integer get(String key) {
        Integer hashCode = (key == null) ? 0 : Math.abs(key.hashCode()) % INITIAL_SIZE;
        LinkedList<Node> ll = customHashMap.get(hashCode);
        for(Node node : ll) {
            if(key == null && node.getKey() == null) {
                return node.getValue();
            }
            else if(key != null && key.equals(node.getKey())) {
                return node.getValue();
            }
        }
        return null;
    }

    public static void main(String args[]) {
        HashMapImpl hashMap = new HashMapImpl();
        hashMap.put("ab", 10);
        hashMap.put("ab", 20);
        hashMap.put("abc", 30);
        hashMap.put("abd", 40);
        hashMap.put("abe", 50);

        System.out.println(hashMap.get("ab"));
        System.out.println(hashMap.get("abc"));
        System.out.println(hashMap.get("abe"));
        System.out.println(hashMap.get("abf"));
    }
}
