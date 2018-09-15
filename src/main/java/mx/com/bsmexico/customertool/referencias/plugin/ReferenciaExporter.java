package mx.com.bsmexico.customertool.referencias.plugin;

import java.util.ArrayList;
import java.util.List;

import mx.com.bsmexico.customertool.api.process.CSVExporter;
import mx.com.bsmexico.customertool.api.process.ExportSource;

public class ReferenciaExporter extends CSVExporter<Referencia> {

	/**
	 * @param source
	 */
	public ReferenciaExporter(ExportSource<Referencia> source) {
		super(source);
	}

	@Override
	protected Object[] getRecord(final Referencia referencia) {
		final List<Object> record = new ArrayList<>();
		record.add(referencia.getReferencia());
		record.add(referencia.getReferenciaCompleta());
		record.add(referencia.getDigitoVerificador());
		return record.toArray();
	}

	@Override
	protected String[] getHeader() {
		return new String[] { "Referencia", "ReferenciaCompleta", "DigitoVerificador" };
	}

}
