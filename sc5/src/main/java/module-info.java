module spirographs.spirographs {
    requires javafx.controls;
    requires javafx.fxml;


    opens spirographs.spirographs to javafx.fxml;
    exports spirographs.spirographs;
}