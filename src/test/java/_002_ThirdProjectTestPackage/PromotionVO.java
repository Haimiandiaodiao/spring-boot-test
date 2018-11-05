package _002_ThirdProjectTestPackage;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 活动需要的VO
 * @Author:Dyy
 * @Description:
 * @Date: Created in 16:11 2018/10/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionVO implements Serializable {
    //======================参数接收
    /**活动的Id*/
    private Integer prId;
    /**活动的名字*/
    private String prName;
    /**活动的开始时间*/
    @JSONField(serializeUsing = FastJsonDateToStringConverter.class,deserializeUsing = FastJsonDateToStringConverter.class)
    private Date prStart;
    /**活动的结束时间*/
    @JSONField(serializeUsing = FastJsonDateToStringConverter.class,deserializeUsing = FastJsonDateToStringConverter.class)
    private Date prEnd;
    /**活动的类型*/
    private Integer prType;
    /**活动的状态 停用或者启用*/
    private Integer dataStatus;
    /**参团人数 个别用到*/
    private Integer prGroupnum;
    /**积分类型*/
    private String scoreType;
    /**积分倍数*/
    private BigDecimal scoreTime;
    /**是否对所有商品有效*/
    private Integer isEnableAll;
    /**海报*/
    private String imgUrl;
    /**头图*/
    private String bannerImgUrl;
    /**添加的商品列表*/
    private List<PromotionGoodsVO> promotionGoodsList;
    /**添加的专题列表*/
    private List<PromotionTopicVO> promotionTopicList;
    //=====================回显添加字段
    /**回显添加 活动创建时间*/
    @JSONField(serializeUsing = FastJsonDateToStringConverter.class,deserializeUsing = FastJsonDateToStringConverter.class)
    private Date sysCreateTime;
    /**内容描述 专题时有用*/
    private String prConent;
    /**是否和钱有关*/
    private Integer isPriceType;
    /**创建人*/
    private String sysCreateUser;
    /**更新人*/
    private String sysEditUser;
    /**更新时间*/
    private Date sysEditTime;

    /**
     * 添加的商品的内部类
     * @Author:Dyy
     * @Description:
     * @Date: Created in 9:47 2018/10/31
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PromotionGoodsVO implements Serializable, Comparable<PromotionGoodsVO> {

        /**活动商品的Id 主键Id*/
        private Integer goId;
        /**商品skuId*/
        private Integer skuId;
        /**活动的Id*/
        private Integer goPrId;
        /**专题的Id*/
        private Integer goToId;
        /**商品的Id*/
        private Integer goodsNum;
        /**添加商品的排序*/
        private Integer sort;
        /**商品的价格*/
        private BigDecimal goPrPrice;
        /**商品限购数量*/
        private Long goLimit;
        //专题  限时购  
        /**数量模式 1 自动 2 手动 */
        private Integer salenumMode;
        /**相应的数量  go_salenum 或者 manual_salenum*/
        private Long goSalenum;
        private Long manualSalenum;
        /**商品的库存*/
        private Long goStock;
        /**商品名称*/
        private String goodsName;
        /**商品sku图片*/
        private String photoUrl;

        /**创建时间*/
        @JSONField(serialize = false)
        private Date sysCreateTime;
        /**创建人*/
        @JSONField(serialize = false)
        private String sysCreateUser;
        /**更新人*/
        @JSONField(serialize = false)
        private String sysEditUser;
        /**更新时间*/
        @JSONField(serialize = false)
        private Date sysEditTime;

        @Override
        public int compareTo(PromotionGoodsVO o) {
            return this.goId - o.goId;
        }
    }

    /**
     * 专题的VO
     * @Author:Dyy
     * @Description:
     * @Date: Created in 9:40 2018/10/31
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class  PromotionTopicVO implements Serializable,Comparable<PromotionTopicVO>{
        /**活动的id*/
        private Integer  toPrId;
        /**专题的Id*/
        private Integer toId;
        /**专题的名字*/
        private String toName;
        /**专题下挂的商品集合*/
        private List<PromotionGoodsVO> topicGoodsList;

        /**创建时间*/
        @JSONField(serialize = false)
        private Date sysCreateTime;
        /**创建人*/
        @JSONField(serialize = false)
        private String sysCreateUser;
        /**更新人*/
        @JSONField(serialize = false)
        private String sysEditUser;
        /**更新时间*/
        @JSONField(serialize = false)
        private Date sysEditTime;

        @Override
        public int compareTo(PromotionTopicVO o) {
            return this.toId-o.toId;
        }
    }

}
