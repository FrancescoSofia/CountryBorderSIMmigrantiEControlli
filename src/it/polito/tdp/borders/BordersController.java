/**
 * Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxNazione"
    private ComboBox<Country> boxNazione; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

	private Model model;

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	if(txtAnno.getText()==null){
    		txtResult.appendText("Inserisci un anno");
    		return;
    	}
    	
    	int anno;
    	try {
		anno = Integer.parseInt(txtAnno.getText());
		} catch (NumberFormatException e) {
			txtResult.appendText("ERRORE: l'anno deve essere in formato numerico\n");
			return;
		}
    	List<Country> stati;
    	
    	try {
			stati = model.creaGrafo(anno);
			
		} catch (RuntimeException e) {
			txtResult.appendText("Errore: " + e.getMessage() + "\n");
			return;
		}

    	txtResult.clear();
    	
    	for(Country c : stati){
    		
    		txtResult.appendText(c.toString()+" numero di stati confinanti :"+c.getStatiConfinanti().size()+"\n");
    	}
    	boxNazione.getItems().addAll(stati);

    }

    @FXML
    void doSimula(ActionEvent event) {
    	
    	if(txtAnno.getText()==null){
    		txtResult.appendText("Inserisci un anno");
    		return;
    	}
    	if(boxNazione.getItems()==null){
    		txtResult.appendText("Seleziona una nazione, se non riesci, calcola i confini prima");
    	}
    	
    	
    	int anno;
    	try {
		anno = Integer.parseInt(txtAnno.getText());
		} catch (NumberFormatException e) {
			txtResult.appendText("ERRORE: l'anno deve essere in formato numerico\n");
			return;
		}
    
    	try {
		model.creaGrafo(anno);
			
		} catch (RuntimeException e) {
			txtResult.appendText("Errore: " + e.getMessage() + "\n");
			return;
		}

    	txtResult.clear();
    	List<Country> ordinati = model.Simula(boxNazione.getValue());
    	txtResult.appendText("Il numero di passi è :"+model.getPassi()+"\n");
    	for(Country c : ordinati){
    		txtResult.appendText(c.toString()+" numero migranti :" +c.getMigranti()+"\n");
    	}
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
        assert boxNazione != null : "fx:id=\"boxNazione\" was not injected: check your FXML file 'Borders.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		
	}
}
