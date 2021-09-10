<#macro login path isRegistration>
    <form method="POST" action="${path}">
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Username: </label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="User name"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Password: </label>
            <div class="col-sm-6">
                <input type="text" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>
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