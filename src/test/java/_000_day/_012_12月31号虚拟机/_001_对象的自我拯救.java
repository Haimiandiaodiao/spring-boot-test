package _000_day._012_12月31号虚拟机;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 21:38 2018/12/31
 */
public class _001_对象的自我拯救 {
    public static _001_对象的自我拯救 SAVE_HOOK = null;

    public void isAlice(){
        System.out.println("yes, iam still alice :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize methd executd!");
        SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new _001_对象的自我拯救();

        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法有优先级比较低，所以暂停0.5秒
        Thread.sleep(500);

        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlice();
        }else{
            System.out.println("no,i am dead :(");
        }
        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法有优先级比较低，所以暂停0.5秒
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlice();
        }else{
            System.out.println("no,i am dead :(");
        }


    }
}
