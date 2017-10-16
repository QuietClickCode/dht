package com.retailers.dht.common.utils;

import org.ansj.domain.Term;
import org.ansj.library.DicLibrary;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.Iterator;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/15
 */
public class AnsjUtils {
    public static void main(String[] args) throws Exception {
        DicLibrary.insert(DicLibrary.DEFAULT, "裙");//设置自定义分词
        DicLibrary.insert(DicLibrary.DEFAULT, "网球裙");//设置自定义分词
        DicLibrary.insert(DicLibrary.DEFAULT, "网球拍");//设置自定义分词
       Iterator<Term> q= ToAnalysis.parse("我想买一条网球裙").iterator();
       while (q.hasNext()){
           System.out.println(q.next().getName());
       }
        System.out.println(DicAnalysis.parse("网球裙"));
        System.out.println(DicAnalysis.parse("网球拍"));
        System.out.println(DicAnalysis.parse("鞭炮"));
        System.out.println(DicAnalysis.parse("我是中国人"));
    }
}
