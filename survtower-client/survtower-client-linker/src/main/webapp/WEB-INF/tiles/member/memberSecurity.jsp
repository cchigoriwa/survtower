<%@ include file="/WEB-INF/utils/includes.jsp" %>

<spring:url value="/webjars/sadc/1.0.0/img/${memberSecurity.member.logo}" var="memberlogoImg" />


<div class="row">

    <h2>Member Security</h2>

    <table>
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
        <input type="submit" onclick="return confirm('Are you sure you want to do that?');" value="Generate Member Key" />
    </form:form>

</div>