;(function (window) {

    var pageLogic = {
        init: {
            before: function () {
                var columns = [
                    {field: "checked", checkbox: true},
                    {field: "num", title: "seq", width: 5, align: "center", formatter: common.formatter.index},
                    {field: "id", title: "id", width: 80, align: "center"},
                    {field: "name", title: "name", width: 80, align: "center"},
                    {field: "key", title: "key", width: 80, align: "center"},
                    {field: "resourceName", title: "resourceName", width: 80, align: "center"},
                    {field: "diagramResourceName", title: "diagramResourceName", width: 80, align: "center"}
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
                    uploadText: "Upload",
                    server: masterpage.ctxp + "/processes/deploy"
                }).uploader;

            },
            //页面控件事件绑定(一般为按钮的事件绑定)
            events: function () {

                $("#delBtn").on("click", function (e) {
                    var selects = pageLogic.btTable.bootstrapTable('getSelections');
                    if (selects.length === 0) {
                        layer.msg("需要先选中一个");
                        return;
                    }

                    if (selects.length > 1) {
                        layer.msg("只能选中一个");
                        return;
                    }

                    common.del({
                        url: pageLogic.initData.restUrlPrefix + "/" + selects[0]["id"]
                    }, function () {
                        common.search();
                    });
                });

            },

            load: function () {
                common.search();
            }
        }
    };

    window.pageLogic = pageLogic;
})(window);
