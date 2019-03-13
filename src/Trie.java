import java.util.TreeMap;


public class Trie {

    //节点信息
    private class Node{

        //当前节点是否是单词的结尾
        public boolean isWord;
        //当前节点保存的字符是哪些
        public TreeMap<Character , Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;  //当前树的根节点
    private int size;   //当前树保存单词的个数

    public Trie(){
        root = new Node();
        size = 0;
    }

    //获取trie中单词的个数
    public int getSize(){
        return size;
    }

    //向trie中添加一个新的单词
    public void add(String word){
        Node cur = root;
        for(int i = 0 ; i < word.length() ; i++){
            char c = word.charAt(i);
            //判断当前节点是否包含字符
            if(cur.next.get(c) == null){
                //当前节点不包含 , 需要在map中进行添加
                cur.next.put(c , new Node());
            }

            //包含了 , 移动到下一个节点
            cur = cur.next.get(c);
        }


        //以上操作结束 , 来到了单词的末尾 , 需要把isWord字段修改为true
        //判断字符是否需要修改
        if(!cur.isWord){
            cur.isWord = true;
            size++;
        }

    }


    //判断单词是否包含在trie中
    public boolean contains(String word){
        Node cur = root;
        for(int i = 0 ; i < word.length() ; i++){
            char c = word.charAt(i);

            //只要有一个字符不匹配 , 就表示树中没有包含该单词
            if(cur.next.get(c) == null){
                return false;
            }

            cur = cur.next.get(c);
        }

        //定位到了最后一个节点 , 需要根据isWord进行判断是否包含在树中
        //如:panda和pan , 查询pan的时候, 有可能pan不存在里面
        return cur.isWord;
    }

    //查询是否在Trie中有单词 , 以prefix为前缀
    public boolean isPrefix(String prefix){
        //查询前缀和查询单词不一样
        //只要trie中有这个前缀就会返回true , 而不是根据当前节点的属性来进行判断的
        Node cur = root;
        for(int i = 0 ; i < prefix.length() ; i++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null){ //判断当前节点的map中是否包含目标字符
                return false;
            }
            cur = cur.next.get(c);
        }

        return true;
    }

}
