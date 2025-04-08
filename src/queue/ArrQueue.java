package queue;

public class ArrQueue<E> {
    //已存入元素的计数器
    private int count = 0;
    //队列内部用来存数据的数组
    private Object[] src;
    private int reSize = 0;

    //构造函数，满足多种用途
    public ArrQueue(int len, int reSize) {
        this.reSize = reSize;
        src = new String[len];
    }

    public ArrQueue() {
        this(10, 50);
    }

    public ArrQueue(int len) {
        this(len, 50);
    }

    public void addHead(E Head) {

    }

    public void addTail(E Tail) {
        src[count++] = Tail;
        if (count > src.length) {
            //新建一个数组，长度是原数组长度+1
            Object[] tem = new Object[src.length+1];
            //将原数组的数据复制到新数组中
            for(int i=0; i<src.length;i++){
                tem[i] = src[i];
            }
            //将要加的数据加到队尾
            tem[src.length] = Tail;
            src = tem;
        }
    }

    public void removeHead(E Head) {

    }

    public void removeTail(E Tail) {

    }

    public void insertRandom(E data) {

    }

    public void addHeads(E[] Heads) {

    }

    public void addTails(E[] Tails) {

    }

    public void removeHeads(E[] Heads) {

    }

    public void removeTails(E[] Tails) {

    }

    //取出一个元素
    public E get(int index) {
        E e = (E) src[index];
        return e;
    }

    public int size() {
        return src.length;//数组的长度
    }


}
