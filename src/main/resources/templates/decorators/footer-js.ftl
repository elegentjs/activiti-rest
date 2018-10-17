<!-- jquery & plugins -->
<script src="<@spring.message "frontend.url"/>/js/vendor/jquery.min.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/jquery-ui-1.10.1.custom.min.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/jquery-validate-1.16.0.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/jquery.ztree.all.min.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/jquery.metisMenu.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/jquery.slimscroll.min.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/jquery.cookie.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/jquery-migrate-1.2.1.min.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/jquery/jquery.menu.js"></script>

<!-- bootstrap & plugins -->
<script src="<@spring.message "frontend.url"/>/js/vendor/bootstrap/bootstrap.min.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/bootstrap/bootstrap-table.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/bootstrap/bootstrap-table-zh-CN.min.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/bootstrap/bootstrap-hover-dropdown.js"></script>
<script src="<@spring.message "frontend.url"/>/js/plugins/bootstrap/bootstrap-datetimepicker.js"></script>


<!-- 模拟浏览器进度条组件 -->
<script src="<@spring.message "frontend.url"/>/js/vendor/pace.min.js"></script>

<!-- layer 组件，消息提示框用 -->
<script src="<@spring.message "frontend.url"/>/js/vendor/layer/layer.min.js"></script>

<!-- custom -->
<script src="<@spring.message "frontend.url"/>/js/main/core.js"></script>
<script src="<@spring.message "frontend.url"/>/js/main/common.js"></script>


<script>
    (function() {
        if (!masterpage.ctxp) {
            masterpage.ctxp = "${request.contextPath}";
        }


        masterpage.init();

    })();
</script>