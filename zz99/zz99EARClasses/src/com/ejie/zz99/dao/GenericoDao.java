package com.ejie.zz99.dao;

import java.util.List;

import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableRowDto;

/**
 * @author ofernandez
 *
 * @param <T> Tipo
 */
public interface GenericoDao<T> {

	/**
	 * Finds a single row in the table.
	 *
	 * @param bean T
	 * @return T
	 */
	T find(T bean);

	/**
	 * Finds a List of rows in the table.
	 *
	 * @param bean            T
	 * @param TableRequestDto tableRequestDto
	 * @return List
	 */
	List<T> findAll(T bean, TableRequestDto tableRequestDto);

	/**
	 * @param bean T
	 * @return Long
	 */
	Long findAllCount(T bean);

	/**
	 * Reorder selection.
	 *
	 * @param filter          T
	 * @param TableRequestDto tableRequestDto
	 * @return List<TableRowDto<T>>
	 */
	List<TableRowDto<T>> reorderSelection(T filter, TableRequestDto tableRequestDto);

	/**
	 * Searches in a table.
	 *
	 * @param filter          T
	 * @param search          T
	 * @param TableRequestDto tableRequestDto
	 * @return List<TableRowDto<T>>
	 */
	List<TableRowDto<T>> search(T filter, T search, TableRequestDto tableRequestDto);

	/**
	 * Inserts a single row in the table.
	 *
	 * @param bean T
	 * @return T
	 */
	T add(T bean);

	/**
	 * Updates a single row in the table.
	 *
	 * @param bean T
	 * @return T
	 */
	T update(T bean);

	/**
	 * Removes a single row in the table.
	 *
	 * @param bean T
	 */
	void remove(T bean);

	List<T> getSelected(T bean, TableRequestDto tableRequestDto);

}
