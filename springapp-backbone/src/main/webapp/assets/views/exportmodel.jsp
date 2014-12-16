<%@ taglib prefix="mapper" uri="/WEB-INF/tld/json.tld"%>
<% String jsonstr=com.app.service.JSONUtil.model2Json(request); %> 
<%= jsonstr %>
