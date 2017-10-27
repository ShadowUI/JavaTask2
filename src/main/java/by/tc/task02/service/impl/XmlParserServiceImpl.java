package by.tc.task02.service.impl;

import by.tc.task02.dao.DAOFactory;
import by.tc.task02.dao.XmlParserDAO;
import by.tc.task02.dao.exeption.DAOException;
import by.tc.task02.entity.Tag;
import by.tc.task02.service.XmlParserService;
import by.tc.task02.service.exeption.ServiceException;

public class XmlParserServiceImpl implements XmlParserService {

	public Tag parse() throws ServiceException {
		try {
			DAOFactory factory = DAOFactory.getInstance();
			XmlParserDAO parserDAO = factory.getParserDAO();

			Tag tag = parserDAO.parse();

			return tag;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
