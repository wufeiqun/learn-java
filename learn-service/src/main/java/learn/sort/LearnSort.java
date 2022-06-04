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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
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
}
