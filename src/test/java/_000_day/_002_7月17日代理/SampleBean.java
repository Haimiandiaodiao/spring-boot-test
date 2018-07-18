package _000_day._002_7月17日代理;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/17 下午5:23<br/>
 */
public class SampleBean {

    private String value;

    public SampleBean() {
    }

    public SampleBean(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SampleBean{" +
                "value='" + value + '\'' +
                '}';
    }
}
