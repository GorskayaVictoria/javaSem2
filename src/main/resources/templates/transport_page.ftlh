<#include 'base.ftlh'/>

<#macro content>
<link rel="stylesheet" href="/css/product_page_style.css">

<div class="content">
    <div class="product">
        <img src="/img/${transport.filePath}" class="card-img1" alt="${transport.name}" >
        <h5 class="card-title">${transport.name}</h5>
        <small class="card-title">${transport.info}</small>
        <small class="card-title">${transport.city}</small>
    </div>
    <div class="buy-button">
        <a href="/order/${transport.id}" class="btn btn-secondary buy-button">
            <div class = "whiteRent"><@spring.message 'transport_page.page.rent'/></div></a>
    </div>
</div>
    <div class="reviews">
        <form method="post" action="/addComment/${transport.id}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="text" name="text" id="text" placeholder="Send review">
            <div>
                <button type="submit" class="btn btn-secondary">Send review</button>
            </div>
        </form>

				<div class="all_reviews">
                    <h2> ОТЗЫВЫ: </h2>
					<#list messages as mes>
					<div class="review">
                        <h5>${mes.user.name} </h5>
                        <text>${mes.text}</text>
                    </div>
                    </#list>
                </div>
    </div>
</#macro>
    <@main/>