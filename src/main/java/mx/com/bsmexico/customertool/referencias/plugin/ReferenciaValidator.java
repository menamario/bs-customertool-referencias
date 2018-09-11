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
			return (v.getReferencia() != null && v.getReferencia().length()==6);
		};
	}

	@Override
	public String getValidationDescription(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isActive(Referencia model) {
		if (model != null && model.getReferencia() != null && model.getReferencia().length() > 0)
			return true;
		return false;
	}

}
