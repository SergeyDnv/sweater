<#import "parts/common.ftlh" as c>

<@c.page>
    <form method="POST" action="/user">
        <input type="hidden" name="userId" value="${user.id}">
        <input type="text" name="userName" value="${user.username}">
        <#list roles as role>
            <div>
                <label><input type="checkbox"
                              name="${role}"
                              ${user.roles?seq_contains(role)?string("checked", "")}> ${role}
                </label>
            </div>
        </#list>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Edit">
    </form>
</@c.page>