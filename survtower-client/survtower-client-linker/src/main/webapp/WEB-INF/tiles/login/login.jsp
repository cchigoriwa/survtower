<%@ include file="/WEB-INF/utils/includes.jsp" %>
<spring:url value="/request-password-reset" var="resetPasswordUrl"></spring:url>

    <div class="row">

        <div>
        <c:if test="${not empty param.login_error}">
            <span style="color:red">
                Your login attempt was not successful, try again.<br/><br/>
            </span>
        </c:if>
    </div>

    <h2>Member State Login</h2>
    <form action="<c:url value="/loginProcess" />" method="post">  
        <fieldset>
            <legend>Sign in</legend>  

            <div>
                <div>
                    <div>
                        <input type="text" name="j_username" id="j_username" value="" placeholder="Email Address" class="form-control login-field" />
                        <span></span>
                    </div>
                </div>            
            </div>
            <div>
                <div>
                    <div>
                        <input type="password" name="j_password" id="j_password" value="" placeholder="Password" class="form-control login-field" />
                        <span></span>
                    </div>
                </div>    
                <div>
                    &nbsp;<a href="${resetPasswordUrl}">Forgot Password?</a>
                </div>
            </div>

            <div>
                <div>
                    <button type="submit">Sign In</button>
                </div>

            </div>





        </fieldset>  
    </form>
</div>


