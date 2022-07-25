<%--
  Created by IntelliJ IDEA.
  User: Ngan
  Date: 22/07/2022
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Hamster Resort</title>
    <%@ include file="/layout/header-p1.jsp" %>
    <!-- App favicon -->
    <link rel="shortcut icon" href="/assets/images/favicon.ico">

    <!-- Notification css (Toast) -->
    <link href="/assets/libs/toastr/css/iziToast.min.css" rel="stylesheet" type="text/css">
    <%--    <link href="/assets/libs/toastr/toastr.min.css" rel="stylesheet" type="text/css">--%>

    <!-- Plugins css -->
    <link href="/assets/libs/bootstrap-tagsinput/bootstrap-tagsinput.css" rel="stylesheet">
    <link href="/assets/libs/switchery/switchery.min.css" rel="stylesheet" type="text/css">

    <link href="/assets/libs/select2/select2.min.css" rel="stylesheet" type="text/css">
    <link href="/assets/libs/bootstrap-touchspin/jquery.bootstrap-touchspin.min.css" rel="stylesheet">
    <link href="/assets/libs/bootstrap-timepicker/bootstrap-timepicker.min.css" rel="stylesheet">
    <link href="/assets/libs/bootstrap-colorpicker/bootstrap-colorpicker.min.css" rel="stylesheet">
    <link href="/assets/libs/bootstrap-datepicker/bootstrap-datepicker.css" rel="stylesheet">

    <script src="/assets/libs/toastr/js/iziToast.min.js"></script>

    <%@ include file="/WEB-INF/layout/header-p2.jsp" %>
</head>

<body>

<!-- Begin page -->
<div id="wrapper">

    <!-- Topbar Start -->
    <%@ include file="/WEB-INF/layout/topbar.jsp" %>
    <!-- end Topbar --> <!-- ========== Left Sidebar Start ========== -->

    <div class="left-side-menu">

        <div class="slimscroll-menu">

            <!--- Sidemenu -->
            <div id="sidebar-menu">
                <%@ include file="/WEB-INF/layout/sidebar-left.jsp" %>
            </div>
            <!-- End Sidebar -->

            <div class="clearfix"></div>

        </div>
        <!-- Sidebar -left -->

    </div>
    <!-- Left Sidebar End -->

    <!-- ============================================================== -->
    <!-- Start Page Content here -->
    <!-- ============================================================== -->

    <div class="content-page">
        <div class="content">

            <!-- Start Content-->
            <div class="col-8">
                <h1>Search customer by <c:out value="${search}"></c:out></h1>
            </div>
            <div class="col-4">
                <div>
                    <p class="navbar-text" style="float:right">Welcome ${sessionScope.user.employeeName}</p>
                </div>
            </div>
        </div>
        <c:if test="${empty customerList}">
            <h3 style="color: red">No results were found</h3>
            <h3>
                <a href="/customer">Back to list all customers</a>
            </h3>
        </c:if>
        <c:if test="${not empty customerList}">
            <h3>
                <c:if test='${requestScope["message"] != null}'>
                    <span class="message">${requestScope["message"]}</span>
                </c:if>
            </h3>
            <h3>
                <a href="/customer">Back to list all customers</a>
            </h3>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Birthday</th>
                    <th>Gender</th>
                    <th>Id card</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Customer type</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope['customerList']}" var="customer">
                    <tr>
                        <td>${customer.getId()}</td>
                        <td>${customer.getName()}</td>
                        <td>${customer.getBirthday()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${customer.getGender() == 1}">Nam</c:when>
                                <c:when test="${customer.getGender() == 2}">Nữ</c:when>
                                <c:otherwise>LGBT</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${customer.getIdCard()}</td>
                        <td>${customer.getPhone()}</td>
                        <td>${customer.getEmail()}</td>
                        <td>${customer.getAddress()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${customer.getCustomerTypeId() == 1}">Diamond</c:when>
                                <c:when test="${customer.getCustomerTypeId() == 2}">Platinium</c:when>
                                <c:when test="${customer.getCustomerTypeId() == 3}">Gold</c:when>
                                <c:when test="${customer.getCustomerTypeId() == 4}">Silver</c:when>
                                <c:when test="${customer.getCustomerTypeId() == 5}">Member</c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

        <%@ include file="/WEB-INF/layout/footer.jsp" %>


    </div>

    <!-- ============================================================== -->
    <!-- End Page content -->
    <!-- ============================================================== -->

</div>
<!-- END wrapper -->

<!-- Vendor js -->
<script src="/assets/js/vendor.min.js"></script>

<!-- Toast js -->

<%--<script src="/assets/libs/toastr/toastr.min.js"></script>--%>

<%--<script src="/assets/js/pages/toastr.init.js"></script>--%>

<!-- Plugin js-->
<script src="/assets/libs/parsleyjs/parsley.min.js"></script>
<script src="/assets/libs/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>
<script src="/assets/libs/switchery/switchery.min.js"></script>
<script src="/assets/libs/select2/select2.min.js"></script>
<script src="/assets/libs/bootstrap-touchspin/jquery.bootstrap-touchspin.min.js"></script>
<script src="/assets/libs/jquery-mask-plugin/jquery.mask.min.js"></script>
<script src="/assets/libs/moment/moment.min.js"></script>
<script src="/assets/libs/bootstrap-timepicker/bootstrap-timepicker.min.js"></script>
<script src="/assets/libs/bootstrap-colorpicker/bootstrap-colorpicker.min.js"></script>
<script src="/assets/libs/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>

<!-- Validation init js-->
<script src="/assets/js/pages/form-validation.init.js"></script>

<!-- Init js-->
<script src="/assets/js/pages/form-advanced.init.js"></script>

<!-- App js -->
<script src="/assets/js/app.min.js"></script>
</body>
</html>
