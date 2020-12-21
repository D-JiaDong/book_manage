<%@ page import="org.ccit.com.domain.packaging.User" %>
<%@ page import="org.ccit.com.domain.packaging.Admin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<header id="page-topbar">
    <div class="navbar-header">
        <div class="d-flex align-items-left">

        </div>

        <div class="d-flex align-items-center">
            <%
                Admin admin = (Admin) session.getAttribute("admin");
                String adminpic=admin.getAdm_pic();
                String adminname=admin.getAdm_name();
            %>
            <div class="dropdown d-inline-block">
                <button type="button" class="btn header-item waves-effect" id="page-header-user-dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <img class="rounded-circle header-profile-user" src="..<%= adminpic%>" alt="Header Avatar">
                    <span class="d-none d-sm-inline-block ml-1"><%= adminname%></span>
                    <i class="mdi mdi-chevron-down d-none d-sm-inline-block"></i>
                </button>
                <div class="dropdown-menu dropdown-menu-right">
                    <a class="dropdown-item d-flex align-items-center justify-content-between" href="../index.jsp">
                        <span>Log Out</span>
                    </a>
                </div>
            </div>

        </div>
    </div>
</header>