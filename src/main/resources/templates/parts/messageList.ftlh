<#include "security.ftlh">
<div class="card-columns" id="message-list">
    <#list messageList as message>
        <div class="card my-3" data-id="${message.id}">
            <div>
                <#if message.getFileName()??>
                    <img src="/img/${message.getFileName()}" class="card-img-top"/>
                </#if>
            </div>
            <div class="m-3">
                <span>${message.getText()}</span><br/>
                <i>#${message.getTag()}</i>
            </div>
            <div class="card-footer text-muted container">
                <div class="row">
                    <a class="col align-self-center" href="/user-messages/${message.author.id}">
                        ${message.getAuthorName()}
                    </a>
                    <#if message.author.id == currentUserId>
                        <a class="col btn btn-primary"
                           href="/user-messages/${message.author.id}?message=${message.id}">Edit</a>
                    </#if>
                </div>
            </div>
        </div>
    <#else>
        No messages
    </#list>
</div>