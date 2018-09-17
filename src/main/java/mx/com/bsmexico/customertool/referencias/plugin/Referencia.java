package mx.com.bsmexico.customertool.referencias.plugin;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import mx.com.bsmexico.customertool.api.layouts.model.LayoutField;
import mx.com.bsmexico.customertool.api.layouts.model.LayoutModel;
import mx.com.bsmexico.customertool.api.layouts.model.LayoutModelType;

/**
 * 
 * Referencia Model
 * 
 * @author jchr
 *
 */
@LayoutModel(type = LayoutModelType.PROPERTY_JAVABEANS, validatorClass = ReferenciaValidator.class)
public class Referencia {

	public static final String FIELD_REFERENCIA = "REFERENCIA";
	public static final String FIELD_REFERENCIA_COMPLETA = "REFERENCIA_COMPLETA";
	public static final String FIELD_DIGITO_VERIFICADOR = "DIGITO_VERIFICADOR";
	
	@LayoutField(name = FIELD_REFERENCIA, title = "Referencia", length = 6)
	private SimpleStringProperty referencia;

	@LayoutField(name = FIELD_REFERENCIA_COMPLETA, title = "Referencia Completa", length = 10, editable = false)
	private SimpleStringProperty referenciaCompleta;
	
	@LayoutField(name = FIELD_DIGITO_VERIFICADOR, title = "Digito Verificador", length = 10, editable = false)
	private SimpleStringProperty digitoVerificador;

	private Map<String, Boolean> estatus = new HashMap<String, Boolean>();
	private Map<String, String> tooltips = new HashMap<String, String>();

	public Map<String, String> getTooltips() {
		return tooltips;
	}

	public String getTooltip(String property) {
		return tooltips.get(property);
	}

	public void setTooltip(String property, String value) {
		this.getTooltips().put(property, value);
	}

	public Referencia() {
		referencia = new SimpleStringProperty();
		estatus.put("referencia", true);
		referenciaCompleta = new SimpleStringProperty();
		estatus.put("numLinea", true);
		digitoVerificador = new SimpleStringProperty();
		estatus.put("bancoParticipante", true);
	}

	public Map<String, Boolean> getEstatus() {
		return estatus;
	}

	public void setEstatus(String property, Boolean value) {
		this.getEstatus().put(property, value);
	}

	
	
	
	

	public String getReferencia() {
		return referencia.get();
	}


	public void setReferencia(String referencia) {
		this.referencia.set(referencia);
	}
	

	public String getReferenciaCompleta() {
		return referenciaCompleta.get();
	}


	public void setReferenciaCompleta(String referenciaCompleta) {
		this.referenciaCompleta.set(referenciaCompleta);
	}
	
	public String getDigitoVerificador() {
		return digitoVerificador.get();
	}


	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador.set(digitoVerificador);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((digitoVerificador.get() == null) ? 0 : digitoVerificador.get().hashCode());
		result = prime * result + ((referencia.get() == null) ? 0 : referencia.get().hashCode());
		result = prime * result + ((referenciaCompleta.get() == null) ? 0 : referenciaCompleta.get().hashCode());
		return result;
	}

}
