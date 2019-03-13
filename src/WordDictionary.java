import java.util.TreeMap;

public class WordDictionary {

    //节点元素(内部类)
    private class Node {
        public boolean isWord;
        //当前节点指向的下层节点
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }


    //Tire结构维护的树根
    private Node root;

    public WordDictionary() {
        root = new Node();
    }


    public void addWord(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            //判断字符是否在当前节点中
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }

            cur = cur.next.get(c);
        }

        //cur指向了单词的结尾 , 把状态更新为true
        cur.isWord = true;
    }


    //用户输入单词模式 , 进行单词匹配(类似正则表达式)
    public boolean search(String word) {


        return match(root, word, 0);
    }

    //从以node为根节点开始 , 匹配word中的第i个元素
    private boolean match(Node node, String word, int i) {
        //递归结束的条件 , 当i移动到了单词的结尾 , 即word.length的时候
        if (i == word.length()) {
            return node.isWord; //返回当前节点表示的状态即可
        }

        //判断匹配的模式
        //情况一 : 普通zif
        //情况二 : "."特殊字符
        char c = word.charAt(i);
        if (!".".equals(c)) {
            if (node.next.get(c) != null) {
                return match(node.next.get(c), word, i + 1);
            } else {
                return false;
            }
        } else {
            //"."为特殊字符 , 可以匹配所有的字符, 所以直接跳过点 , 进行下个字符的匹配
            for (char nextChar : node.next.keySet()) {
                if (match(node.next.get(nextChar), word, i + 1)) {
                    //只要有一个匹配成功的就返回true
                    return true;
                }
            }

            return false;
        }


    }

}