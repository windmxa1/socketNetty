package com.siyann.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import com.alibaba.druid.pool.DruidPooledConnection;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DBUtils {
	/**
	 * 修改线路状态
	 */
	public static void updateStatus(int status, String host) {
		Statement ptmt = null;
		DBPoolConnection dbp = null;
		DruidPooledConnection conn = null;
		try {
			dbp = DBPoolConnection.getInstance();
			conn = dbp.getConnection();
			conn.setAutoCommit(false);
			String sql = "update z_gx_host set status=" + status
					+ " where host='" + host + "'";
			ptmt = conn.createStatement();
			ptmt.executeUpdate(sql);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ptmt, conn);
		}
	}

	/**
	 * 插入报警
	 */
	public static void insertAlarm(String sb, String host, int index) {
		Statement ptmt = null;
		DBPoolConnection dbp = null;
		DruidPooledConnection conn = null;
		try {
			dbp = DBPoolConnection.getInstance();
			conn = dbp.getConnection();
			conn.setAutoCommit(false);
			ptmt = conn.createStatement();
			if (index == 0) {
				insertShortDistanceAlarm(sb, host, ptmt, conn);
			} else {
				insertLongDistanceAlarm(sb, host, ptmt);
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ptmt, conn);
		}
	}

	/**
	 * 插入长距报警
	 */
	private static void insertLongDistanceAlarm(String sb, String host,
			Statement ptmt) throws Exception {
		ObjectMapper mapper = JsonUtils.getInstance();
		LongDistance ab = mapper.readValue(sb.toString(), LongDistance.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = sdf.parse(ab.getOccur_time()).getTime() / 1000;
		String sql = "insert into z_alarm(occur_time,distance,description,level,ack,last_time,host) values('"
				+ time
				+ "' ,'"
				+ ab.getDistance()
				+ "','"
				+ AlarmUtil.getDescription(ab.getAlarm_level())
				+ "','"
				+ ab.getAlarm_level()
				+ "','"
				+ 0
				+ "','"
				+ ab.getLast_time()
				+ "','" + host + "')";
		ptmt.executeUpdate(sql);
	}

	/**
	 * 插入短距报警
	 */
	private static void insertShortDistanceAlarm(String sb, String host,
			Statement ptmt, DruidPooledConnection conn) throws Exception {
		ObjectMapper mapper = JsonUtils.getInstance();
		ShortDistance ab = mapper.readValue(sb.toString(), ShortDistance.class);
		String name = "防区" + ab.getAlarm_zone();
		String sql = "select name from z_alarm_zone where host=\"" + host
				+ "\" and zone=\"" + ab.getAlarm_zone() + "\"";
		ptmt = conn.createStatement();
		ResultSet rs = ptmt.executeQuery(sql);
		String r = "";
		while (rs.next()) {
			r = rs.getString(1);
		}
		if (r != null && !"".equals(r)) {
			name = r;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = sdf.parse(ab.getAlarm_time()).getTime() / 1000;
		sql = "insert into z_alarm(occur_time,description,ack,host) values('"
				+ time + "','" + name + " "
				+ AlarmUtil.getAlarmPattern(ab.getAlarm_pattern()) + "','" + 0
				+ "','" + host + "')";
		ptmt.executeUpdate(sql);
	}

	/**
	 * 插入异常记录,先判断30秒内是否有数据，有就更新时间，没有就插入新记录
	 */
	public static void insertException(String msg, String host) {
		Statement ptmt = null;
		DBPoolConnection dbp = null;
		DruidPooledConnection conn = null;
		try {
			dbp = DBPoolConnection.getInstance();
			conn = dbp.getConnection();
			conn.setAutoCommit(false);
			Long time = System.currentTimeMillis() / 1000;
			String queryId = "select id from z_exception where host = '" + host
					+ "' and time>" + (time - 30)
					+ " order by id desc limit 0,1";
			ptmt = conn.createStatement();
			ResultSet rs = ptmt.executeQuery(queryId);
			String id = "";
			while (rs.next()) {
				id = rs.getString(1);
			}
			String sql = "";
			if (id == null || id.trim().equals("")) {
				sql = "insert into z_exception(time,ack,description,host) values('"
						+ time + "' ,'" + 0 + "','" + msg + "','" + host + "')";

			} else {
				sql = "update z_exception set time = " + time
						+ ",ack=0 where id=" + id;
			}
			ptmt.executeUpdate(sql);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close(ptmt, conn);
		}
	}

	public static void close(Statement ptmt, DruidPooledConnection conn) {
		if (ptmt != null) {
			try {
				ptmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
