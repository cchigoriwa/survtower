<%@ include file="/WEB-INF/utils/includes.jsp" %>

<h4>Reset Password</h4>

<p>To verify your password, please enter it once in each field below.</p>
<p></p>


<form:form modelAttribute="appUserUtil" method="post" class="form-horizontal" id="user-form">
    <table>
        <tr>
            <th>
                Password: <form:errors path="password" cssClass="errors"/>
                <br/>
                <form:password path="password" size="30" maxlength="30"/>
            </th>
        </tr>

        <tr>
            <th>
                Confirm Password: <form:errors path="confirmPassword" cssClass="errors"/>
                <br/>
                <form:password path="confirmPassword" size="30" maxlength="30"/>
            </th>
        </tr>

        <tr>
            <td>
                <p class="submit"><input type="submit" value="Change Password"/></p>
            </td>
        </tr>
    </table>
</form:form>    