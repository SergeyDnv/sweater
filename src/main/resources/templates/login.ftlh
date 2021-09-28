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

    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>

    <#if message??>
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    </#if>

    <div class="mb-3">
        <h5>Login page</h5>
    </div>
    <@l.login "/login" false/>
</@c.page>