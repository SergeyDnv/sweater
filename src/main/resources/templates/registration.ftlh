<#import "parts/common.ftlh" as c>
<#import "parts/loginPart.ftlh" as l>

<@c.page>

    ${message!""}

    <div class="mb-3">
        <h5>Registration</h5>
    </div>
    <@l.login "/registration" true/>
</@c.page>