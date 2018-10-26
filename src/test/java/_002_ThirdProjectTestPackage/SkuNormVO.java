package _002_ThirdProjectTestPackage;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * 在新添加上商品sku值的时候使用的VO
 * @Author:Dyy
 * @Description:
 * @Date: Created in 11:29 2018/10/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkuNormVO implements Serializable {
    /**商品的系统主键*/
    @JSONField(serializeUsing = FastJsonLongToStringConverter.class, deserializeUsing = FastJsonLongToStringConverter.class)
    private Long sysGoodsId;

    /**ItemId要对哪一个Item进行操作*/
    @JSONField(serializeUsing = FastJsonLongToStringConverter.class, deserializeUsing = FastJsonLongToStringConverter.class)
    private Long itemId;

    /**要添加的Item的规格数组*/
    private Set<String> normList;
}
