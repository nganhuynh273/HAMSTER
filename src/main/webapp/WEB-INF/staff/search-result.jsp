<%--
  Created by IntelliJ IDEA.
  User: Ngan
  Date: 23/07/2022
  Time: 2:18 AM
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
        <h1>Search staff by <c:out value="${search}"></c:out></h1>
      </div>
      <div class="col-4">
        <div>
          <p class="navbar-text" style="float:right">Welcome ${sessionScope.user.staffName}</p>
        </div>
      </div>
    </div>
    <c:if test="${empty staffList}">
      <h3 style="color: red">No results were found</h3>
      <h3>
        <a href="/staff">Back to list all customers</a>
      </h3>
    </c:if>
    <c:if test="${not empty staffList}">
      <h3>
        <c:if test='${requestScope["message"] != null}'>
          <span class="message">${requestScope["message"]}</span>
        </c:if>
      </h3>
      <h3>
        <a href="/staff">Back to list all customers</a>
      </h3>
      <table class="table table-striped">
        <thead>
        <tr>
          <th>#</th>
          <th>Họ và Tên</th>
          <th>Ngày sinh</th>
          <th>CCCD/CMND</th>
          <th>Lương</th>
          <th>Số điện thoại</th>
          <th>Email</th>
          <th>Địa chỉ</th>
          <th>Chức vụ</th>
          <th>Trình độ học vấn</th>
          <th>Bộ phận</th>
          <th>Username</th>
          <th>Password</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope['staffList']}" var="staff">
          <tr>
            <td>${staff.getId()}</td>
            <td>${staff.getName()}</td>
            <td>
              <fmt:formatDate pattern = "dd/MM/yyyy" value = "${staff.getBirthday()}" />
            </td>
            <td>${staff.getIdCard()}</td>
            <td>${staff.getSalary()}</td>
            <td>${staff.getPhone()}</td>
            <td>${staff.getEmail()}</td>
            <td>${staff.getAddress()}</td>
            <td>
              <c:choose>
                <c:when test="${staff.getPositionId() == 1}">Lễ tân</c:when>
                <c:when test="${staff.getPositionId() == 2}">Phục vụ</c:when>
                <c:when test="${staff.getPositionId() == 3}">Chuyên viên</c:when>
                <c:when test="${staff.getPositionId() == 4}">Giám sát</c:when>
                <c:when test="${staff.getPositionId() == 5}">Quản lý</c:when>
                <c:when test="${staff.getPositionId() == 6}">Giám đốc</c:when>
              </c:choose>
            </td>
            <td>
              <c:choose>
                <c:when test="${staff.getEducationDegreeId() == 1}">Trung cấp</c:when>
                <c:when test="${staff.getEducationDegreeId() == 2}">Cao đẳng</c:when>
                <c:when test="${staff.getEducationDegreeId() == 3}">Đại học</c:when>
                <c:when test="${staff.getEducationDegreeId() == 4}">Sau đại học</c:when>
              </c:choose>
            </td>
            <td>
              <c:choose>
                <c:when test="${staff.getDivisionId() == 1}">Sale</c:when>
                <c:when test="${staff.getDivisionId() == 2}">Marketing</c:when>
                <c:when test="${staff.getDivisionId() == 3}">Hành chính</c:when>
                <c:when test="${staff.getDivisionId() == 4}">Phục vụ</c:when>
                <c:when test="${staff.getDivisionId() == 5}">Quản lý</c:when>
              </c:choose>
            </td>
            <td>${staff.getUsername()}</td>
            <td>${staff.getPassword()}</td>
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
