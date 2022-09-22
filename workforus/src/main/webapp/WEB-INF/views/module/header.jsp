<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:url var="staticUrl" value="/static"/>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${staticUrl}/css/bootstrap.css">

<link rel="stylesheet" href="${staticUrl}/vendors/iconly/bold.css">
<link rel="stylesheet" href="${staticUrl}/css/app.css">

<link rel="stylesheet" href="${staticUrl}/vendors/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet" href="${staticUrl}/vendors/bootstrap-icons/bootstrap-icons.css">
<link rel="icon" href="${staticUrl}/images/logo/favicon.ico">
    
<header class='mb-3'>
	<nav class="navbar navbar-expand navbar-light navbar-top">
		<div class="container-fluid">

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-lg-0">
					<li class="nav-item dropdown me-1">
					<a class="nav-link active dropdown-toggle text-gray-600" href="#"
						data-bs-toggle="dropdown" aria-expanded="false">
						<i class='bi bi-envelope bi-sub fs-4'></i>
					</a>
						<ul class="dropdown-menu dropdown-menu-end"
							aria-labelledby="dropdownMenuButton">
							<li>
								<h6 class="dropdown-header">Mail</h6>
							</li>
							<li>
								<a class="dropdown-item" href="#">new mail</a>
							</li>
						</ul>
					</li>
					<li class="nav-item dropdown me-3">
					<a class="nav-link active dropdown-toggle text-gray-600" href="#"
						data-bs-toggle="dropdown" data-bs-display="static" aria-expanded="false">
						<i class='bi bi-bell bi-sub fs-4'></i>
					</a>
						<ul class="dropdown-menu dropdown-menu-end notification-dropdown"
							aria-labelledby="dropdownMenuButton">
							<li class="dropdown-header">
								<h6>Notifications</h6>
							</li>
							<li class="dropdown-item notification-item">
							<a class="d-flex align-items-center" href="#">
								<div class="notification-icon bg-primary">
									<i class="bi bi-cart-check"></i>
								</div>
								<div class="notification-text ms-4">
									<p class="notification-title font-bold">Event today</p>
									<p class="notification-subtitle font-thin text-sm">Reminder that you have an event today</p>
								</div>
							</a>
							</li>
							<li class="dropdown-item notification-item">
							<a class="d-flex align-items-center" href="#">
								<div class="notification-icon bg-success">
									<i class="bi bi-file-earmark-check"></i>
								</div>
								<div class="notification-text ms-4">
									<p class="notification-title font-bold">David send you a chat</p>
									<p class="notification-subtitle font-thin text-sm">1 Minutes ago</p>
								</div>
							</a>
							</li>
							<li>
								<p class="text-center py-2 mb-0">
									<a href="#">See all notification</a>
								</p>
							</li>
						</ul>
					</li>
				</ul>
				<div class="dropdown">
					<a href="#" data-bs-toggle="dropdown" aria-expanded="false">
						<div class="user-menu d-flex">
							<div class="user-name text-end me-3">
								<h6 class="mb-0 text-gray-600">John Ducky</h6>
								<p class="mb-0 text-sm text-gray-600">Administrator</p>
							</div>
							<div class="user-img d-flex align-items-center">
								<div class="avatar avatar-md">
									<img src="${staticUrl}/images/faces/1.jpg">
								</div>
							</div>
						</div>
					</a>
					<ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownMenuButton" style="min-width: 11rem;">
						<li>
							<h6 class="dropdown-header">Hello, John!</h6>
						</li>
						<li>
							<a class="dropdown-item" href="#">
								<i class="icon-mid bi bi-person me-2"></i> My Profile
							</a>
						</li>
						<li>
							<hr class="dropdown-divider">
						</li>
						<li>
							<a class="dropdown-item" href="#">
								<i class="icon-mid bi bi-box-arrow-left me-2"></i> Logout
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
</header>