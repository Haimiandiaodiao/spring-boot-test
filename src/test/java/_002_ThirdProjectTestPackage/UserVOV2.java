package _002_ThirdProjectTestPackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户升级所需要的VO
 * @Author:Dyy
 * @Description:
 * @Date: Created in 10:26 2018/11/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVOV2 implements Serializable {

    /**小程序id  xopenId  必须要传的*/
    private String xopenid;
    /**微信openid  gopenId  必须要传的*/
    private String gopenid;
    /**联合的登录Id unionid 必须要传*/
    private String unionid;
    /**昵称 必须要传的*/
    private String nickname;
    /**身份证号*/
    private String cardid;
    /**省*/
    private String province;
    /**市*/
    private String city;
    /**pic头像*/
    private String pic;
    /**ip*/
    private String ip;
    /**真实姓名*/
    private String realname;
    /**手机号*/
    private String mobile;
    /**性别*/
    private Integer gender;
    /**分享人Id*/
    private Integer sharedId;

    
}
