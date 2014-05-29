package com.app.cxfrs.impls;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.JAXRSServiceImpl;
import org.apache.cxf.jaxrs.impl.MetadataMap;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.jaxrs.utils.HttpUtils;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageContentsList;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.service.Service;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.log4j.Logger;

public class CustomOutboundInterceptor extends AbstractPhaseInterceptor<Message>{
	
	private Logger logger = Logger.getLogger(CustomOutboundInterceptor.class);
	
	public CustomOutboundInterceptor() {
		super(Phase.PRE_MARSHAL);
		
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		logger.debug(getClass().getName()+ ":: handle message interceptor ");
		HttpServletRequest httpServletRequest = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
		HttpServletResponse httpServletResponse = (HttpServletResponse) message.get(AbstractHTTPDestination.HTTP_RESPONSE);
		String innerTransactionName = (String) message.get(Message.REQUEST_URI);
		logger.debug(getClass().getName()+ innerTransactionName);
		String resourceUri = (String) message.get(Message.REQUEST_URI);
		String httpMethod = (String) message.get(Message.HTTP_REQUEST_METHOD);

		logger.debug(getClass().getName()+ resourceUri);
		logger.debug(getClass().getName()+ httpMethod);
		  MessageContentsList objs = MessageContentsList.getContentsList(message);
	        if (objs == null || objs.size() == 0) {
	            return;
	        }
	        
	        Object responseObj = objs.get(0);
	        
	        Response response = null;
	        if (responseObj instanceof Response) {
	            response = (Response)responseObj;
	        } 
	        
	        logger.debug(getClass().getName()+"::: exchange message :: encoding is " +message.getExchange().get("encoding"));  
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Method getMethodToBeInvoked(Message message) 
	{		
		return getOperationResourceInfo(message).getAnnotatedMethod();
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
