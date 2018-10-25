<!doctype html>
<html>
<head>
    <#include "header-css.ftl">
</head>

<body>
    <a id="totop" href="#"><i class="fa fa-angle-up"></i></a>

    <div id="wrapper">
        <div id="page-wrapper"style="margin-left: 0">
            <div id="title-breadcrumb-option-demo" class="page-title-breadcrumb navbar-fixed-top" style="background-color: #2a3b4c; box-shadow: 3px 3px 3px #888">
                <div class="page-header pull-left">
                    <div class="page-title" style="color: #FFF"><@spring.message "logoName"/> <span style="font-size: 16px!important;">V1.0.0</span></div>
                </div>

                <ul class="nav navbar-nav" style="margin-left: 5px;">
                    <li>
                        <a href="${request.contextPath}/models">流程模型</a>
                    </li>
                    <li>
                        <a href="${request.contextPath}/definitions">流程定义</a>
                    </li>
                </ul>
                <div class="page-header pull-right">
                    <div class="page-title" style="font-size: 22px;"></div>
                </div>
                <div class="clearfix"></div>
            </div>

            <div class="page-content" style="margin-top: 50px;">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="portlet box">
                            <div class="portlet-body">
                                <sitemesh:write property="body"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="footer">
        <div class="copyright"><@spring.message "copyright"/></div>
    </div>

<#include "footer-js.ftl">

<!-- 页面js代码-->
<sitemesh:write property='ex-section.ScriptBody'/>
</body>
</html>