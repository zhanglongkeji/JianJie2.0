package com.zlkj.jianjie.util;

import android.content.Context;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 字符串工具类
 * 
 * @author Administrator
 * 
 */
public class StringUtils
{

    public static final String IMGURL="http://test.newjinrong.com/";
    /**
     * 空字符串
     */
    public static final String STR_EMPTY = "";

    /**
     * 判断字符串是否为�?
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str)
    {
        return str == null || "".equals(str.trim());
    }


    public static boolean isStringEmpty(String str) {
        return str == null || str.trim().length() == 0 || str.trim().equals("null") || "".equals(str.trim());
    }

    public static boolean isNotStringEmpty(String str) {
        return !isStringEmpty(str);
    }


    /**
     * 判断字符串是否为�?
     * 
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str)
    {
        if(str == null || "".equals(str.trim())){
            return  false;
        }else{
            return true;
        }  

    }


    /**
     * 判断字符串是否为null,或�?""、{}、[]
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty2(String str)
    {
        return str == null || "".equals(str.trim()) || "{}".equals(str)
                || "[]".equals(str);
    }

    /**
     * 字符串转stream
     * 
     * @param str
     * @return
     */
    public static InputStream StringToInputStream(String str)
    {
        if (StringUtils.isEmpty(str))
            return null;
        ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
        return stream;
    }

