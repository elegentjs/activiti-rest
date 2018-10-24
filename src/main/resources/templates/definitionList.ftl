<html>
<head>
    <title>Activiti</title>

    <ex-section id="CSS">
        <!--引入CSS-->
        <link rel="stylesheet" type="text/css" href="<@spring.message "frontend.url"/>/css/vendor/uploader/webuploader.css">
    </ex-section>
</head>
<body>
<div>
    <!-- 工具栏 -->
    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
                <button class="btn btn-sm btn-danger" id="delBtn" >
                    <a target="_blank" style="color: #FFF;" href="${request.contextPath}/modeler/create">新建流程</a>
                </button>
            </div>
            <div class="pull-right">
                <div id="uploader">
                </div>
            </div>
        </div>
    </div>

    <div class="row" style="margin-top: 10px;">
        <div class="col-sm-12">
            <div id="mainGrid">
                <table id="btTable" class="table table-condensed table-hover table-striped"></table>
            </div>
        </div>
    </div>

</div>

<ex-section id="ScriptBody">
    <script type="text/javascript" src="<@spring.message "frontend.url"/>/js/vendor/webuploader.js"></script>
    <script type="text/javascript" src="${request.contextPath}/js/main/modules/definitionList.js?v=<@spring.message 'js.version'/>"></script>
    <script type="text/javascript">
        pageLogic.initData = {
            modalParams: [
                {
                    width: 800,
                    height: 400,
                    title: "流程定义"
                }
            ],
            restUrlPrefix: "/definitions",
            queryMsg: {
            }
        };

    </script>
</ex-section>

</body>
</html>
