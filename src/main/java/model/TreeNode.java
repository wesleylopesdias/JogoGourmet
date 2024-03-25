package model;

import java.util.function.Consumer;

public class TreeNode<T> {
    private final T valor;
    private TreeNode<T> pai;
    private TreeNode<T> simNode;
    private TreeNode<T> naoNode;

    public TreeNode(final TreeNode<T> pai, final T valor) {
        this.valor = valor;
        this.pai = pai;
    }

    public boolean proximo(final Consumer<TreeNode<T>> acao, boolean isSim) {
        if (isSim) {
            if (simNode != null) {
                acao.accept(simNode);
                return true;
            }
        }
        else {
            if (naoNode != null) {
                acao.accept(naoNode);
                return true;
            }
        }

        return false;
    }

    public TreeNode<T> getProx(final boolean sim) {
        if (sim)
            return simNode;
        else
            return naoNode;

    }

    public boolean isFolha() {
        return simNode == null && naoNode == null;
    }

    public T getValor() {
        return valor;
    }

    public TreeNode<T> getPai() {
        return pai;
    }
    
    public void setPai(TreeNode<T> novoPai) {
      this.pai = novoPai;
    }

    public TreeNode<T> getSimNode() {
        return simNode;
    }

    public void setSimNode(TreeNode<T> simNode) {
        this.simNode = simNode;
    }

    public TreeNode<T> getNaoNode() {
        return naoNode;
    }

    public void setNaoNode(TreeNode<T> naoNode) {
        this.naoNode = naoNode;
    }
}
