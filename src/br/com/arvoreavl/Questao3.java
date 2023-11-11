package br.com.arvoreavl;

public class Questao3 {
    Arvore arv = new Arvore();
    public void MaiorElemento(){
        arv.inserir(10);
        arv.inserir(7);
        arv.inserir(15);
        arv.inserir(5);
        arv.inserir(3);
        arv.inserir(12);
        arv.inserir(20);
        arv.inserir(25);
        arv.inserir(30);

        System.out.print("O maior elemento da arvore: " + arv.encontarMaiorValor());
    }
}
