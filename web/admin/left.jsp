<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!-- ========== Left Sidebar Start ========== -->
<div class="vertical-menu mm-active">
    <div data-simplebar="init" class="h-100 mm-show">
        <div class="simplebar-content-wrapper" style="height: 100%; padding-right: 0px; padding-bottom: 0px; overflow: hidden;">
            <div class="simplebar-content" style="padding: 0px;">

                <div class="navbar-brand-box">
                    <br>
                    <span style="padding-top: 10px;">
                            <a href="../admin/index.jsp" class="logo">
                               <h5>&nbsp;图书借阅系统</h5>
                            </a>
                        </span>
                </div>

                <!--- Sidemenu -->
                <div id="sidebar-menu" class="mm-active">
                    <!-- Left Menu Start -->
                    <ul class="metismenu list-unstyled mm-show" id="side-menu">

                        <li class="menu-title">系统目录</li>

                        <li>
                            <a href="../admin/index.jsp" class="waves-effect">
                                <i class='bx bx-home-smile'></i>
                                <span>首页展板</span>
                            </a>
                        </li>
                        <li id="select_Li">
                            <a id="select_a" href="javascript: void(0);" class="has-arrow waves-effect"><span>查找图书</span></a>
                            <ul id="select_ul" class="sub-menu mm-collapse" aria-expanded="false">
                            </ul>
                        </li>
                       
                        
                        <li ><a href="../admin/alter_book.jsp">图书管理</a></li>
                        <li ><a href="../admin/alter_booktype.jsp">图书类型管理</a></li>
                        
                        <li id="borrow_Li">
                            <a id="borrow_a" href="javascript: void(0);" class="has-arrow waves-effect"><span>借阅管理</span></a>
                            <ul id="borrow_ul" class="sub-menu mm-collapse" aria-expanded="false">
<%--                                <li id="borrow_all"><a href="${pageContext.request.contextPath}/admin/borrow_book_manage.jsp?opr=all">全部</a></li>--%>
<%--                                <li id="borrow_sanwen"><a href="${pageContext.request.contextPath}/admin/borrow_book_manage.jsp?opr=sanwen">散文</a></li>--%>
<%--                                <li id="borrow_suibi"><a href="${pageContext.request.contextPath}/admin/borrow_book_manage.jsp?opr=suibi">随笔</a></li>--%>
<%--                                <li id="borrow_xiaoshuo"><a href="${pageContext.request.contextPath}/admin/borrow_book_manage.jsp?opr=xiaoshuo">小说</a></li>--%>
<%--                                <li id="borrow_kepu"><a href="${pageContext.request.contextPath}/admin/borrow_book_manage.jsp?opr=kepu">科普</a></li>--%>
<%--                                <li id="borrow_biancheng"><a href="${pageContext.request.contextPath}/admin/borrow_book_manage.jsp?opr=biancheng">编程</a></li>--%>
                            </ul>
                        </li>
                        <li><a href="../admin/select_user.jsp">查询用户</a></li>
    
    
    
                        <li>
                            <a href="javascript: void(0);" class="has-arrow waves-effect"><span>更多功能</span></a>
                            <ul class="sub-menu mm-collapse" aria-expanded="false">
                                <li><a href="pages-invoice.html">系统状态</a></li>
                                <li><a href="pages-starter.html">版权声明</a></li>
                                <li><a href="pages-maintenance.html">联系我们</a></li>
                                <li><a href="pages-faqs.html">帮助手册</a></li>
                            </ul>
                        </li>

                    </ul>

                </div>
                <!-- Sidebar -->
            </div>
        </div>
        <!-- Left Sidebar End -->
    </div>
</div>

