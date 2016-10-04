import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by zplchn on 9/4/16.
 */

//155
public class MinStack {
    Deque<Integer> st;
    Deque<Integer> mt;
    /** initialize your data structure here. */
    public MinStack() {
        st = new ArrayDeque<>();
        mt = new ArrayDeque<>();
    }

    public void push(int x) {
        st.push(x);
        if (mt.isEmpty() || x <= mt.peek())
            mt.push(x);
    }

    public void pop() {
        int x = st.pop();
        if (!mt.isEmpty() && x == mt.peek())
            mt.pop();
    }

    public int top() {
        return st.peek();
    }

    public int getMin() {
        return mt.peek();
    }

}
