<%--
  Created by IntelliJ IDEA.
  User: LIANG
  Date: 2020/11/7
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@include file="header.jsp"%>
<%@include file="left.jsp"%>
<%@include file="topbar.jsp"%>

<html>
    <head>
        <title>index</title>
    </head>
    <body>
        <div class="main-content">
        
            <div class="page-content">
                <div class="container-fluid">
                    <!-- start page title -->
                    <div class="row">
                        <div class="col-12">
                        
<%--                            <div class="page-title-box d-flex align-items-center justify-content-between">--%>
<%--                                <h4 class="mb-0 font-size-18">热门图书</h4>--%>
<%--                            </div>--%>
                            <div class="page-title-box d-flex align-items-center justify-content-between">
                                <form action="#" method="post" name="bookForm" class="form-inline">
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <div class="form-group" style="display: inline;">
                                                <label for="username"  class="control-label" style="display: inline;">用户姓名:</label>
                                                <input type="text" id="username" name="username" value="" class="form-control-sm" style="display: inline;"/>
                                            </div>
                
                                            <div class="form-group" style="display: inline;">
                                                <label for="usertel"  class="control-label" style="display: inline;">用户电话:</label>
                                                <input type="text" id="usertel" name="usertel" value="" class="form-control-sm" style="display: inline;"/>
                                            </div>
                                            
                                            <input type="hidden" name="currentPage" value="1"/>
                                            <input type="hidden" name="userId" value="{{ request.session.user.userId }}"/>
                                            <input type="hidden" name="opr" value="search"/>
                                            <div class="form-group" style="display: inline;">
                                                <input type="reset" class="btn btn-default pull-left" onclick="clearSearch()" value="取消"/>
                                                <input type="button" class="btn btn-primary" onclick="alluser(8,1)" value="查询"/>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- end page title -->
                   
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                    <form action="#" method="post" name="classForm" class="form-inline">
                                        <table class="table activate-select dt-responsive nowrap table table-hover" >
                                            <thead>
                                            <tr>
                                                <th>头像</th>
                                                <th>用户ID</th>
                                                <th>用户名称</th>
                                                <th>年龄</th>
                                                <th>用户电话</th>
                                                <th>个人介绍</th>
                                            </tr>
                                            </thead>
                                            <tbody id="bodyData">
                                            </tbody>
                                            <tfoot id="pageData">
                                            
                                            </tfoot>
                                        </table>
                                    </form>
                                </div> <!-- end card body-->
                            </div> <!-- end card -->
                        </div><!-- end col-->
                    </div>
                    <!-- end row-->
            
                </div> <!-- container-fluid -->
            </div>
            <!-- End Page-content -->
        
            <footer class="footer">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-sm-6">
                            2020 © Opatix.
                        </div>
                        <div class="col-sm-6">
                            <div class="text-sm-right d-none d-sm-block">
                                Design & Develop by Myra
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
    
        </div>
    </body>
</html>
<%@include file="bottom.html"%>>
<script>
    function clearSearch() {
        document.bookForm.usertel.value = ""
        document.bookForm.username.value=""
    }
</script>
<script type="text/javascript" src="../static/js/adminselect_alluser.js" ></script>
