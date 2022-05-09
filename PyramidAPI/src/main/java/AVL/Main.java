package AVL;

public class Main {
    
    public static void main(String[] args) {
        Naipe a = new Naipe("10p");
        Naipe b = new Naipe("Ac");
        Naipe c = new Naipe("Jt");
        Naipe k = new Naipe("Qd");
        Naipe e = new Naipe("Kc");
        Naipe f = new Naipe("7p");

        System.out.println("Id: " + a.id + " - Peso: " + a.peso + " - Simbolo: " + a.simbolo + " - Valor: " + a.valor);
        System.out.println("Id: " + b.id + " - Peso: " + b.peso + " - Simbolo: " + b.simbolo + " - Valor: " + b.valor);
        System.out.println("Id: " + c.id + " - Peso: " + c.peso + " - Simbolo: " + c.simbolo + " - Valor: " + c.valor);
        System.out.println("Id: " + k.id + " - Peso: " + k.peso + " - Simbolo: " + k.simbolo + " - Valor: " + k.valor);
        System.out.println("Id: " + e.id + " - Peso: " + e.peso + " - Simbolo: " + e.simbolo + " - Valor: " + e.valor);
        System.out.println("Id: " + f.id + " - Peso: " + f.peso + " - Simbolo: " + f.simbolo + " - Valor: " + f.valor);
    }
}
