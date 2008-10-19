package eu.sergiobelli.gebib.model.orm.data;

import java.net.URL;

//import org.sbelli.gecomp.utils.exceptions.GeCompExceptionManager;
//import org.sbelli.gecomp.utils.logger.GeCompLogger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import eu.sergiobelli.gebib.exception.GeBibExceptionManager;
import eu.sergiobelli.gebib.util.logger.GeBibLogger;

public class IBatisSessionHandler {

	private static SqlMapClient sqlMapClient;
	private static IBatisSessionHandler instance;
	public static IBatisSessionHandler getInstance () {
		if (instance == null) {
			instance = new IBatisSessionHandler();
		}
		return instance;
	}
	
	private static final String SQL_MAP_CONFIG = "SqlMapConfig.xml";
	private IBatisSessionHandler() {
		
		logger.info("Trying to get IBatis configuration...");
		try {
			if (sqlMapClient == null) {
				
				URL url = ClassLoader.getSystemResource(SQL_MAP_CONFIG);
				logger.debug("url = " + url.getPath());
				
				sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(url.openStream());
			}
		} catch (Exception e) {
			GeBibExceptionManager.manageException(logger, e);
			throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
		}
		logger.info("Trying to get IBatis configuration = DONE");
		
	}
	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}
	
	protected final GeBibLogger logger = new GeBibLogger(this.getClass().getName());
	
}
