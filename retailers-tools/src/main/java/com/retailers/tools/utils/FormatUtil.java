package com.retailers.tools.utils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by admin on 2015/8/19.
 */
public class FormatUtil {
    //注销标示字符
    private static final String LOGOUT_CHAR = "_";
    /**
     * 格式化用户名称<br>
     * 例如：*三（张三）
     * @param userName 用户名称
     * @return
     */
    public static String formatUserName(String userName) {
//        if (StringUtils.isNotEmpty(userName)) {
//            userName = "*" + userName.substring(1);
//        } else {
//            userName = "";
//        }
        return userName;
    }
    /**
     * 格式化手机号
     * 例如：150****8360（15025388360）
     * @param phone 手机号
     * @return
     */
    public static String formatPhone(String phone) {
        String strPhone = "";
        phone = getAccount(phone);
        if (StringUtils.isMobileNO(phone)) {
            strPhone = phone.substring(0,3)+ "****" + phone.substring(7);
        }
        return strPhone;
    }
    /**
     * 格式化银卡号
     * 例如：0018（6228480402564890018）
     * @param bankNo 银卡号
     * @return
     */
    public static String formatBankNo(String bankNo) {
        if (VerifyUtil.isBankno(bankNo)) {
            bankNo = bankNo.substring(bankNo.length()-4);
        } else {
            if (VerifyUtil.isNumber(bankNo) && bankNo.length() > 4) {
                bankNo = bankNo.substring(bankNo.length()-4);
            }
        }
        return bankNo;
    }
    /**
     * 格式化银卡号
     * 例如：***************0018（6228480402564890018）
     * @param bankNo 银卡号
     * @return
     */
    public static String formatBankNoTwo(String bankNo) {
        if (VerifyUtil.isBankno(bankNo)) {
            char[] arr = bankNo.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (i < arr.length - 4) {
                    arr[i] = '*';
                }
            }
            return new String(arr);
        }
        return "";
    }
    /**
     * 格式化身份证号
     * 例如：440************316（440901197709194316）
     * @param idCardNo 身份证号
     * @return
     */
    public static String formatIdCardNo(String idCardNo) {
        //440901197709194316
        if (VerifyUtil.isIdentityCard(idCardNo)) {
            char[] arr = idCardNo.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (i >= 3 && i < arr.length - 3) {
                    arr[i] = '*';
                }
            }
            return new String(arr);
        }
        return "";
    }
    /**
     * 格式化身份证号2（只显示尾号）
     * 例如：**************4316（440901197709194316）
     * @param idCardNo 身份证号
     * @return
     */
    public static String formatIdCardNoTwo(String idCardNo) {
        //440901197709194316
        if (VerifyUtil.isIdentityCard(idCardNo)) {
            char[] arr = idCardNo.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (i < arr.length - 4) {
                    arr[i] = '*';
                }
            }
            return new String(arr);
        }
        return "";
    }
//
//    /**
//     * 格式化金额(小数点后两位)
//     * 例如：234.00（234）
//     * @param price
//     * @return
//     */
//    public static String formatPrice(double price) {
//        BigDecimal bigDecimal = new BigDecimal(price);
//        String retusltPrice = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
//        return retusltPrice;
//    }
    /**
     * 格式化收入金额(小数点后两位)
     * 例如：收234元==》+234.00
     * @param price 金额(单位：分)
     * @return
     */
    public static String formatWalletAndSeq(Double price) {
        if (price == null || price == 0)  {
            return "0.00";
        }
        String retusltPrice = "";
        if (price > 0) {
            retusltPrice = "+" + formatWallet(price);
        } else {
            retusltPrice =  formatWallet(price);
        }
        return retusltPrice;
    }
    /**
     * 格式化输出金额(小数点后两位)
     * 例如：支123元==》-123.00
     * @param price 金额(单位：分)
     * @return
     */
    public static String formatWalletOut(double price) {
        if (price == 0) {
            return "0.00";
        }
        if (price > 0) {
           price = -price;
        }
        return formatWallet(price);
    }
    /**
     * 格式化输出金额(小数点后两位)
     * 例如：12300分==》123.00
     * @param price 金额(单位：分)
     * @return (单位：元)
     */
    public static String formatWallet(double price) {
    	price = NumberUtils.priceChangeYuan(price);
        BigDecimal bigDecimal = new BigDecimal(price);
        String retusltPrice = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        return retusltPrice;
    }
    /**
     * double保留2小数，并转换成string
     * 例如：(double)123.234元==》"123.23"
     * @param price 参数
     * @return
     */
    public static String formatDouble(double price) {
        BigDecimal bigDecimal = new BigDecimal(price);
        String retusltPrice = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        return retusltPrice;
    }

