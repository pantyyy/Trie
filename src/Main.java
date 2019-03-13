import java.util.ArrayList;

public class Main {


    public static void main(String[] args){
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();

        //把每个单词读入words集合中
        if(FileOperation.readFile("pride-and-prejudice.txt", words)){
            long startTime = System.nanoTime();

            //创建一个二叉搜索树对象
            BSTSet<String> set = new BSTSet<>();
            //遍历单词集合 , 把单词放入二叉树中
            for(String word : words)
                set.add(word);

            for(String word : words)
                set.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + " s");




            //--------------------------测试trie的时间复杂度
            startTime = System.nanoTime();

            Trie trie = new Trie();
            for(String word : words)
                trie.add(word);

            for(String word : words)
                trie.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total different words: " + trie.getSize());
            System.out.println("Trie: " + time + " s");
        }
    }
}
