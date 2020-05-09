
<#include 'base.ftlh'/>


<style>
	.error {
		color: #ff0000;
	}
</style>
<#macro content>
	<link rel="stylesheet" href="/css/login_page_style.css" type="text/css">


		<div class="registration_window">
			<@spring.bind "regForm"/>

			<form  action="/signUp" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				<div>
					<div class="form_row">
						<div class="form_field2">
							<@spring.message 'registration_page.page.name'/>: <br>
							<@spring.formInput "regForm.name"/>
							<@spring.showErrors "<br>","error"/>
							<br><br>

						</div>
						<div class="form_field2">
							<@spring.message 'registration_page.page.email'/>: <br>
							<@spring.formInput "regForm.email"/>
							<@spring.showErrors "<br>","error"/>
							<br><br>
						</div>
					</div>

					<div class="form_row">
						<div class="form_field2">
							<@spring.message 'registration_page.page.password'/>: <br>
							<@spring.formInput "regForm.password"/>
							<@spring.showErrors "<br>","error"/>

							<br><br>
						</div>



						<div class="form_field2">
							<@spring.message 'registration_page.page.password'/>: <br>
							<@spring.formInput "regForm.confirmPassword"/>
							<@spring.showErrors "<br>","error"/>
							<br><br>
						</div>





					</div>


					<div>
						<button type="submit" class="btn btn-secondary submit_button"><@spring.message 'registration_page.page.submit'/></button>
					</div>
				</div>
			</form>
		</div>

</#macro>
<@main/>