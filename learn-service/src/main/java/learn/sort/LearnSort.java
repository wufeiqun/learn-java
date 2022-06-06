package learn.sort;

/**
 * @author 吴飞群
 * @createTime 2022/06/04
 */
public class LearnSort {

    /**
     * leetCode: 21
     * 合并两个有序链表
     */
    public ListNode mergeTwoLists_1(ListNode list1, ListNode list2) {
        ListNode merged = new ListNode(0);

        ListNode cur = merged;

        while(list1 != null && list2 != null){
            if (list1.value <= list2.value){
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        cur.next = list1 == null ? list2 : list1;

        return merged.next;
    }

    public ListNode mergeTwoLists_2(ListNode list1, ListNode list2){
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.value < list2.value){
            list1.next = mergeTwoLists_2(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists_2(list2.next, list1);
            return list2;
        }
    }
}
