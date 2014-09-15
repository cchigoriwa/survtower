<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ include file="/WEB-INF/utils/includes.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <spring:url value="/resources/foundation/css/foundation.css" var="foundationCss"/>
        <spring:url value="/resources/foundation/js/vendor/modernizr.js" var="modernizrJs"/>
        <spring:url value="/resources/survtower/css/app.css" var="appCss"/>
        <title>SADC Surveillance External Portal</title>        
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="${foundationCss}" />
        <link rel="stylesheet" href="${appCss}" />
        <script src="${modernizrJs}"></script>
    </head>
    <body>
        <tiles:insertAttribute name="header" />
        <div>
            <tiles:insertAttribute name="content" />            
            <tiles:insertAttribute name="footer" />   
        </div>

        <spring:url value="/resources/foundation/js/vendor/jquery.js" var="jqueryJs"/>
        <spring:url value="/resources/foundation/js/foundation.min.js" var="foundationMinJs"/>
        <spring:url value="/resources/foundation/js/foundation/foundation.topbar.js" var="foundationTopBarJs"/>
        <script src="${jqueryJs}"></script>
        <script src="${foundationMinJs}"></script>
        <script src="${foundationTopBarJs}"></script>
        <script>
            $(document).foundation();
        </script>
    </body>
</html>