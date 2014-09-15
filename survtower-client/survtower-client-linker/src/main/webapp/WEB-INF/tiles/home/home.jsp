<%@ include file="/WEB-INF/utils/includes.jsp" %>
<div class="row">
    <div class="large-12 columns">
        <div class="panel">
            <h3>SADC Surveillance External Portal! </h3>
            <p>To become part of the SADC Surveillance Ecosystem a SADC member should submit details to us.</p>
            <p>Once you are a legitimite member of this group you can login to view and maintain your membership:</p>
            <div class="row">
                <div class="large-4 medium-4 columns">
                    <p><a href="#">SADC Surveillance Documentation</a><br />Everything you need to know about using the framework.</p>
                </div>
                <div class="large-4 medium-4 columns">
                    <p><a href="#">SADC Surveillance Update</a><br />Latest code, issue reports, feature requests and more.</p>
                </div>
                <div class="large-4 medium-4 columns">
                    <p><a href="#">@SADCSurveil</a><br />Ping us on Twitter if you have questions.</p>
                </div>        
            </div>
            <div class="row">
                <spring:url value="/login" var="loginUrl"/>
                <spring:url value="/logout" var="logoutUrl"/>
                <c:choose>
                    <c:when test="${loggedIn}">
                        <a href="${logoutUrl}">Logout</a>
                    </c:when>
                    <c:otherwise>                         
                        <a href="${loginUrl}">Login</a>
                    </c:otherwise>
                </c:choose>
                
               
            </div>
        </div>
    </div>
</div>