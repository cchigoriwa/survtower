<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ include file="/WEB-INF/utils/includes.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>SurvTower</title>        
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />        
    </head>
    <body>
        <tiles:insertAttribute name="header" />
        <div>
            <tiles:insertAttribute name="content" />            
            <tiles:insertAttribute name="footer" />   
        </div>
    </body>
</html>