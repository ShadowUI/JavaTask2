package by.tc.task02.dao;

import by.tc.task02.dao.exeption.DAOException;
import by.tc.task02.entity.Tag;

public interface XmlParserDAO {
	Tag parse() throws DAOException;
}
