<%--
  Created by IntelliJ IDEA.
  User: LIANG
  Date: 2020/11/7
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@include file="../admin/header.jsp"%>
<%@include file="../admin/left.jsp"%>
<%@include file="../admin/topbar.jsp"%>

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
                            <div class="page-title-box d-flex align-items-center justify-content-between">
                                <h4 class="mb-0 font-size-18">图书管理</h4>
                            
                                <div class="page-title-right">
                                    <ol class="breadcrumb m-0">
                                        <li class="breadcrumb-item"><a href="javascript: void(0);">图书管理</a></li>
                                    </ol>
                                </div>
                        
                            </div>
                        </div>
                    </div>
                    <!-- end page title -->
                
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
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
    
                                                <div class="form-group" style="display: inline;">
                                                    <input type="reset" class="btn btn-default pull-left" onclick="clearSearch()" value="取消"/>
                                                    <%
                                                        String opr = request.getParameter("opr");
                                                    %>
                                                    <input type="button" class="btn btn-primary" onclick="alter_book(8,1,0,'<%= opr%>')" value="查询"/>
                                                    <input type="button" class="btn btn-primary" onclick="alter_book(8,1,0,'add')" value="添加"/>
                                            </div>
                                        </div>
                                        <table class="table activate-select dt-responsive nowrap">
                                            <thead>
                                            <tr>
                                                <th>书籍ID</th>
                                                <th>名称</th>
                                                <th>//</th>
                                                <th>//</th>
                                                <th>作者</th>
                                                <th>价格</th>
                                                <th>数量</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody id="bodyData">
                                        
                                            </tbody>
                                            <tfoot id="pageData">
                                        
                                            </tfoot>
                                        </table>
                                        <!-- Modal -->
                                        <div class="modal fade" id="modal-update" tabindex="-1" role="dialog" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">修改图书信息</h5>
                                                        <button type="button" class="close waves-effect waves-light" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="form-group">
                                                           <%--@declare id="unum"--%><label for="Unum"  class="control-label">图书数量：</label>
                                                            <input type="text" name="Unum"  class="form-control"/>
                                                        </div>
                                                        <div class="form-group">
                                                            <%--@declare id="upri"--%><label for="Upri"  class="control-label">图书价格：</label>
                                                            <input type="text" name="Upri"  class="form-control"/>
                                                        </div>
                                                        <input name="Uid" type="hidden" />
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary waves-effect waves-light" data-dismiss="modal">取消</button>
                                                        <button type="button" class="btn btn-primary waves-effect waves-light" onclick="alter_book(8,1,0,'submitUpdate')">保存</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal fade" id="modal-add" tabindex="-1" role="dialog" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">添加图书信息</h5>
                                                        <button type="button" class="close waves-effect waves-light" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="form-group" style="display: inline;">
                                                        <%--@declare id="aname"--%><label for="Aname"  class="control-label" style="display: inline;">书名:</label>
                                                        <input type="text"  name="Aname" value="" class="form-control-sm" style="display: inline;"/>
                                                    </div>
    
                                                    <div class="form-group" style="display: inline;">
                                                        <%--@declare id="aauthor"--%><label for="Aauthor"  class="control-label" style="display: inline;">作者名:</label>
                                                        <input type="text"  name="Aauthor" value="" class="form-control-sm" style="display: inline;"/>
                                                    </div>
                                                    <div class="form-group" style="display: inline;">
                                                        <%--@declare id="anum"--%><label for="Anum"  class="control-label" style="display: inline;">图书数量:</label>
                                                        <input type="text" name="Anum" value="" class="form-control-sm" style="display: inline;"/>
                                                    </div>
                                                    <div class="form-group" style="display: inline;">
                                                        <%--@declare id="apri"--%><label for="Apri"  class="control-label" style="display: inline;">图书单价:</label>
                                                        <input type="text" name="Apri" value="" class="form-control-sm" style="display: inline;"/>
                                                    </div>
                                                    <div  name="addbooktype" id="addbooktype" class="form-group" style="display: inline;">
                                                        <%--@declare id="booktype"--%><label for="booktype"  class="control-label" style="display: inline;">图书类型:</label>
                                                    </div>
    
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary waves-effect waves-light" data-dismiss="modal">取消</button>
                                                        <button type="button" class="btn btn-primary waves-effect waves-light" onclick="alter_book(8,1,0,'submitAdd')">保存</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
<%@include file="../admin/bottom.html"%>>
<script>
    function clearSearch() {
        document.bookForm.bookauthor.value = ""
        document.bookForm.bookname.value=""
    }
</script>
<script type="text/javascript" src="../static/js/alter_book.js" ></script>
