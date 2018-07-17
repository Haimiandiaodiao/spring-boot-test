package _000_day._001_7月17日反射;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/17 上午10:21<br/>
 */
public class Teacher<T> {

    //---------------构造方法-------------------
    //（默认的构造方法）
    Teacher(T str){
        System.out.println("父类(默认)的构造方法 s = " + str);
    }

    //无参构造方法
    public Teacher(){
        System.out.println("父类调用了公有、无参构造方法执行了。。。");
    }

    //有一个参数的构造方法
    public Teacher(char name){
        System.out.println("父类姓名：" + name);
    }

    //有多个参数的构造方法
    public Teacher(T name , int age){
        System.out.println("父类姓名："+name+"年龄："+ age);//这的执行效率有问题，以后解决。
    }

    //受保护的构造方法
    protected Teacher(boolean n){
        System.out.println("父类受保护的构造方法 n = " + n);
    }

    //私有构造方法
    private Teacher(int age){
        System.out.println("父类私有的构造方法   年龄："+ age);
    }




    public String Aname;
    protected int Aage;
    char Asex;
    private String AphoneNum;

    @Override
    public String toString() {
        return "Student [name=" + Aname + ", age=" + Aage + ", sex=" + Asex
                + ", phoneNum=" + AphoneNum + "]";
    }


    //**************成员方法***************//
    public void Ashow1(String s){
        System.out.println("父类调用了：公有的，String参数的show1(): s = " + s);
    }
    protected void Ashow2(){
        System.out.println("父类调用了：受保护的，无参的show2()");
    }
    void Ashow3(){
        System.out.println("父类调用了：默认的，无参的show3()");
    }
    private String Ashow4(int age){
        System.out.println("父类调用了，私有的，并且有返回值的，int参数的show4(): age = " + age);
        return "abcd";
    }

}
