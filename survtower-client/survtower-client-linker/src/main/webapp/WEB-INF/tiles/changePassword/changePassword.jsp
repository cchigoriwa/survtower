<%@ include file="/WEB-INF/utils/includes.jsp" %>

<div class="row">

<h4>Change Password</h4>


<form:form modelAttribute="appUserUtil" method="post" >
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
</div>