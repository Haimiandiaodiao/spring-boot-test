package _002_ThirdProjectTestPackage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="商品规格项信息",description="商品规格项信息页面显示")
public class SeekGoodsItemVO {
    /**主键自动加一*/
    @ApiModelProperty(value="主键Id",name="goodsId")
    private Integer itemId;

    /**商品系统主键*/
    @ApiModelProperty(value="商品系统主键",name="goodsSysId")
    private Long goodsSysId;

    /**规格名称*/
    @ApiModelProperty(value="规格名称",name="itemName")
    private String itemName;
    /**item排序*/
    @ApiModelProperty(value="商品来源ID 如对接京东显示京东的主键ID",name="platgoodsId")
    private Integer itemSort;
    /**是否显示*/
    @ApiModelProperty(value="是否显示",name="goodsName")
    private Boolean isShow;
    /**商品规格值列表*/
    @ApiModelProperty(value="商品规格值列表",name="norms")
    private List<SeekGoodsNormVO> norms;



    /**商品创建时间*/
    @ApiModelProperty(value="商品规格项创建时间",name="sysCreateTime")
    private Date sysCreateTime;
    /**商品修改时间*/
    @ApiModelProperty(value="商品规格项修改时间",name="sysEditTime")
    private Date sysEditTime;
    /**商品创建用户ID*/
    @ApiModelProperty(value="商品规格项创建用户ID",name="sysCreateUser")
    private String sysCreateUser;
    /**商品修改用户ID*/
    @ApiModelProperty(value="商品规格项修改用户ID",name="sysEditUser")
    private String sysEditUser;
    /**系统space*/
    @ApiModelProperty(value="系统space",name="sysSpaceNo")
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
