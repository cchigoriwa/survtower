<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ include file="/WEB-INF/utils/includes.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <spring:url value="/resources/foundation/css/foundation.css" var="foundationCss"/>
        <spring:url value="/resources/foundation/js/vendor/modernizr.js" var="modernizrJs"/>
        <title>SADC Surveillance External Portal</title>        
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="${foundationCss}" />
        <script src="${modernizrJs}"></script>
    </head>
    <body>
        <tiles:insertAttribute name="header" />
        <div>
            <tiles:insertAttribute name="content" />            
            <tiles:insertAttribute name="footer" />   
        </div>
    </body>
</html>