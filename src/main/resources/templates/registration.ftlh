<#import "parts/common.ftlh" as c>
<#import "parts/loginPart.ftlh" as l>

<@c.page>

    ${message!""}

    <@l.login "/registration" />
</@c.page>