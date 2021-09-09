<#macro login path>
    <form method="POST" action="${path}">
        <div>
            <label> Username: <input type="text" name="username"/></label>
        </div>
        <div>
            <label> Password: <input type="text" name="password"/></label>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div>
            <input type="submit" value="Submit"/>
        </div>
    </form>
</#macro>

<#macro logout>
    <form method="POST" action="/logout">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Logout">
    </form>
</#macro>