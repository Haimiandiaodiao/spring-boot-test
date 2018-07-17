package _012_alg;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/4 下午9:04<br/>
 */
public class _001_链表的操作 {

    /**
     * 龟兔赛跑算法
     */
    @Test
    public void 链表有环测试() {
        LinkedNode a1 = new LinkedNode("1");
        LinkedNode a2 = new LinkedNode("2");
        LinkedNode a3 = new LinkedNode("3");
        LinkedNode a4 = new LinkedNode("4");
        LinkedNode a5 = new LinkedNode("5");
        LinkedNode a6 = new LinkedNode("6");
        LinkedNode a7 = new LinkedNode("7");
        LinkedNode a8 = new LinkedNode("8");
        LinkedNode a9 = new LinkedNode("9");
        LinkedNode a10 = new LinkedNode("10");
        LinkedNode a11 = new LinkedNode("11");
        LinkedNode a12 = new LinkedNode("12");
        LinkedNode a13 = new LinkedNode("13");
        LinkedNode a14 = new LinkedNode("14");
        LinkedNode a15 = new LinkedNode("15");
        LinkedNode a16 = new LinkedNode("16");
        LinkedNode a17 = new LinkedNode("17");
        LinkedNode a18 = new LinkedNode("18");
        LinkedNode a19 = new LinkedNode("19");

        a1.setNext(a2);
        a2.setNext(a3);
        a3.setNext(a4);
        a4.setNext(a5);
        a5.setNext(a6);
        a6.setNext(a7);//环起点位置
        a7.setNext(a8);
        a8.setNext(a9);
        a9.setNext(a10);
        a10.setNext(a11);
        a11.setNext(a12);
        a12.setNext(a13);
        a13.setNext(a14);
        a14.setNext(a15);
        a15.setNext(a16);
        a16.setNext(a17);
        a17.setNext(a18);
        a18.setNext(a19);
        a19.setNext(a6);

       //=================验证是否有环存在
        LinkedNode slow = a1;
        LinkedNode fast = a1;
        boolean flage = false;

        //校验是不是有环
        while(fast.getNext() != null && fast.getNext().getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if(slow == fast){
                flage = true;
                break;
            }
        }
        System.out.println("是不是有环："+flage);

        //===============环的起始位置
        slow=a1;
        int i = 0;//记录的是步数
        for(;slow != fast;i++){
            slow = slow.getNext();
            fast = fast.getNext();
        }

        System.out.println("环的起始位置是："+i+1);//因为起始节点没有算
        System.out.println("环起点位置的数据是"+slow.getData());


        //计算环的大小 i=1是因为fast.getNext提前走了一步
        for (i=1;slow != fast.getNext();i++){
            fast = fast.getNext();
        }
        System.out.println("环的大小是："+i);
    }
}
class LinkedNode<T>{
    private T data;
    private LinkedNode next;

    public LinkedNode(T data) {
        this.data = data;
    }

    public T getData(){
        return data;
    }

    public void setNext(LinkedNode next){
        this.next = next;
    }

    public LinkedNode getNext(){
        return this.next;
    }
}
