<html>
<head>
    <title>Activiti</title>

    <ex-section id="CSS">
        <!--引入CSS-->
        <link rel="stylesheet" type="text/css" href="<@spring.message "frontend.url"/>/css/vendor/uploader/webuploader.css">

        <style type="text/css">
            #title-breadcrumb-option-demo > ul > li > a[href="/definitions"] {
                background-color: #fff;
            }
        </style>

    </ex-section>
</head>
<body>
<div>
    <!-- 工具栏 -->
    <div class="row">
        <div class="col-sm-12">
            <div class="pull-left">
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

        $("#title-breadcrumb-option-demo > ul > li > a[href='/definitions']").css("background-color", "#FFF");

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
