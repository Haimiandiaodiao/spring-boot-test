package _003_JavaNetwork;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class _008_KeepAlive {

    /**
     * 现在好多网站使用的都是https请求了。所以会经常收到301的响应
     * 而且现在的网站都是keep-alive的长链接请求的所以 所以 不能够通过包装流BufferedReader 判断readLine != null的形式来判断是不是到了
     * 响应的结尾   而是要通过判断请求头中的 content-length读取制定长度的流信息
     *
     * 暂且还不请出循环中的判断每次都判断\r\n\r\n会不会有效率的问题
     * 但是可以完整的取到网站的数据
     * @throws IOException
     */
    @Test
    public void baseUse1() throws IOException {
        Socket socket = new Socket("www.baidu.com", 80);
        String request = "GET / HTTP/1.1\r\n" +
                "Host: www.baidu.com\r\n" +
                "Cache-Control: no-cache\r\n" +
                "Postman-Token: ecee26ed-af00-4866-963f-c3f58d07a2b9\r\n" +
                "Accept-Charset: utf-8\r\n"+
                "Accept: text/html\r\n"+
                "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22\r\n"+
                "\r\n";
        System.out.println(request);
        System.out.println("================================");
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(request.getBytes());
        outputStream.flush();

        InputStream inputStream = socket.getInputStream();

        StringBuffer header = new StringBuffer();

        while (true){
            // 拿到请求头
            int read = inputStream.read();
            header.append((char)read);

            if (header.indexOf("\r\n\r\n") != -1){
                break;
            }
        }
        System.out.println(header);
        System.out.println("====================");
        //获得请求头中的
        int i = header.indexOf("Content-Length:");
        int i1 = header.indexOf("\r\n", i);

        String length = header.substring(i + ("Content-Length:".length()), i1).trim();
        System.out.println("====>"+length);
        int i2 = Integer.parseInt(length);
        byte[] tmp = new byte[i2];
        System.out.println("======$$$$=============$$$====>");
//        for (int i3 = 0; i3 < i2; i3++) {
//            System.out.print((char) inputStream.read());
//        }
        inputStream.read(tmp);

        String s = new String(tmp, "utf-8");
        System.out.println(s);
        socket.close();




    }

    @Test
    public void baseUse2(){
        String temp = "<!DOCTYPE html><!--STATUS OK-->\r\n" +
                "<html>\r\n" +
                "<head>\r\n" +
                "\t<meta http-equiv=\"content-type\" content=\"text/html;charset=utf-8\">\r\n" +
                "\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">\r\n" +
                "\t<link rel=\"dns-prefetch\" href=\"//s1.bdstatic.com\"/>\r\n" +
                "\t<link rel=\"dns-prefetch\" href=\"//t1.baidu.com\"/>\r\n" +
                "\t<link rel=\"dns-prefetch\" href=\"//t2.baidu.com\"/>\r\n" +
                "\t<link rel=\"dns-prefetch\" href=\"//t3.baidu.com\"/>\r\n" +
                "\t<link rel=\"dns-prefetch\" href=\"//t10.baidu.com\"/>\r\n" +
                "\t<link rel=\"dns-prefetch\" href=\"//t11.baidu.com\"/>\r\n" +
                "\t<link rel=\"dns-prefetch\" href=\"//t12.baidu.com\"/>\r\n" +
                "\t<link rel=\"dns-prefetch\" href=\"//b1.bdstatic.com\"/>\r\n" +
                "\t<title>ç\u0099¾åº¦ä¸\u0080ä¸\u008Bï¼\u008Cä½ å°±ç\u009F¥é\u0081\u0093</title>\r\n" +
                "\t<link href=\"http://s1.bdstatic.com/r/www/cache/static/home/css/index.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n" +
                "\t<!--[if lte IE 8]><style index=\"index\" >#content{height:480px\\9}#m{top:260px\\9}</style><![endif]-->\r\n" +
                "\t<!--[if IE 8]><style index=\"index\" >#u1 a.mnav,#u1 a.mnav:visited{font-family:simsun}</style><![endif]-->\r\n" +
                "\t<script>var hashMatch = document.location.href.match(/#+(.*wd=[^&].+)/);if (hashMatch && hashMatch[0] && hashMatch[1]) {document.location.replace(\"http://\"+location.host+\"/s?\"+hashMatch[1]);}var ns_c = function(){};</script>\r\n" +
                "\t<script>function h(obj){obj.style.behavior='url(#default#homepage)';var a = obj.setHomePage('//www.baidu.com/');}</script>\r\n" +
                "\t<noscript><meta http-equiv=\"refresh\" content=\"0; url=/baidu.html?from=noscript\"/></noscript>\r\n" +
                "\t<script>window._ASYNC_START=new Date().getTime();</script>\r\n" +
                "</head>\r\n" +
                "<body link=\"#0000cc\"><div id=\"wrapper\" style=\"display:none;\"><div id=\"u\"><a href=\"//www.baidu.com/gaoji/preferences.html\"  onmousedown=\"return user_c({'fm':'set','tab':'setting','login':'0'})\">æ\u0090\u009Cç´¢è®¾ç½®</a>|<a id=\"btop\" href=\"/\"  onmousedown=\"return user_c({'fm':'set','tab':'index','login':'0'})\">ç\u0099¾åº¦é¦\u0096é¡µ</a>|<a id=\"lb\" href=\"https://passport.baidu.com/v2/?login&tpl=mn&u=http%3A%2F%2Fwww.baidu.com%2F\" onclick=\"return false;\"  onmousedown=\"return user_c({'fm':'set','tab':'login'})\">ç\u0099»å½\u0095</a><a href=\"https://passport.baidu.com/v2/?reg&regType=1&tpl=mn&u=http%3A%2F%2Fwww.baidu.com%2F\"  onmousedown=\"return user_c({'fm':'set','tab':'reg'})\" target=\"_blank\" class=\"reg\">æ³¨å\u0086\u008C</a></div><div id=\"head\"><div class=\"s_nav\"><a href=\"/\" class=\"s_logo\" onmousedown=\"return c({'fm':'tab','tab':'logo'})\"><img src=\"//www.baidu.com/img/baidu_jgylogo3.gif\" width=\"117\" height=\"38\" border=\"0\" alt=\"å\u0088°ç\u0099¾åº¦é¦\u0096é¡µ\" title=\"å\u0088°ç\u0099¾åº¦é¦\u0096é¡µ\"></a><div class=\"s_tab\" id=\"s_tab\"><a href=\"http://news.baidu.com/ns?cl=2&rn=20&tn=news&word=\" wdfield=\"word\"  onmousedown=\"return c({'fm':'tab','tab':'news'})\">æ\u0096°é\u0097»</a>&#12288;<b>ç½\u0091é¡µ</b>&#12288;<a href=\"http://tieba.baidu.com/f?kw=&fr=wwwt\" wdfield=\"kw\"  onmousedown=\"return c({'fm':'tab','tab':'tieba'})\">è´´å\u0090§</a>&#12288;<a href=\"http://zhidao.baidu.com/q?ct=17&pn=0&tn=ikaslist&rn=10&word=&fr=wwwt\" wdfield=\"word\"  onmousedown=\"return c({'fm':'tab','tab':'zhidao'})\">ç\u009F¥é\u0081\u0093</a>&#12288;<a href=\"http://music.baidu.com/search?fr=ps&key=\" wdfield=\"key\"  onmousedown=\"return c({'fm':'tab','tab':'music'})\">é\u009F³ä¹\u0090</a>&#12288;<a href=\"http://image.baidu.com/i?tn=baiduimage&ps=1&ct=201326592&lm=-1&cl=2&nc=1&word=\" wdfield=\"word\"  onmousedown=\"return c({'fm':'tab','tab':'pic'})\">å\u009B¾ç\u0089\u0087</a>&#12288;<a href=\"http://v.baidu.com/v?ct=301989888&rn=20&pn=0&db=0&s=25&word=\" wdfield=\"word\"   onmousedown=\"return c({'fm':'tab','tab':'video'})\">è§\u0086é¢\u0091</a>&#12288;<a href=\"http://map.baidu.com/m?word=&fr=ps01000\" wdfield=\"word\"  onmousedown=\"return c({'fm':'tab','tab':'map'})\">å\u009C°å\u009B¾</a>&#12288;<a href=\"http://wenku.baidu.com/search?word=&lm=0&od=0\" wdfield=\"word\"  onmousedown=\"return c({'fm':'tab','tab':'wenku'})\">æ\u0096\u0087åº\u0093</a>&#12288;<a href=\"//www.baidu.com/more/\"  onmousedown=\"return c({'fm':'tab','tab':'more'})\">æ\u009B´å¤\u009AÂ»</a></div></div><form id=\"form\" name=\"f\" action=\"/s\" class=\"fm\" ><input type=\"hidden\" name=\"ie\" value=\"utf-8\"><input type=\"hidden\" name=\"f\" value=\"8\"><input type=\"hidden\" name=\"rsv_bp\" value=\"1\"><span class=\"bg s_ipt_wr\"><input name=\"wd\" id=\"kw\" class=\"s_ipt\" value=\"\" maxlength=\"100\"></span><span class=\"bg s_btn_wr\"><input type=\"submit\" id=\"su\" value=\"ç\u0099¾åº¦ä¸\u0080ä¸\u008B\" class=\"bg s_btn\" onmousedown=\"this.className='bg s_btn s_btn_h'\" onmouseout=\"this.className='bg s_btn'\"></span><span class=\"tools\"><span id=\"mHolder\"><div id=\"mCon\"><span>è¾\u0093å\u0085¥æ³\u0095</span></div><ul id=\"mMenu\"><li><a href=\"javascript:;\" name=\"ime_hw\">æ\u0089\u008Bå\u0086\u0099</a></li><li><a href=\"javascript:;\" name=\"ime_py\">æ\u008B¼é\u009F³</a></li><li class=\"ln\"></li><li><a href=\"javascript:;\" name=\"ime_cl\">å\u0085³é\u0097\u00AD</a></li></ul></span><span class=\"shouji\"><strong>æ\u008E¨è\u008D\u0090&nbsp;:&nbsp;</strong><a href=\"http://w.x.baidu.com/go/mini/8/10000020\" onmousedown=\"return ns_c({'fm':'behs','tab':'bdbrowser'})\">ç\u0099¾åº¦æµ\u008Fè§\u0088å\u0099¨ï¼\u008Cæ\u0089\u0093å¼\u0080ç½\u0091é¡µå¿«2ç§\u0092ï¼\u0081</a></span></span></form></div><div id=\"content\"><div id=\"u1\"><a href=\"http://news.baidu.com\" name=\"tj_trnews\" class=\"mnav\">æ\u0096°é\u0097»</a><a href=\"http://www.hao123.com\" name=\"tj_trhao123\" class=\"mnav\">hao123</a><a href=\"http://map.baidu.com\" name=\"tj_trmap\" class=\"mnav\">å\u009C°å\u009B¾</a><a href=\"http://v.baidu.com\" name=\"tj_trvideo\" class=\"mnav\">è§\u0086é¢\u0091</a><a href=\"http://tieba.baidu.com\" name=\"tj_trtieba\" class=\"mnav\">è´´å\u0090§</a><a href=\"https://passport.baidu.com/v2/?login&tpl=mn&u=http%3A%2F%2Fwww.baidu.com%2F\" name=\"tj_login\" id=\"lb\" onclick=\"return false;\">ç\u0099»å½\u0095</a><a href=\"//www.baidu.com/gaoji/preferences.html\" name=\"tj_settingicon\" id=\"pf\">è®¾ç½®</a><a href=\"//www.baidu.com/more/\" name=\"tj_briicon\" id=\"bri\">æ\u009B´å¤\u009Aäº§å\u0093\u0081</a></div><div id=\"m\"><p id=\"lg\"><img src=\"//www.baidu.com/img/bd_logo.png\" width=\"270\" height=\"129\"></p><p id=\"nv\"><a href=\"http://news.baidu.com\">æ\u0096°&nbsp;é\u0097»</a>ã\u0080\u0080<b>ç½\u0091&nbsp;é¡µ</b>ã\u0080\u0080<a href=\"http://tieba.baidu.com\">è´´&nbsp;å\u0090§</a>ã\u0080\u0080<a href=\"http://zhidao.baidu.com\">ç\u009F¥&nbsp;é\u0081\u0093</a>ã\u0080\u0080<a href=\"http://music.baidu.com\">é\u009F³&nbsp;ä¹\u0090</a>ã\u0080\u0080<a href=\"http://image.baidu.com\">å\u009B¾&nbsp;ç\u0089\u0087</a>ã\u0080\u0080<a href=\"http://v.baidu.com\">è§\u0086&nbsp;é¢\u0091</a>ã\u0080\u0080<a href=\"http://map.baidu.com\">å\u009C°&nbsp;å\u009B¾</a></p><div id=\"fm\"><form id=\"form1\" name=\"f1\" action=\"/s\" class=\"fm\"><span class=\"bg s_ipt_wr\"><input type=\"text\" name=\"wd\" id=\"kw1\" maxlength=\"100\" class=\"s_ipt\"></span><input type=\"hidden\" name=\"rsv_bp\" value=\"0\"><input type=hidden name=ch value=\"\"><input type=hidden name=tn value=\"baidu\"><input type=hidden name=bar value=\"\"><input type=\"hidden\" name=\"rsv_spt\" value=\"3\"><input type=\"hidden\" name=\"ie\" value=\"utf-8\"><span class=\"bg s_btn_wr\"><input type=\"submit\" value=\"ç\u0099¾åº¦ä¸\u0080ä¸\u008B\" id=\"su1\" class=\"bg s_btn\" onmousedown=\"this.className='bg s_btn s_btn_h'\" onmouseout=\"this.className='bg s_btn'\"></span></form><span class=\"tools\"><span id=\"mHolder1\"><div id=\"mCon1\"><span>è¾\u0093å\u0085¥æ³\u0095</span></div></span></span><ul id=\"mMenu1\"><div class=\"mMenu1-tip-arrow\"><em></em><ins></ins></div><li><a href=\"javascript:;\" name=\"ime_hw\">æ\u0089\u008Bå\u0086\u0099</a></li><li><a href=\"javascript:;\" name=\"ime_py\">æ\u008B¼é\u009F³</a></li><li class=\"ln\"></li><li><a href=\"javascript:;\" name=\"ime_cl\">å\u0085³é\u0097\u00AD</a></li></ul></div><p id=\"lk\"><a href=\"http://baike.baidu.com\">ç\u0099¾ç§\u0091</a>ã\u0080\u0080<a href=\"http://wenku.baidu.com\">æ\u0096\u0087åº\u0093</a>ã\u0080\u0080<a href=\"http://www.hao123.com\">hao123</a><span>&nbsp;|&nbsp;<a href=\"//www.baidu.com/more/\">æ\u009B´å¤\u009A&gt;&gt;</a></span></p><p id=\"lm\"></p></div></div><div id=\"ftCon\"><div id=\"ftConw\"><p id=\"lh\"><a id=\"seth\" onClick=\"h(this)\" href=\"/\" onmousedown=\"return ns_c({'fm':'behs','tab':'homepage','pos':0})\">æ\u008A\u008Aç\u0099¾åº¦è®¾ä¸ºä¸»é¡µ</a><a id=\"setf\" href=\"//www.baidu.com/cache/sethelp/index.html\" onmousedown=\"return ns_c({'fm':'behs','tab':'favorites','pos':0})\" target=\"_blank\">æ\u008A\u008Aç\u0099¾åº¦è®¾ä¸ºä¸»é¡µ</a><a onmousedown=\"return ns_c({'fm':'behs','tab':'tj_about'})\" href=\"http://home.baidu.com\">å\u0085³äº\u008Eç\u0099¾åº¦</a><a onmousedown=\"return ns_c({'fm':'behs','tab':'tj_about_en'})\" href=\"http://ir.baidu.com\">About Baidu</a></p><p id=\"cp\">&copy;2018&nbsp;Baidu&nbsp;<a href=\"/duty/\" name=\"tj_duty\">ä½¿ç\u0094¨ç\u0099¾åº¦å\u0089\u008Då¿\u0085è¯»</a>&nbsp;äº¬ICPè¯\u0081030173å\u008F·&nbsp;<img src=\"http://s1.bdstatic.com/r/www/cache/static/global/img/gs_237f015b.gif\"></p></div></div><div id=\"wrapper_wrapper\"></div></div><div class=\"c-tips-container\" id=\"c-tips-container\"></div>\r\n" +
                "<script>window.__async_strategy=2;</script>\r\n" +
                "<script>var bds={se:{},su:{urdata:[],urSendClick:function(){}},util:{},use:{},comm : {domain:\"http://www.baidu.com\",ubsurl : \"http://sclick.baidu.com/w.gif\",tn:\"baidu\",queryEnc:\"\",queryId:\"\",inter:\"\",templateName:\"baidu\",sugHost : \"http://suggestion.baidu.com/su\",query : \"\",qid : \"\",cid : \"\",sid : \"\",indexSid : \"\",stoken : \"\",serverTime : \"\",user : \"\",username : \"\",loginAction : [],useFavo : \"\",pinyin : \"\",favoOn : \"\",curResultNum:\"\",rightResultExist:false,protectNum:0,zxlNum:0,pageNum:1,pageSize:10,newindex:0,async:1,maxPreloadThread:5,maxPreloadTimes:10,preloadMouseMoveDistance:5,switchAddMask:false,isDebug:false,ishome : 1},_base64:{domain : \"http://b1.bdstatic.com/\",b64Exp : -1,pdc : 0}};var name,navigate,al_arr=[];var selfOpen = window.open;eval(\"var open = selfOpen;\");var isIE=navigator.userAgent.indexOf(\"MSIE\")!=-1&&!window.opera;var E = bds.ecom= {};bds.se.mon = {'loadedItems':[],'load':function(){},'srvt':-1};try {bds.se.mon.srvt = parseInt(document.cookie.match(new RegExp(\"(^| )BDSVRTM=([^;]*)(;|$)\"))[2]);document.cookie=\"BDSVRTM=;expires=Sat, 01 Jan 2000 00:00:00 GMT\"; }catch(e){}</script>\r\n" +
                "<script>if(!location.hash.match(/[^a-zA-Z0-9]wd=/)){document.getElementById(\"ftCon\").style.display='block';document.getElementById(\"u1\").style.display='block';document.getElementById(\"content\").style.display='block';document.getElementById(\"wrapper\").style.display='block';setTimeout(function(){try{document.getElementById(\"kw1\").focus();document.getElementById(\"kw1\").parentNode.className += ' iptfocus';}catch(e){}},0);}</script>\r\n" +
                "<script type=\"text/javascript\" src=\"http://s1.bdstatic.com/r/www/cache/static/jquery/jquery-1.10.2.min_f2fb5194.js\"></script>\r\n" +
                "<script>(function(){var index_content = $('#content');var index_foot= $('#ftCon');var index_css= $('head [index]');var index_u= $('#u1');var result_u= $('#u');var wrapper=$(\"#wrapper\");window.index_on=function(){index_css.insertAfter(\"meta:eq(0)\");result_common_css.remove();result_aladdin_css.remove();result_sug_css.remove();index_content.show();index_foot.show();index_u.show();result_u.hide();wrapper.show();if(bds.su&&bds.su.U&&bds.su.U.homeInit){bds.su.U.homeInit();}setTimeout(function(){try{$('#kw1').get(0).focus();window.sugIndex.start();}catch(e){}},0);if(typeof initIndex=='function'){initIndex();}};window.index_off=function(){index_css.remove();index_content.hide();index_foot.hide();index_u.hide();result_u.show();result_aladdin_css.insertAfter(\"meta:eq(0)\");result_common_css.insertAfter(\"meta:eq(0)\");result_sug_css.insertAfter(\"meta:eq(0)\");wrapper.show();};})();</script>\r\n" +
                "<script>window.__switch_add_mask=1;</script>\r\n" +
                "<script type=\"text/javascript\" src=\"http://s1.bdstatic.com/r/www/cache/static/global/js/instant_search_newi_redirect1_20bf4036.js\"></script>\r\n" +
                "<script>initPreload();$(\"#u,#u1\").delegate(\"#lb\",'click',function(){try{bds.se.login.open();}catch(e){}});if(navigator.cookieEnabled){document.cookie=\"NOJS=;expires=Sat, 01 Jan 2000 00:00:00 GMT\";}</script>\r\n" +
                "<script>$(function(){for(i=0;i<3;i++){u($($('.s_ipt_wr')[i]),$($('.s_ipt')[i]),$($('.s_btn_wr')[i]),$($('.s_btn')[i]));}function u(iptwr,ipt,btnwr,btn){if(iptwr && ipt){iptwr.on('mouseover',function(){iptwr.addClass('ipthover');}).on('mouseout',function(){iptwr.removeClass('ipthover');}).on('click',function(){ipt.focus();});ipt.on('focus',function(){iptwr.addClass('iptfocus');}).on('blur',function(){iptwr.removeClass('iptfocus');}).on('render',function(e){var $s = iptwr.parent().find('.bdsug');var l = $s.find('li').length;if(l>=5){$s.addClass('bdsugbg');}else{$s.removeClass('bdsugbg');}});}if(btnwr && btn){btnwr.on('mouseover',function(){btn.addClass('btnhover');}).on('mouseout',function(){btn.removeClass('btnhover');});}}});</script>\r\n" +
                "<script type=\"text/javascript\" src=\"http://s1.bdstatic.com/r/www/cache/static/home/js/bri_7f1fa703.js\"></script>\r\n" +
                "<script>(function(){var _init=false;window.initIndex=function(){if(_init){return;}_init=true;var w=window,d=document,n=navigator,k=d.f1.wd,a=d.getElementById(\"nv\").getElementsByTagName(\"a\"),isIE=n.userAgent.indexOf(\"MSIE\")!=-1&&!window.opera;(function(){if(/q=([^&]+)/.test(location.search)){k.value=decodeURIComponent(RegExp[\"\\x241\"])}})();(function(){var u = G(\"u1\").getElementsByTagName(\"a\"), nv = G(\"nv\").getElementsByTagName(\"a\"), lk = G(\"lk\").getElementsByTagName(\"a\"), un = \"\";var tj_nv = [\"news\",\"tieba\",\"zhidao\",\"mp3\",\"img\",\"video\",\"map\"];var tj_lk = [\"baike\",\"wenku\",\"hao123\",\"more\"];un = bds.comm.user == \"\" ? \"\" : bds.comm.user;function _addTJ(obj){addEV(obj, \"mousedown\", function(e){var e = e || window.event;var target = e.target || e.srcElement;if(target.name){ns_c({'fm':'behs','tab':target.name,'un':encodeURIComponent(un)});}});}for(var i = 0; i < u.length; i++){_addTJ(u[i]);}for(var i = 0; i < nv.length; i++){nv[i].name = 'tj_' + tj_nv[i];}for(var i = 0; i < lk.length; i++){lk[i].name = 'tj_' + tj_lk[i];}})();(function() {var links = {'tj_news': ['word', 'http://news.baidu.com/ns?tn=news&cl=2&rn=20&ct=1&ie=utf-8'],'tj_tieba': ['kw', 'http://tieba.baidu.com/f?ie=utf-8'],'tj_zhidao': ['word', 'http://zhidao.baidu.com/search?pn=0&rn=10&lm=0'],'tj_mp3': ['key', 'http://music.baidu.com/search?fr=ps&ie=utf-8'],'tj_img': ['word', 'http://image.baidu.com/i?ct=201326592&cl=2&nc=1&lm=-1&st=-1&tn=baiduimage&istype=2&fm=&pv=&z=0&ie=utf-8'],'tj_video': ['word', 'http://video.baidu.com/v?ct=301989888&s=25&ie=utf-8'],'tj_map': ['wd', 'http://map.baidu.com/?newmap=1&ie=utf-8&s=s'],'tj_baike': ['word', 'http://baike.baidu.com/search/word?pic=1&sug=1&enc=utf8'],'tj_wenku': ['word', 'http://wenku.baidu.com/search?ie=utf-8']};var domArr = [G('nv'), G('lk'),G('cp')],kw = G('kw1');for (var i = 0, l = domArr.length; i < l; i++) {domArr[i].onmousedown = function(e) {e = e || window.event;var target = e.target || e.srcElement,name = target.getAttribute('name'),items = links[name],reg = new RegExp('^\\\\s+|\\\\s+\\x24'),key = kw.value.replace(reg, '');if (items) {if (key.length > 0) {var wd = items[0], url = items[1],url = url + ( name === 'tj_map' ? encodeURIComponent('&' + wd + '=' + key) : ( ( url.indexOf('?') > 0 ? '&' : '?' ) + wd + '=' + encodeURIComponent(key) ) );target.href = url;} else {target.href = target.href.match(new RegExp('^http:\\/\\/.+\\.baidu\\.com'))[0];}}name && ns_c({'fm': 'behs','tab': name,'query': encodeURIComponent(key),'un': encodeURIComponent(bds.comm.user || '') });};}})();};if(window.pageState==0){initIndex();}})();document.cookie = 'IS_STATIC=1;expires=' + new Date(new Date().getTime() + 10*60*1000).toGMTString();</script>\r\n" +
                "</body></html>";
    //14615
        System.out.println(temp.getBytes().length);
    }
}
