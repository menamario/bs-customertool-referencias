package mx.com.bsmexico.customertool.referencias.plugin;

import java.util.List;

import mx.com.bsmexico.customertool.api.process.CSVImporter;
import mx.com.bsmexico.customertool.api.process.ImportTarget;

/**
 * @author jchr
 *
 */
public class ReferenciaImporter extends CSVImporter<Referencia> {
	
	public ReferenciaImporter(ImportTarget<Referencia> target) throws IllegalArgumentException {
		super(target);	
	}

	@Override
	protected Referencia getInstance(final List<String> record) {
		final Referencia referencia = new Referencia();		
		referencia.setReferencia(record.get(0));
		return referencia;
	}

	@Override
	protected String[] getHeader() {
		return new String[] { "Referencia", "ReferenciaCompleta", "DigitoVerificador" };
	}

}
