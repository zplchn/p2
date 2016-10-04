import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by zplchn on 9/27/16.
 */
public class Stacks {
    //71
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0)
            return path;
        String[] token = path.split("/");
        Deque<String> deque = new ArrayDeque<>();

        for (String t : token){
            if (t.isEmpty() || t.equals("."))
                continue;
            else if (t.equals("..")){
                if (!deque.isEmpty())
                    deque.pollFirst();
            }
            else
                deque.offerFirst(t);
        }
        StringBuilder sb = new StringBuilder();
        if (deque.isEmpty())
            return "/";
        while (!deque.isEmpty()){
            sb.append('/');
            sb.append(deque.pollLast());
        }
        return sb.toString();
    }

    //225
    class MyStack {
        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        int top; //cache the top so that top is o(1)

        // Push element x onto stack.
        public void push(int x) {
            q1.offer(x);
            top = x;
        }

        // Removes the element on top of the stack.
        public void pop() {
            while (q1.size() > 1) {
                top = q1.poll();
                q2.offer(top);
            }

            q1.poll();
            Deque<Integer> t = q1;
            q1 = q2;
            q2 = t;
        }

        // Get the top element.
        public int top() {
            if (empty())
                q1.peek();
            return top;
        }

        // Return whether the stack is empty.
        public boolean empty() {
            return q1.isEmpty();
        }
    }

    //232
    class MyQueue {
        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();

        // Push element x to the back of queue.
        public void push(int x) {
            s1.push(x);
        }

        // Removes the element from in front of queue.
        public void pop() {
            if (!s2.isEmpty())
                s2.pop();
            else {
                while (s1.size() > 1)
                    s2.push(s1.pop());
                s1.pop();
            }
        }

        // Get the front element.
        public int peek() {
            if (!s2.isEmpty())
                return s2.peek();
            else {
                while (!s1.isEmpty())
                    s2.push(s1.pop());
                return s2.peek();
            }
        }

        // Return whether the queue is empty.
        public boolean empty() {
            return s1.isEmpty() && s2.isEmpty();
        }
    }
}
