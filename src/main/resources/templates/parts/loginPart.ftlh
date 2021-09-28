<#macro login path isRegistration>
    <form method="POST" action="${path}">
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Username: </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control ${(usernameError??)?string('is-invalid', '')}"
                       value="<#if user??>${user.username}</#if>" placeholder="User name"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Password: </label>
            <div class="col-sm-6">
                <input type="text" name="password" class="form-control ${(passwordError??)?string('is-invalid', '')}"
                       placeholder="Password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegistration>
            <div class="form-group row">
                <label class="col-sm-1 col-form-label"> Password confirmation: </label>
                <div class="col-sm-6">
                    <input type="text" name="passwordConfirmation"
                           class="form-control ${(passwordConfirmationError??)?string('is-invalid', '')}"
                           placeholder="Password confirmation"/>
                    <#if passwordConfirmationError??>
                        <div class="invalid-feedback">
                            ${passwordConfirmationError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-1 col-form-label"> Email: </label>
                <div class="col-sm-6">
                    <input type="email" name="email" class="form-control ${(emailError??)?string('is-invalid', '')}"
                           value="<#if user??>${user.email}</#if>" placeholder="Email"/>
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
        </#if>
        <hr>
        <#if !isRegistration>
            <a href="/registration">Registration</a>
        </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</#macro>

<#macro logout>
    <form method="POST" action="/logout">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Logout</button>
    </form>
</#macro>