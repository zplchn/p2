//208
class TrieNode {
    TrieNode[] children;
    boolean isWord;
    // Initialize your data structure here.
    public TrieNode() {
        children = new TrieNode[26];
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null || word.length() == 0)
            return;
        TrieNode tn = root;
        for (int i = 0; i < word.length(); ++i){
            if (tn.children[word.charAt(i) - 'a'] == null){
                tn.children[word.charAt(i) - 'a'] = new TrieNode();
            }
            tn = tn.children[word.charAt(i) - 'a'];
        }
        tn.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (word == null || word.length() == 0)
            return false;
        TrieNode tn = root;
        for (int i = 0; i < word.length(); ++i){
            if (tn.children[word.charAt(i) - 'a'] == null)
                return false;
            tn = tn.children[word.charAt(i) - 'a'];
        }
        return tn.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0)
            return false;
        TrieNode tn = root;
        for (int i = 0; i < prefix.length(); ++i){
            if (tn.children[prefix.charAt(i) - 'a'] == null)
                return false;
            tn = tn.children[prefix.charAt(i) - 'a'];
        }
        return true;

    }
}
