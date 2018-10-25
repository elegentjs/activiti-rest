;(function (window) {

    var pageLogic = {
        init: {
            before: function () {
                var columns = [
                    {field: "checked", checkbox: true},
                    {field: "num", title: "序号", width: 5, align: "center", formatter: common.formatter.index},
                    {field: "id", title: "模型ID", width: 80, align: "center"},
                    {field: "name", title: "名称", width: 80, align: "center"},
                    {field: "key", title: "模型Key", width: 80, align: "center"},
                    {field: "metaInfo", title: "metaInfo", width: 80, align: "center"},
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
            operation: function (val, row) {
                return "<i class='fa fa-download' onclick='pageLogic.download(\"" + row["id"] + "\")'></i>&nbsp;&nbsp;&nbsp;<i class='fa fa-trash-o' onclick='pageLogic.del(\"" + row["id"] + "\")'></i>";
            }

        },

        /* 流程定义删除 */
        del: function (id) {
            common.del({
                url: pageLogic.initData.restUrlPrefix + "/" + id
            }, function () {
                common.search();
            });
        },

        /* 下载流程定义 */
        download: function (id) {
            window.location.href = masterpage.ctxp + pageLogic.initData.restUrlPrefix + "/" + id + "/download"
        }





    };

    window.pageLogic = pageLogic;
})(window);
