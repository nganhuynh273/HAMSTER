<%--
  Created by IntelliJ IDEA.
  User: Ngan
  Date: 22/07/2022
  Time: 9:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Hamster Resort</title>
    <%@ include file="/WEB-INF/layout/header-p1.jsp" %>
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
                                        <h2 class="text-dark"><b>DANH SÁCH HỢP ĐỒNG</b></h2>
                                    </div>
                                    <div class="col-md-3">
                                        <div style="float: right">
                                            <a href="/contract-detail?action=create" class="btn btn-outline-purple">
                                                <i class="fas fa-plus"></i>
                                                <span>TẠO HỢP ĐỒNG CHI TIẾT</span>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-4">
                                        <div>
                                            <p class="navbar-text" style="float:right">Welcome ${sessionScope.user.staffName}</p>
                                        </div>
                                    </div>
                                </div>

                                <table id="datatable" class="table table-bordered dt-responsive nowrap" style="border-collapse: collapse; border-spacing: 0; width: 100%;">

                                    <thead>
                                    <tr class="text-center">
                                        <th>ID</th>
                                        <th>ID hợp đồng</th>
                                        <th>Dịch vụ đi kèm</th>
                                        <th>Số lượng</th>
                                    </tr>
                                    </thead>

                                    <tbody>

                                    <c:forEach items="${requestScope['contractDetailList']}" var="contract_detail">
                                        <tr>
                                            <td>${contract_detail.id}</td>
                                            <td>${contract_detail.contractId}</td>
                                            <td>
                                                <c:forEach items="${requestScope['accompaniedServiceList']}" var="accompanied_service">
                                                    <c:if test="${contract_detail.accompaniedServiceId == accompanied_service.id}">
                                                        ${accompanied_service.name}
                                                    </c:if>
                                                </c:forEach>
                                            </td>
                                            <td>${contract_detail.quantity}</td>
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
</body>
</html>
