package _015_AliOss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.BucketInfo;
import com.aliyun.oss.model.CannedAccessControlList;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 17:16 2018/9/12
 */
public class Oss基础操作 {

    @Test
    public void baseUse(){
        String endpoint ="http://oss-cn-beijing.aliyuncs.com";
        String accessId= "JRNhofSZOGSYeQXl11111";
        String accessKey = "FCw0oARKjfED0LsV1CZDlG8B3vHZp6";
        OSSClient ossClient = new OSSClient(endpoint, accessId, accessKey);
        List<Bucket> buckets = ossClient.listBuckets();

        List<String> exsitsBucket = buckets.stream().map(e -> e.getName()).collect(Collectors.toList());
//        Bucket bucket = ossClient.createBucket("B0CD0050CF0BF01B".toLowerCase());
//        ossClient.setBucketAcl("60D30A50C900B042".toLowerCase(), CannedAccessControlList.PublicRead);
        ossClient.deleteBucket("B0CD0050CF0BF01B".toLowerCase());
        ossClient.deleteBucket("D0540570D8063036".toLowerCase());
        ossClient.deleteBucket("60D30A50C900B042".toLowerCase());
    }

    @Test
    public void streamTest() {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int monthValue = now.getMonthValue();
        System.out.println(year);
        System.out.println(monthValue);

    }
}
