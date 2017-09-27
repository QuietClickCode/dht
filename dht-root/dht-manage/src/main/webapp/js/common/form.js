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
