//package _002_ThirdProjectTestPackage;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//
///**
// * 新的积分分润规则
// * @Author:Dyy
// * @Description:
// * @Date: Created in 13:30 2018/11/29
// */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class NewScoreShareRulezConfig implements Serializable {
//
//    //购买者积分比例
//    private ScaleConf buyersProportion;
//    //直接推荐人的积分比例
//    private ScaleConf directReferrerProportion;
//    //vip导购的
//    private ScaleConf vipProportion;
//    //总监的积分比例
//    private ScaleConf directorProportion;
//    //上级总监的积分比例
//    private ScaleConf superiorDirectorProportion;
//    //总经理的积分比例
//    private ScaleConf generalManagerProportion;
//    //上级总经理的积分比例
//    private ScaleConf superiorGeneralManagerProportion;
//
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class ScaleConf{
//        /**相应身份分润比例*/
//        private BigDecimal proportion;
//        /**是否启动积分倍数*/
//        private Integer isScale;
//        /**开启积分翻倍的倍数*/
//        private BigDecimal scale;
//        /**翻倍开启的时间毫秒值 开启翻倍时必传*/
//        private Long startTime;
//        /**翻倍结束的时间毫秒值 开启翻倍时必传*/
//        private Long endTime;
//    }
//}
