<#import "parts/common.ftlh" as c>
<#import "parts/loginPart.ftlh" as l>

<@c.page>
    <@l.logout/>
    <span><a href="user">Users</a></span>
    <hr>
    <form method="POST" action="/main" enctype="multipart/form-data">
        <input type="text" name="text" placeholder="Введите сообщение">
        <input type="text" name="tag" placeholder="Введите тег">
        <input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Добавить">
    </form>

    <h3>Список сообщений</h3>
    <form method="GET" action="/main">
        <input type="text" name="searchTag" value="${searchTag}" placeholder="Введите тег для поиска">
        <input type="submit" value="Поиск по тегу">
    </form>

    <#list messageList as message>
        <div>
            <b>${message.getId()}</b>
            <span>${message.getText()}</span>
            <i>${message.getTag()}</i>
            <strong>${message.getAuthorName()}</strong>
            <div>
                <#if message.getFileName()??>
                    <img src="/img/${message.getFileName()}">
                </#if>
            </div>
        </div>
    <#else>
        No messages
    </#list>
</@c.page>