<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-8">
            <form method="GET" action="/main" class="form-inline">
                <input type="text" class="form-control" name="searchTag" value="${searchTag}"
                       placeholder="Введите тег для поиска">
                <button type="submit" class="btn btn-primary ml-2">Поиск по тегу</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#addNewMessage" role="button" aria-expanded="false"
       aria-controls="addNewMessage">
        Add new message
    </a>
    <div class="collapse" id="addNewMessage">
        <div class="form-group mt-3">
            <form method="POST" action="/main" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" name="text" class="form-control" placeholder="Введите сообщение">
                </div>
                <div class="form-group">
                    <input type="text" name="tag" class="form-control" placeholder="Введите тег">
                </div>
                <div class="custom-file">
                    <input type="file" class="custom-file-input" name="file">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
                <div class="form-group">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </div>
                <button type="submit" class="btn btn-primary">Добавить</button>
            </form>
        </div>
    </div>

    <div class="card-columns">
        <#list messageList as message>
            <div class="card my-3">
                <div>
                    <#if message.getFileName()??>
                        <img src="/img/${message.getFileName()}" class="card-img-top">
                    </#if>
                </div>
                <div class="m-3">
                    <span>${message.getText()}</span>
                    <i>${message.getTag()}</i>
                </div>
                <div class="card-footer text-muted">
                    ${message.getAuthorName()}
                </div>
            </div>
        <#else>
            No messages
        </#list>
    </div>
</@c.page>