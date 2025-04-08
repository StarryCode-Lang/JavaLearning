package HashMap.frb230915_v02;

//自定义哈希表
//练习：1.哈希表key的重复问题
//    2.可以存null key  null value
//    3.把泛型用在哈希表中
//    4.研究下系统中HashMap 底层原理，写篇教程
public class MHashMap {
    //保存链表的数据
    private LinkList[] linkListArr;
    private static int len = 16;
    private int size;

    //初始化linkListArr数组长度
    public MHashMap() {
        this(len);
    }

    public MHashMap(int size) {
        this.size = size;
        linkListArr = new LinkList[size];
        //初始化链表的根节点
        for (int i = 0; i < size; i++) {
            linkListArr[i] = new LinkList();
        }
    }

    // put
    public void put(Object key, Object value) {
        //根据key 计算位置
        int index = hash(key);
        LinkList link = linkListArr[index];
        link.add(key, value);
    }

    // get
    public Object get(Object key) {
        //根据key 计算位置
        int index = hash(key);
        LinkList link = linkListArr[index];
        //遍历遍历链表
        for (int i = 0; i < link.size(); i++) {
            Node node = link.get(i);
            //比较链表中key,如果找到返回对应的对应的value
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    //散列函数
    public int hash(Object key) {
        return key.hashCode() % size;
    }

    public static void main(String[] args) {
        MHashMap hashMap = new MHashMap();
        hashMap.put("1", "10");
        hashMap.put("2", "20");
        System.out.println("val1="+hashMap.get("1"));
        System.out.println("val2="+hashMap.get("2"));
    }

}
