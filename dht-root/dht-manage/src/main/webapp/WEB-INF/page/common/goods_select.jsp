<!--公用商品选择器 -->
<div class="modal fade" id="commonGoodsSelectDialog"  role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modaltitle"><i class="fa fa-envelope-o"></i> 部门授权</h4>
            </div>
            <div id="auto-div" style="height: 400px;">
                <ul id="orgPermissionTree" class="ztree" style="width: 280px;"></ul>
            </div>
            <div class="modal-footer clearfix">
                <button type="button" class="btn btn-danger" onclick="closeCommonGoodsSelectDialog();"><i class="fa fa-times"></i> 取消</button>
                <button type="button" class="btn btn-primary pull-left" onclick="confirmCommonGoodsSelectButton();"><i class="fa fa-envelope"></i>确认</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.excheck.min.js"></script>
<script>
    /**
     * 公用商品选择器
     */
    function commonGoodsSelect(){

    }
    /**
     *公用商品选 择器取消按钮
     */
    function cancelCommonGoodsSelectButton(){
        $('#commonGoodsSelectDialog').modal('hide')
    }
    /**
     *公用商品选择器确认按钮事件
     */
    function confirmCommonGoodsSelectButton(){

    }
</script>
