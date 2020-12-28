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
                                                <label for="bookname"  class="control-label" style="display: inline;">书名:</label>
                                                <input type="text" id="bookname" name="bookname" value="" class="form-control-sm" style="display: inline;"/>
                                            </div>
                
                                            <div class="form-group" style="display: inline;">
                                                <label for="bookauthor"  class="control-label" style="display: inline;">作者:</label>
                                                <input type="text" id="bookauthor" name="bookauthor" value="" class="form-control-sm" style="display: inline;"/>
                                            </div>
                                            
                                            <input type="hidden" name="currentPage" value="1"/>
                                            <input type="hidden" name="blogId"/>
                                            <input type="hidden" name="userId" value="{{ request.session.user.userId }}"/>
                                            <input type="hidden" name="opr" value="search"/>
                                            <div class="form-group" style="display: inline;">
                                                <input type="reset" class="btn btn-default pull-left" onclick="clearSearch()" value="取消"/>
                                                <input type="button" class="btn btn-primary"  value="查询" onclick="ISborrowbook(8,1)"/>
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
                                                <th>书籍ID</th>
                                                <th>名称</th>
                                                <th>作者</th>
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
        document.bookForm.bookauthor.value = ""
        document.bookForm.bookname.value=""
    }
</script>
<script type="text/javascript" src="../static/js/ISborrow_book.js" ></script>
