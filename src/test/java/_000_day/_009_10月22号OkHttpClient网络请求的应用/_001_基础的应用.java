package _000_day._009_10月22号OkHttpClient网络请求的应用;

import okhttp3.*;
import okio.BufferedSink;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 13:58 2018/10/22
 */
public class _001_基础的应用 {


    /**
     * 异步的httpClient请求
     * @Author:Dyy
     * @Description:
     * @Date: Created in 14:10 2018/10/22
     * @param
     */
    @Test
    public void 异步Get请求(){
        String url = "http://www.baidu.com";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = okHttpClient.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求异常");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
                System.exit(0);
            }
        });
        while (true);
    }


    /**
     * 同步的Http请求
     * @Author:Dyy
     * @Description:
     * @Date: Created in 14:11 2018/10/22
     * @param
     */
    @Test
    public void 同步的HTTP请求() throws IOException {
        String url = "http:www.baidu.com";
        OkHttpClient request = new OkHttpClient();

        Request build = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = request.newCall(build);
        Response execute = call.execute();
        System.out.println(execute.body().string());
    }


    /**
     * Post的方式提交请求
     * @Author:Dyy
     * @Description:
     * @Date: Created in 16:20 2018/10/22
     * @param
     */
    @Test
    public void Post提交请求() throws IOException {
        MediaType mediaType = MediaType.get("text/x-markdown; charset=utf-8");

        String request = "I am Jdqm.";
        Request request1 = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(mediaType, request))
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request1);
        Response execute = call.execute();
        Headers headers = execute.headers();
        for (int i = 0; i < headers.size(); i++) {
            System.out.println(headers.name(i)+":"+headers.value(i));
        }

        System.out.println(execute.body().string());
    }


    /**
     * 通过流的形式提交请求内容
     * @Author:Dyy
     * @Description:
     * @Date: Created in 16:29 2018/10/22
     * @param
     */
    @Test
    public void Post方式提交流() throws IOException {
        RequestBody requestBody = new RequestBody() {

            @Override
            public MediaType contentType() {
                return MediaType.parse("text/x-markdown; charset=utf-8");
            }

            @Override
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                bufferedSink.writeUtf8("I am Dyy.");
            }
        };

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        Response execute = call.execute();
        Headers headers = execute.headers();
        for (int i = 0; i < headers.size(); i++) {
            System.out.println(headers.name(i)+":"+headers.value(i));
        }

        System.out.println(execute.body().string());
    }

    /**
     * 提交文件的内容到指定的地址
     * @Author:Dyy
     * @Description:
     * @Date: Created in 16:43 2018/10/22
     * @param
     */
    @Test
    public void Post提交文件() throws IOException {
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        OkHttpClient okHttpClient = new OkHttpClient();
        File file = new File("./test.txt");
        Request build = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(mediaType, file))
                .build();

        Call call = okHttpClient.newCall(build);
        Response execute = call.execute();
        Headers headers = execute.headers();
        for (int i = 0; i < headers.size(); i++) {
            System.out.println(headers.name(i)+":"+headers.value(i));
        }
        System.out.println(execute.body().string());
    }


    @Test
    public void Post方式提交表单请求() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        FormBody build = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();
        Request build1 = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(build)
                .build();
        Call call = okHttpClient.newCall(build1);
        Response execute = call.execute();
        Headers headers = execute.headers();

        for (int i = 0; i < headers.size(); i++) {
            System.out.println(headers.name(i)+":"+headers.value(i));

        }
        System.out.println(execute.body().string());
    }


    @Test
    public void 缓冲请求数据() throws IOException {
        Request request = new Request.Builder()
                //设置不使用缓存
                .cacheControl(new CacheControl.Builder().noCache().build())
                .url("http://publicobject.com/helloworld.txt")
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        Response execute = call.execute();
        System.out.println(execute.body().string());
    }


    /**
     * 缓冲请求
     * @Author:Dyy
     * @Description:
     * @Date: Created in 10:22 2018/10/23
     * @param
     */
    @Test
    public void 缓存() throws IOException {

        Request request = new Request.Builder()
                //设置不使用缓存
                .cacheControl(new CacheControl.Builder().noCache().build())
                .url("http://publicobject.com/helloworld.txt")
                .build();

        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        Response execute = call.execute();
        System.out.println(execute.body().string());




        Request build = new Request.Builder()
                .cacheControl(new CacheControl.Builder()
                        .onlyIfCached()
                        .build())
                .url("http://publicobject.com/helloworld.txt")
                .build();

        Response execute1 = okHttpClient.newCall(build).execute();

        System.out.println(execute1.body().string());
    }




}
