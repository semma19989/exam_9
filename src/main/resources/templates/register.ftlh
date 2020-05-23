<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <h2>Please register</h2>
    <div class="mx-auto mt-4 w-70">
    <form action="/register" method="post">

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>

        <div>
            <label class="flex flex-v-center">
                <p class="label-input-text">Имя:</p>
                <input type="text" name="name" placeholder="ваше имя"  value="${(dto.name)!''}">
            </label>
        </div>
        <div>
            <label class="flex flex-v-center">
                <p class="label-input-text">Email address:</p>
                <input type="text" name="email" placeholder="ваш адрес почты"  value="${(dto.email)!''}" >
            </label>
        </div>
        <div>
            <label class="flex flex-v-center">
                <p class="label-input-text">Password:</p>
                <input type="text" name="password" placeholder="пароль"  value="">
            </label>
        </div>
        <div>
            <button type="submit">Registration</button>
        </div>
        <div>
            <#if errors?? >
                <p>Вinput data with errors!</p>
                <#list errors as fieldError>
                    <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                </#list>
            </#if>
        </div>
    </form>
    </div>
    <div class="bottom-box">
        <a href="/">On the homepage</a>
    </div>
</@main.renderWith>