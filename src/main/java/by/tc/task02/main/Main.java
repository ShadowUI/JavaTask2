package by.tc.task02.main;

import by.tc.task02.entity.ComplexTag;
import by.tc.task02.entity.Tag;
import by.tc.task02.service.XmlParserService;
import by.tc.task02.service.ServiceFactory;
import by.tc.task02.service.exeption.ServiceException;

public class Main {
	public static void main(String...args) throws ServiceException {
		try {
			Tag parentTag;
			ServiceFactory factory = ServiceFactory.getInstance();
			XmlParserService parseService = factory.getXmlParserService();

			parentTag = parseService.parse();
			InfoPrinter.print((ComplexTag) parentTag);

		} catch (ServiceException e) {
			throw new ServiceException(e);
		}
	}
}
