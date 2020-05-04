
<#include 'base.ftlh'/>

<#macro content>
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script>
        var webSocket;
        function connect() {
            webSocket = new WebSocket('ws://localhost:8080/chat1');

            document.cookie = 'X-Authorization=' + '12345' + ';path=/';

            webSocket.onmessage = function receiveMessage(response) {
                let data = response['data'];
                let json = JSON.parse(data);
                $('#messagesList').append("<div class=\"review\">" +  json['name'] + ': ' + json['text']+ "</div>")
            }
        }

        function sendMessage(text, pageId, pageName) {
            let message = {
                "text": text,
                "from": pageId,
                "name": pageName
            };

            webSocket.send(JSON.stringify(message));
        }
    </script>

<link rel="stylesheet" href="/css/product_page_style.css">

<body onload="connect()">
<div class="content">



    <div class="reviews">
        <h3>ТЕКСТ СООБЩЕНИЯ</h3>
        <label for="message"></label>
        <input name="message" id="message" placeholder="Сообщение">
        <button class="btn btn-secondary submit_button" onclick="sendMessage($('#message').val(), '${pageId}', '${pageName}')">Отправить</button>

    <h3>Сообщения</h3>
    <div class="all_reviews">


            <ul id="messagesList">

            </ul>


    </div>
</div>
</div>
</div>
</body>
</#macro>
<@main/>