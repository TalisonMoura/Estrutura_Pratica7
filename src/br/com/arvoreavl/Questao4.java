package br.com.arvoreavl;

public class Questao4 {
    Arvore arv = new Arvore();
    Arvore arv2 = new Arvore();
    public void IsIdenticas(){
        arv.inserir(10);
        arv.inserir(7);
        arv.inserir(15);
        arv.inserir(5);
        arv.inserir(3);
        arv.inserir(12);
        arv.inserir(20);
        arv.inserir(25);
        arv.inserir(30);

        arv2.inserir(10);
        arv2.inserir(7);
        arv2.inserir(40);
        arv2.inserir(5);
        arv2.inserir(3);
        arv2.inserir(12);
        arv2.inserir(20);
        arv2.inserir(25);
        arv2.inserir(30);

        if(arv.isIdenticas(arv2)){
            System.out.println("As arvores são identicas");
        }else{
            System.out.println("As arvores não são identicas");
        }
    }
}
