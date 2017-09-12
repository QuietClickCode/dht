package com.retailers.tools.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 序列生成
 */
public class SequenceGenerator {

    private static SequenceGenerator instance = null;

    private int seq; // 小序号
    private DecimalFormat seqFormat = null;
    private SimpleDateFormat dateFormat = null;

    public synchronized static SequenceGenerator getInstance() {
        if (instance == null)
            instance = new SequenceGenerator();

        return instance;
    }

    private SequenceGenerator() {
        seq = 1;
        seqFormat = new DecimalFormat("000000");
        dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    }

    /*
     * 产生唯一序号， 格式：(YYYYMMDDHHMMSS)-(6位数字序号)
     */
    public String generate() {
        String result = "";
        int curseq;

        synchronized (SequenceGenerator.class) {
            if (seq > 999999) {
                seq = 1;
            }

            curseq = seq++;
        }

        result = new StringBuilder().append(dateFormat.format(new Date())).append("-").append(
                        seqFormat.format(curseq)).toString();

        return result;
    }
}
