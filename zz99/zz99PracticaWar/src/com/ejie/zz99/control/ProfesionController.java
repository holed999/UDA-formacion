package com.ejie.zz99.control;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResponseDto;
import com.ejie.x38.reports.ReportData;
import com.ejie.zz99.model.Mantenimiento;
import com.ejie.zz99.model.Profesion;
import com.ejie.zz99.model.ProfesionFilter;
import com.ejie.zz99.model.enums.SituacionCombo;
import com.ejie.zz99.service.ProfesionService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@RequestMapping(value = "/profesion")
public class ProfesionController {

	private static final Logger logger = LoggerFactory.getLogger(ProfesionController.class);

	@Autowired
	private ProfesionService profesionService;

	/**
	 * Metodo de presentacion del RUP_TABLE.
	 *
	 * @param model Model
	 * @return String
	 */
	@RequestMapping(value = "/maint", method = RequestMethod.GET)
	public String getMaint(Model model) {
		logger.info("[GET - View] : Profesion");
		model.addAttribute("Profesion", new Profesion());
		model.addAttribute("ProfesionFilter", new ProfesionFilter());
		return "mantProfesion";
	}

	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody TableResponseDto<Profesion> filter(@RequestJsonBody(param = "filter") ProfesionFilter filter,
			@RequestJsonBody TableRequestDto tableRequestDto) {
		logger.info("[POST - filter] : Obtener servicios");
		return this.profesionService.filter(filter, tableRequestDto);
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public @ResponseBody Profesion filter() {
		logger.info("[POST - filter] : Obtener servicios");
		Profesion profesion = new Profesion();
		return this.profesionService.find(profesion);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Profesion add(@RequestBody Profesion profesion) {
		logger.info("[POST] : Profesion: add");
		Profesion rdo = this.profesionService.add(profesion);
		logger.info("[POST] : Profesion insertado correctamente");
		return rdo;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public @ResponseBody Profesion edit(@RequestBody Profesion profesion) {
		logger.info("[PUT] : Profesion: edit");
		Profesion rdo = this.profesionService.update(profesion);
		logger.info("[PUT] : Profesion actualizado correctamente");
		return rdo;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Profesion get(@PathVariable String id) {
		logger.info("[GET - findBy_PK] : Obtener Profesion por PK");

		Profesion profesion = new Profesion();
		profesion.setIdentificador(Integer.valueOf(id));
		return this.profesionService.find(profesion);
	}

	@RequestMapping(value = "combo/situacion", method = RequestMethod.GET)
	public @ResponseBody List<SituacionCombo> getAllSituacion() {
		List<SituacionCombo> lista = new ArrayList<>();
		SituacionCombo alta = new SituacionCombo("A", "Alta", "Alta");
		SituacionCombo baja = new SituacionCombo("B", "Baja", "Baja");
		lista.add(alta);
		lista.add(baja);
		return lista;

	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Profesion remove(@PathVariable String codigo) {
		Profesion profesion = new Profesion();
		profesion.setIdentificador(Integer.valueOf(codigo));
		this.profesionService.remove(profesion);
		logger.info("[DELETE] : profesion borrada correctamente");
		return profesion;
	}

	@RequestMapping(value = "/xlsxReport", method = RequestMethod.POST)
	protected ModelAndView getXLSXReport(@ModelAttribute Profesion filterprofesion,
			ModelMap modelMap,
			@RequestParam(value = "columns", required = false) String columns)
			throws JsonParseException, JsonMappingException, IOException {

		// Obtener datos
		List<Profesion> listProfesionAll = this.profesionService.findAll(filterprofesion, null);

		// Nombre fichero
		modelMap.put("fileName", "Fichero");

		// Datos
		List<Object> reportData = new ArrayList<Object>();

		// Hoja 1
		ReportData<Profesion> profesionExcelDataAll = new ReportData<Profesion>();
		// nombre hoja
		profesionExcelDataAll.setSheetName("profesiones");
		// cabeceras hoja
		profesionExcelDataAll.setHeaderNames(generaColumnas());
		// datos hoja
		profesionExcelDataAll.setModelData(listProfesionAll);
		reportData.add(profesionExcelDataAll);

		modelMap.put("reportData", reportData);

		// Generacion del informe
		return new ModelAndView("xlsxReport", modelMap);
	}

	private LinkedHashMap<String, String> generaColumnas() {
		Field[] campos = Mantenimiento.class.getDeclaredFields();
		LinkedHashMap<String, String> mapa = new LinkedHashMap<String, String>();

		for (Field campo : campos) {

			campo.setAccessible(true);

			if(!"serialVersionUID".equals(campo.getName()) && !"descFilter".equals(campo.getName())) {
				mapa.put(campo.getName(), campo.getName());
			}

		}

		return mapa;
	}
}
