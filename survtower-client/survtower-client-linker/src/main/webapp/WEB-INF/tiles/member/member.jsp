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
            <th>Email Address</th>
            <td>${memberSecurity.emailAddress}</td>
        </tr>
        <tr>
            <th>Member ID</th>
            <td>${memberSecurity.memberID}</td>
        </tr>

        <tr>
            <th>Member Key</th>
            <td>${memberSecurity.memberKey}</td>
        </tr>
    </table>

    <spring:url value="/member/memberkey/generate" var="generateMemberKeyUrl" />

    <form:form method="post" action="${generateMemberKeyUrl}">
        <input type="submit" value="Generate Member Key" />
    </form:form>

</div>