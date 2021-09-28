<#import "parts/common.ftlh" as c>
<#import "parts/loginPart.ftlh" as l>

<@c.page>

    <div class="mb-3">
        <h5>${user.username}</h5>
    </div>

    ${message!""}

    <form method="post">
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Password: </label>
            <div class="col-sm-6">
                <input type="text" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"> Email: </label>
            <div class="col-sm-6">
                <input type="email" name="email" class="form-control" placeholder="Email" value="${user.email!''}"/>
            </div>
        </div>
        <hr>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
</@c.page>