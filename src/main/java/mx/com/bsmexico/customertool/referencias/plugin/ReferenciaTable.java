package mx.com.bsmexico.customertool.referencias.plugin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import mx.com.bsmexico.customertool.api.layouts.control.EditableLayoutTable;
import mx.com.bsmexico.customertool.api.layouts.model.validation.LayoutModelValidator;
import mx.com.bsmexico.customertool.api.process.ExportSource;
import mx.com.bsmexico.customertool.api.process.ImportTarget;

public class ReferenciaTable extends EditableLayoutTable<Referencia>
		implements ImportTarget<Referencia>, ExportSource<Referencia> {

	private final int INITIAL_CAPACITY = 15;

	public ReferenciaTable() throws IllegalArgumentException, InstantiationError {
		super(Referencia.class);
		setIndexColumn();

	}
	

	protected void polulate() {
		for (int idx = 0; idx <= INITIAL_CAPACITY; idx++) {
			this.data.add(new Referencia());
		}
	}

	@Override
	protected void addRow() {
		getItems().add(new Referencia());

	}

	@Override
	public void setData(List<Referencia> data) {
		ObservableList<Referencia> observableList = FXCollections.observableList(data);
		if (observableList.size()<INITIAL_CAPACITY){
			for(int i = 0; i < INITIAL_CAPACITY-observableList.size(); i++){
				observableList.add(new Referencia());
			} 
		}
		setItems(observableList);

	}

	@Override
	public List<Referencia> getData() {
		List<Referencia> exportList = new ArrayList<Referencia>();
		try{
			for(Referencia r:getItems()){
				if(isActiveModel(r)){
					exportList.add(r);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return exportList;
	}

	@Override
	protected String[] getFieldOrder() {
		return new String[] { Referencia.FIELD_REFERENCIA, Referencia.FIELD_REFERENCIA_COMPLETA,
				Referencia.FIELD_DIGITO_VERIFICADOR, Referencia.FIELD_COMENTARIOS };
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean validateTable() throws Exception {
		boolean isValid = true;
		if (this.metamodel.getValidator() != null) {
			this.validated = true;
			isValid = ((LayoutModelValidator<Referencia>) this.metamodel.getValidator()).isValid(getData());
			if (!isValid) {
				refresh();
			}
		}
		return isValid;
	}


	@Override
	public boolean validateModel(Referencia model) throws Exception {
		boolean isValid = true;
		if (this.metamodel.getValidator() != null) {
			isValid = ((LayoutModelValidator<Referencia>) this.metamodel.getValidator()).isValid(model);
			if (!isValid) {
				refresh();
			}
		}
		return isValid;
	}


	@Override
	public boolean isActiveModel(Referencia model) throws Exception {
		boolean isActive = true;
		if (this.metamodel.getValidator() != null) {
			isActive = ((LayoutModelValidator<Referencia>) this.metamodel.getValidator()).isActive(model);
		}
		return isActive;
	}
	
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void setColumns() throws Exception {
		String[] ids = getFieldOrder();
		if (!ArrayUtils.isEmpty(ids)) {
			TableColumn ct = null;
			for (String id : ids) {
				ct = columnFactory.<String>getColumn(id, 100);
				ct.prefWidthProperty().bind(widthProperty().multiply(0.24));
				if(id.equals(Referencia.FIELD_REFERENCIA_COMPLETA) || id.equals(Referencia.FIELD_DIGITO_VERIFICADOR)){
					ct.setStyle("-fx-alignment: CENTER;");
				}
				getColumns().add(ct);
			}
		}
	}
	



}
