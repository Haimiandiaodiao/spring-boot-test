package _013_face._001_findJavaFile;

import org.junit.Test;

import java.io.*;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/13 下午3:53<br/>
 */
public class Computer implements Runnable {

    private File file ;
    private boolean innerDoucment=false;//是否在文档注释内
    private int lines;

    public Computer(File file) {
        this.file = file;
    }

    public Computer(String filename){
        this.file = new File(filename);
    }

    private static String spaceRegx = "\\s*";//一个或多个空白字符
    private static String commonsLineLines = "^\\s*//.*";
    private static String documentLineHeadLines = "^\\s*(/\\*\\*).*";//必须以/*开头
    private static String documentLineTailLines = ".*(\\*/)\\s*$";//必须以*/结尾

    @Override
    public void run() {
        try {
            FileInputStream input = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String tmp ;
            while ((tmp = br.readLine()) != null){
                ++lines;
                //1.计算空行数
                //2.计算行注解数
                //3.计算文档注解数
                //4.计算代码的行数
                if(tmp.matches(documentLineTailLines)){
                    Finder.documentLineLines.getAndIncrement();
                    this.innerDoucment = false;
                    continue;
                }

                if(innerDoucment){
                    Finder.documentLineLines.getAndIncrement();
                    continue;
                }

                if (tmp.matches(spaceRegx)){
                    Finder.spaceLines.getAndIncrement();
                    continue;
                }

                if(tmp.matches(commonsLineLines)){
                    Finder.commonsLineLines.getAndIncrement();
                    continue;
                }

                if(tmp.matches(documentLineHeadLines)){
                    Finder.documentLineLines.getAndIncrement();
                    this.innerDoucment = true;
                    continue;
                }

                //最后就是普通代码行
                Finder.codeLines.getAndIncrement();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
