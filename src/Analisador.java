import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Analisador {

    public static void main(String[] args){
        String[] arquivos = {
                "aleatorio_100.csv", "aleatorio_1000.csv", "aleatorio_10000.csv",
                "crescente_100.csv", "crescente_1000.csv", "crescente_10000.csv",
                "decrescente_100.csv", "decrescente_1000.csv", "decrescente_10000.csv"
        };

        //imprime o cabeçalho da tabela
        System.out.printf("%-15s | %-10s | %-18s | %-18s | %-18s%n",
                "Tipo de Dados", "Tamanho","Bubble sort (s)", "Insertion Sort (s)", "Quick Sort (s)");
        System.out.println(new String(new char[85]).replace("\0","-"));

        for (String arquivo : arquivos){
            try{
                int[] dadosOriginais = lerCsv(arquivo);
                String[] partesNome = arquivo.replace(".csv","").split("_");
                String tipo = partesNome[0].substring(0,1).toUpperCase() + partesNome[0].substring(1);
                int tamanho = Integer.parseInt(partesNome[1]);

                //medir o tempo pra cada algoritimo
                double tempoInsertion = medirTempo(Algoritimos::insertionSort, dadosOriginais);
                double tempoBubble = medirTempo(Algoritimos::bubbleSort, dadosOriginais);
                double tempoQuick = medirTempo(Algoritimos::quickSort, dadosOriginais);

                //imprimir linha da tabela com resultados
                System.out.printf("%-15s | %-10d | %-18.6f | %-18.6f | %-18.6f%n",
                        tipo, tamanho, tempoInsertion, tempoBubble, tempoQuick);

            }catch (IOException e){
                System.err.println("Erro ao ler arquivo" + arquivo + ":" + e.getMessage());
            }
        }
    }

    private static int[] lerCsv(String caminhoArquivo) throws IOException {
        ListaDinamica numeros = new ListaDinamica();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            br.readLine(); // Lê a primeira linha (o cabeçalho "Value") e a descarta.

            String linha;
            // loop vai começar a ler a partir da SEGUNDA linha do arquivo.
            while ((linha = br.readLine()) != null) {
                //verificação para evitar erro em linhas em branco
                if (!linha.trim().isEmpty()) {
                    numeros.adicionar(Integer.parseInt(linha.trim()));
                }
            }
        }
        return numeros.paraArray();
    }

    //metodo pra copiar um array
    private static int[] copiarArray(int[] arrayOriginal){
        int[] copia = new int[arrayOriginal.length];
        for (int i = 0; i < arrayOriginal.length; i++){
            copia[i] = arrayOriginal[i];
        }
        return copia;
    }

    private static double medirTempo (MetodoOrdenacao metodo,int[] dadosOriginais){
        //usa a função de copia
        int[] dadosCopia = copiarArray(dadosOriginais);

        long inicio = System.nanoTime();
        metodo.ordenar(dadosCopia);
        long fim = System.nanoTime();

        return (fim - inicio) / 1_000_000_000.0;
    }
}
