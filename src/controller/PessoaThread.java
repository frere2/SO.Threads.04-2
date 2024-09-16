package controller;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class PessoaThread extends Thread {
    private String nome;
    private int velocidade;
    private Semaphore porta;

    public PessoaThread(String nome, Semaphore porta) {
        this.nome = nome;
        this.velocidade = ThreadLocalRandom.current().nextInt(4, 7);
        this.porta = porta;
    }

    @Override
    public void run() {
        try {
            int distanciaTotal = 200;
            System.out.println(nome + " começou a caminhar a uma velocidade de " + velocidade + " m/s.");
            while (distanciaTotal > 0) {
                int caminhada = Math.min(velocidade, distanciaTotal);
                distanciaTotal -= caminhada;
                System.out.println(nome + " percorreu " + caminhada + " metros, restam " + distanciaTotal + " metros.");
                Thread.sleep(1000);
            }

            System.out.println(nome + " chegou à porta e está esperando para atravessar.");

            porta.acquire();

            int tempoParaAbrirPorta = ThreadLocalRandom.current().nextInt(1, 3) * 1000;
            System.out.println(nome + " está atravessando a porta...");
            Thread.sleep(tempoParaAbrirPorta);

            System.out.println(nome + " atravessou a porta!");
        } catch (InterruptedException e) {
            System.out.println(nome + " foi interrompido.");
        }
        finally {
            porta.release();
        }
    }
}