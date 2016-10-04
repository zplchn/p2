import java.util.*;

/**
 * Created by zplchn on 9/6/16.
 */
public class Tree {
    class TreeNode{
        int val;
        TreeNode left, right;
        TreeNode(int x){val = x;}
    }

    //94
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()){
            if (root != null){
                stack.push(root);
                root = root.left;
            }
            else {
                TreeNode tn = stack.pop();
                res.add(tn.val);
                root = tn.right;
            }
        }
        return res;
    }


    //98
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTHelper(TreeNode root, long min, long max){
        if (root == null)
            return true;
        if (root.val >= max || root.val <= min)
            return false;
        return isValidBSTHelper(root.left, min, root.val) && isValidBSTHelper(root.right, root.val, max);
    }

    //100
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null)
            return q == null;
        if (q == null)
            return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    //101
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(TreeNode l, TreeNode r){
        if (l == null)
            return r == null;
        if (r == null)
            return false;
        return l.val == r.val && isSymmetricHelper(l.left,r.right) && isSymmetricHelper(l.right, r.left);
    }

    //102
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        List<Integer> combi = new ArrayList<>();
        int cur = 1, next = 0;
        while (!queue.isEmpty()){
            TreeNode tn = queue.poll();
            combi.add(tn.val);
            if (tn.left != null){
                queue.offer(tn.left);
                ++next;
            }
            if (tn.right != null){
                queue.offer(tn.right);
                ++next;
            }
            if (--cur == 0){
                res.add(combi);
                combi = new ArrayList<>();
                cur = next;
                next = 0;
            }
        }
        return res;
    }

    //103
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        List<Integer> combi = new ArrayList<>();
        queue.offer(root);
        int cur = 1, next = 0;
        boolean rev = false;
        while (!queue.isEmpty()){
            TreeNode tn = queue.poll();
            combi.add(tn.val);
            if (tn.left != null){
                queue.offer(tn.left);
                ++next;
            }
            if (tn.right != null){
                queue.offer(tn.right);
                ++next;
            }
            if (--cur == 0){
                if (rev)
                    Collections.reverse(combi);
                rev = !rev;
                res.add(combi);
                combi = new ArrayList<>();
                cur = next;
                next = 0;
            }
        }
        return res;
    }

    //104
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;

    }

    //105
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length != preorder.length)
            return null;
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i)
            hm.put(inorder[i] , i);
        return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, hm);
    }

    private TreeNode buildTreeHelper(int[] pre, int pl, int pr, int[] in, int il, int ir, Map<Integer, Integer> hm){
        if (il > ir)
            return null;
        TreeNode root = new TreeNode(pre[pl]);
        int k = hm.get(root.val);
        root.left = buildTreeHelper(pre, pl+1, pl + k - il, in, il, k - 1, hm);
        root.right = buildTreeHelper(pre, pl + k - il + 1, pr, in, k + 1, ir, hm );
        return root;
    }

    //106
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0 || postorder == null
                || postorder.length != inorder.length)
            return null;
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i)
            hm.put(inorder[i], i);
        return buildTree2Helper(inorder, 0, inorder.length - 1, postorder,
                0, postorder.length - 1, hm);
    }

    private TreeNode buildTree2Helper(int[] in, int il, int ir, int[] po,
                                      int pl, int pr, Map<Integer, Integer> hm){
        if (il > ir)
            return null;
        TreeNode root = new TreeNode(po[pr]);
        int k = hm.get(root.val);
        root.left = buildTree2Helper(in, il, k - 1, po, pl, pl + k - il - 1, hm);
        root.right = buildTree2Helper(in, k + 1, ir, po, pl + k - il, pr - 1, hm);
        return root;
    }


    //107
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int cur = 1, next = 0;
        List<Integer> lvl = new ArrayList<>();
        while (!queue.isEmpty()){
            TreeNode tn = queue.poll();
            lvl.add(tn.val);
            if (tn.left != null){
                queue.offer(tn.left);
                ++next;
            }
            if (tn.right != null){
                queue.offer(tn.right);
                ++next;
            }
            if (--cur == 0){
                res.add(lvl);
                lvl = new ArrayList<>();
                cur = next;
                next = 0;
            }
        }
        Collections.reverse(res);
        return res;
    }

    //108
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBSTHelper(int[] nums, int l, int r){
        if (l > r)
            return null;
        int m = l + ((r - l) >> 1);
        TreeNode root = new TreeNode(nums[m]);
        root.left = sortedArrayToBSTHelper(nums, l, m - 1);
        root.right = sortedArrayToBSTHelper(nums, m + 1, r);
        return root;
    }

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int x){val = x;}
    }
    //109
    private ListNode cur;
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        int n = 0;
        cur = head;
        while (cur != null && cur.next != null){
            cur = cur.next;
            ++n;
        }
        cur = head;
        return sortedListToBSTHelper(0, n);
    }

    private TreeNode sortedListToBSTHelper(int l, int r){
        if (l > r)
            return null;
        int m = l + ((r - l) >> 1);
        TreeNode ln = sortedListToBSTHelper(l, m - 1);
        TreeNode root = new TreeNode(cur.val);
        cur = cur.next;
        root.left = ln;
        root.right = sortedListToBSTHelper(m + 1, r);
        return root;
    }

    //110
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return isBalancedHelper(root) != -1;
    }

    private int isBalancedHelper(TreeNode root){
        if (root == null)
            return 0;
        int lh = isBalancedHelper(root.left);
        if (lh == -1)
            return -1;
        int rh = isBalancedHelper(root.right);
        if (rh == -1)
            return -1;
        if (Math.abs(lh - rh) > 1)
            return -1;
        return Math.max(lh, rh) + 1;
    }

    //111
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null)
            return minDepth(root.right) + 1;
        else if (root.right == null)
            return minDepth(root.left) + 1;
        else
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;

    }

    //112
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false; //first if null tree should false. if missing l or r child should also false
        if (root.left == null && root.right == null)
            return sum == root.val;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    //113
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        pathSumHelper(root, sum, res, new ArrayList<Integer>());
        return res;
    }

    private void pathSumHelper(TreeNode root, int sum, List<List<Integer>> res, List<Integer> combi){
        if (root == null)
            return;
        if (root.left == null && root.right == null){
            if (sum == root.val){
                List<Integer> t = new ArrayList<>(combi);
                t.add(root.val);
                res.add(t);
            }
            return;
        }
        combi.add(root.val);
        pathSumHelper(root.left, sum - root.val, res, combi);
        pathSumHelper(root.right, sum - root.val, res, combi);
        combi.remove(combi.size() - 1);
    }

    //114
    private TreeNode pre;
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        TreeNode t = root.right;
        if (pre != null){
            pre.right = root;
            pre.left = null; //must also set left to null otherwise both point to the same node
        }
        pre = root;
        flatten(root.left);
        flatten(t);
    }

    //116
    class TreeLinkNode{
        int val;
        TreeLinkNode left, right, next;
        public TreeLinkNode(int x) { val = x;}
    }

    public void connect(TreeLinkNode root) {
        if (root == null)
            return;
        if (root.left != null) //perfect tree, may missing leaves
            root.left.next = root.right;
        if (root.right != null)
            root.right.next = root.next == null ? null : root.next.left;
        connect(root.left);
        connect(root.right);
    }

    //124
    private int maxSum = Integer.MIN_VALUE; //if all nodes are < 0!!
    public int maxPathSum(TreeNode root) {
        if (root == null)
            return 0;
        maxPathSumHelper(root);
        return maxSum;
    }

    private int maxPathSumHelper(TreeNode root){
        if (root == null)
            return 0;
        int ls = Math.max(maxPathSumHelper(root.left), 0);
        int rs = Math.max(maxPathSumHelper(root.right), 0);
        maxSum = Math.max(maxSum, ls + rs + root.val);
        return Math.max(ls, rs) + root.val;
    }
    //129
    private int sum;
    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        sumNumbersHelper(root, 0);
        return this.sum;
    }

    private void sumNumbersHelper(TreeNode root, int pre){
        if (root == null)
            return;
        int x = pre * 10 + root.val;
        if (root.left == null && root.right == null)
            this.sum += x;
        sumNumbersHelper(root.left, x);
        sumNumbersHelper(root.right, x);
    }

    //144
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> st = new ArrayDeque<>();
        while (root != null || !st.isEmpty()){
            if (root != null){
                res.add(root.val);
                if (root.right != null)
                    st.push(root.right);
                root = root.left;
            }
            else
                root = st.pop();
        }
        return res;
    }

    //199
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int cur = 1, next = 0;
        while (!queue.isEmpty()){
            TreeNode tn = queue.poll();
            if (tn.left != null){
                queue.offer(tn.left);
                ++next;
            }
            if (tn.right != null){
                queue.offer(tn.right);
                ++next;
            }
            if (--cur == 0){
                res.add(tn.val);
                cur = next;
                next = 0;
            }
        }
        return res;
    }

    //222
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        int lh = 0, rh = 0;
        TreeNode t = root;
        while (t != null){
            ++lh;
            t = t.left;
        }
        t = root;
        while (t != null){
            ++rh;
            t = t.right;
        }
        if (lh == rh)
            return (2 << lh) - 1;
        return countNodes(root.left) + 1 + countNodes(root.right);
    }

    //226
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        return root;
    }

    //235
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null)
            return null;
        while (root != null) {
            if (p.val < root.val && q.val < root.val)
                root = root.left;
            else if (p.val > root.val && q.val > root.val)
                root = root.right;
            else
                break;
        }
        return root;
    }

    //236
    public TreeNode lowestCommonAncestorBT(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null)
            return null;
        if (root == p || root == q)
            return root;
        TreeNode left = lowestCommonAncestorBT(root.left, p, q);
        TreeNode right = lowestCommonAncestorBT(root.right, p, q);
        if (left != null && right != null)
            return root;
        return left != null ? left : right;
    }

    //250
    private int uni;
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null)
            return 0;
        countUnivalSubtreesHelper(root);
        return this.uni;
    }

    private boolean countUnivalSubtreesHelper(TreeNode root){
        if (root == null)
            return true;
        boolean le = countUnivalSubtreesHelper(root.left);
        boolean ri = countUnivalSubtreesHelper(root.right);

        if (le && ri && (root.left == null || root.val == root.left.val) && (root.right == null || root.val == root.right.val)){
            ++uni;
            return true;
        }
        return false;
    }

    //257
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null)
            return res;
        binaryTreePathsHelper(root, "", res);
        return res;
    }

    private void binaryTreePathsHelper(TreeNode root, String pre, List<String> res){
        if (root == null)
            return;
        pre += (pre == ""? "": "->") + root.val;
        if (root.left == null && root.right == null){
            res.add(pre);
            return;
        }
        binaryTreePathsHelper(root.left, pre, res);
        binaryTreePathsHelper(root.right, pre, res);
    }

    //270
    private int closest;
    public int closestValue(TreeNode root, double target) {
        if (root == null)
            return -1;
        closetValueHelper(root, target, Double.MAX_VALUE);
        return closest;
    }

    private void closetValueHelper(TreeNode root, double target, double delta){
        if (root == null)
            return;
        double diff = Math.abs(root.val - target);
        if (diff < delta) {
            closest = root.val;
            delta = diff;
        }
        if (root.val < target)
            closetValueHelper(root.right, target, delta);
        else
            closetValueHelper(root.left, target, delta);
    }
}
