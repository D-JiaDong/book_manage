<%--
  Created by IntelliJ IDEA.
  User: LIANG
  Date: 2020/11/11
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../user/header.jsp"%>>
<%@include file="../user/topbar.jsp" %>
<html>
    <head>
        <title>热门图书浏览</title>
        
    </head>
    <body>
        <div class="main-content-front">
        
            <div class="page-content">
                <div class="container-fluid">
                
                    <div class="row">
                        <div class="col-lg-8">
                            <div class="card card-animate">
                                <div class="card-body">
                                    <div class="dropdown float-right position-relative">
                                        <a href="#" class="dropdown-toggle h4 text-muted" data-toggle="dropdown"
                                            aria-expanded="false">
                                            <i class="mdi mdi-dots-vertical"></i>
                                        </a>
                                        <ul class="dropdown-menu dropdown-menu-right">
                                            <li><a href="#" class="dropdown-item">Action</a></li>
                                            <li><a href="#" class="dropdown-item">Another action</a></li>
                                            <li><a href="#" class="dropdown-item">Something else here</a></li>
                                            <li class="dropdown-divider"></li>
                                            <li><a href="#" class="dropdown-item">Separated link</a></li>
                                        </ul>
                                    </div>
                                    <h4 class="card-title d-inline-block">All Projects</h4>
                                    <form action="#" method="post" name="blogForm" class="form-inline">
                                        <input type="hidden" name="blogTitle">
                                        <div class="table-responsive">
                                            <table class="table table-borderless table-hover mb-0">
                                                <thead class="thead-light">
                                                <tr>
                                                    <th colspan="10">热门图书列表</th>
                                                </tr>
                                                </thead>
                                                <tbody id="bodyData">
                                            
                                                </tbody>
                                                <tfoot id="pageData">
                                            
                                                </tfoot>
                                            </table>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div> <!-- end col -->
                
                    </div>
                    <!-- end row-->
            
                </div> <!-- container-fluid -->
            </div>
            <!-- End Page-content -->
        </div>
    </body>
</html>
