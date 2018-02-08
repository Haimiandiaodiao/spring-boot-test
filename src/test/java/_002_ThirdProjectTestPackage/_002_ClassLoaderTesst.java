package _002_ThirdProjectTestPackage;

import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.util.StringTokenizer;

/**
 * @auther Dyy
 * @create 2018/1/23
 */
public class _002_ClassLoaderTesst {

    @Test
    public void showExtClassLoader(){
        String s = System.getProperty("java.ext.dirs");
        File[] dirs;
        if(s != null){
            StringTokenizer st = new StringTokenizer(s, File.pathSeparator);
            int count = st.countTokens();
            dirs = new File[count];
            for (int i = 0; i < dirs.length; i++) {
                dirs[i]=new File(st.nextToken());
            }
        }else {
            dirs = new File[0];
        }
        for (File dir : dirs) {
            System.out.println(dir.toString());
        }
    }


    @Test
    public void showDe(){
        BigDecimal a= new BigDecimal("0");
        System.out.println(a.setScale(2).toString());
    }
}
