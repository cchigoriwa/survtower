<%@ include file="/WEB-INF/utils/includes.jsp" %>

<spring:url value="/webjars/sadc/1.0.0/img/${memberSecurity.member.logo}" var="memberlogoImg" />


<div class="row">

    <h2>Member DashBoard</h2>

    <table>
        <tr>
            <th>&nbsp;</th>
            <td><img src="${memberlogoImg}" alt="Member-Logo" /></td>
        </tr>
        <tr>
            <th>Name</th>
            <td>${memberSecurity.member.name}</td>
        </tr>
        <tr>
            <th>Code</th>
            <td>${memberSecurity.member.code}</td>
        </tr>
       
    </table>

</div>