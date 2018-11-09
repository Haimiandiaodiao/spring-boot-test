package _000_day._010_10月23号Guava的请求操作;


/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 10:08 2018/11/7
 */
public class Entity1 implements Comparable<Entity1>{
    private static int  inc = 0;
    //hash的值
    private int num;
    //equal比较的值
    private int id;


    public Entity1() {
        num = inc++;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity1 entity1 = (Entity1) o;
        return  entity1.getId() == id;
    }

    @Override
    public int hashCode() {
        return num;
    }

    @Override
    public int compareTo(Entity1 o) {
        return id-o.getId();
    }
}
