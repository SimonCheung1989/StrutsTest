package com.simon.jdbc;

import java.io.Writer;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.sql.CLOB;

import org.apache.log4j.Logger;

import com.simon.util.CommonConstants;
import com.simon.util.WebLogicJndiUtil;

public class OracleTools {
	private static Logger logger = Logger.getLogger(OracleTools.class);

	private String user;
	private String pass;
	private String url;

	private Connection conn = null;// 连接对象
	private ResultSet rs = null;// 结果集对象
	private Statement sm = null;

	/**
	 * 构造函数获得数据库用户名和密码
	 * 
	 * @param user
	 * @param pass
	 */
	public OracleTools(String user, String pass) {
		this.user = user;
		this.pass = pass;
		this.url = CommonConstants.ORACLE_URL;
	}

	/**
	 * 连接数据库
	 * 
	 * @return
	 */
	public Connection createConnection() {
		String sDBDriver = "oracle.jdbc.driver.OracleDriver";
		try {
			Class.forName(sDBDriver).newInstance();
			conn = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			logger.debug("数据库连接失败");
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭数据库
	 * 
	 * @param conn
	 */
	public void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			logger.debug("数据库关闭失败");
			e.printStackTrace();
		}
	}

	/**
	 * 插入数据
	 * 
	 * @param insert
	 *            插入语句
	 * @return
	 */
	public int insert(String insert) {
		conn = createConnection();
		int re = 0;
		try {
			conn.setAutoCommit(false);// 事物开始

			sm = conn.createStatement();
			re = sm.executeUpdate(insert);
			if (re < 0) { // 插入失败
				conn.rollback(); // 回滚
				sm.close();
				closeConnection(conn);
				return re;
			}
			conn.commit(); // 插入正常
			sm.close();
			closeConnection(conn);
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeConnection(conn);
		return 0;
	}

	/**
	 * 查询语句 返回结果集
	 * 
	 * @param select
	 * @return
	 */
	public ResultSet selectSql(String select) {
		conn = createConnection();
		try {
			sm = conn.createStatement();
			rs = sm.executeQuery(select);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 根据结果集输出
	 * 
	 * @param rs
	 */
	public void printRs(ResultSet rs) {
		int columnsCount = 0;
		boolean f = false;
		try {
			if (!rs.next()) {
				return;
			}
			ResultSetMetaData rsmd = rs.getMetaData();
			columnsCount = rsmd.getColumnCount();// 数据集的列数
			for (int i = 0; i < columnsCount; i++) {
				logger.debug(rsmd.getColumnLabel(i + 1)); // 输出列名
			}
			while (!f) {
				for (int i = 1; i <= columnsCount; i++) {
					logger.debug(rs.getString(i));
				}
				if (!rs.next()) {
					f = true;
				}
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeConnection(conn);
	}

	public int insert2(String insert, String uuid, String ggnr) {
		// conn = createConnection();
		try {
			conn = WebLogicJndiUtil.getOracleConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		int re = 0;
		try {
			conn.setAutoCommit(false);// 事物开始

			sm = conn.createStatement();
			re = sm.executeUpdate(insert);

			if (re < 0) { // 插入失败
				conn.rollback(); // 回滚
				sm.close();
				closeConnection(conn);
				return re;
			} else {
				ResultSet rs = sm
						.executeQuery("select ggnr from ws_tzgg where uuid='"
								+ uuid + "' for update");
				Writer outStream = null;
				if (rs.next()) {
					// 得到java.sql.Clob对象后强制转换为oracle.sql.CLOB
					CLOB clob = null;
					if ("weblogic.jdbc.wrapper.Clob_oracle_sql_CLOB".equals(rs
							.getClob("ggnr").getClass().getName())) {
						Method method = rs.getClob("ggnr").getClass()
								.getMethod("getVendorObj", new Class[0]);
						clob = (CLOB) method.invoke(rs.getClob("ggnr"), null);
					} else {
						clob = (oracle.sql.CLOB) rs.getClob("ggnr");
					}
					outStream = clob.getCharacterOutputStream();
					// data是传入的字符串，定义：String data
					char[] c = ggnr.toCharArray();
					outStream.write(c, 0, c.length);
					outStream.flush();
					outStream.close();
				}
			}
			conn.commit(); // 插入正常
			sm.close();
			closeConnection(conn);
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		closeConnection(conn);
		return 0;
	}

}
