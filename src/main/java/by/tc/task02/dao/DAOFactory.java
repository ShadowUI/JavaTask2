package by.tc.task02.dao;

import by.tc.task02.dao.impl.XmlParserDAOImpl;

public final class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private final XmlParserDAO parserDAO = new XmlParserDAOImpl();

	private DAOFactory() {}

	public XmlParserDAO getParserDAO() {
		return parserDAO;
	}

	public static DAOFactory getInstance() {
		return instance;
	}
}
