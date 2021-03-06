package ba.unsa.etf.rpr;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class guiController {
    public ListView lista;
    //public Button ispisiBtn;
    private GeografijaDAO baza;
    public ObservableList<Grad> gradovi;
    public TextField gradZaDodavanje;
    public TextField drzavaZaBrisanje;
    public Button dugmeBrisiDrzavu;
    public Label opcija;
    public BorderPane mainPane;

    public guiController(GeografijaDAO baza) {
        this.baza = baza;
    }



    public void save(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                new FileChooser.ExtensionFilter("DOCX Files", "*.docx"),
                new FileChooser.ExtensionFilter("XSLX Files", "*.xslx")
        );
        File file = fileChooser.showSaveDialog(stage);
    }

    private void izaberi(Locale locale) {
        Stage primaryStage = (Stage)mainPane.getScene().getWindow();
        Locale.setDefault(locale);
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gui.fxml"), bundle);
        loader.setController(new guiController(baza));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
    }

    public void setBos(ActionEvent actionEvent) {
        izaberi(new Locale("bs","BIH"));
    }

    public void setEng(ActionEvent actionEvent) {
        izaberi(new Locale("en","US"));
    }

    public void setNJem(ActionEvent actionEvent) {
        izaberi(new Locale("de","DE"));
    }

    public void setFran(ActionEvent actionEvent) {
        izaberi(new Locale("fr","FR"));
    }

    public void pozivGradoviD(ActionEvent actionEvent) {
    }

    public void nadjiGlavniGrad(ActionEvent actionEvent) {
    }

    public void obrisiDrzavuINjeneGradove(ActionEvent actionEvent) {
    }

    public void ispis(ActionEvent actionEvent) {
        baza = GeografijaDAO.getInstance();
        GradoviReport report = new GradoviReport();
        try {
            report.showReport(GeografijaDAO.getConnection());
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
