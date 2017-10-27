package by.tc.task02.service;

import by.tc.task02.service.impl.XmlParserServiceImpl;

public final class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private final XmlParserService xmlParserService = new XmlParserServiceImpl();

	private ServiceFactory() {}

	public XmlParserService getXmlParserService() {

		return xmlParserService;
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

}