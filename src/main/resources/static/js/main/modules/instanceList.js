;(function (window) {

    var pageLogic = {
        init: {
            before: function () {
                var columns = [
                    {field: "checked", checkbox: true},
                    {field: "num", title: "序号", width: 5, align: "center", formatter: common.formatter.index},
                    {field: "id", title: "实例ID", width: 80, align: "center"},
                    {field: "name", title: "名称", width: 80, align: "center"},

                    {field: "processDefinitionKey", title: "流程定义Key", width: 80, align: "center"},

                    {field: "deploymentId", title: "deploymentId", width: 80, align: "center"},
                    {field: "businessKey", title: "businessKey", width: 80, align: "center"},

                    {field: "processVariables", title: "流程变量", width: 80, align: "center", formatter: pageLogic.formatter.processVariables},

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
                return "<a target='_blank' href='" + masterpage.ctxp + pageLogic.initData.restUrlPrefix + "/" + row["id"] + "/diagram" + "'><i class='fa fa-building-o' title='查看流程进度' style='font-size: 1.8em'></i></a>&nbsp;&nbsp;&nbsp;"
                     + "<i class='fa fa-trash-o' style='font-size: 1.8em' onclick='pageLogic.del(\"" + row["id"] + "\")'></i>";
            },

            processVariables: function (val, row) {
                return JSON.stringify(val);
            }

        },

        /* 流程实例删除 */
        del: function (id) {
            common.del({
                url: pageLogic.initData.restUrlPrefix + "/" + id
            }, function () {
                common.search();
            });
        }




    };

    window.pageLogic = pageLogic;
})(window);
