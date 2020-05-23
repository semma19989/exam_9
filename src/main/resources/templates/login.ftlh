<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <h2>Please sign in</h2>
    <form action="/login" method="post">

        <div>
            <#if error!false >
                <p style="color:red">Invalid username or password !</p>
            </#if>
        </div>

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>

        <div>
            <label class="flex flex-v-center ">
                <span class="label-input-text">Адрес почты:</span>
                <input type="text" name="username" placeholder="ваш адрес почты"  value="${(dto.email)!''}" autofocus >
            </label>
        </div>
        <div>
            <label class="flex flex-v-center label-input-text">
                <span class="label-input-text">Пароль:</span>
                <input type="text" name="password" placeholder="пароль" value="">
            </label>
        </div>
        <div>
            <button type="submit"></a>Войти!</button>
        </div>
    </form>
    <div class="bottom-box">
        <a href="/">On the homepage</a>
    </div>
    <div class="bottom-box">
        <a class="flex box-128" href="/forgot-password"> forget your password </a>
    </div>
</@main.renderWith>