<#import "partials/main.ftlh" as main />

<@main.renderWith>
<h2>Please enter email to reset password</h2>
<div>
    <form action="/forgot-password" method="post">

        <#if _csrf??>
            <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
        </#if>

        <div>
            <label class="flex flex-v-center">
                <p class="label-input-text">Адрес почты:</p>
                <input type="text" name="email" placeholder="ваш адрес почты" >
            </label>
        </div>
        <div>
            <button type="submit">Отправить</button>
        </div>
        <div>
            <#if errors?? >
                <p>Input data with errors!</p>
                <#list errors as fieldError>
                    <p>${fieldError.field} - ${fieldError.defaultMessage!'-- no message --'}</p>
                </#list>
            </#if>
        </div>
    </form>