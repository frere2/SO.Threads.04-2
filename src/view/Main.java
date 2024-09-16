package view;

import controller.PessoaThread;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore porta = new Semaphore(1);

        Thread pessoa1 = new PessoaThread("Pessoa 1", porta);
        Thread pessoa2 = new PessoaThread("Pessoa 2", porta);
        Thread pessoa3 = new PessoaThread("Pessoa 3", porta);
        Thread pessoa4 = new PessoaThread("Pessoa 4", porta);

        pessoa1.start();
        pessoa2.start();
        pessoa3.start();
        pessoa4.start();
    }
}
