<#import "parts/common.ftlh" as c>
<#import "parts/loginPart.ftlh" as l>

<@c.page>
<#--<div th:if="${param.error}">
    Invalid username and password
    <br><br>
</div>
<div th:if="${param.logout}">
    You have been logged out
    <br><br>
</div>-->

    <div class="mb-3">
        <h5>Login page</h5>
    </div>
    <@l.login "/login" false/>
</@c.page>