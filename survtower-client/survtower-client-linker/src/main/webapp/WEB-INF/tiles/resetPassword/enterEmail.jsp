<%@ include file="/WEB-INF/utils/includes.jsp" %>

<c:if test="${error!=null}">
    <div class="alert alert-error">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        ${error}
    </div>
</c:if>


<form method="post" class="form-horizontal" id="user-form">
    <fieldset>  
        <legend>Password Reset Request</legend>  
        <div class="control-group">
            <label class="control-label">Email Address</label>

            <div class="controls">
                <input name="emailAddress" type="text" />
                <span class="help-inline">${status.errorMessage}</span>
            </div>
        </div>
        <div class="form-actions">  
            <button type="submit" class="btn btn-primary">Request</button>
        </div>  
    </fieldset>  
</form>   