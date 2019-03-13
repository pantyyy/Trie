import java.util.TreeMap;

public class MapSum {

    //内部节点类
    private class Node{
        public int value;
        public TreeMap<Character , Node> next;

        public Node(int value){
            this.value = value;
            next = new TreeMap<>();
        }

        public Node(){
            this(0);
        }
    }


    Node root;

    MapSum(){
        root = new Node();
    }


    //添加方法
    public void insert(String key , int val){
        Node cur = root;
        for(int i = 0 ; i < key.length() ; i++){
            char c = key.charAt(i);
            if(cur.next.get(c) == null){
                cur.next.put(c , new Node());
            }
            cur = cur.next.get(c);
        }

        //经过以上循环 , 到达单词的结尾 , 在单词的结尾赋值即可
        cur.value = val;
    }


    //根据输入的前缀 , 求包含此前缀的和
    public int sum(String prefix){
        //找到前缀的节点
        Node cur = root;
        for(int i = 0 ; i < prefix.length() ; i++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null){
                return 0;
            }
            cur = cur.next.get(c);
        }

        //经过以上循环到达前缀的最后一个节点
        return sum(cur);
    }


    //计算以node为根节点的和
    private int sum(Node node){
        //递归结束的条件
        //遍历到了单词的末尾 , 也就是node.next == null的时候
        if(node.next.size() == 0){
            return node.value;
        }

        //把当前node节点的值 , 加上他的子树的值才是返回结果
        int res = node.value;
        for(char c : node.next.keySet()){
            //累加各个子树的和
            res += sum(node.next.get(c));
        }

        return res;
    }
}
