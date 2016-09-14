package com.zlkj.jianjie.imageload;


import com.zlkj.jianjie.util.CommonUtil;



public class FileManager
{

    public static String getSaveFilePath()
    {
        if (CommonUtil.hasSDCard())
        {
            return CommonUtil.getRootFilePath() + "cha4.net/files/";
        }
        else
        {
            return CommonUtil.getRootFilePath() + "cha4.net/files";
        }
    }
}
