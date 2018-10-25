;(function (window) {

    var pageLogic = {
        init: {
            before: function () {
                var columns = [
                    {field: "checked", checkbox: true},
                    {field: "num", title: "序号", width: 5, align: "center", formatter: common.formatter.index},
                    {field: "id", title: "流程定义ID", width: 80, align: "center", formatter: pageLogic.formatter.id},
                    {field: "name", title: "名称", width: 80, align: "center"},
                    {field: "key", title: "流程定义Key", width: 80, align: "center"},
                    {field: "resourceName", title: "资源名称", width: 80, align: "center"},
                    {field: "diagramResourceName", title: "流程图", width: 80, align: "center", formatter: pageLogic.formatter.diagram},
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

                pageLogic.uploader = $("#uploader").upload({
                    uploadText: "上传BPMN",
                    server: masterpage.ctxp + pageLogic.initData.restUrlPrefix + "/deploy"
                }).uploader;

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
                    url: pageLogic.initData.restUrlPrefix + "/" + val + "/instances/count"
                }, function (data) {
                    if (data === 0) return;

                    $("#btTable tr[data-uniqueid='" + val + "']").find("td").eq(2).html("<a title='当前有" + data + "个流程实例已启动' style='text-decoration: underline' target='_blank' href='" + masterpage.ctxp + "/instances?definitionId=" + val + "'>" + val + "<span  style='color: red; font-size: 1.2em'> [" + data + "]</span>" + "</a>" );
                });

                return val;
            },

            operation: function (val, row) {
                return "<i class='fa fa-download' style='font-size: 1.2em' onclick='pageLogic.download(\"" + row["id"] + "\")'></i>&nbsp;&nbsp;&nbsp;<i class='fa fa-trash-o' onclick='pageLogic.del(\"" + row["id"] + "\")'></i>";
            },

            diagram: function (val, row) {
                return "<a style='color: dodgerblue; font-size: 1.2em' title='查看流程图' target='_blank' href='" + masterpage.ctxp + pageLogic.initData.restUrlPrefix + "/" + row["id"] + "/diagram" + "'>" + val + "</a>";
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
