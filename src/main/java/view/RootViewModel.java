package view;

import java.util.function.Consumer;
import java.util.function.Function;

import model.TreeNode;


class RootViewModel {

    private final TreeNode<String> root;
    private final Function<TreeNode<String>, Boolean> onProxString;
    private final Function<TreeNode<String>, Boolean> onPalpite;
    private final Function<String, TreeNode<String>> onDerrota;
    private final Consumer<String> onVitoria;

    RootViewModel(final Function<TreeNode<String>, Boolean> onProxNode, Function<TreeNode<String>, Boolean> onPalpite, Function<String, TreeNode<String>> onDerrota, Consumer<String> onVitoria) {
        this.onProxString = onProxNode;
        this.onPalpite = onPalpite;
        this.onDerrota = onDerrota;
        this.onVitoria = onVitoria;

        this.root = new TreeNode<String>(null, "Seu prato é massa?");
        final TreeNode<String> sim = new TreeNode<String>(root, "lasanha");
        final TreeNode<String> nao = new TreeNode<>(root, "bolo do chocolate?");

        root.setSimNode(sim);
        root.setNaoNode(nao);
    }

    void iniciarProcura() {
        procurar(this.root);
    }

    private void procurar(final TreeNode<String> StringNode) {
        if (StringNode.isFolha()) {
            palpite(StringNode);
        }
        else {
            final boolean clicouSim = onProxString.apply(StringNode);
            final TreeNode<String> proximoString = StringNode.getProx(clicouSim);
            if (proximoString == null)
                palpite(StringNode);
            else
                procurar(proximoString);
        }


    }

    private void palpite(final TreeNode<String> pratoAtualNode) {
        final boolean result = onPalpite.apply(pratoAtualNode);
        if (result) {
            onVitoria.accept(pratoAtualNode.getValor());
        } else {
            final TreeNode<String> novoPrato = onDerrota.apply(pratoAtualNode.getValor());
            if (novoPrato != null) {
              novoPrato.setNaoNode(pratoAtualNode);
              novoPrato.setPai(pratoAtualNode.getPai());
              
              if (novoPrato.getPai().getSimNode() == pratoAtualNode)
                novoPrato.getPai().setSimNode(novoPrato);
              else
                novoPrato.getPai().setNaoNode(novoPrato);
                
              
                
              pratoAtualNode.setPai(novoPrato);
            }
        }

    }
}
