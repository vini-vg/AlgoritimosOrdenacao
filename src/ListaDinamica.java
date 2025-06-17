public class ListaDinamica {
    private int[] dados;
    private int tamanho;
    private static final int CAPACIDADE_INICIAL = 100;

    public ListaDinamica() {
        this.dados = new int[CAPACIDADE_INICIAL];
        this.tamanho = 0;
    }

    // adiciona elemento
    public void adicionar(int numero) {
        if (tamanho == dados.length) {
            redimensionar();
        }
        dados[tamanho] = numero;
        tamanho++;
    }

    // aumenta o array
    private void redimensionar() {
        int novaCapacidade = dados.length * 2;
        int[] novoArray = new int[novaCapacidade];
        // copia os elementos do array antigo para o novo
        for (int i = 0; i < tamanho; i++) {
            novoArray[i] = dados[i];
        }
        this.dados = novoArray; // substitui o array antigo pelo novo, maior
    }

    // converte lista para um array de inteiros do tamanho exato
    public int[] paraArray() {
        int[] resultado = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            resultado[i] = dados[i];
        }
        return resultado;
    }
}
