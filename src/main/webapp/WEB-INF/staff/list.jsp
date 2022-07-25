<%--
  Created by IntelliJ IDEA.
  User: Ngan
  Date: 22/07/2022
  Time: 10:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Hamster Resort</title>
    <%@ include file="/layout/header-p1.jsp" %>
    <!-- Notification css (Toast) -->
    <link href="/assets/libs/toastr/css/iziToast.min.css" rel="stylesheet" type="text/css">

    <link href="/assets/libs/datatables/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css">
    <link href="/assets/libs/datatables/buttons.bootstrap4.min.css" rel="stylesheet" type="text/css">
    <link href="/assets/libs/datatables/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css">
    <link href="/assets/libs/datatables/select.bootstrap4.min.css" rel="stylesheet" type="text/css">
    <%@ include file="/WEB-INF/layout/header-p2.jsp" %>
    <script src="/assets/libs/toastr/js/iziToast.min.js"></script>

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
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body table-responsive">

                                <div class="row mb-3">
                                    <div class="col-md-9">
                                        <h2 class="text-dark"><b>DANH SÁCH NHÂN VIÊN</b></h2>
                                    </div>
                                    <h3>
                                        <c:if test='${requestScope["message"] != null}'>
                                            <span class="message">${requestScope["message"]}</span>
                                        </c:if>
                                    </h3>
                                    <div class="col-md-3">
                                        <div style="float: right">
                                            <a href="/staff?action=create" class="btn btn-outline-purple">
                                                <i class="fas fa-plus"></i>
                                                <span>THÊM KHÁCH HÀNG</span>
                                            </a>
                                        </div>
                                    </div>
                                </div>

                                <table id="datatable" class="table table-bordered dt-responsive nowrap" style="border-collapse: collapse; border-spacing: 0; width: 100%;">

                                    <thead>
                                    <tr class="text-center">
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
                                        <th>Actions</th>
                                    </tr>
                                    </thead>

                                    <tbody>

                                    <c:forEach var="staff" items="${requestScope['staffList']}" >
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
                                                    <c:when test="${staff.getDivisionId() == 1}">Sale </c:when>
                                                    <c:when test="${staff.getDivisionId() == 2}">Marketing</c:when>
                                                    <c:when test="${staff.getDivisionId() == 3}">Hành chính</c:when>
                                                    <c:when test="${staff.getDivisionId() == 4}">Phục vụ</c:when>
                                                    <c:when test="${staff.getDivisionId() == 5}">Quản lý</c:when>
                                                </c:choose>
                                            </td>
                                            <td>${staff.getUsername()}</td>
                                            <td>${staff.getPassword()}</td>
                                            <td>
                                                <a title="Edit" href="/staff?action=edit&id=${staff.getId()}" class="btn btn-outline-secondary">
                                                    <i class="fas fa-pencil-alt"></i>
                                                </a>
                                                <a title="Delete" href="/staff?action=delete&id=${staff.getId()}" class="btn btn-outline-danger" onclick="onDelete(${staff.getId()})">
                                                    <i class="fas fa-trash"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end container-fluid -->
        </div>
        <!-- end content -->

        <!-- Footer Start -->
        <%@ include file="/WEB-INF/layout/footer.jsp" %>
        <!-- end Footer -->

    </div>

</div>
<div class="modal fade" id="modelId" tabindex="-1" role="dialog" aria-labelledby="modelTitleId" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Confirm Delete</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="/staff">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="" id="idStaffDell">
                <div class="modal-body">
                    Bạn chắc chắn muốn xóa?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Delete</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->

</div>
<!-- END wrapper -->
<!-- Vendor js -->
<script src="/assets/js/vendor.min.js"></script>

<!-- Required datatable js -->
<script src="/assets/libs/datatables/jquery.dataTables.min.js"></script>
<script src="/assets/libs/datatables/dataTables.bootstrap4.min.js"></script>
<!-- Buttons examples -->
<script src="/assets/libs/datatables/dataTables.buttons.min.js"></script>
<script src="/assets/libs/datatables/buttons.bootstrap4.min.js"></script>
<script src="/assets/libs/jszip/jszip.min.js"></script>
<script src="/assets/libs/pdfmake/pdfmake.min.js"></script>
<script src="/assets/libs/pdfmake/vfs_fonts.js"></script>
<script src="/assets/libs/datatables/buttons.html5.min.js"></script>
<script src="/assets/libs/datatables/buttons.print.min.js"></script>

<!-- Responsive examples -->
<script src="/assets/libs/datatables/dataTables.responsive.min.js"></script>
<script src="/assets/libs/datatables/responsive.bootstrap4.min.js"></script>

<script src="/assets/libs/datatables/dataTables.keyTable.min.js"></script>
<script src="/assets/libs/datatables/dataTables.select.min.js"></script>

<!-- Datatables init -->
<script src="/assets/js/pages/datatables.init.js"></script>

<!-- App js -->
<script src="/assets/js/app.min.js"></script>
<script>
    function onDelete(${staff.getId()}) {
        document.getElementById("idStaffDel").value = ${staff.getId()};
    }
    $(document).ready(function () {
        $("#tableCustomer").DataTable({
            "dom": 'lrtip',
            "lengthChange": false,
            "pageLength": 4
        })
    })
</script>
</body>
</html>
