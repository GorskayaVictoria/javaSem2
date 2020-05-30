<#include 'base.ftlh'/>

<#macro content>
<link rel="stylesheet" href="/css/login_page_style.css" type="text/css">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
<div class="registration_window">
    <form method="POST" enctype="multipart/form-data" novalidate>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <div class="form_row">
            <div class="form_field2">
                <label for="number">Ваш телефон для связи<br></label>
                <input class="field" type="number" name="number" >
            </div>
        </div>
        <div>
            <button type="submit" class="btn btn-secondary submit_button">Submit</button>
        </div>
    </form>

</div>


</#macro>
<@main/>