<%--
  Created by IntelliJ IDEA.
  User: Ngan
  Date: 22/07/2022
  Time: 5:21 PM
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
                                            <a href="/contract?action=create" class="btn btn-outline-purple">
                                                <i class="fas fa-plus"></i>
                                                <span>TẠO HỢP ĐỒNG</span>
                                            </a>
                                        </div>
                                    </div>
                                </div>

                                <table id="datatable" class="table table-bordered dt-responsive nowrap" style="border-collapse: collapse; border-spacing: 0; width: 100%;">

                                    <thead>
                                    <tr class="text-center">
                                        <th>ID</th>
                                        <th>Ngày bắt đầu hợp đồng</th>
                                        <th>Ngày kết thúc hợp đồng</th>
                                        <th>Tiền đặt cọc</th>
                                        <th>Tổng tiền thành toán</th>
                                        <th>ID Nhân viên</th>
                                        <th>ID Khách hàng</th>
                                        <th>ID Dịch vụ</th>
<%--                                        <th>Actions</th>--%>
                                    </tr>
                                    </thead>

                                    <tbody>

                                    <c:forEach var="contract" items="${contractList}" >
                                        <tr>

                                            <td>${contract.getId()}</td>
                                            <td>${contract.getStartDate()}
<%--                                                <fmt:formatDate pattern = "dd/MM/yyyy" value = "${contract.getStartDate()}" />--%>
                                            </td>
                                            <td>${contract.getEndDate()}
<%--                                                <fmt:formatDate pattern = "dd/MM/yyyy" value = "${contract.getEndDate()}" />--%>
                                            </td>
                                            <td>
                                                <fmt:formatNumber pattern = "###,###,### VNĐ" value="${contract.getDeposit()}"/>
                                            </td>
                                            <td>
                                                <fmt:formatNumber pattern = "###,###,### VNĐ" value="${contract.getTotalMoney()}"/>
                                            </td>
                                            <td>${contract.getStaffId()}</td>
                                            <td>${contract.getCustomerId()}</td>
                                            <td>${contract.getServiceId()}</td>
<%--                                            <td>--%>
<%--                                                <a title="Edit" href="/contract?action=edit&id=${customer.getId()}" class="btn btn-outline-secondary">--%>
<%--                                                    <i class="fas fa-pencil-alt"></i>--%>
<%--                                                </a>--%>
<%--                                                <a title="Delete" href="/contract?action=delete&id=${customer.getId()}" class="btn btn-outline-danger" onclick="onDelete(${contract.getId()})">--%>
<%--                                                    <i class="fas fa-trash"></i>--%>
<%--                                                </a>--%>

<%--                                            </td>--%>
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
            <form action="/contract">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="" id="idCustomerDell">
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
    function onDelete(${contract.getId()}) {
        document.getElementById("idCustomerDel").value = ${contract.getId()};
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
