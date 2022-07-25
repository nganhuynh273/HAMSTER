<%--
  Created by IntelliJ IDEA.
  User: Ngan
  Date: 23/07/2022
  Time: 12:23 AM
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
            <div class="container-fluid">

                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-body">
                                <h2 class="mb-4 text-purple">THÊM NHÂN VIÊN</h2>

                                <form class="parsley-examples" method="post" autocomplete="off">
                                    <div class="row mt-3">
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="name">Họ Và tên <span class="text-danger">*</span></label>
                                            <input type="text" name="name" parsley-trigger="change" required="" placeholder="Nhập tên" class="form-control" id="name">

                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="birthday">Ngày sinh<span class="text-danger">*</span></label>
                                            <input type="date" name="birthday" parsley-trigger="change" required="" placeholder="Nhập ngày sinh" class="form-control" id="birthday">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="id_card">CCCD/CMND<span class="text-danger">*</span></label>
                                            <input type="number" name="id_card" parsley-trigger="change" required="" placeholder="Nhập CCCD/CMND" class="form-control" id="id_card">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="salary">Lương<span class="text-danger">*</span></label>
                                            <input type="number" name="salary" parsley-trigger="change" required="" placeholder="Nhập lương" class="form-control" id="salary">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="phone">Số điện thoại<span class="text-danger">*</span></label>
                                            <input id="phone" name="phone" type="text" required="" class="form-control">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="email">Email<span class="text-danger">*</span></label>
                                            <input type="text" name="email" placeholder="" required="" class="form-control" id="email">
                                        </div>
                                    </div>

                                    <div class="row mt-3">

                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="address">Địa chỉ<span class="text-danger">*</span></label>
                                            <input type="text" name="address" placeholder="" required=""
                                                   class="form-control" id="address">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="position_id">Chức vụ<span class="text-danger">*</span></label>
                                            <select class="form-control" data-toggle="select2" id="position_id" name="position_id">
                                                <option disabled ${staff == null ? "selected" : ""}>- Chọn chức vụ -</option>
                                                <option value="1">Lễ tân</option>
                                                <option value="2">Phục vụ</option>
                                                <option value="3">Chuyên viên</option>
                                                <option value="4">Giám sát</option>
                                                <option value="5">Quản lý</option>
                                                <option value="6">Giám đốc</option>
                                            </select>
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="education_degree_id">Trình độ học vấn<span class="text-danger">*</span></label>
                                            <select class="form-control" data-toggle="select2" id="education_degree_id" name="education_degree_id">
                                                <option disabled ${staff == null ? "selected" : ""}>- Chọn trình độ học vấn -</option>
                                                <option value="1">Trung cấp</option>
                                                <option value="2">Cao đẳng</option>
                                                <option value="3">Đại học</option>
                                                <option value="4">Sau đại học</option>
                                            </select>
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="division_id">Bộ phận<span class="text-danger">*</span></label>
                                            <select class="form-control" data-toggle="select2" id="division_id" name="division_id">
                                                <option disabled ${staff == null ? "selected" : ""}>- Chọn bộ phận -</option>
                                                <option value="1">Sale</option>
                                                <option value="2">Marketing</option>
                                                <option value="3">Hành chính</option>
                                                <option value="4">Phục vụ</option>
                                                <option value="5">Quản lý</option>
                                            </select>
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="username">Username<span class="text-danger">*</span></label>
                                            <input type="text" name="username" placeholder="" required=""
                                                   class="form-control" id="username">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="password">Password<span class="text-danger">*</span></label>
                                            <input type="text" name="password" placeholder="" required=""
                                                   class="form-control" id="password">
                                        </div>
                                        <div class="form-group text-right mb-0">
                                            <button class="btn btn-purple waves-effect waves-light mr-1" type="submit">
                                                Add
                                            </button>
                                            <a href="/staff" class="btn btn-secondary waves-effect waves-light">
                                                <span>Back</span>
                                            </a>
                                            <p class="text-center">
                                                <c:if test='${requestScope["message"] != null}'>
                                                    <span class="message  text-success">${requestScope["message"]}</span>
                                                </c:if>
                                            </p>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

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
