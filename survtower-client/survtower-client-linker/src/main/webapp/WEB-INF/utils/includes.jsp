<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="appTags" tagdir="/WEB-INF/tags" %>
<sec:authentication property="name" var="userName"></sec:authentication>
<spring:url value="/member/change_password" var="changePasswordUrl"></spring:url> 
<spring:url value="/member/security" var="memberSecurityUrl"></spring:url> 
<spring:url value="/" var="homeUrl"></spring:url>
<spring:url value="/member/dashboard" var="memberDashboardUrl"/>
<spring:url value="/login" var="loginUrl"/>
<spring:url value="/logout" var="logoutUrl"/>