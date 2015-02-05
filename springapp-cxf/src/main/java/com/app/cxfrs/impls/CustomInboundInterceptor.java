package com.app.cxfrs.impls;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.JAXRSServiceImpl;
import org.apache.cxf.jaxrs.impl.MetadataMap;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.jaxrs.utils.HttpUtils;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.Service;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.AuthoriseScope;



public class CustomInboundInterceptor extends AbstractPhaseInterceptor<Message>{
	
	
	Logger logger = LoggerFactory.getLogger(CustomInboundInterceptor.class);	

	public CustomInboundInterceptor(String phase) {
		super(Phase.PRE_INVOKE);
		// TODO Auto-generated constructor stub
	}
	
	public CustomInboundInterceptor() {
		super(Phase.PRE_INVOKE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		logger.debug(getClass().getName()+ ":: handle message interceptor ");
		HttpServletRequest httpServletRequest = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);		
		String innerTransactionName = (String) message.get(Message.REQUEST_URI);
		List<Object> params = message.getContent(List.class);
		Set<Class<?>> formats = message.getContentFormats();
		for(Class format:formats)
		{
			logger.debug("Format types :: "+format.getClass() +" :: "+message.getContent(format));
		}
		
//		OperationResourceInfo ori = message.getExchange().get(OperationResourceInfo.class);
//	    Method method = ori.getMethodToInvoke();
//	    Class<?>[] types = method.getParameterTypes();
//	    Type[] genericParameterTypes = method.getGenericParameterTypes();

//	    for (int i = 0; i < types.length; i++)
//	    {
//	        Class<?> type = types[i];
//	        List obj = (List) message.getContent(List.class);
//	        System.out.println(obj);
//	        System.out.println(type);
//	    }
		
		logger.debug(getClass().getName()+ innerTransactionName);
		String resourceUri = (String) message.get(Message.REQUEST_URI);
		String httpMethod = (String) message.get(Message.HTTP_REQUEST_METHOD);
		
		message.getExchange().put("encoding", "SET");

		logger.debug(getClass().getName()+ resourceUri);
		logger.debug(getClass().getName()+ httpMethod);

		try {
			Method methodToBeInvoked = getMethodToBeInvoked(message);
			if (methodToBeInvoked == null) {
				logger.debug(getClass().getName() + ": An error occured or there is not a resource matching the requested URI (404 HTTP code) so an inner CAL transaction won't be created");
				// There is not a resource matching the requested URI. Leaving
				// to the JAX-RS framework to return a 404 HTTP code
				return;
			} else {
				
				  
                      for (Annotation anno : methodToBeInvoked.getDeclaredAnnotations()) {
                          System.out.println("Annotation in Method '"
                                  + methodToBeInvoked + "' : " + anno);
                          
                          
                      }
                      
                      AuthoriseScope scopeAnnotation = methodToBeInvoked.getAnnotation(AuthoriseScope.class);
                      String[] scopes = scopeAnnotation.scopes();
                      
                  	logger.debug("Valid scopes "+scopes.toString());		

				
			}
		} catch (Exception e) {
			String errorMessage = getClass().getName() + ": It was not possible to define CAL transaction name policy due to: " + e.getMessage();
			logger.debug(errorMessage, e);
		}
		

		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Method getMethodToBeInvoked(Message message) 
	{	
		OperationResourceInfo operationalResource = getOperationResourceInfo(message);
		return operationalResource.getAnnotatedMethod();
	}
	
	/**
	 * Returns null if there is not a resource matching the requested URI
	 */
	private OperationResourceInfo getOperationResourceInfo(Message message) {
		Exchange exchange = message.getExchange();
		Service service = exchange.getService();
		MultivaluedMap<String, String> values = new MetadataMap<String, String>();

		ClassResourceInfo classResourceInfo = getClassResourceInfo(message,
				service, values);

		if (classResourceInfo == null) {
			return null;
		}

		String httpMethod = HttpUtils.getProtocolHeader(message,
				Message.HTTP_REQUEST_METHOD, "POST");
		String requestContentType = getRequestContentType(message);
		List<MediaType> acceptContentTypes = getAcceptContentTypes(message);

		OperationResourceInfo operationResourceInfo = null;
		try {
			operationResourceInfo = JAXRSUtils.findTargetMethod(
					classResourceInfo, message, httpMethod, values,
					requestContentType, acceptContentTypes, false);
		} catch (ClientErrorException e) {
			// This means that the method could not be found due to the a URI
			// that does not match to any resource, or any of the methods
			// applied over a resource
			return null;
		}

		return operationResourceInfo;
	}

	private ClassResourceInfo getClassResourceInfo(Message message,
			Service service, MultivaluedMap<String, String> values) {
		List<ClassResourceInfo> resources = ((JAXRSServiceImpl) service)
				.getClassResourceInfos();
		String rawPath = HttpUtils.getPathToMatch(message, true);
		ClassResourceInfo classResourceInfo = JAXRSUtils.selectResourceClass(
				resources, rawPath, values, message);

		return classResourceInfo;
	}

	private String getRequestContentType(Message message) {
		String requestContentType = (String) message.get(Message.CONTENT_TYPE);
		if (requestContentType == null) {
			requestContentType = "*/*";
		}

		return requestContentType;
	}

	private List<MediaType> getAcceptContentTypes(Message message) {
		String acceptTypes = HttpUtils.getProtocolHeader(message,
				Message.ACCEPT_CONTENT_TYPE, null);
		if (acceptTypes == null) {
			acceptTypes = "*/*";
		}

		List<MediaType> acceptContentTypes = null;
		try {
			acceptContentTypes = JAXRSUtils.sortMediaTypes(acceptTypes);
		} catch (IllegalArgumentException ex) {
			throw new NotAcceptableException();
		}

		return acceptContentTypes;
	}


}
