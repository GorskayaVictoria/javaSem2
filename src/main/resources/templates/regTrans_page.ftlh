<#include 'base.ftlh'/>

<#macro content>
    <link rel="stylesheet" href="/css/login_page_style.css" type="text/css">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <div class="registration_window">
        <form method="POST" enctype="multipart/form-data" novalidate>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="form_row">
                <div class="form_field2">
                    <label for="name">Name<br></label>
                    <input class="field" type="text" name="name" >
                </div>
                <div class="form_field2">
                    <label for="email">city<br></label>
                    <input class="field" type="text" name="city"  >
                </div>
            </div>

            <div class="form_row">
                <div class="form_field2">
                    <label for="txtNewPassword">info<br></label>
                    <input class="field" type="text" name="info"  >
                </div>
                <div class="form_field2">
                    <label for="txtConfirmPassword"> year<br></label>
                    <input class="field" type="number" name="year" >
                </div>

            </div>
            <div id="filename"></div>

            <div>
                <button type="submit" class="btn btn-secondary submit_button">Submit</button>
            </div>
        </form>


        <div class="form_row">
            <div>

                <input type="file" id="file" name="file" placeholder="Имя файла..."/>

                <button type="submit"  onclick="sendFile()" >загрузка</button>

            </div>

        </div>
    </div>

    <script>
        function sendFile() {
            // данные для отправки
            let formData = new FormData();
            // забрал файл из input
            let files = ($('#file'))[0]['files'];
            // добавляю файл в formData
            [].forEach.call(files, function (file, i, files) {
                formData.append("file", file);
            });

            $.ajax({
                type: "POST",
                url: "/files",
                headers: {"X-CSRF-TOKEN": $("input[name='_csrf']").val()},
                data: formData,
                processData: false,
                contentType: false
            })
                .done(function (response) {
                    let fileUrl =  ""+ response;
                    $('#filename').empty();
                    $('#filename').append('<input type="hidden" name="filePath" value="'+ fileUrl + '">');
                })
                .fail(function () {
                    alert(formData)
                })

        }
    </script>


<#--    <script type="application/javascript">-->
<#--        function f() {-->
<#--            if ($("#query").val().length >= 0) {-->
<#--                $.ajax({-->
<#--                    url: "/searchUsers",-->
<#--                    data: {"name": $("#query").val()},-->
<#--                    dataType: "json",-->
<#--                    success: function (msg) {-->
<#--                        if (msg.length >= 1) {-->
<#--                            $("#res").html("");-->
<#--                            $("#res").append("<tr>" + "\n")-->
<#--                            $("#res").append("<th> Имя </th>" + "\n");-->
<#--                            $("#res").append("<th> Email </th>" + "\n");-->
<#--                            $("#res").append("</tr>" + "\n")-->
<#--                            for (var i = 0; i < msg.length; i++) {-->
<#--                                $("#res").append("<tr>" + "\n")-->
<#--                                $("#res").append("<td>" + msg[i].name + "</td>" + "\n");-->
<#--                                $("#res").append("<td>" + msg[i].email + "</td>" + "\n");-->
<#--                                $("#res").append("</tr>" + "\n")-->
<#--                            }-->
<#--                        } else {-->
<#--                            $("#res").html("No results..");-->
<#--                        }-->
<#--                    }-->
<#--                })-->
<#--            }-->
<#--            else {-->
<#--                $("#res").html("");-->
<#--            }-->
<#--        }-->
<#--    </script>-->


</#macro>
<@main/>