<#import "/spring.ftl" as spring />
<#assign home_url><@spring.url relativeUrl="${servletPath}/"/></#assign>
<#assign company_url><@spring.messageText code="company.url" text=companyUrl!"http://www.spring.io/"/></#assign>
<#assign company_name><@spring.messageText code="company.name" text=companyName!"Spring"/></#assign>
<div id="header">
	<div id="name-and-company">
		<div id='site-name'>
			<a href=${home_url} title="Site Name" rel="home">
				<@spring.messageText code="site.name" text=siteName!"SmartOffice Batch Admin"/>
			</a>
		</div>
		      
	</div> <!-- /name-and-company -->
</div> <!-- /header -->
