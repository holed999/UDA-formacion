package com.ejie.zz99.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableRequestDto.Multiselection;
import com.ejie.zz99.common.PreparedStatementSetter;
import com.ejie.zz99.common.SqlConstantes;

/**
 * @author vdorantes
 *
 * @param <T> Tipo
 */
public class BaseDaoImpl {

	private JdbcTemplate jdbcTemplate;

	/**
	 * Method use to set the datasource.
	 *
	 * @param dataSource DataSource
	 * @return
	 */
	@Resource()
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * @return JdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	/**
	 * @param secuencia String
	 * @return Integer
	 */
	protected Integer getNextVal(String secuencia) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT ").append(secuencia).append(".NEXTVAL FROM DUAL");
		return this.jdbcTemplate.queryForObject(query.toString(), Integer.class);
	}

	protected String defaultIfBlank(String value, String defaultValue) {
		return StringUtils.isNotBlank(value) ? value : defaultValue;
	}

	protected void batchUpdate(String query, List<Object[]> params) {
		this.jdbcTemplate.batchUpdate(query, new PreparedStatementSetter(params));
	}

	protected String generarWhereIgual(String campo, Object valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(SqlConstantes.AND).append(campo).append(" = ?");
			params.add(valor);
		}
		return where.toString();
	}

	protected String generarWhereLike(String campo, String valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (StringUtils.isNotBlank(valor)) {
			where.append(SqlConstantes.AND).append(this.normalizarCampoSql(campo)).append(SqlConstantes.LIKE)
					.append(this.normalizarCampoSql("?")).append(SqlConstantes.ESCAPE);
			params.add("%" + valor.toUpperCase() + "%");
			where.append(SqlConstantes.AND).append(campo).append(SqlConstantes.IS_NOT_NULL);
		}
		return where.toString();
	}

	protected String generarWhereLikeSinEspacios(String campo, String valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (StringUtils.isNotBlank(valor)) {
			where.append(SqlConstantes.AND).append(this.normalizarCampoSql(campo)).append(SqlConstantes.LIKE)
					.append(this.normalizarCampoSql("?")).append(SqlConstantes.ESCAPE);
			params.add("%" + valor.replaceAll("\\s", "").toUpperCase() + "%");
			where.append(SqlConstantes.AND).append(campo).append(SqlConstantes.IS_NOT_NULL);
		}
		return where.toString();
	}

	protected String generarWhereLike(String campo1, String campo2, String valor, List<Object> params) {

		final StringBuilder where = new StringBuilder();
		if (StringUtils.isNotBlank(valor)) {
			where.append(SqlConstantes.AND + "(").append(this.normalizarCampoSql(campo1)).append(SqlConstantes.LIKE)
					.append(this.normalizarCampoSql("?")).append(SqlConstantes.ESCAPE);
			params.add("%" + valor.toUpperCase() + "%");
			where.append(SqlConstantes.AND).append(campo1).append(SqlConstantes.IS_NOT_NULL);
			where.append(SqlConstantes.OR).append(this.normalizarCampoSql(campo2)).append(SqlConstantes.LIKE)
					.append(this.normalizarCampoSql("?")).append(SqlConstantes.ESCAPE);
			params.add("%" + valor.toUpperCase() + "%");
			where.append(SqlConstantes.AND).append(campo2).append(SqlConstantes.IS_NOT_NULL).append(")");
		}
		return where.toString();

	}

	protected String generarWhereIgualOrNull(String campo1, Object valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(SqlConstantes.AND).append("(").append(campo1).append(" = ?");
			params.add(valor);
			where.append(" OR ").append(campo1).append(" IS NULL)");
		}
		return where.toString();
	}

	public String normalizarCampoSql(String campoSql) {
		final StringBuilder sqlBuffer = new StringBuilder();
		sqlBuffer.append(" TRANSLATE(UPPER(");
		sqlBuffer.append(campoSql);
		sqlBuffer.append("),'");
		sqlBuffer.append(SqlConstantes.CARACTERES_A_TRADUCIR);
		sqlBuffer.append("','");
		sqlBuffer.append(SqlConstantes.CARACTERES_TRADUCIDOS);
		sqlBuffer.append("') ");
		return sqlBuffer.toString();
	}

	protected void generarWhereMultiSelect(StringBuilder where, String codigo, TableRequestDto jqGridRequestDto,
			List<Object> params) {
		Multiselection multiselection = jqGridRequestDto.getMultiselection();
		List<String> selectedIds = multiselection.getSelectedIds();
		Boolean selectedAll = multiselection.getSelectedAll();
		if (selectedAll != null) {
			if (Boolean.TRUE.equals(selectedAll)) {
				where.append(this.generarWhereNotIn(codigo, selectedIds, params));
			} else {
				where.append(this.generarWhereIn(codigo, selectedIds, params));
			}
		}
	}

	protected String generarWhereIn(String campo, List<?> valores, List<Object> params) {
		return generarWhereIn(campo, valores, params, true);
	}

	protected String generarWhereIn(String campo, List<?> valores, List<Object> params, boolean withAnd) {
		final StringBuilder where = new StringBuilder();
		if (valores != null && !valores.isEmpty() && !(valores.size() == 1 && "[]".equals(valores.get(0)))) {
			if (withAnd)
				where.append(SqlConstantes.AND);
			where.append(campo).append(generarIn(valores));
			params.addAll(valores);
		}
		return where.toString();
	}

	protected String generarIn(List<?> valores) {
		final StringBuilder where = new StringBuilder();
		where.append(" IN (");
		for (Integer i = 0; i < valores.size(); i++) {
			if (i > 0) {
				where.append(", ");
			}
			where.append("?");
		}
		where.append(")");
		return where.toString();
	}

	protected String generarWhereNotIn(String campo, List<?> valores, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valores != null && !valores.isEmpty()) {
			where.append(SqlConstantes.AND).append(campo).append(" NOT ").append(generarIn(valores));
			params.addAll(valores);
		}
		return where.toString();
	}

	protected String generarWhereNotInAndIsNull(String campo, List<?> valores, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valores != null && !valores.isEmpty()) {
			where.append(SqlConstantes.AND).append(" ( ").append(campo).append(" NOT ").append(generarIn(valores))
					.append(" OR ").append(campo).append(" IS NULL ").append(")");
			params.addAll(valores);
		}
		return where.toString();
	}

	protected String generarWhereMayorIgual(String campo, Integer valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(SqlConstantes.AND);
			where.append(campo);
			where.append(" >= ?");
			params.add(valor);
		}
		return where.toString();
	}

	protected String generarWhereMenorIgual(String campo, Integer valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(SqlConstantes.AND);
			where.append(campo);
			where.append(" <= ?");
			params.add(valor);
		}
		return where.toString();
	}

	protected String generarWhereMayorIgualFecha(String campo, Date valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(SqlConstantes.AND);
			where.append(campo);
			where.append(" >= ?");
			params.add(valor);
		}
		return where.toString();
	}

	protected String generarWhereMenorIgualFecha(String campo, Date valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(SqlConstantes.AND);
			where.append(campo);
			where.append(" <= ?");
			params.add(valor);
		}
		return where.toString();
	}

	protected String generarWhereIgualHoyFecha(String campo, Date valor, List<Object> params) {
		final StringBuilder where = new StringBuilder();
		if (valor != null) {
			where.append(SqlConstantes.AND);
			where.append("TO_CHAR(" + campo + ", 'MM-DD') ");
			where.append("= TO_CHAR(?, 'MM-DD')");
			params.add(valor);
		}
		return where.toString();
	}
}
