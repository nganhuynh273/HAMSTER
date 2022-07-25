<%--
  Created by IntelliJ IDEA.
  User: Ngan
  Date: 23/07/2022
  Time: 12:41 AM
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
                                <h2 class= "mb-4 text-secondary">CHỈNH SỬA NHÂN VIÊN</h2>
                                <h4>
                                    <c:if test='${requestScope["message"] != null}'>
                                        <span class="message">${requestScope["message"]}</span>
                                    </c:if>
                                </h4>
                                <div>
                                    <p class="navbar-text" style="float:right">Welcome ${sessionScope.user.staffName}</p>
                                </div>
                                <form class="parsley-examples" method="post" autocomplete="off">
                                    <c:if test="${staff != null}">
                                    <div class="row mt-2">
                                        <div class="col-4">
                                            <label class="col-form-label">Id</label>
                                        </div>
                                        <div class="col-8">
                                            <input type="text" disabled class="form-control" name="id" value="${requestScope["staff"].id}">
                                        </div>
                                    </div>
                                    </c:if>
                                    <div class="row mt-3">
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="name">Họ Và tên <span class="text-danger">*</span></label>
                                            <input type="text" name="name" parsley-trigger="change" required="" placeholder="Nhập tên" class="form-control" id="name" value="${staff.getName()}">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="birthday">Ngày sinh<span class="text-danger">*</span></label>
                                            <input type="date" name="birthday" parsley-trigger="change" required="" placeholder="Nhập ngày sinh" class="form-control" id="birthday" value = "${staff.getBirthday()}"  >
                                        </div>

                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="id_card">CCCD/CMND<span class="text-danger">*</span></label>
                                            <input type="number" name="id_card" placeholder="" required="" value="${staff.getIdCard()}" class="form-control" id="id_card">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="salary">Lương<span class="text-danger">*</span></label>
                                            <input type="number" name="salary" placeholder="" required="" value="${staff.getSalary()}" class="form-control" id="salary">
                                        </div>
                                    </div>

                                    <div class="row mt-3">
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="phone">Số điện thoại<span class="text-danger">*</span></label>
                                            <input type="number" name="phone" placeholder="" required="" value="${staff.getPhone()}" class="form-control" id="phone">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="email">Email<span class="text-danger">*</span></label>
                                            <input type="text" name="email" placeholder="" required="" value="${staff.getEmail()}" class="form-control" id="email">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="address">Địa chỉ<span class="text-danger">*</span></label>
                                            <input type="text" name="address" placeholder="" required="" value="${staff.getAddress()}" class="form-control" id="address">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="position">Chức vụ<span class="text-danger">*</span></label>
                                            <select class="form-control" data-toggle="select2" id="position" name="position">
                                                <c:choose>
                                                    <c:when test="${staff.getPositionId() == 1}">
                                                        <input type="text" class="form-control" name="position" value="Lễ tân">
                                                    </c:when>
                                                    <c:when test="${staff.getPositionId() == 2}">
                                                        <input type="text" class="form-control" name="position" value="Phục vụ">
                                                    </c:when>
                                                    <c:when test="${staff.getPositionId() == 3}">
                                                        <input type="text" class="form-control" name="position" value="Chuyên viên">
                                                    </c:when>
                                                    <c:when test="${staff.getPositionId() == 4}">
                                                        <input type="text" class="form-control" name="position" value="Giám sát">
                                                    </c:when>
                                                    <c:when test="${staff.getPositionId() == 5}">
                                                        <input type="text" class="form-control" name="position" value="Quản lý">
                                                    </c:when>
                                                    <c:when test="${staff.getPositionId() == 6}">
                                                        <input type="text" class="form-control" name="position" value="Giám đốc">
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </div>

                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="education_degree">Trình độ học vấn<span class="text-danger">*</span></label>
                                            <select class="form-control" data-toggle="select2" id="education_degree" name="education_degree">
                                                <c:choose>
                                                    <c:when test="${staff.getEducationDegreeId() == 1}">
                                                        <input type="text" class="form-control" name="education_degree" value="Trung cấp">
                                                    </c:when>
                                                    <c:when test="${staff.getEducationDegreeId() == 2}">
                                                        <input type="text" class="form-control" name="education_degree" value="Cao đẳng">
                                                    </c:when>
                                                    <c:when test="${staff.getEducationDegreeId() == 3}">
                                                        <input type="text" class="form-control" name="education_degree" value="Đại học">
                                                    </c:when>
                                                    <c:when test="${staff.getEducationDegreeId() == 4}">
                                                        <input type="text" class="form-control" name="education_degree" value="Sau đại học">
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </div>

                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="division">Bộ phận<span class="text-danger">*</span></label>
                                            <select class="form-control" data-toggle="select2" id="division" name="division">
                                                <c:choose>
                                                    <c:when test="${staff.getDivisionId() == 1}">
                                                        <input type="text" class="form-control" name="division" value="Sale">
                                                    </c:when>
                                                    <c:when test="${staff.getDivisionId() == 2}">
                                                        <input type="text" class="form-control" name="division" value="Marketing">
                                                    </c:when>
                                                    <c:when test="${staff.getDivisionId() == 3}">
                                                        <input type="text" class="form-control" name="division" value="Hành chính">
                                                    </c:when>
                                                    <c:when test="${staff.getDivisionId() == 4}">
                                                        <input type="text" class="form-control" name="division" value="Phục vụ">
                                                    </c:when>
                                                    <c:when test="${staff.getDivisionId() == 5}">
                                                        <input type="text" class="form-control" name="division" value="Quản lý">
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="username">Username<span class="text-danger">*</span></label>
                                            <input type="text" name="username" placeholder="" required="" value="${staff.getUsername()}" class="form-control" id="username">
                                        </div>
                                        <div class="form-group col-lg-4 col-md-6 col-sm-12">
                                            <label for="password">Password<span class="text-danger">*</span></label>
                                            <input type="number" name="password" placeholder="" required="" value="${staff.getPassword()}" class="form-control" id="password">
                                        </div>


                                        <div class="form-group text-right mb-0">
                                            <button class="btn btn-secondary waves-effect waves-light mr-1" type="submit">
                                                Update
                                            </button>
                                            <a href="/staff" class="btn btn-dark waves-effect waves-light">
                                                <span>Back</span>
                                            </a>
                                        </div>
                                        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">Confirm update</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                       Bạn chắc chắn muốn chỉnh sửa nhân viên?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="submit" class="btn btn-primary">Yes</button>
                                                        <button type="submit" class="btn btn-secondary" data-dismiss="modal">No</button>
                                                    </div>
                                                </div>
                                            </div>
                                    </div>


                                    <%@ include file="/WEB-INF/layout/footer.jsp" %>



                        </div>
                        <!-- END wrapper -->


                        <!-- Right Sidebar -->
                        <%--<%@ include file="/layout/sidebar-right.jsp" %>--%>
                        <!-- /Right-bar -->

                        <!-- Right bar overlay-->
                        <%--<div class="rightbar-overlay"></div>--%>

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

