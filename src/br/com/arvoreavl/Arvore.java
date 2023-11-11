package br.com.arvoreavl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Arvore {
    private No raiz;

    public Arvore() {
        raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }

    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
        NumerosInseridos(valor);
    }

    List<Integer> elementos = new ArrayList<>();
    public void NumerosInseridos(int value){
        elementos.add(value);
    }

    public boolean pesquisar(int valor) {
        return pesquisarRecursivo(raiz, valor);
    }

    private boolean pesquisarRecursivo(No node, int valor) {
        if (node == null) {
            return false;
        }

        if (valor == node.valor) {
            return true;
        }

        if (valor < node.valor) {
            return pesquisarRecursivo(node.esquerda, valor);
        }

        return pesquisarRecursivo(node.direita, valor);
    }

    public void imprimir() {
        imprimirRecursivo(raiz, 0);
    }

    private void imprimirRecursivo(No node, int nivel) {
        if (node != null) {
            imprimirRecursivo(node.direita, nivel + 1);
            for (int i = 0; i < nivel; i++) {
                System.out.print("    ");
            }
            System.out.println(node.valor);
            imprimirRecursivo(node.esquerda, nivel + 1);
        }
    }

    public void delete(int key) {
        raiz = deleteRec(raiz, key);
    }

    private No deleteRec(No node, int key) {
        if (node == null) {
            return node;
        }
        if (key < node.valor) {
            node.esquerda = deleteRec(node.esquerda, key);
        } else if (key > node.valor) {
            node.direita = deleteRec(node.direita, key);
        } else {
            if (node.esquerda == null) {
                return node.direita;
            } else if (node.direita == null) {
                return node.esquerda;
            }
            node.valor = minValue(node.direita);
            node.direita = deleteRec(node.direita, node.valor);
        }
        return node;
    }

    public int encontarMenorValor() {
        return minValue(raiz);
    }

    private int minValue(No node) {
        int minValue = node.valor;
        while (node.esquerda != null) {
            minValue = node.esquerda.valor;
            node = node.esquerda;
        }
        return minValue;
    }

    public int encontarMaiorValor() {
        return maxValue(raiz);
    }

    private int maxValue(No node) {
        int maxValue = node.valor;
        while (node.direita != null) {
            maxValue = node.direita.valor;
            node = node.direita;
        }
        return maxValue;
    }

    public void inOrder() {
        inOrderRec(raiz);
    }

    private void inOrderRec(No node) {
        if (node != null) {
            inOrderRec(node.esquerda);
            System.out.print(node.valor + " ");
            inOrderRec(node.direita);
        }
    }

    public void preOrder() {
        preOrderIterative(raiz);
    }

    private void preOrderIterative(No node) {
        if (node == null) {
            return;
        }

        Stack<No> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            No current = stack.pop();
            System.out.print(current.valor + " ");

            if (current.direita != null) {
                stack.push(current.direita);
            }
            if (current.esquerda != null) {
                stack.push(current.esquerda);
            }
        }
    }

    public void postOrder() {
        postOrderRec(raiz);
    }

    private void postOrderRec(No node) {
        if (node != null) {
            postOrderRec(node.esquerda);
            postOrderRec(node.direita);
            System.out.print(node.valor + " ");
        }
    }
    int altura = 0 ;
    private No inserirRecursivo(No node, int valor) {
    if (node == null) {
        return new No(valor);
    }
    if (valor < node.valor) {
        node.esquerda = inserirRecursivo(node.esquerda, valor);
    } else if (valor > node.valor) {
        node.direita = inserirRecursivo(node.direita, valor);
    } else {
        return node;
    }

    node.altura = 1 + Math.max(altura(node.esquerda), altura(node.direita));

    int balance = getBalance(node);

        if (balance > 1 && valor < node.esquerda.valor) {
        return rotacaoDireita(node);
    }
        if (balance < -1 && valor > node.direita.valor) {
        return rotacaoEsquerda(node);
    }
        if (balance > 1 && valor > node.esquerda.valor) {
        node.esquerda = rotacaoEsquerda(node.esquerda);
        return rotacaoDireita(node);
    }
        if (balance < -1 && valor < node.direita.valor) {
        node.direita = rotacaoDireita(node.direita);
        return rotacaoEsquerda(node);
    }
        altura = getBalance(node);
        return node;
}

    private int altura(No node) {
        if (node == null) {
            return 0;
        }
        return node.altura;
    }

    public int getAltura(){
        return altura;
    }

    private int getBalance(No node) {
        if (node == null) {
            return 0;
        }
        return altura(node.esquerda) - altura(node.direita);
    }

    private No rotacaoDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        y.altura = 1 + Math.max(altura(y.esquerda), altura(y.direita));
        x.altura = 1 + Math.max(altura(x.esquerda), altura(x.direita));

        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.direita;
        No T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        x.altura = 1 + Math.max(altura(x.esquerda), altura(x.direita));
        y.altura = 1 + Math.max(altura(y.esquerda), altura(y.direita));

        return y;
    }

    public boolean isIdenticas(Arvore outraArvore) {
        return isIdenticasRec(this.raiz, outraArvore.raiz);
    }

    private boolean isIdenticasRec(No node1, No node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 != null && node2 != null) {
            return (node1.valor == node2.valor) &&
                    isIdenticasRec(node1.esquerda, node2.esquerda) &&
                    isIdenticasRec(node1.direita, node2.direita);
        }
        return false;
    }
}
