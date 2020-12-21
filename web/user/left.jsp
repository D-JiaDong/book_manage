<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!-- ========== Left Sidebar Start ========== -->
<div class="vertical-menu mm-active">
    <div data-simplebar="init" class="h-100 mm-show">
        <div class="simplebar-content-wrapper" style="height: 100%; padding-right: 0px; padding-bottom: 0px; overflow: hidden;">
    <div class="simplebar-content" style="padding: 0px;">
    
        <div class="navbar-brand-box">
            <br>
            <span style="padding-top: 10px;">
                            <a href="../user/index.jsp" class="logo">
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
                    <a href="../user/index.jsp" class="waves-effect">
                        <i class='bx bx-home-smile'></i>
                        <span>首页展板</span>
                    </a>
                </li>
                <li id="select_Li">
                    <a id="select_a" href="javascript: void(0);" class="has-arrow waves-effect"><span>查找图书</span></a>
                    <ul id="select_ul" class="sub-menu mm-collapse" aria-expanded="false">
                        <li id="all"><a href="${pageContext.request.contextPath}/user/select_book.jsp?opr=all">全部</a></li>
                        <li id="sanwen"><a href="${pageContext.request.contextPath}/user/select_book.jsp?opr=sanwen">散文</a></li>
                        <li id="suibi"><a href="${pageContext.request.contextPath}/user/select_book.jsp?opr=suibi">随笔</a></li>
                        <li id="xiaoshuo"><a href="${pageContext.request.contextPath}/user/select_book.jsp?opr=xiaoshuo">小说</a></li>
                        <li id="kepu"><a href="${pageContext.request.contextPath}/user/select_book.jsp?opr=kepu">科普</a></li>
                        <li id="biancheng"><a href="${pageContext.request.contextPath}/user/select_book.jsp?opr=biancheng">编程</a></li>
                    </ul>
                </li>
                <li><a href="select_book.jsp">查找图书</a></li>
                <li><a href="#">借阅图书</a></li>
                
        
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

