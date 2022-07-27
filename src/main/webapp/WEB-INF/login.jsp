<%--
  Created by IntelliJ IDEA.
  User: Ngan
  Date: 25/07/2022
  Time: 11:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Log In | Uplon - Responsive Bootstrap 4 Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Responsive bootstrap 4 admin template" name="description">
    <meta content="Coderthemes" name="author">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- App favicon -->
    <link rel="shortcut icon" href="assets\images\favicon.ico">

    <!-- App css -->
    <link href="assets\css\bootstrap.min.css" rel="stylesheet" type="text/css" id="bootstrap-stylesheet">
    <link href="assets\css\icons.min.css" rel="stylesheet" type="text/css">
    <link href="assets\css\app.min.css" rel="stylesheet" type="text/css" id="app-stylesheet">

</head>

<body class="authentication-bg">

<div class="account-pages pt-5 my-5">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6 col-xl-5">
                <div class="account-card-box">
                    <div class="card mb-0">
                        <div class="card-body p-4">

                            <div class="text-center">

                                <h5 class="text-muted text-uppercase py-3 font-16">Sign In</h5>
                            </div>

                            <form  class="mt-2" method="post">

                                <div class="form-group mb-3">
                                    <input name = "userName" id="userName" class="form-control" type="text" required="" placeholder="Nhập username">
                                </div>

                                <div class="form-group mb-3">
                                    <input name = "password" class="form-control" type="password" required="" id="password" placeholder="Nhập mật khẩu">
                                </div>

                                <div class="form-group mb-3">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="checkbox-signin" checked="">
                                        <label class="custom-control-label" for="checkbox-signin">Remember me</label>
                                    </div>
                                </div>

                                <div class="row">
                                    <c:choose>
                                        <c:when test = "${requestScope['message'] == null}" >
                                        </c:when>
                                        <%--                                        <c:when test='${requestScope["message"] == "Tên đăng nhập hoặc mật khẩu không đúng!"}'>--%>
                                        <%--                                            <%@ include file="/alert/success.jsp"%>--%>
                                        <%--                                        </c:when>--%>
                                        <c:otherwise>
                                            <div class="alert alert-danger alert-dismissible danger" id="d-none" style="margin-left: 15px">
                                                <button type="button" class="close" aria-label="Close" data-bs-dismiss="alert">
                                                    <span aria-hidden="true" id="close">x</span>
                                                </button>
                                                <strong>${requestScope["message"]}</strong>
                                            </div>

                                            <script>
                                                document.getElementById("userName").addEventListener("focus", function (){
                                                    document.getElementById("d-none").style.display = "none";
                                                });
                                                document.getElementById("password").addEventListener("focus", function (){
                                                    document.getElementById("d-none").style.display = "none";
                                                });
                                            </script>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:if test='${requestScope["errors"] != null}'>
                                        <%@ include file="/alert/danger.jsp"%>
                                    </c:if>
                                </div>

                                <div class="form-group text-center">
                                    <button class="btn btn-success btn-block waves-effect waves-light" type="submit"> Log In </button>
                                </div>


                            </form>

                            <div class="text-center mt-4">
                                <h5 class="text-muted py-2"><b>Sign in with</b></h5>

                            </div>

                        </div> <!-- end card-body -->
                    </div>
                    <!-- end card -->
                </div>

                <!-- end row -->

            </div> <!-- end col -->
        </div>
        <!-- end row -->
    </div>
    <!-- end container -->
</div>
<!-- end page -->

<!-- Vendor js -->
<script src="assets\js\vendor.min.js"></script>

<!-- App js -->
<script src="assets\js\app.min.js"></script>

</body>
</html>