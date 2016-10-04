import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by zplchn on 9/10/16.
 */
public class LinkedList {

    class ListNode{
        int val;
        ListNode next;
        ListNode(int x){val = x;}
    }

    //2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        int carry = 0, sum = 0;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (l1 != null || l2 != null || carry != 0){
            sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            l1 = l1 != null ? l1.next : l1;
            l2 = l2 != null ? l2.next : l2;
        }
        return dummy.next;
    }

    //19
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n < 1)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode l, r;
        l = r = dummy;
        while (r != null && r.next != null && n-- > 0){
            r = r.next;
        }
        if (n > 0) //dont use n != 0 n is possible become - 1
            return head;
        while (r != null && r.next != null){
            r = r.next;
            l = l.next;
        }
        l.next = l.next.next;
        return dummy.next;
    }

    //21
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            }
            else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null)
            cur.next = l1;
        if (l2 != null)
            cur.next = l2;
        return dummy.next;
    }

    //23
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        Queue<ListNode> pq = new PriorityQueue<>((l1, l2) -> l1.val - l2.val);
        for (ListNode ln : lists){
            if (ln != null)
                pq.offer(ln);
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (!pq.isEmpty()){
            ListNode ln = pq.poll();
            cur.next = ln;
            cur = cur.next;
            if (ln.next != null)
                pq.offer(ln.next);
        }
        cur.next = null;
        return dummy.next;
    }

    //24
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy, cur = head;

        while (cur != null && cur.next != null){
            ListNode next = cur.next.next;
            pre.next = cur.next;
            cur.next.next = cur;
            cur.next = next;
            pre = cur;
            cur = next;
        }
        return dummy.next;
    }

    //82
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy, cur = head, next = head;
        while (cur != null){
            next = cur.next;
            while (next != null && next.val == cur.val)
                next = next.next;
            if (cur.next == next){
                pre.next = cur;
                pre = pre.next;
            }
            cur = next;
        }
        pre.next = null;
        return dummy.next;
    }

    //83
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return head;
        ListNode pre = head, cur = head.next;
        while (cur != null){
            while (cur != null && cur.val == pre.val)
                cur = cur.next;
            pre.next = cur;
            pre = cur;
            cur = cur == null ? cur : cur.next;
        }
        return head;
    }

    //86
    public ListNode partition(ListNode head, int x) {
        if (head == null)
            return head;
        ListNode lh = new ListNode(0);
        ListNode gh = new ListNode(0);
        ListNode lc = lh, gc = gh;

        while (head != null){
            if (head.val < x) {
                lc.next = head;
                lc = lc.next;
            }
            else {
                gc.next = head;
                gc = gc.next;
            }
            head = head.next;
        }
        if (gc != null)
            gc.next = null;
        lc.next = gh.next;
        return lh.next;
    }

    //141
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) //not value
                return true;
        }
        return false;
    }

    //142
    public ListNode detectCycle(ListNode head) {
        //http://www.cnblogs.com/hiddenfox/p/3408931.html
        if (head == null || head.next == null)
            return null;
        ListNode slow, fast;
        slow = fast = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle)
            return null;
        slow = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //160
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        int lenA = 1, lenB = 1;
        ListNode ta = headA, tb = headB;
        while (ta.next != null){
            ta = ta.next;
            ++lenA;
        }
        while (tb.next != null){
            tb = tb.next;
            ++lenB;
        }
        if (ta != tb)
            return null;
        ta = headA;
        tb = headB;
        while (lenA - lenB > 0){
            ta = ta.next;
            --lenA;
        }
        while (lenB - lenA > 0){
            tb = tb.next;
            --lenB;
        }
        while (ta != tb){
            ta = ta.next;
            tb = tb.next;
        }
        return ta;
    }

    //203
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return head;
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy, cur = head;

        while (cur != null){
            while (cur != null && cur.val == val)
                cur = cur.next;
            pre.next = cur;
            cur = cur != null ? cur.next : cur;
            pre = pre.next;
        }
        if (pre != null)
            pre.next = null;
        return dummy.next;
    }

    //206
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //237
    public void deleteNode(ListNode node) {
        if (node == null || node.next == null)
            return;
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
