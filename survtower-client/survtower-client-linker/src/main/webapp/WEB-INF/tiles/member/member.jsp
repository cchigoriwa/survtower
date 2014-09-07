<%@ include file="/WEB-INF/utils/includes.jsp" %>


<h2>Member DashBoard</h2>

<table>
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