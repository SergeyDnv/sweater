<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-8">
            <form method="GET" action="/main" class="form-inline">
                <input type="text" class="form-control" name="searchTag" value="${searchTag!}"
                       placeholder="Введите тег для поиска"/>
                <button type="submit" class="btn btn-primary ml-2">Поиск по тегу</button>
            </form>
        </div>
    </div>

    <#include "parts/messageEdit.ftlh"/>

    <#include "parts/messageList.ftlh"/>
</@c.page>