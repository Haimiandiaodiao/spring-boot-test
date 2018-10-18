package _002_ThirdProjectTestPackage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="商品规格项和规格值",description="商品规格项和规格值")
public class SeekGoodsNormVO  {
    /**主键自动加一*/
    @ApiModelProperty(value="主键Id",name="goodsId")
    private Integer normId;
    /**渠道主键*/
    @ApiModelProperty(value="渠道Id",name="channelId")
    private long goodsId;
    /**商品来源ID 如对接京东显示 京东的主键ID*/
    @ApiModelProperty(value="商品来源ID 如对接京东显示京东的主键ID",name="platgoodsId")
    private String noreName;
    /**商品名称*/
    @ApiModelProperty(value="商品名称",name="goodsName")
    private String noreValue;
    /**商品简要描述*/
    @ApiModelProperty(value="商品简要描述多用于分享显示的话术",name="goodsNote")
    private Boolean isShow;
    /**排序规则*/
    @ApiModelProperty(value="商品简要描述多用于分享显示的话术",name="goodsNote")
    private Integer sort;


    /**系统自动生成主键*/
    @ApiModelProperty(value="系统自动生成主键",name="sysSpaceNo")
    private String sysSpaceNo;
    /**系统生成版本号码*/
    @ApiModelProperty(value="系统生成版本号码",name="sysVersionNo")
    private Integer sysVersionNo;
    /**是否有效 1=有效 0=无效  默认为1*/
    @ApiModelProperty(value="是否有效 1=有效 0=无效  默认为1",name="sysIsEnable")
    private Integer sysIsEnable;
    /**系统自动生成主键编号*/
    @ApiModelProperty(value="系统自动生成主键编号",name="sysIdString")
    private Long sysIdString;


}
