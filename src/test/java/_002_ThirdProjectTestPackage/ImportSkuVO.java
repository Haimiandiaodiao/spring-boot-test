package _002_ThirdProjectTestPackage;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 10:28 2018/10/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportSkuVO implements Serializable {
    /**商品的Id*/
    private String goodsId;
    /**规格项的列表*/
    private List<ImportSkuNormListItem> normList;
    /**组合规格值*/
    private List<ImporSkuSkuListItem> skuList;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ImportSkuNormListItem{
        /**规格项的名称*/
        private String name;
        /**规格项的规格值*/
        private List<String> norms;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ImporSkuSkuListItem{
        /**sku的组合*/
        @JSONField(ordinal = 1)
        private List<String> skuName;
        /**销售价钱*/
        @JSONField(ordinal = 2)
        private BigDecimal price;
        /**库存*/
        @JSONField(ordinal = 3)
        private BigDecimal stock;
        /**默认图片地址*/
        @JSONField(ordinal = 4)
        private String defaultPhotoId;
        /**条形码*/
        @JSONField(ordinal = 5)
        private String barCode;
        /**反润比例*/
        @JSONField(ordinal = 6)
        private BigDecimal profit;
        /**毛重*/
        @JSONField(ordinal = 7)
        private BigDecimal netWeight;
        /**重量*/
        @JSONField(ordinal = 8)
        private BigDecimal weight;
        /**渠道skuid*/
        @JSONField(ordinal = 9)
        private String platSkuId;
        /**商品的Id*/
        @JSONField(ordinal = 10)
        private String goodsId;
        /**市场价*/
        @JSONField(ordinal = 11)
        private BigDecimal mallPrice;
        /**sku的状态*/
        @JSONField(ordinal = 12)
        private Integer dataStatus;
    }
}

