package _000_day._002_7月17日代理;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/17 下午5:53<br/>
 */
public class SampleBean2 {

    private String value;
    private Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SampleBean2() {
    }

    public SampleBean2(String value,Integer age) {
        this.value = value;
        this.age = age;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SampleBean2{" +
                "value='" + value + '\'' +
                ", age=" + age +
                '}';
    }
}
