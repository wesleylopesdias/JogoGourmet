package view.widget;

import javafx.scene.control.TextInputDialog;

import java.util.function.Consumer;

public class DialogFimDerrota extends TextInputDialog {

    public DialogFimDerrota(final String message) {
        this.setTitle("Não sei no que você pensou");
        this.setHeaderText(null);
        this.setContentText(message);
    }

    public void show(Consumer<String> callback) {
        this.showAndWait().ifPresent(callback);
    }

}
