<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../layout/header.jsp"%>

<div class="container" var="boards">
	<br /> <br />
	<div>
		<h3>${boards.title}</h3>
	</div>
	<hr/>

	<div>${boards.content}</div>


</div>

<%@ include file="../layout/footer.jsp"%>

