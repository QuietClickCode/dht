$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
/**
 * 清除表单校验
 * @param formId 表单id
 * @param callback 重新加载校验方法
 */
function clearFormValidation(formId,callback){
    $("#"+formId).data('bootstrapValidator').destroy();
    $('#'+formId).data('bootstrapValidator', null);
    if(callback){
        callback();
    }
}
/**
 * 取得ue富文本编译路径
 * @param type 图片用途
 * @param isWatermark 是否添加水印
 * @param isCompress 是否压缩
 */
function ueditorUploadUrl(type,isWatermark,isCompress){
    let fileUploadUrl ="/file/imageUpload";
    if(!isWatermark){
        isWatermark=false;
    }
    if(!isCompress){
        isCompress=false;
    }
    fileUploadUrl+="?isWatermark="+isWatermark+"&isCompress="+isCompress+"&type="+type;
    return fileUploadUrl;
}
