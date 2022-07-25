<%--
  Created by IntelliJ IDEA.
  User: Ngan
  Date: 22/07/2022
  Time: 4:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <title>Customer Management</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/static/bootstrap-4.3.1-dist/css/bootstrap.min.css">
</head>
<body>
<div class="row">
    <div class="col-8">
        <h1>Customer Management</h1>
    </div>
</div>
<h4>
    <a href=/customer?action=search>Search customer</a>
</h4>
<h4>
    <a href="/customer" class="btn btn-secondary waves-effect waves-light">
        <span>Back</span>
    </a>
</h4>

<form method="post">
    <div class="row mt-2">
        <div class="col-4">
            <h3>Please enter <c:out value="${search}"></c:out> of Customer: </h3>
        </div>
    </div>
    <div class="row mt-2">
        <div class="col-4">
            <input type="text" class="form-control" name="<c:out value="${search}"></c:out>">
        </div>
    </div>
    <div class="row mt-2">
        <div class="col-2">
            <input type="submit" class="form-control" value="Search Customer">
        </div>
    </div>
</form>


<script src="/static/jquery/jquery-3.5.1.min.js"></script>
<script src="/static/js/popper.min.js"></script>
<script src="/static/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
<script src="/static/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/datatables/js/dataTables.bootstrap4.min.js"></script>

</body>
</html>
