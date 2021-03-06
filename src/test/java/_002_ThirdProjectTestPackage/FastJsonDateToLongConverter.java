package _002_ThirdProjectTestPackage;


import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 14:02 2018/10/17
 */
public class FastJsonDateToLongConverter implements ObjectSerializer,ObjectDeserializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
        if(type == Date.class){
            jsonSerializer.write(((Date)o).getTime());
        }
    }

    @Override
    public Date deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        Object value = defaultJSONParser.parse();
        Date result = null;
        if(value != null) {
            Class<?> aClass = value.getClass();
            if (aClass == Integer.class) {
                Integer value1 = (Integer) value;
                long l = value1.longValue();
                result = new Date(l);
            }
            if (aClass == Long.class) {
                Long value1 = (Long) value;
                result = new Date(value1);
            }
        }
        return result;
    }



    @Override
    public int getFastMatchToken() {
        return 0;
    }
}
