package _002_ThirdProjectTestPackage;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 15:26 2018/10/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonTestInsert {

    private Integer age;

    private String nama;

    @JSONField(serializeUsing = FastJsonDateToLongConverter.class,deserializeUsing =FastJsonDateToLongConverter.class )
    private Date create;

    @JSONField(serializeUsing = FastJsonLongToStringConverter.class,deserializeUsing = FastJsonLongToStringConverter.class )
    private Long  sysId;
}