    /**
     * URL编码转换
     * 
     * @param src
     * @return
     */
    public static String escape(String src)
    {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++)
        {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j)
                    || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256)
            {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            }
            else
            {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    /**
     * URL解码转换
     * 
     * @param src
     * @return
     */
    public static String unescape(String src)
    {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length())
        {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos)
            {
                if (src.charAt(pos + 1) == 'u')
                {
                    ch = (char) Integer.parseInt(
                            src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                }
                else
                {
                    ch = (char) Integer.parseInt(
                            src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            }
            else
            {
                if (pos == -1)
                {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                }
                else
                {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

    /**
     * 字符串替�?
     * 
     * @param line
     * @param oldString
     * @param newString
     * @return
     */
    public static final String replace(String line, String oldString,
            String newString)
    {
        if (line == null)
        {
            return null;
        }
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0)
        {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0)
            {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }                                
        return line;
    }

    /**
     * 根据body内容生成完整的HTML内容
     * 
     * @param bodyContent
     * @param encode
     *            默认为utf-8
     * @return
     */
    public static String genHtml(String bodyContent, String encode)
    {
        if (encode == null || "".equals(encode.trim()))
            encode = "utf-8";

        StringBuffer sb = new StringBuffer();
        sb.append("<html xmlns=http://www.w3.org/1999/xhtml>\n");
        sb.append("<head>\n");
        sb.append("<meta http-equiv='Content-Type' content='text/html; charset="
                + encode + "' />\n");
        sb.append("<title>查看消息</title>\n");
        sb.append("</head>\n");
        sb.append("\n");
        sb.append("<body>\n");
        sb.append(bodyContent);
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    public static String removeHtml(String htmlStr)
    {
        String result = "";
        boolean flag = true;
        if (htmlStr == null || "".equals(htmlStr.trim()))
        {
            return "";
        }

        htmlStr = htmlStr.replace("\"", ""); // 去掉引号

        char[] a = htmlStr.toCharArray();
        int length = a.length;
        for (int i = 0; i < length; i++)
        {
            if (a[i] == '<')
            {
                flag = false;
                continue;
            }
            if (a[i] == '>')
            {
                flag = true;
                continue;
            }
            if (flag == true)
            {
                result += a[i];
            }
        }
        return result.toString();
    }

    /************************************* Base64 编解�?*********************************************/
    private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
            .toCharArray();

    /** Base64 encode the given data */
    public static String encode(byte[] data)
    {
        int start = 0;
        int len = data.length;
        StringBuffer buf = new StringBuffer(data.length * 3 / 2);

        int end = len - 3;
        int i = start;
        int n = 0;

        while (i <= end)
        {
            int d = ((((int) data[i]) & 0x0ff) << 16)
                    | ((((int) data[i + 1]) & 0x0ff) << 8)
                    | (((int) data[i + 2]) & 0x0ff);

            buf.append(legalChars[(d >> 18) & 63]);
            buf.append(legalChars[(d >> 12) & 63]);
            buf.append(legalChars[(d >> 6) & 63]);
            buf.append(legalChars[d & 63]);

            i += 3;

            if (n++ >= 14)
            {
                n = 0;
                buf.append(" ");
            }
        }

        if (i == start + len - 2)
        {
            int d = ((((int) data[i]) & 0x0ff) << 16)
                    | ((((int) data[i + 1]) & 255) << 8);

            buf.append(legalChars[(d >> 18) & 63]);
            buf.append(legalChars[(d >> 12) & 63]);
            buf.append(legalChars[(d >> 6) & 63]);
            buf.append("=");
        }
        else if (i == start + len - 1)
        {
            int d = (((int) data[i]) & 0x0ff) << 16;

            buf.append(legalChars[(d >> 18) & 63]);
            buf.append(legalChars[(d >> 12) & 63]);
            buf.append("==");
        }

        return buf.toString();
    }

    /**
     * Decodes the given Base64 encoded String to a new byte array. The byte
     * array holding the decoded data is returned.
     */
    public static byte[] decode(String s)
    {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try
        {
            decode(s, bos);
        }
        catch (IOException e)
        {
            throw new RuntimeException();
        }
        byte[] decodedBytes = bos.toByteArray();
        try
        {
            bos.close();
            bos = null;
        }
        catch (IOException ex)
        {
            System.err.println("Error while decoding BASE64: " + ex.toString());
        }
        return decodedBytes;
    }

    private static void decode(String s, OutputStream os) throws IOException
    {
        int i = 0;

        int len = s.length();

        while (true)
        {
            while (i < len && s.charAt(i) <= ' ')
                i++;

            if (i == len)
                break;

            int tri = (decode(s.charAt(i)) << 18)
                    + (decode(s.charAt(i + 1)) << 12)
                    + (decode(s.charAt(i + 2)) << 6)
                    + (decode(s.charAt(i + 3)));

            os.write((tri >> 16) & 255);
            if (s.charAt(i + 2) == '=')
                break;
            os.write((tri >> 8) & 255);
            if (s.charAt(i + 3) == '=')
                break;
            os.write(tri & 255);

            i += 4;
        }
    }

    private static int decode(char c)
    {
        if (c >= 'A' && c <= 'Z')
            return ((int) c) - 65;
        else if (c >= 'a' && c <= 'z')
            return ((int) c) - 97 + 26;
        else if (c >= '0' && c <= '9')
            return ((int) c) - 48 + 26 + 26;
        else
            switch (c)
            {
            case '+':
                return 62;
            case '/':
                return 63;
            case '=':
                return 0;
            default:
                throw new RuntimeException("unexpected code: " + c);
            }
    }

    /************************************* 压缩、解�?****************************************/

    /**
     * 压缩方法
     * 
     * @param str
     * @return
     */
    public static byte[] compress(String str)
    {
        if (str == null)
            return null;
        byte[] compressed;
        ByteArrayOutputStream out = null;
        ZipOutputStream zout = null;
        try
        {
            out = new ByteArrayOutputStream();
            zout = new ZipOutputStream(out);
            zout.putNextEntry(new ZipEntry("0"));
            zout.write(str.getBytes());
            zout.closeEntry();
            compressed = out.toByteArray();
        }
        catch (IOException e)
        {
            compressed = null;
        }
        finally
        {
            if (zout != null)
            {
                try
                {
                    zout.close();
                }
                catch (IOException e)
                {
                }
            }
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                }
            }
        }
        return compressed;
    }

    /**
     * 
     * @param compressed
     * @return
     */
    public static final String decompress(byte[] compressed)
    {

        if (compressed == null)
            return null;

        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        ZipInputStream zin = null;
        String decompressed;

        try
        {

            out = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(compressed);
            zin = new ZipInputStream(in);
            byte[] buffer = new byte[1024];
            int offset = -1;

            while ((offset = zin.read(buffer)) != -1)
            {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        }
        catch (IOException e)
        {
            decompressed = null;
        }
        finally
        {
            if (zin != null)
            {
                try
                {
                    zin.close();
                }
                catch (IOException e)
                {
                }
            }

            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                }

            }

            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                }
            }
        }

        return decompressed;
    }

    /**
     * 验证是否符合手机号码格式
     * 
     * @param phoneNum
     * @return
     */
    public static boolean checkPhoneNum(String phoneNum)
    {
        final String regx = "^13[\\d]{9}$|^14[5,7]{1}\\d{8}$|^15[^4]{1}\\d{8}$|^17[0,6,7,8]{1}\\d{8}$|^18[\\d]{9}$";

        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(phoneNum);

        return m.matches();
    }

    public static String format(double value, String chart)
    {
        DecimalFormat df = new DecimalFormat();
        df.applyPattern(chart);
        return df.format(value);
    }

    public static String getVolume(String src)
    {
        if (!src.contains("."))
        {
            return src;
        }
        else
        {
            String tmp = src.substring(src.indexOf(".") + 1);
            int i = Integer.parseInt(tmp);
            System.out.println(i);
            if (i == 0)
            {
                return src.substring(0, src.indexOf("."));
            }
            else
            {
                return src;
            }
        }
    }

    /**
     * Unicode转String方法
     * 
     * @param str
     * @return
     */
    public static String UnicodeToString(String str)
    {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(str);
        char ch;
        while (matcher.find())
        {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), ch + "");
        }
        return str;
    }

    /**
     * 字体大小动�?设置方法
     * 
     * @param string
     *            ,start,end,TextSize
     * @return
     */
    public static SpannableString setMyTextType(String string, int start,
            int end, int TextSize)
    {
        SpannableString builder = new SpannableString(string);
        builder.setSpan(new AbsoluteSizeSpan(TextSize), start, end,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return builder;
    }

    /*public static CustomProgressDialog showProgress(Context context) {
        CustomProgressDialog window = showProgress(context,
                context.getString(R.string.wait));
        return window;
    }

    public static CustomProgressDialog showProgress(final Context context,
            String hintText) {
        // CustomProgressDialog window = CustomProgressDialog.show(context, "",
        // hintText);
        CustomProgressDialog window = new CustomProgressDialog(context,
                hintText);
        // window.getWindow().setGravity(Gravity.CENTER);
        window.show();

        // 默认可取消的模式，并在取消之时�?知用户，线程仍在后台加载�?
        window.setCancelable(true);
        window.setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface arg0) {
                StringUtils.showShortMessage(context,
                        context.getString(R.string.auto_transferred));
            }
        });
        return window;
    }*/


    public static void showShortMessage(Context mContext, CharSequence text) {
        if (text != null && text.length() > 0) {
            Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
        }
    }

    //修改字符串中某些字符的颜�?
    public static CharSequence setTextColor(String text,int start[],int end[],String color,int a){

        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.parseColor(color)); 
        ForegroundColorSpan redSpan2 = new ForegroundColorSpan(Color.parseColor("#6C552B"));
        ForegroundColorSpan redSpan3 = new ForegroundColorSpan(Color.parseColor(color)); 
        ForegroundColorSpan redSpan4 = new ForegroundColorSpan(Color.parseColor(color)); 
        ForegroundColorSpan redSpan5 = new ForegroundColorSpan(Color.parseColor("#6C552B")); 
        ForegroundColorSpan redSpan6 = new ForegroundColorSpan(Color.parseColor("#6C552B")); 
        ForegroundColorSpan redSpan7 = new ForegroundColorSpan(Color.parseColor("#6C552B")); 


        SpannableStringBuilder builder = new SpannableStringBuilder(text);

        if(a==3){
            builder.setSpan(redSpan2, 0, start[0], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(redSpan, start[0], end[0], Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            builder.setSpan(redSpan5, end[0], start[1], Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            builder.setSpan(redSpan3, start[1], end[1], Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            builder.setSpan(redSpan6,  end[1], start[2], Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            builder.setSpan(redSpan4,  start[2], end[2], Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            builder.setSpan(redSpan7,  end[2], text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        }else if(a==2){
            builder.setSpan(redSpan2, 0, start[0], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(redSpan, start[0], end[0], Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            builder.setSpan(redSpan5, end[0], start[1], Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            builder.setSpan(redSpan3, start[1], end[1], Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            builder.setSpan(redSpan7,  end[1], text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        }else if(a==1){
            builder.setSpan(redSpan2, 0, start[0], Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(redSpan, start[0], end[0], Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            builder.setSpan(redSpan7,  end[0], text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }


        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色  "#E43B1F"

        return builder;
    }


    //小数点四舍五�?
    public static double convert(double value)
    {
        long l1 = Math.round(value * 100); // 四舍五入
        double ret = l1 / 100.0; // 注意：使�?100.0 而不�?100
        return ret;
    }

    //判断是否为中�?
    public static boolean isChinese(String name)
    {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); i++)
        {
            Matcher m = p.matcher(cTemp[i] + "");
            if (!m.matches())
            {
                return false;
            }
        }
        return true;

    }




    /** 
     * 时间戳转换成日期格式字符�?
     * @param seconds 精确到秒的字符串 
     * @param
     * @return 
     */  
    public static String timeStamp2Date(String seconds,String format) {  
        if(seconds == null || StringUtils.isEmpty(seconds) || seconds.equals("null")){  
            return "";  
        }  
        if(format == null || StringUtils.isEmpty(format)) format = "yyyy-MM-dd HH:mm:ss";  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds+"000")));  
    }  


    /** 
     * 时间戳转换成日期格式字符�?
     * @param seconds 精确到秒的字符串 
     * @param
     * @return 
     */  
    public static String timeStamp2Date2(String seconds,String format) {  
        if(seconds == null || StringUtils.isEmpty(seconds) || seconds.equals("null")){  
            return "";  
        }  
        if(format == null || StringUtils.isEmpty(format)) format = "yyyy/MM/dd";  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds+"000")));  
    }  
    
    /** 
     * 日期格式字符串转换成时间�?
     * @param
     * @param format 如：yyyy-MM-dd HH:mm:ss 
     * @return 
     */  
    public static String date2TimeStamp(String date_str,String format){  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(format);  
            return String.valueOf(sdf.parse(date_str).getTime()/1000);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  

    /** 
     * 取得当前时间戳（精确到秒�?
     * @return 
     */  
    public static String timeStamp(){  
        long time = System.currentTimeMillis();  
        String t = String.valueOf(time/1000);  
        return t;  
    }  


    //判断网络连接是否可用
    public static boolean isNetworkAvailable(Context context) {   
        ConnectivityManager cm = (ConnectivityManager) context   
                .getSystemService(Context.CONNECTIVITY_SERVICE);   
        if (cm == null) {   
        } else{
            /*  //如果仅仅是用来判断网络连�?
�??�??�??  //则可以使�?cm.getActiveNetworkInfo().isAvailable();  
             */           
            NetworkInfo[] info = cm.getAllNetworkInfo();   
            if (info != null) {   
                for (int i = 0; i < info.length; i++) {   
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {   
                        return true;   
                    }   
                }   
            }   
        }   
        return false;   
    } 

    //判断GPS是否打开
    public static boolean isGpsEnabled(Context context) {   
        LocationManager lm = ((LocationManager) context   
                .getSystemService(Context.LOCATION_SERVICE));   
        List<String> accessibleProviders = lm.getProviders(true);   
        return accessibleProviders != null && accessibleProviders.size() > 0;   
    } 

    //判断WIFI是否打开
    public static boolean isWifiEnabled(Context context) {   
        ConnectivityManager mgrConn = (ConnectivityManager) context   
                .getSystemService(Context.CONNECTIVITY_SERVICE);   
        TelephonyManager mgrTel = (TelephonyManager) context   
                .getSystemService(Context.TELEPHONY_SERVICE);   
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn   
                .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel   
                .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);   
    } 
    //判断是否�?G网络
    public static boolean is3rd(Context context) {   
        ConnectivityManager cm = (ConnectivityManager) context   
                .getSystemService(Context.CONNECTIVITY_SERVICE);   
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();   
        if (networkINfo != null   
                && networkINfo.getType() == ConnectivityManager.TYPE_MOBILE) {   
            return true;   
        }   
        return false;   
    }  

    //判断是wifi还是3g网络,用户的体现�?在这里了，wifi就可以建议下载或者在线播放�?
    public static boolean isWifi(Context context) {   
        ConnectivityManager cm = (ConnectivityManager) context   
                .getSystemService(Context.CONNECTIVITY_SERVICE);   
        NetworkInfo networkINfo = cm.getActiveNetworkInfo();   
        if (networkINfo != null   
                && networkINfo.getType() == ConnectivityManager.TYPE_WIFI) {   
            return true;   
        }   
        return false;   
    }



    //去除html标签
    public static String delHTMLTag(String htmlStr){ 
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 

        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //过滤script标签 

        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 

        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 

        return htmlStr.trim(); //返回文本字符�?
    } 



    /**
     * json String 解析字段
     * @param values
     * @param obj
     * @return
     */
    public static String getJsonStringValues(String values,JSONObject obj){

        String vac=""; 
        try
        {
            vac= obj.getString(values);

        }
        catch (Exception e)
        {
            vac="";
        }

        return vac;
    }
    
    

    /**
     * json int 解析字段
     * @param values
     * @param obj
     * @return
     */
    public static int getJsonIntValues(String values,JSONObject obj){

        int vac; 
        try
        {
            vac= obj.getInt(values);
        }
        catch (Exception e)
        {
            vac=0;
        }
        
        return vac;
    }
    

    
    
    public static String[] getJsonObjectStringArray(JSONArray jsonArray,
            String key) {
        String res[];
        try {
            res = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = new JSONObject(jsonArray.getString(i));
                res[i] = getJsonStringValue(jsonObject2, key);
            }
        } catch (Exception e) {
            return new String[] {};
        }
        return res;
    }

    public static String[] getJsonStringArrayValue(JSONArray jsonArray) {
        String res[];
        try {
            res = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                res[i] = jsonArray.getString(i);
            }
        } catch (Exception e) {
            return new String[] {};
        }
        return res;
    }

    public static int[] getJsonObjectIntegerArray(JSONArray jsonArray,
            String key) {
        int res[];
        try {
            res = new int[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = new JSONObject(jsonArray.getString(i));
                res[i] = getJsonIntegerValue(jsonObject2, key);
            }
        } catch (Exception e) {
            return new int[] {};
        }
        return res;
    }

    public static JSONObject getJsonObject(JSONArray jsonArray, int index) {
        try {
            if (jsonArray != null && index >= 0 && index < jsonArray.length()) {
                return jsonArray.getJSONObject(index);
            }
        } catch (JSONException e) {
            return null;
        }
        return null;
    }

    public static String getArrayValue(String[] array, int index) {
        if (array != null && index >= 0 && index < array.length) {
            return array[index];
        }
        return "";
    }

    public static int getArrayValue(int[] array, int index) {
        if (array != null && index >= 0 && index < array.length) {
            return array[index];
        }
        return 0;
    }

    public static String getJsonStringValue(JSONObject jsonObject, String key) {
        return getJsonStringValue(jsonObject, key, "");
    }

    public static String getJsonStringValue(JSONObject jsonObject, String key,
            String defaultValue) {
        try {
            if (jsonObject != null && jsonObject.has(key)) {
                String value = jsonObject.getString(key).trim();
                if (value.equals("null")) {
                    value = "";
                }
                return value;
            }
        } catch (Exception e) {
            return defaultValue;
        }
        return defaultValue;
    }

    public static int getJsonIntegerValue(JSONObject json, String key) {
        return getJsonIntegerValue(json, key, 0);
    }

    public static int getJsonIntegerValue(JSONObject jsonObject, String key,
            int defaultValue) {
        try {
            if (jsonObject != null && jsonObject.has(key)) {
                return jsonObject.getInt(key);
            }
        } catch (Exception e) {
            return defaultValue;
        }
        return defaultValue;
    }

    public static Long getJsonLongValue(JSONObject json, String key) {
        return getJsonLongValue(json, key, 0L);
    }

    public static Long getJsonLongValue(JSONObject jsonObject, String key,
            Long defaultValue) {
        try {
            if (jsonObject != null && jsonObject.has(key)) {
                return jsonObject.getLong(key);
            }
        } catch (Exception e) {
            return defaultValue;
        }
        return defaultValue;
    }

    public static float getJsonFloatValue(JSONObject jsonObject, String key,
            float defaultValue) {
        try {
            if (jsonObject != null && jsonObject.has(key)) {
                return Float.valueOf(jsonObject.getString(key));
            }
        } catch (Exception e) {
            return defaultValue;
        }
        return defaultValue;
    }

    public static boolean getJsonBooleanValue(JSONObject jsonObject,
            String key, boolean defaultValue) {
        try {
            if (jsonObject != null && jsonObject.has(key)) {
                return jsonObject.getBoolean(key);
            }
        } catch (Exception e) {
            return defaultValue;
        }
        return defaultValue;
    }

    public static JSONObject getJsonObject(JSONObject jsonObject, String key) {
        try {
            if (jsonObject != null && jsonObject.has(key)) {
                return jsonObject.getJSONObject(key);
            }
        } catch (Exception e) {
            return new JSONObject();
        }
        return new JSONObject();
    }

    public static JSONArray getJsonArray(JSONObject jsonObject, String key) {
        try {
            if (jsonObject != null && jsonObject.has(key)) {
                return jsonObject.getJSONArray(key);
            }
        } catch (Exception e) {
            StringUtils.printLog("test", jsonObject.toString());
            return new JSONArray();
        }
        return new JSONArray();
    }
    
    public static void printLog(String tag, String msg) {
        Log.e(tag, msg);
    }



    /**
     * 验证是否是手机号码
     *
     * @param str
     * @return
     */
    public static boolean isCellphone(String str) {
        Pattern pattern = Pattern
                .compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0,6-8])|(18[0-9]))\\d{8}$");
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断email格式是否正确
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }



    //根据包名来查找到相应的APK渠道
    public static String ProductidToApk(String packeg){
        String productid="";

        if(packeg.equals("a_bjdk_qq")){
            productid="8";
        }else  if(packeg.equals("a_bjdk_anzhuoshichang")){
            productid="9";
        }else  if(packeg.equals("a_bjdk_baidu91")){
            productid="10";
        }else  if(packeg.equals("a_bjdk_91shouji")){
            productid="11";
        }else  if(packeg.equals("a_bjdk_360")){
            productid="12";
        }else  if(packeg.equals("a_bjdk_nduo")){
            productid="13";
        }else  if(packeg.equals("a_bjdk_mi")){
            productid="14";
        }else  if(packeg.equals("a_bjdk_anzhiAppChina")){
            productid="15";
        }else  if(packeg.equals("a_bjdk_taobao")){
            productid="16";
        }else  if(packeg.equals("a_bjdk_appchina")){
            productid="17";
        }else  if(packeg.equals("a_bjdk_gfan")){
            productid="18";
        }else  if(packeg.equals("a_bjdk_flyme")){
            productid="19";
        }else  if(packeg.equals("a_bjdk_189store")){
            productid="20";
        }else  if(packeg.equals("a_bjdk_10086shop")){
            productid="21";
        }else  if(packeg.equals("a_bjdk_wostore")){
            productid="22";
        }else  if(packeg.equals("a_bjdk_mmy")){
            productid="23";
        }else  if(packeg.equals("a_bjdk_lenovo")){
            productid="24";
        }else  if(packeg.equals("a_bjdk_eoe")){
            productid="26";
        }else  if(packeg.equals("a_bjdk_wdj")){
            productid="27";
        }else  if(packeg.equals("a_bjdk_oppo")){
            productid="28";
        }else  if(packeg.equals("a_bjdk_sogo")){
            productid="29";
        }else  if(packeg.equals("a_bjdk_suning")){
            productid="30";
        } else  if(packeg.equals("a_bjdk_liqu")){
            productid="31";
        }else  if(packeg.equals("a_bjdk_jinli")){
            productid="32";
        }else  if(packeg.equals("a_bjdk_huawei")){
            productid="33";
        }else  if(packeg.equals("a_bjdk_3g")){
            productid="34";
        }else  if(packeg.equals("a_bjdk_adonline")){
            productid="35";
        }else  if(packeg.equals("a_bjdk_anbei2345")){
            productid="36";
        }else  if(packeg.equals("a_bjdk_2345")){
            productid="37";
        }else  if(packeg.equals("a_bjdk_vivo")){
            productid="38";
        }else  if(packeg.equals("a_bjdk_samsung")){
            productid="39";
        }

        return  productid;

    }



}
