package com.ejie.zz99.common;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

public class PreparedStatementSetter implements BatchPreparedStatementSetter {

	private List<Object[]> params;

	public PreparedStatementSetter(List<Object[]> params) {
		this.params = params;
	}

	@Override()
	public int getBatchSize() {
		return this.params.size();
	}

	@Override()
	public void setValues(PreparedStatement ps, int pos) throws SQLException {
		Object[] aux = this.params.get(pos);
		Integer i = 1;
		for (Object valor : aux) {
			this.setParam(ps, i++, valor);
		}
	}

	protected void setParam(PreparedStatement ps, Integer i, Object valor) throws SQLException {
		if (valor instanceof String) {
			setParam(ps, i, (String) valor);
		} else if (valor instanceof Integer) {
			setParam(ps, i, (Integer) valor);
		} else if (valor instanceof Long) {
			setParam(ps, i, (Long) valor);
		} else if (valor instanceof Double) {
			setParam(ps, i, (Double) valor);
		} else if (valor instanceof Date) {
			setParam(ps, i, (Date) valor);
		} else {
			if (valor == null) {
				ps.setString(i, "");
			} else {
				ps.setObject(i, valor);
			}
		}
	}

	protected void setParam(PreparedStatement ps, Integer i, String valor) throws SQLException {
		if (valor == null) {
			ps.setNull(i, Types.VARCHAR);
		} else {
			ps.setString(i, valor);
		}
	}

	protected void setParam(PreparedStatement ps, Integer i, Integer valor) throws SQLException {
		if (valor == null) {
			ps.setNull(i, Types.INTEGER);
		} else {
			ps.setInt(i, valor);
		}
	}

	protected void setParam(PreparedStatement ps, Integer i, Double valor) throws SQLException {
		if (valor == null) {
			ps.setNull(i, Types.DOUBLE);
		} else {
			ps.setDouble(i, valor);
		}
	}

	protected void setParam(PreparedStatement ps, Integer i, Long valor) throws SQLException {
		if (valor == null) {
			ps.setNull(i, Types.INTEGER);
		} else {
			ps.setLong(i, valor);
		}
	}

	protected void setParam(PreparedStatement ps, Integer i, Date valor) throws SQLException {
		if (valor == null) {
			ps.setNull(i, Types.TIMESTAMP);
		} else {
			ps.setTimestamp(i, new Timestamp(valor.getTime()));
		}
	}
}
