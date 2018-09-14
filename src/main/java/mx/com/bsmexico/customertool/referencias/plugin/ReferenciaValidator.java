package mx.com.bsmexico.customertool.referencias.plugin;

import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import mx.com.bsmexico.customertool.api.layouts.model.validation.LayoutModelValidator;

/**
 * @author jchr
 *
 */
public class ReferenciaValidator extends LayoutModelValidator<Referencia> {

	@Override
	public boolean isValidField(String fieldName, Referencia model) {
		boolean isValid = false;
		if (StringUtils.isNotBlank(fieldName) && model != null) {
			switch (fieldName) {
			case Referencia.FIELD_REFERENCIA:
				isValid = referencia().test(model);
				break;
			default:
				break;
			}
		}
		return isValid;
	}

	@Override
	public boolean isValid(Referencia model) {
		return !isActive(model) || (model != null && referencia().test(model));
	}

	@Override
	public boolean isValid(List<Referencia> models) {
		boolean isValid = true;
		if (models != null) {
			for (Referencia model : models) {
				if (!this.isValid(model)) {
					isValid = false;
					break;
				}
			}
		}
		return isValid;
	}

	public Predicate<Referencia> referencia() {
		return v -> {
			return (v.getReferencia() != null && v.getReferencia().length()==6 && NumberUtils.isDigits(v.getReferencia()));
		};
	}

	@Override
	public String getValidationDescription(String fieldName) {
		String desc = StringUtils.EMPTY;
		if (StringUtils.isNotBlank(fieldName)) {
			switch (fieldName) {
			case Referencia.FIELD_REFERENCIA:
				desc = "En este campo debe Capturar un valor numerico de 6 posiciones p.e. 152623";
				break;
			case Referencia.FIELD_REFERENCIA_COMPLETA:
				desc = "En este campo se mostrara la referencia completa incluyendo el digito verificador";
				break;
			case Referencia.FIELD_DIGITO_VERIFICADOR:
				desc = "Digito Verificador calculado segun el algoritmo elegido";
				break;
			default:
				break;
			}
		}
		return desc;
	}

	@Override
	public boolean isActive(Referencia model) {
		if (model != null && model.getReferencia() != null && model.getReferencia().length() > 0)
			return true;
		return false;
	}

}
