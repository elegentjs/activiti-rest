;(function (window) {

    var pageLogic = {
        init: {
            before: function () {
                var columns = [
                    {field: "checked", checkbox: true},
                    {field: "num", title: "序号", width: 5, align: "center", formatter: common.formatter.index},
                    {field: "id", title: "模型ID", width: 80, align: "center", formatter: pageLogic.formatter.id},
                    {field: "name", title: "名称", width: 80, align: "center"},
                    {field: "key", title: "模型Key", width: 80, align: "center"},
                    {field: "deploymentId", title: "deploymentId", width: 80, align: "center"},
                    {field: "createTime", title: "createTime", width: 80, align: "center"},
                    {field: "version", title: "version", width: 80, align: "center"},
                    {field: "lastUpdateTime", title: "lastUpdateTime", width: 80, align: "center"},
                    {field: "tenantId", title: "tenantId", width: 80, align: "center"},
                    {field: "operation", title: "操作", width: 120, align: "center", formatter: pageLogic.formatter.operation}
                ];

                common.initTable(columns, {
                    pagination: false
                });

            },

            layout: function() {
                common.layout();
            },
            after: function () {

            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {

            },

            load: function () {
                common.search();
            }
        },

        formatter: {
            id: function (val, row) {
                common.postJSON({
                    url: pageLogic.initData.restUrlPrefix + "/" + val + "/definitions/count"
                }, function (data) {
                    if (data === 0) return;

                    $("#btTable tr[data-uniqueid='" + val + "']").find("td").eq(2).html(val + "<span title='当前有" + data + "个流程定义已发布' style='color: red; font-size: 1.2em'> [" + data + "]</span>");
                });

                return val;
            },
            operation: function (val, row) {
                return "<i class='fa fa-pencil' title='编辑' style='font-size: 1.2em' onclick='pageLogic.edit(\"" + row["id"] + "\")'></i>&nbsp;&nbsp;&nbsp;" +
                       "<i class='fa fa-trash-o' title='删除' style='font-size: 1.2em' onclick='pageLogic.del(\"" + row["id"] + "\")'></i>&nbsp;&nbsp;&nbsp;" +
                       "<i class='fa fa-send-o' title='发布流程定义' style='font-size: 1.2em' onclick='pageLogic.deploy(\"" + row["id"] + "\")'></i>";
            }

        },

        /* 流程模型删除 */
        del: function (id) {
            common.del({
                url: pageLogic.initData.restUrlPrefix + "/" + id
            }, function () {
                common.search();
            });
        },

        /* 流程模型编辑 */
        edit: function (id) {
            window.location.href = masterpage.ctxp + "/modeler.html?modelId=" + id;
        },

        deploy: function (id) {
            common.postJSON({
                url: pageLogic.initData.restUrlPrefix + "/" + id + "/deploy"
            }, function (data) {
                layer.msg("流程部署成功");
                common.search();
            });
        }





    };

    window.pageLogic = pageLogic;
})(window);
