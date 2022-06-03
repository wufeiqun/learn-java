package learn.test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 吴飞群
 * @createTime 2022/05/20
 */
public class Test {

    private static class Node {
        private final int key;
        private String value;
        private Node next;

        public Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    private static class SinglyLinkedList {

        // head节点是单链表的开始，不用来存储数据
        private final Node head = new Node(0, null);

        /**
         * 将节点添加到尾部
         *
         * @param node
         */
        public void add(Node node) {
            Node temp = head;
            while (true) {
                if (temp.next == null) {
                    temp.next = node;
                    break;
                }
                temp = temp.next;
            }
        }

        /**
         * 按顺序添加节点
         *
         * @param node
         */
        public void addOfOrder(Node node) {
            Node temp = head;
            while (true) {
                if (temp.next == null) {
                    temp.next = node;
                    break;
                } else if(temp.next.key > node.getKey()){
                    node.next = temp.next;
                    temp.next = node;
                    break;
                }
                temp = temp.next;
            }
        }

        /**
         * 获取某个节点
         *
         * @param key
         * @return
         */
        public Node get(int key) {
            if (head.next == null) {
                return null;
            }
            Node temp = head.next;
            while (temp != null) {
                if (temp.key == key) {
                    return temp;
                }
                temp = temp.next;
            }
            return null;
        }

        /**
         * 移除一个节点
         *
         * @param node
         */
        public void remove(Node node) {
            Node temp = head;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                if (temp.next.key == node.key) {
                    temp.next = temp.next.next;
                    break;
                }
                temp = temp.next;
            }
        }

        /**
         * 修改一个节点
         *
         * @param node
         */
        public void update(Node node) {
            Node temp = head.next;
            while (true) {
                if (temp == null) {
                    break;
                }
                if (temp.key == node.key) {
                    temp.value = node.value;
                    break;
                }
                temp = temp.next;
            }
        }

        /**
         * 打印链表
         */
        public void print() {
            Node temp = head.next;
            while (temp != null) {
                System.out.println(temp.toString());
                temp = temp.next;
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        HashMap<Integer, String> map1 = new HashMap<>();
        ConcurrentHashMap<Integer, String> map2 = new ConcurrentHashMap<>();

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    map1.put(j, "OK");
                    map2.put(j, "OK");
                }
            }).start();
        }

        Thread.sleep(1000);

        System.out.println(map1.size());
        System.out.println(map2.size());

    }
}
