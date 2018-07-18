package _000_day._002_7月17日代理;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/18 下午1:51<br/>
 */
public class MixinTest {
   public interface Interface1{
        String first();
    }
   public interface Interface2{
        String second();
    }

   public interface TotalInterface3 extends Interface1,Interface2{

   }


   public class Class1 implements Interface1{

        @Override
        public String first() {
            return "我是class1实现的接口1";
        }
    }

    public class Class2 implements Interface2{

        @Override
        public String second() {
            return "我是class2实现的接口2";
        }
    }

}
