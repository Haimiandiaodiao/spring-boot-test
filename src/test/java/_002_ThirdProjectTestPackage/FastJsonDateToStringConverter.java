package _002_ThirdProjectTestPackage;


import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * FastJson转化时将Date类型转Long类型转化器
 * @Author:Dyy
 * @Description:
 * @Date: Created in 14:02 2018/10/17
 */
public class FastJsonDateToStringConverter implements ObjectSerializer, ObjectDeserializer {
    @Override
    public void write(JSONSerializer jsonSerializer, Object o, Object o1, Type type, int i) throws IOException {
        if(type == Date.class){
            if(o != null) {
                long time = ((Date) o).getTime();
                jsonSerializer.write(time);
            }
        }
    }

    @Override
    public Date deserialze(DefaultJSONParser defaultJSONParser, Type type, Object o) {
        Object value = defaultJSONParser.parse();
        Date result = null;
        if(value != null) {
            Class<?> aClass = value.getClass();
            if(aClass == Long.class){
                result = new Date((Long)value);
            }
            if (aClass == String.class) {
                long value1 = Long.parseLong((String) value);
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
