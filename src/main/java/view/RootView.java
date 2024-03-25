package view;

import java.text.MessageFormat;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import model.TreeNode;
import view.widget.DialogPerguntaPrato;

public class RootView {

  private final RootViewModel viewModel;

  public RootView() {
    viewModel = new RootViewModel(this::perguntaCaracteristica, this::confirmarString, this::criaPratoDerrota, this::exibeAlertaVitoria);
  }

  private boolean perguntaCaracteristica(TreeNode<String> nodeString) {
    final DialogPerguntaPrato dialog = new DialogPerguntaPrato(
        "Tem esta caracteristica?", nodeString.getValor());
    final Optional<ButtonType> buttonType = dialog.showAndWait();
    if (buttonType.isPresent()) {
      if (buttonType.get().getButtonData() == ButtonBar.ButtonData.YES) {
        return true;
      }
      else if (buttonType.get().getButtonData() == ButtonBar.ButtonData.NO) {
        return false;
      }
    }
    return false;
  }

  private Boolean confirmarString(TreeNode<String> nodeString) {
    final DialogPerguntaPrato dialog = new DialogPerguntaPrato(
        "Este é o seu prato?", MessageFormat.format("Seu prato é {0}?", nodeString.getValor()));
    final Optional<ButtonType> buttonType = dialog.showAndWait();
    if (buttonType.isPresent()) {
      if (buttonType.get().getButtonData() == ButtonBar.ButtonData.YES) {
        return true;
      }
      else if (buttonType.get().getButtonData() == ButtonBar.ButtonData.NO) {
        return false;
      }
    }
    return null;
  }

  private TreeNode<String> criaPratoDerrota(final String prato) {

    final TextInputDialog nomeDialog = new TextInputDialog();
    nomeDialog.setTitle("Não sei no que você pensou");
    nomeDialog.setHeaderText(null);
    nomeDialog.setContentText("Qual o prato que você pensou?");
    Optional<String> nomeOptional = nomeDialog.showAndWait();

    if (nomeOptional.isPresent() && !nomeOptional.get().trim().isEmpty()) {
      final String nome = nomeOptional.get();

      final TextInputDialog caracteristicaDialog = new TextInputDialog();
      caracteristicaDialog.setTitle("Não sei no que você pensou");
      caracteristicaDialog.setHeaderText(null);
      if (prato != null)
        caracteristicaDialog.setContentText(MessageFormat.format("{0} é ________, mas {1} não.", nome, prato));
      else
        caracteristicaDialog.setContentText(MessageFormat.format("{0} é ________.", nome));

      final Optional<String> caractOptional = caracteristicaDialog.showAndWait();

      if (caractOptional.isPresent() && !caractOptional.get().trim().isEmpty()) {
        final TreeNode<String> resultNode = new TreeNode<String>(null, MessageFormat.format("Seu prato é {0}?", caractOptional.get()));
        final TreeNode<String> sim = new TreeNode<String>(resultNode, nome);
        resultNode.setSimNode(sim);
        return resultNode;
      }
    }

    return null;
  }

  private void exibeAlertaVitoria(String prato) {
    final Alert msgVitoria = new Alert(Alert.AlertType.INFORMATION);
    msgVitoria.setHeaderText(null);
    msgVitoria.setTitle("Vitória");
    msgVitoria.setContentText(MessageFormat.format("Acertei, sabia que você gostava de {0}", prato));
    msgVitoria.showAndWait();
  }

  @FXML
  public void iniciarClickHandle() {
    this.viewModel.iniciarProcura();
  }

}
