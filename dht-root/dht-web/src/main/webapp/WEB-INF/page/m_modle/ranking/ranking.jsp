<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>排名公式</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <style>
        ::-webkit-scrollbar {display:none}
        .price{
            float: right;
        }

        .rannk_tb tr th{
            font-size: 0.3rem;
        }

        .all-order-tab{
            overflow-x: scroll;

        }

        .all-order-tab a{
            width: 1.4rem;
        }

        .right_icon{
            display: block;
            position: absolute;
            right: 0;
            z-index: 9999;
            width: 0.5rem;
            height: 0.7rem;
            line-height: 0.55rem;
            background: url("/img/right_icon.jpg")no-repeat;
            background-size: 0.3rem 0.3rem;
            background-position: 0.2rem 0.17rem;
        }

        body{
            overflow: hidden;
        }

        .scroll_area{
            width: 100%;
            height: 11rem;
            overflow: scroll;
            padding-bottom: 1.32rem;
        }

        .rannk_tb .td_photo .p1{
            margin-top: 0;
        }

        .rannk_tb .td_number{
            width: 1.2rem;
        }

        .rannk_tb .td_photo{
            width: 1.6rem;
            font-size: 0.3rem;
        }
    </style>
</head>
<body class="bge6">
<div class="specialty-title2 borderB">
    <a class="icon-return" href="javascript:void(0);" onclick="window.history.back(); return false;"></a>
    <span>排名公示</span>
</div>

<div class="all-order-tab" id="J_allOrderTab">
    <div style="width: 30rem;padding-right: 0.5rem;">
        <c:forEach items="${gts}" var="rtnList" varStatus="vs">
            <a href="#gt_${rtnList.rtId}" onclick="selectGts(${rtnList.rtId})" <c:if test="${vs.index==0}">class="active" </c:if> attr="${rtnList.rtId}">${rtnList.rtName}</a>
        </c:forEach>
        <span class="right_icon"></span>
    </div>

</div>

<div class="box2">
    <c:forEach items="${gts}" var="rtnList" varStatus="vs">
        <div class="rank_layout <c:if test="${vs.index!=0}">displayN</c:if>" id="gt_${rtnList.rtId}">
            <div class="rank_title">
                平台实时消费金额:<span id="saleTotalPrice_${rtnList.rtId}"></span>
            </div>
            <table class="rannk_tb" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <th class="th1">序号</th>
                    <th class="th2">会员ID</th>
                    <th class="th3">返现金额</th>
                    <th class="th4">状态</th>
                </tr>
            </table>
            <div class="scroll_area">
                <table class="rannk_tb" border="0" cellspacing="0" cellpadding="0" id="tbl_${rtnList.rtId}">

                </table>
            </div>

        </div>
        <%--<ul class="all-order-list displayN" >--%>
        <%--</ul>--%>
    </c:forEach>
</div>
<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/tabs.js"></script>
<script>
    //当前页数据
    var curPage=1;
    $(function(){
        var href = window.location.hash;
        console.log(href)
        if (href != "")
            $(href).click();

        var initSelect = $("#J_allOrderTab .active").attr("attr");
        console.log(initSelect);
        if(initSelect){
            queryRankingList(initSelect);
        }
    });
    function selectGts(gtId){
        console.log(gtId);
//        $("#gt_"+gtId).html("");
        queryRankingList(gtId)
    }

    /**
     * 根据订单类型取得订单列表数据
     * @param orderStatus
     */
    function queryRankingList(gcId){
        $.ajax({
            url: "/ranking/queryRankingList",
            type: "post",
            dataType: "json",
            data:{"gcId":gcId},
            success: function (data) {
                if(data.status==0){
                    showData(data.data,gcId);
                }
            }
        });
    }
    /**
     * 数据展示
     * @param rows
     */
    function showData(rows,gcId){
        var buyTotalPrice = 0;
        if(rows.length>0){
            $("#tbl_"+gcId).html("");

            for(var row of rows){
                buyTotalPrice+=row.ccbqMoney;
               let html='<tr><td class="td_number">'+row.rownum+'</td><td class="td_photo"><p class="p1">'+row.ccbqUid+'</p>';
               html+='</td><td class="td_back_rmb"><p class="p1">'+row.ccbqMoneys+'</p><p class="p2">累计到'+buyTotalPrice*20/100+'返现</p></td>';
               let ccbqStatus="己返现";
               let ccbqStatusColor="";
               if(row.ccbqStatus==0){
                   ccbqStatus="排队中";
                   ccbqStatusColor="color: #2dc978;";
               }else if(row.ccbqStatus==1){
                   ccbqStatus="等待返现";
                   ccbqStatusColor="color: #FF0000;";
               }
               html+='<td class="td_backed_rem" style="'+ccbqStatusColor+'">'+ccbqStatus+'</td></tr>';
                $("#tbl_"+gcId).append(html);
            }
        }
        $("#saleTotalPrice_"+gcId).html(buyTotalPrice/100);
    }
</script>
</body>
</html>