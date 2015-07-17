<%@ include file="/WEB-INF/utils/includes.jsp" %>

<h4>Reset Password</h4>

<p>To verify your password, please enter it once in each field below.</p>
<p></p>


<form:form modelAttribute="appUserUtil" method="post" class="form-horizontal" id="user-form">
    <transunion:passwordField label="Enter new password" name="password" />
    <transunion:passwordField label="Re-enter new password" name="confirmPassword" />
    <div class="form-actions">
        <button type="submit">Submit</button>
    </div>
</form:form>    