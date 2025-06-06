<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改</title>
    <link href="<%=path%>/css/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
    <script src="<%=path%>/js/jquery-3.4.1.min.js"></script>
    <script src="<%=path%>/css/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=path%>/js/jquery.validate.min.js"></script>
    <script src="<%=path%>/js/jquery.validate.extend.js"></script>
    <script src="<%=path%>/js/jquery.validate.method.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/js/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/js/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
    <link href="<%=path%>/css/pdmcontent01.css" rel="stylesheet">
</head>
<body>
<div class="page-content">
    <div class="panel panel-default">
        <div class="panel-heading">修改</div>
        <div class="panel-body">
            <form action="<%=path%>/novelchapter_update.action" method="post" id="addForm">
                <input name="id" type="hidden" value="${novelchapter.id}"/>
                <input name="novelid" type="hidden" value="${novelchapter.novelid}"/>
                <input name="wordscount" id="wordscount" type="hidden" value="0"/>
                <div class="container-fluid">

                    <div class="row rowmargin">
                        <div class="col-sm-7">
                            <div class="form-group">
                                <label class="col-sm-3 control-label"><font color="red">*</font>章节标题 </label>
                                <div class="col-sm-9 form-inline">
                                    <input id="title" name="title" size="35" class="form-control" type="text"
                                           value="${novelchapter.title}" tip="请输入章节标题"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row rowmargin">
                        <div class="col-sm-7">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">章节内容 </label>
                                <div class="col-sm-9 form-inline">
                                    <script id="container" type="text/plain" style="width:850px;height:300px;"></script>
                                    <input id="contents" name="contents" type="hidden"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-sm-2">
                        </div>
                        <div class="col-sm-4" style="margin-left: 20px;">
                            <button type="submit" class="btn btn-primary" style="margin-top: 40px;">提交</button>
                            <button type="button" id="rebackBtn" class="btn btn-default"
                                    style="margin-top: 40px;margin-left: 20px;">返回
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        var ue = UE.getEditor('container');
        ue.ready(function () {
            //设置编辑器的内容
            ue.setContent('${novelchapter.contents}');
        });
        $("#rebackBtn").click(function () {
            window.history.go(-1);
        });
        $("#addForm").validate({
            submitHandler: function (form) {//验证通过后的执行方法
                var htmlv = ue.getContent();
                if (htmlv == null || htmlv == '') {
                    alert("内容不能为空");
                    return false;
                }
                document.getElementById("contents").value = htmlv;
                document.getElementById("wordscount").value = ue.getContentLength(true);

                form.submit();
            },
            rules: {
                title: {
                    required: true,
                },
            },
            messages: {
                title: {
                    required: '章节标题不能为空',
                },
            }
        });
    });
</script>
</html>