//    /**
//     * 格式化月份
//     * @param month 月份
//     * @return
//     */
//    public static String formatMonth(String month) {
//        String rMonth = month;
//        String toMonth = DateUtil.dateToString(new Date(), "MM");//本月
//        if (month.equals(toMonth)) {
//            rMonth = "本月";
//        } else {
//            String[][] arrs = new String[][]{{"01","一月"},{"02","二月"},{"03","三月"},{"04","四月"},{"05","五月"},{"06","六月"},{"07","七月"},{"08","八月"},{"09","九月"},{"10","十月"},{"11","十一月"},{"12","十二月"}};
//            for (String[] arr : arrs) {
//                if (arr[0].equals(month)) {
//                    rMonth = arr[1];
//                    break;
//                }
//            }
//        }
//        return rMonth;
//    }

    /**
     * 格式化月份
     * @param month 月份
     * @param date 时间
     * @return
     */
    public static String formatMonth(String month,String date) {
        //当月的显示本月。当年的显示月份（2月，1月），去年的显示（2015年12月）
        String[][] arrs = new String[][]{{"01","一月"},{"02","二月"},{"03","三月"},{"04","四月"},{"05","五月"},{"06","六月"},{"07","七月"},{"08","八月"},{"09","九月"},{"10","十月"},{"11","十一月"},{"12","十二月"}};
        String[][] yearArray = new String[][]{{"0","零"},{"1","一"},{"2","二"},{"3","三"},{"4","四"},{"5","五"},{"6","六"},{"7","七"},{"8","八"},{"9","九"}};
        String toYear = DateUtil.dateToString(new Date(), "yyyy");
        String rYear = DateUtil.dateToString(DateUtil.stringToDate(date), "yyyy");
        String toMonth = DateUtil.dateToString(new Date(), "MM");//本月
        String rMonth = "";
       try {
           //当年
           if (toYear.equals(rYear)) {
               if (month.equals(toMonth)) {
                   rMonth = "本月";
               } else {
                   for (String[] arr : arrs) {
                       if (arr[0].equals(month)) {
                           rMonth = arr[1];
                           break;
                       }
                   }
               }
           } else {
               char[] yearChar = rYear.toCharArray();
               String resultYear = "";
               for (int i = 0; i < yearChar.length; i++) {
                   for (String[] key : yearArray) {
                       if (yearChar[i] == key[0].charAt(0)) {
                           resultYear += key[1];
                           break;
                       }
                   }
               }
               for (String[] arr : arrs) {
                   if (arr[0].equals(month)) {
                       rMonth = resultYear + "年" + arr[1];
                       break;
                   }
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
        return rMonth;
    }

    /**
     * 格式化账号<br>
     * 当是注销账号是修改注销标示
     * 例如：15025388360（15025388360_717）
     * @param phone 手机号
     * @return
     */
    public static String getAccount(String phone) {
        String strPhone = "";
        if (StringUtils.isNotEmpty(phone)) {
            int index = phone.indexOf(LOGOUT_CHAR);
            if (index != -1) {
                strPhone = phone.substring(0,index);
            } else {
                strPhone = phone;
            }
        }
        return strPhone;
    }
    /**
     * 格式身份证号<br>
     * 当是注销账证号时修改注销标示
     * 例如：411423198802116516（411423198802116516_717）
     * @param idCardNo 身份证号
     * @return
     */
    public static String getIdCardNo(String idCardNo) {
        String strIdCardNo = "";
        if (StringUtils.isNotEmpty(idCardNo)) {
            int index = idCardNo.indexOf(LOGOUT_CHAR);
            if (index != -1) {
                strIdCardNo = idCardNo.substring(0,index);
            } else {
                strIdCardNo = idCardNo;
            }
        }
        return strIdCardNo;
    }
    /**
     * 将账号注销<br>
     * 例如：账号：15025388360，用户id：717，则注销后账号：（15025388360_717）
     * @param account 手机号
     * @param userId 用户id
     * @return
     */
    public static String getLogoutAccount(String account,long userId) {
        String strPhone = "";
        if (StringUtils.isNotEmpty(account)) {
            strPhone = account + LOGOUT_CHAR + userId;
        }
        return strPhone;
    }
    /**
     * 注销身份证号<br>
     * 例如：身份证：411423198802116516，用户id：717，则注销后：（411423198802116516_717）
     * @param idCardNo 身份证号
     * @param userId 用户id
     * @return
     */
    public static String getLogoutIdCardNo(String idCardNo,long userId) {
        String strIdCardNo = "";
        if (StringUtils.isNotEmpty(idCardNo)) {
            strIdCardNo = idCardNo + LOGOUT_CHAR + userId;
        }
        return strIdCardNo;
    }
    /**
     * 格式化用户名称<br>
     * 例如：*三（张三）
     * @param userName 用户名称
     * @return
     */
    public static String formatUserRealName(String userName) {
        if (StringUtils.isNotEmpty(userName)) {
            userName = "*" + userName.substring(1);
        } else {
            userName = "";
        }
        return userName;
    }
}
