<a class="btn btn-primary" data-toggle="collapse" href="#addNewMessage" role="button" aria-expanded="false"
   aria-controls="addNewMessage">
    Edit message
</a>
<div class="collapse <#if message??>show</#if>" id="addNewMessage">
    <div class="form-group mt-3">
        <form method="POST" action="/main" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" name="text" class="form-control ${(textError??)?string('is-invalid', '')}"
                       value="<#if message??>${message.text}</#if>" placeholder="Введите сообщение"/>
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" name="tag" class="form-control ${(tagError??)?string('is-invalid', '')}"
                       value="<#if message??>${message.tag}</#if>" placeholder="Введите тег"/>
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>
            <div class="custom-file">
                <input type="file" class="custom-file-input" name="file"/>
                <label class="custom-file-label" for="customFile">Choose file</label>
            </div>
            <div class="form-group">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="hidden" name="id" value="<#if message??>${message.id}</#if>"/>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
        </form>
    </div>
</div>