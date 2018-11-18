package _002_ThirdProjectTestPackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 用户升级的配置
 * @Author:Dyy
 * @Description:
 * @Date: Created in 17:32 2018/11/14
 */
@Data
@NoArgsConstructor
public class UpdateRule {
    /**升级到VIp的配置Key*/
    public static final String updateToVipConfigKey = "UsersUpgrade";
    /**升级到总监的配置Key*/
    public static final String updateToMajorConfigKey = "UsersUpgrade";
    /**升级到店长的配置Key*/
    public static final String updateToShopManagerConfigKey = "UsersUpgrade";

    /**
     * Config中配置的参数
     * {
     * 	"integral": 1,              #扣除积分
     *
     *
     * 	"recommendedNum": 2,        #直接推荐的vip人数
     * 	"directlyVipCnt": 4,        #团队总VIp人数
     * 	"totalVipCnt": 7,           #扣除积分数
     *
     *
     * 	"vipCntExceptDirectly": 3,  #直接推荐的总监数
     * 	"teamManagerCnt": 5,        #团队内总监人数
     * 	"totalVipNum": 8,           #团队总vip人数
     * 	"heirIntegral": 6,          #满足团队总VIP人数条件的基础上，每一个VIP自己在平台消费获得的积分数
     * 	"takeoutIntegral": 9        #扣除积分数
     * }
     *
     *
     * */

    /**
     * 升级到经理的配置
     * */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateToMangerRule extends UpdateRule{
        /**要扣除的积分数*/
        private BigDecimal integral;
    }

    /**
     * 升级到总监的配置
     * */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateToMajorRule extends UpdateRule{
        /**直接推荐VIP人数*/
        private Integer recommendedNum;
        /**团队总Vip人数*/
        private Integer directlyVipCnt;
        /**扣除积分数*/
        private BigDecimal totalVipCnt;
    }

    /**
     * 升级到店长的配置
     * */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateToShopManagerRule extends UpdateRule{
        /**直接推荐的总监人数*/
        private Integer vipCntExceptDirectly;
        /**团队内总监人数*/
        private Integer teamManagerCnt;
        /**团队总vip人数*/
        private Integer  totalVipNum;
        /**每一个Vip在平台获得积分数*/
        private BigDecimal heirIntegral;
        /**升级扣除积的总分数*/
        private BigDecimal takeoutIntegral;
    }

}
