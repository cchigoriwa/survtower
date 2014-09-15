<%@ include file="/WEB-INF/utils/includes.jsp" %>
<spring:url value="/webjars/sadc/1.0.0/img/logo.png" var="centralLogoImg" />
<div class="contain-to-grid fixed">
    <nav class="top-bar" data-topbar role="navigation"> 
        <ul class="title-area"> 
            <li class="name"> 
                <h1>
                    <a href="${memberDashboardUrl}">Dashboard</a>
                </h1> 
            </li> 
            <!-- Remove the class "menu-icon" to get rid of menu icon. Take out "Menu" to just have icon alone --> 
            <li class="toggle-topbar menu-icon">
                <a href="#"><span>Menu</span></a>
            </li>
        </ul> 
        <section class="top-bar-section"> 
            <!-- Right Nav Section --> 
            <ul class="right"> 
                <li class="active">
                    <a href="#">Member State: Active</a>
                </li> 
                <li class="has-dropdown"> 
                    <a href="#">Security</a> 
                    <ul class="dropdown"> 
                        <li>
                            <a href="${memberSecurityUrl}">Member Security Details</a>
                        </li> 
                        <li>
                            <a href="${changePasswordUrl}">Change Password</a>
                        </li>
                        <li>
                            <a href="${logoutUrl}">Logout</a>
                        </li>
                    </ul> 
                </li> 
            </ul> 
            <!-- Left Nav Section --> 
            <ul class="left">
                <li>
                    <a href="#"></a>
                </li>
            </ul> 
        </section>
    </nav>
</div>

<div class="row">
    <img src="${centralLogoImg}" alt="Central-Logo" />
</div>
<div class="row">
    <div class="large-12 columns">
        <h1>SADC Surveillance External Portal</h1>
    </div>
</div>